package org.zhangmz.cymbidium.service.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhangmz.cymbidium.service.orm.mapper.AccountMapper;
import org.zhangmz.cymbidium.service.orm.model.Account;

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

    public int save(Account account) {
    	int rtn = -1;
        if (account.getId() != null) {
        	rtn = accountMapper.updateByPrimaryKey(account);
        } else {
        	rtn = accountMapper.insert(account);
        }
        logger.debug("查看返回的变更记录数:" + rtn);
        return rtn;
    }
}
