package org.zhangmz.cymbidium.batch.orm.mapper;

import org.apache.ibatis.annotations.Update;
import org.zhangmz.cymbidium.batch.orm.MyMapper;
import org.zhangmz.cymbidium.batch.orm.model.Account;

public interface AccountMapper extends MyMapper<Account> {
    
    @Update("update accounts set register_date=now()")
    void updateDate();
}