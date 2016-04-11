/**
 * 
 */
package org.zhangmz.cymbidium.data.service.channel;

import org.zhangmz.cymbidium.modules.vo.SimpleRequest;
import org.zhangmz.cymbidium.modules.vo.SimpleResponse;

/**
 * @ClassName:IChannelService.java
 * @Description: 终端接入服务接口
 * @author:张孟志
 * @date:2016年3月11日下午12:35:02
 * @version V1.0
 * 说明：
 */
public interface IChannelService {
	public SimpleResponse doService(SimpleRequest request);
}
