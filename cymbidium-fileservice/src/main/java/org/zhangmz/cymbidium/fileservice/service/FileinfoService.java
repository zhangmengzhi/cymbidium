package org.zhangmz.cymbidium.fileservice.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zhangmz.cymbidium.fileservice.orm.mapper.FileinfoMapper;
import org.zhangmz.cymbidium.fileservice.orm.model.Fileinfo;
import com.github.pagehelper.PageHelper;

public class FileinfoService {	
	private static Logger logger = LoggerFactory.getLogger(FileinfoService.class);
	@Autowired
    private FileinfoMapper fileinfoMapper;
	
	/*************************************************************************
 	 * 说明：以下是单表CURD
 	 * 作者：张孟志
 	 * 日期：2016-04-13
 	 ************************************************************************/
 	public List<Fileinfo> search(Fileinfo fileinfo) {
        if (fileinfo.getPage() != null && fileinfo.getRows() != null) {
            PageHelper.startPage(fileinfo.getPage(), fileinfo.getRows());
        }
        return fileinfoMapper.select(fileinfo);
    }
 	
    public List<Fileinfo> getAll(Fileinfo fileinfo) {
        if (fileinfo.getPage() != null && fileinfo.getRows() != null) {
            PageHelper.startPage(fileinfo.getPage(), fileinfo.getRows());
        }
        return fileinfoMapper.selectAll();
    }

    public Fileinfo getById(Long id) {
        return fileinfoMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Long id) {
        fileinfoMapper.deleteByPrimaryKey(id);
    }

    public int save(Fileinfo fileinfo) {
    	int rtn = -1;
        if (fileinfo.getId() != null) {
        	rtn = fileinfoMapper.updateByPrimaryKey(fileinfo);
        } else {
        	rtn = fileinfoMapper.insert(fileinfo);
        }
        logger.debug("查看返回的变更记录数:" + rtn);
        return rtn;
    }
}
