/**
 * 
 */
package org.zhangmz.cymbidium.data.service.channel;

import org.springframework.stereotype.Service;
import org.zhangmz.cymbidium.modules.constants.Codes;
import org.zhangmz.cymbidium.modules.constants.Messages;
import org.zhangmz.cymbidium.modules.service.channel.IChannelService;
import org.zhangmz.cymbidium.modules.vo.SimpleRequest;
import org.zhangmz.cymbidium.modules.vo.SimpleResponse;

/**
 * @ClassName:DefaultChannelService.java
 * @Description: 默认服务类
 * @author:张孟志
 * @date:2016年3月11日下午12:43:27
 * @version V1.0
 * 说明：默认服务类，没有什么用处
 */
@Service
public class DefaultChannelService implements IChannelService {

	/* (non-Javadoc)
	 * @see org.zhangmz.pickles.service.IChannelService#doService(org.zhangmz.pickles.modules.vo.SimpleRequest)
	 */
	@Override
	public SimpleResponse doService(SimpleRequest request) {
		return new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, Messages.UNKNOW_CODE);
	}
}
