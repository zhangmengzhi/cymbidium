package org.zhangmz.cymbidium.batch.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhangmz.cymbidium.batch.conf.datasource.DynamicDataSourceContextHolder;
import org.zhangmz.cymbidium.batch.orm.mapper.AccountMapper;

@Service
public class AccountService {

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);
	
    @Autowired
    private AccountMapper accountMapper;
    
    // 管理员登陆方法
 	public void checkRecords() {
 		// 使用数据源1
 		DynamicDataSourceContextHolder.setDataSourceType("ds1");
 		checkCount1();
 		// 使用数据源2
 		DynamicDataSourceContextHolder.setDataSourceType("ds2");
 		checkCount2();		
 		// 使用默认数据源
 		DynamicDataSourceContextHolder.clearDataSourceType();
 		updateDate();
 	}
 	
 	private void checkCount1() {
 		logger.debug("checkCount1:" + accountMapper.selectCount(null));
 	}
 	
 	private void checkCount2() {
 		logger.debug("checkCount2:" + accountMapper.selectCount(null));
 	}
 	
 	private void updateDate() {
 		logger.debug("updateDate:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
 		accountMapper.updateDate();
 	}
}
