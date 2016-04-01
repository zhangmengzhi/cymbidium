package org.zhangmz.cymbidium.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.zhangmz.cymbidium.batch.conf.datasource.DynamicDataSourceRegister;

/**
 * 
 * @ClassName:AuthorityApplication 
 * @Description:SpringBoot 应用
 * @author:张孟志
 * @date:2016年3月31日 下午4:34:04 
 * @version V1.0
 * 说明：启动SpringBoot 应用入口
 */
@SpringBootApplication
@EnableScheduling
@Import({DynamicDataSourceRegister.class}) // 注册动态多数据源
public class BatchApplication {
	public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }
}
