package org.zhangmz.cymbidium.batch.conf.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName:DynamicDataSource 
 * @Description:动态数据源
 * @author:张孟志
 * @date:2016年4月1日 下午2:24:00 
 * @version V1.0
 * 说明：动态数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }

}
