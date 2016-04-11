package org.zhangmz.cymbidium.data.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhangmz.cymbidium.data.orm.mapper.EnduserMapper;
import org.zhangmz.cymbidium.data.orm.model.Enduser;

/**
 * 
 * @ClassName:EnduserService
 * @Description:终端用户管理
 * @author:张孟志
 * @date:2016年1月7日 上午10:42:26 
 * @version V1.0
 * 说明：终端用户管理
 */
@Service
public class EnduserService {

	private static Logger logger = LoggerFactory.getLogger(EnduserService.class);	
	
    @Autowired
    private EnduserMapper enduserMapper;  

 	 	
 	/*************************************************************************
 	 * 说明：以下是单表CURD
 	 * 作者：张孟志
 	 * 日期：2016-01-10
 	 ************************************************************************/ 	
 	public List<Enduser> search(Enduser enduser) {
        if (enduser.getPage() != null && enduser.getRows() != null) {
            PageHelper.startPage(enduser.getPage(), enduser.getRows());
        }
        return enduserMapper.select(enduser);
    }
 	
    public List<Enduser> getAll(Enduser enduser) {
        if (enduser.getPage() != null && enduser.getRows() != null) {
            PageHelper.startPage(enduser.getPage(), enduser.getRows());
        }
        return enduserMapper.selectAll();
    }

    public Enduser getById(Long id) {
        return enduserMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Long id) {
    	enduserMapper.deleteByPrimaryKey(id);
    }

    public int save(Enduser enduser) {
    	int rtn = -1;
        if (enduser.getId() != null) {
        	rtn = enduserMapper.updateByPrimaryKey(enduser);
        } else {
        	rtn = enduserMapper.insert(enduser);
        }
        logger.debug("查看返回的变更记录数:" + rtn);
        return rtn;
    }
}
