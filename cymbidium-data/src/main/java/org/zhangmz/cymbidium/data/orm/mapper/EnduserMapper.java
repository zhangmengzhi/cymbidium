package org.zhangmz.cymbidium.data.orm.mapper;

import org.apache.ibatis.annotations.Param;
import org.zhangmz.cymbidium.data.orm.MyMapper;
import org.zhangmz.cymbidium.data.orm.model.Enduser;

/**
 * 
 * @ClassName:EnduserMapper.java
 * @Description:
 * @author:张孟志
 * @date:2016年3月11日上午9:22:41
 * @version V1.0
 * 说明：终端用户（enduser）的操作类
 */
public interface EnduserMapper extends MyMapper<Enduser> {

	/**
	 * 根据用户组编码/手机号码查询终端用户
	 * @param groupCode
	 * @param phone
	 * @return
	 * 用于终端用户登陆
	 */
	Enduser selectByPhone(@Param("groupCode")String groupCode, @Param("phone")String phone);
}