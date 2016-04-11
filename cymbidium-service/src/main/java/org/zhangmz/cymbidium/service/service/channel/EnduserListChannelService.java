/**
 * 
 */
package org.zhangmz.cymbidium.service.service.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhangmz.cymbidium.service.helper.AuthorityHelper;
import org.zhangmz.cymbidium.service.helper.vo.EnduserElement;
import org.zhangmz.cymbidium.modules.constants.Codes;
import org.zhangmz.cymbidium.modules.constants.Messages;
import org.zhangmz.cymbidium.modules.convert.JsonMapper;
import org.zhangmz.cymbidium.modules.service.channel.IChannelService;
import org.zhangmz.cymbidium.modules.vo.SimpleRequest;
import org.zhangmz.cymbidium.modules.vo.SimpleResponse;
import org.zhangmz.cymbidium.service.orm.model.Enduser;
import org.zhangmz.cymbidium.service.service.EnduserService;

/**
 * @ClassName:EnduserListChannelService
 * @Description: 终端用户列表查询服务
 * @author:张孟志
 * @date:2016年3月11日下午12:43:27
 * @version V1.0
 * 说明：终端用户列表查询服务
 */
@Service
public class EnduserListChannelService implements IChannelService {
	private static Logger logger = LoggerFactory.getLogger(EnduserListChannelService.class);	
	private static JsonMapper binder = JsonMapper.nonDefaultMapper();
	
    @Autowired
    private AuthorityHelper authorityHelper;    

    @Autowired
    private EnduserService enduserService;

	/* (non-Javadoc)
	 * @see org.zhangmz.pickles.service.IChannelService#doService(org.zhangmz.pickles.modules.vo.SimpleRequest)
	 */
	@Override
	public SimpleResponse doService(SimpleRequest request) {
		
		// 从requset中获取TOKEN，进一步获取终端用户信息
		Enduser enduser = authorityHelper.getEnduser(request.get_token_());
		
		// TODO 取_data_值，对data解密，暂时使用明文
		String date = request.get_data_();
		// JSON-->对象转换
		Map<String, Object> map = binder.fromJson(date, HashMap.class);
		int page = (int) map.get("page");
		int rows = (int) map.get("rows");
		
		// 分页查询
		Enduser eu = new Enduser();
		eu.setGroupId(enduser.getGroupId());
		eu.setPage(page);
		eu.setRows(rows);
		eu.setStatus("Yes");
		List<Enduser> enduserList = enduserService.search(eu);		
		// List<Enduser> enduserList = enduserService.search(enduser.getGroupId(), page, rows);
		
		List<EnduserElement> eel = new ArrayList<>();
		for (Enduser eu0 : enduserList) {
			EnduserElement ee = new EnduserElement(eu0);
			eel.add(ee);
		}
		
		// 组装返回对象
		SimpleResponse sr = new SimpleResponse(Codes.SUCCESS_TRUE_NUMBER, Messages.SUCCESS, eel);

		logger.debug(binder.toJson(sr));
		return sr;
	}
}
