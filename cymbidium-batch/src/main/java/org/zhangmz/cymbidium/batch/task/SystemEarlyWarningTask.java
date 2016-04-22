/**
 * 
 */
package org.zhangmz.cymbidium.batch.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hyperic.sigar.Sigar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zhangmz.cymbidium.modules.system.FormMap;
import org.zhangmz.cymbidium.modules.system.SystemInfo;
import org.zhangmz.cymbidium.modules.utils.Common;

/**
 * @ClassName:SystemEarlyWarningTask.java
 * @Description:系统预警任务
 * @author:张孟志
 * @date:2016年4月22日上午9:16:04
 * @version V1.0
 * 说明：系统预警任务
 * 发生系统风险（CPU/内存/存储超出预设值）时发送邮件 /短信报警
 */
@Component
public class SystemEarlyWarningTask {
	private static Logger logger = LoggerFactory.getLogger(SystemEarlyWarningTask.class);
	  
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	// CPU阈值
	@Value("${spring.batch.systemEarlyWarningTask.cpu}")
	private String cpu;
		
	// JVM阈值
	@Value("${spring.batch.systemEarlyWarningTask.cpu}")
	private String jvm;
	
	// 内存阈值
	@Value("${spring.batch.systemEarlyWarningTask.cpu}")
	private String ram;

	// 管理员邮箱地址
	@Value("${spring.batch.systemEarlyWarningTask.toEmail}")
	private String toEmail;

	// 管理员手机
	@Value("${spring.batch.systemEarlyWarningTask.toPhone}")
	private String toPhonetoPhone;
	
	/**
	 * 
	 * @Title: reportSystemInfo 
	 * @Description: 系统预警
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年4月22日
	 * 说明：发生系统风险（CPU/内存/存储超出预设值）时发送邮件 /短信报警
	 */
	@Scheduled(cron="${spring.batch.systemEarlyWarningTask.cronExpression}")
    public void reportSystemInfo() {
    	logger.debug("----------- begin -----------");
    	String date = dateFormat.format(new Date());
    	logger.debug("Hello, The time is now " + date);
    	
    	FormMap usage = SystemInfo.usage(new Sigar());
		String cpuUsage = usage.get("cpuUsage")+"";// CPU使用率
		String serverUsage = usage.get("ramUsage")+"";// 系统内存使用率
		String JvmUsage = usage.get("jvmUsage")+"";// 计算ＪＶＭ内存使用率
		
		// 当系统消耗内存大于或等于用户设定的内存时，发送邮件
		String cpubool = "";
		String jvmbool = "";
		String rambool = "";
		String mark = "<font color='red'>";
		if (Double.parseDouble(cpuUsage) > Double.parseDouble(cpu)) {
			cpubool = "style=\"color: red;font-weight: 600;\"";
			mark += "CPU当前：" + cpuUsage + "%,超出预设值  " + cpu + "%<br>";
		}
		if (Double.parseDouble(JvmUsage) > Double.parseDouble(jvm)) {
			jvmbool = "style=\"color: red;font-weight: 600;\"";
			mark += "JVM当前：" + JvmUsage + "%,超出预设值 " + jvm + "%<br>";
		}
		if (Double.parseDouble(serverUsage) > Double.parseDouble(ram)) {
			rambool = "style=\"color: red;font-weight: 600;\"";
			mark += "内存当前：" + serverUsage + "%,超出预设值  " + ram + "%";
		}
		mark += "</font>";
				
		// 邮件内容
		String title = "服务器预警提示 - " + date;
		String centent = "<meta charset=\"UTF-8\"><br/><br/>当前时间是：" + date + "<br/><br/>" 
		+ "<style type=\"text/css\">" + ".common-table{" 
				+ "-moz-user-select: none;" + "width:100%;" + "border:0;" 
				+ "table-layout : fixed;" + "border-top:1px solid #dedfe1;" 
				+ "border-right:1px solid #dedfe1;" + "}" 
				+ "/*header*/" + ".common-table thead td,.common-table thead th{" 
				+ "    height:23px;" + "   background-color:#e4e8ea;" 
				+ "   text-align:center;" + "   border-left:1px solid #dedfe1;" + "}" 
				+ ".common-table thead th, .common-table tbody th{" 
				+ "padding-left:7px;" + "padding-right:7px;" + "width:15px;" + "text-align:center;" + "}" 
				+ ".common-table tbody td,  .common-table tbody th{" 
				+ "    height:25px!important;" + "border-bottom:1px solid #dedfe1;" 
				+ "border-left:1px solid #dedfe1;" + "cursor:default;" 
				+ "word-break: break-all;" + "-moz-outline-style: none;" 
				+ "_padding-right:7px;" + "text-align:center;" + "}</style>"
		+ "<table class=\"common-table\">" 
			+ "<thead>" 
			+ "<tr>" + "<td width=\"100\">名称</td>" + "<td width=\"100\">参数</td>" 
				+ "<td width=\"275\">预警设置</td>" + "</tr>" 
			+ "</thead>" 
			+ "<tbody id=\"tbody\">" 
			+ "<tr " + cpubool + "><td>CPU</td><td style=\"text-align: left;\">当前使用率：" + cpuUsage
				+ "%</td><td>使用率超出  " + cpu + "%,,发送邮箱提示 </td></tr>" 
			+ "<tr " + rambool + "><td>服务器内存</td><td style=\"text-align: left;\">当前使用率：" + serverUsage 
				+ "%</td><td>使用率超出  " + ram + "%,发送邮箱提示 </td></tr>" 
			+ "<tr " + jvmbool + "><td>JVM内存</td><td style=\"text-align: left;\">当前使用率：" + JvmUsage
				+ "%</td><td>使用率超出  " + jvm + "%,,发送邮箱提示 </td></tr>" 
			+ "</tbody>" 
		+ "</table>";
		mark = mark.replaceAll("'","\"");
		
		if (!Common.isEmpty(cpubool) || !Common.isEmpty(jvmbool) || !Common.isEmpty(rambool)) {
			// TODO 发送邮件/短信
			logger.debug("-----------  mark  -----------");
			logger.debug(mark);
			logger.debug("----------- title -----------");
			logger.debug(title);
			logger.debug("-----------centent-----------");
			logger.debug(centent);
		}
		
    	logger.debug("-----------  end  -----------");
    }
}
