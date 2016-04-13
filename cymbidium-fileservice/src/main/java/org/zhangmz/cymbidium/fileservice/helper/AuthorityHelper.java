package org.zhangmz.cymbidium.fileservice.helper;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zhangmz.cymbidium.modules.constants.Messages;
import org.zhangmz.cymbidium.modules.convert.JsonMapper;
import org.zhangmz.cymbidium.modules.helper.HttpClientHelper;
import org.zhangmz.cymbidium.modules.vo.SimpleResponse;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 
 * @ClassName:AuthorityHelper 
 * @Description:权限助手
 * @author:张孟志
 * @date:2016年4月7日 下午2:07:35 
 * @version V1.0
 * 说明：根据TOKEN从cymbidium-authority获取用户信息
 * 		保存缓存
 * 		查询用户权限（用户信息）
 * 
 * 这个类应该是单例（Sping Bean默认为单例）
 */
@Component
public class AuthorityHelper {
	private static Logger logger = LoggerFactory.getLogger(AuthorityHelper.class);
	private static JsonMapper binder = JsonMapper.nonDefaultMapper();

	@Value("${cymbidium.authority.url.logout}")
	private String isLogoutUrl;
	
	@Value("${cymbidium.authority.url.islogin}")
	private String isLoginUrl;
	
	// 注入配置值  10分钟过期  10X60=600
	@Value("${app.loginTimeoutSecs:600}")
	private int loginTimeoutSecs;

    // guava cache
 	private Cache<String, Integer> loginEndusers;

 	@PostConstruct
 	public void init() {
 		logger.debug("登录信息缓存过期时间设置（秒）： " + loginTimeoutSecs);
 		// expireAfterWrite 储存后开始计时；expireAfterAccess 最后一次访问开始计时
 		// CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(loginTimeoutSecs, TimeUnit.SECONDS).build();
 		loginEndusers = CacheBuilder
 				.newBuilder()
 				.maximumSize(1000)
 				.expireAfterAccess(loginTimeoutSecs, TimeUnit.SECONDS)
 				.build();
 	}
 	 	
 	public void putEnduser(String token, int enduserId) {
 		loginEndusers.put(token, enduserId);
 	}
 	
 	public int getEnduser(String token) {
 		return loginEndusers.getIfPresent(token);
	}
 	
 	public void invalidateEnduser(String token) {
 		loginEndusers.invalidate(token);
	}
 	
 	/**
 	 * 
 	 * @Title: isLogin 
 	 * @Description: 是否已登录
 	 * @param token
 	 * @param type    登陆类型 1：控制台用户；2：终端用户
 	 * @return
 	 * @throws 
 	 * 增加人:张孟志
 	 * 增加日期:2016年1月25日 上午9:25:58
 	 * 说明：从登录缓存中获取登录信息，判断是否有登录
 	 */
 	public boolean isLogin(String token, int type) { 
 		boolean bln = false;
 		
 		// 没有本地缓存，查询cymbidium-authority服务
		String isLoginUri = isLoginUrl + "/" + token;
		SimpleResponse simpleResponse = HttpClientHelper.doGet(isLoginUri);
		
		if(simpleResponse.getCode() == 1
			&& simpleResponse.getMessage().equals(Messages.SUCCESS)){
			
			// 从cymbidium-authority服务中获取到用户信息，保存到本地缓存
			// 注意对象转换后的数据类型 LinkedHashMap<String, Object>
			Map cacheInfo = (Map) simpleResponse.getResult("loginCache");
			// 获取到ID即可
			this.putEnduser(token, (int) cacheInfo.get("id"));
			bln = true;
		}
 		
 		return bln;
	}
}