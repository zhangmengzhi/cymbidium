package org.zhangmz.cymbidium.authority.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhangmz.cymbidium.authority.helper.AuthorityHelper;
import org.zhangmz.cymbidium.modules.utils.DiscuzHashPassword;
import org.zhangmz.cymbidium.modules.utils.Ids;
import org.zhangmz.cymbidium.authority.orm.mapper.AccountMapper;
import org.zhangmz.cymbidium.authority.orm.model.Account;
import org.zhangmz.cymbidium.modules.exception.ErrorCode;
import org.zhangmz.cymbidium.modules.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @ClassName:AccountService 
 * @Description:账户管理
 * @author:张孟志
 * @date:2016年1月7日 上午10:42:26 
 * @version V1.0
 * 说明：账户管理
 */
@Service
public class AccountService {

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);
	
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AuthorityHelper authorityHelper;    
    
    // 管理员登陆方法
	public String login(String phoneEmail, String password) { 		
 		return this.login(null, phoneEmail, password, true);
	}
	
	// 用户登陆方法
	public String login(String groupCode, String phoneEmail, String password) {
		return this.login(groupCode, phoneEmail, password, false);
	}
    

	/**
	 * 
	 * @Title: login 
	 * @Description: 处理登录请求 
	 * @param groupCode       用户组编码
	 * @param phoneEmail      手机号或Email
	 * @param password        密码
	 * @param isAdminConsole  是否登陆管理员控制台
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年3月8日 下午4:34:58
	 * 说明：处理登录请求
	 *      检验用户登录凭证，目前使用用户组编码、手机号或Email、password
	 *      登录成功产生一个唯一的标识TOKEN，
	 *      保存登录记录（如果不保存数据库则需要将TOKEN保存在缓存）返回TOKEN
	 */
	private String login(String groupCode, String phoneEmail, String password, boolean isAdminConsole) {		
		
		if (checkBlank(groupCode, phoneEmail, password, isAdminConsole)) {
 			logger.warn(phoneEmail + "用户信息或密码为空。 ");
			throw new ServiceException("用户信息或密码为空。", ErrorCode.UNAUTHORIZED);
 		}
		
		Account account;
		if(isAdminConsole){
			account = accountMapper.getByPhoneEmail(phoneEmail);
		} else {
			account = accountMapper.selectByPhoneEmail(groupCode, phoneEmail);
		}
		

		if (account == null) {
			logger.error(phoneEmail + "登录失败，未注册用户。 ");
			throw new ServiceException("未注册用户", ErrorCode.UNAUTHORIZED);
		}

		// 设置为Discuz加密方式，为数据迁移做准备
		if (!account.getHashPassword().equals(
				DiscuzHashPassword.getHashPassword(password, account.getSalt()))) {
			logger.warn(phoneEmail + "登录失败，密码错误。 ");
			throw new ServiceException("密码错误", ErrorCode.UNAUTHORIZED);
		}

		String token = Ids.uuid2();
		
		// 这里可以将登录信息保存到数据库		
		// 将登录信息放入缓存
		authorityHelper.putAccount(token, account);
		/*
		loginTimeInfos.put(email, new Date());
		*/
				
		logger.info(phoneEmail + " login, TOKEN = " + token + ", admin? " + authorityHelper.isAdministrator(token));
		return token;
	}
	
	/**
	 * 
	 * @Title: checkBlank 
	 * @Description: 检查参数是否为空
	 * @param groupCode       用户组编码
	 * @param phoneEmail      手机号或Email
	 * @param password        密码
	 * @param isAdminConsole  是否登陆管理员控制台
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年3月8日 下午4:38:18
	 * 说明：登陆管理员控制台不需要用户组编码这个参数
	 */
	private boolean checkBlank(String groupCode, String phoneEmail, String password, boolean isAdminConsole) {
		boolean bln = StringUtils.isBlank(phoneEmail) || StringUtils.isBlank(password);
		
		if(!isAdminConsole){
			bln = bln || StringUtils.isBlank(groupCode);
		}
		
		return bln;
	}

	/**
	 * 
	 * @Title: logout 
	 * @Description: 处理用户退出请求
	 * @param token
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月25日 下午8:16:52
	 * 说明：处理用户退出请求
	 *      从缓存中取出用户信息、登录用户计数减少
	 */
 	public void logout(String token) {
 		if(StringUtils.isBlank(token)){
 			return;
 		}
 		
		Account account = authorityHelper.getAccount(token);
		if (account == null) {
			logger.warn("logout an alreay logout token:" + token);
		} else {
			authorityHelper.invalidateAccount(token);
		}		

		logger.info("logout, TOKEN = " + token);
	}
 	
 	/**
 	 * 
 	 * @Title: register 
 	 * @Description:处理用户注册请求
 	 * @param email
 	 * @param name
 	 * @param password
 	 * @throws 
 	 * 增加人:张孟志
 	 * 增加日期:2016年1月25日 下午8:17:13
 	 * 说明：处理用户注册请求
	 *      从缓存中取出用户信息、登录用户计数减少
 	 */
 	@Transactional
	public void register(String email, String name, String password) {

		if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
			throw new ServiceException("Invalid parameter", ErrorCode.BAD_REQUEST);
		}

		Account account = new Account();
		account.setEmail(email);
		account.setName(name);
		account.setPassword(password);
		this.save(account);

		logger.info(email + " register. ");
	} 	
 	
 	/*************************************************************************
 	 * 说明：以下是单表CURD
 	 * 作者：张孟志
 	 * 日期：2016-01-10
 	 ************************************************************************/
 	public List<Account> search(Account account) {
        if (account.getPage() != null && account.getRows() != null) {
            PageHelper.startPage(account.getPage(), account.getRows());
        }
        return accountMapper.select(account);
    }
 	
    public List<Account> getAll(Account account) {
        if (account.getPage() != null && account.getRows() != null) {
            PageHelper.startPage(account.getPage(), account.getRows());
        }
        return accountMapper.selectAll();
    }

    public Account getById(Long id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Long id) {
        accountMapper.deleteByPrimaryKey(id);
    }

    public void save(Account account) {
        if (account.getId() != null) {
            accountMapper.updateByPrimaryKey(account);
        } else {
            accountMapper.insert(account);
        }
    }
    
    /*************************************************************************
 	 * 说明：权限判断服务
 	 * 作者：张孟志
 	 * 日期：2016-01-26
 	 ************************************************************************/
    public boolean isAdmin(String token){
    	return authorityHelper.isAdmin(token);
    }
    
    public boolean isAdministrator(Account account){
    	return authorityHelper.isAdministrator(account);
    }
    
    public boolean isAdministrator(String token){
    	return authorityHelper.isAdministrator(token);
    }
    
    public boolean isLogin(String token){
    	return authorityHelper.isLogin(token);
    }   

    public void invalidateAccount(String token){
    	authorityHelper.invalidateAccount(token);
    }
}
