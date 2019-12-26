/*
 * Copyright (c) 2017, 1DAOYUN and/or its affiliates. All rights reserved.
 * 1DAOYUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.xiandian.douxue.insight.server.service.job.clean;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.xiandian.douxue.insight.server.base.Server;
import com.xiandian.douxue.insight.server.base.Service;
import com.xiandian.douxue.insight.server.base.ServiceJob;
import com.xiandian.douxue.insight.server.base.ServiceState;
import com.xiandian.douxue.insight.server.dao.JobDataReposity;
import com.xiandian.douxue.insight.server.dao.MongoDBStorage;
import com.xiandian.douxue.insight.server.utils.ReadFile;
import com.xiandian.douxue.insight.server.utils.UtilTools;

/**
 * 岗位数据清洗。（标签数据转换成感知数据）TAG_DATA to PERCEPT_DATA.
 * 
 * @since v1.0
 * @date 20170815
 * @author XianDian Cloud Team
 */
public class JobCleanService implements Service {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Properties  cronProperties = UtilTools.getConfig(System.getProperty("user.dir") + "/configuration/service_cron.properties");	
	private static JobDataReposity jobDataReposity = JobDataReposity.getInstance();

	
	private String SERVICE_NAME = "douxue/insight/job/clean";
	private Service parent;
	private Server server;
	//记录任务是否完成
	private AtomicBoolean isTaskDone = new AtomicBoolean();

	//分析的岗位群(hbasetable=cloud,bigdata,ai,iot,software,mobile)
	private static Properties hbaseclassify = UtilTools
			.getConfig(System.getProperty("user.dir") + "/configuration/hbaseclassify.properties");

	/**
	 * 岗位数据清洗。
	 * @param server
	 * @param service
	 */
	public JobCleanService(Server server, Service service) {
		this.server = server;
		this.parent = service;
		init();
	}
	
	@Override
	public Server getServer() {

		return server;
	}

	@Override
	public String getServiceName() {

		return SERVICE_NAME;
	}

	@Override
	public ServiceState init() {
		isTaskDone.set(true);
		return ServiceState.STATE_INITIALIZED;
	}

	/**
	 * 启动就清晰
	 */
	@Override
	public ServiceState start() {
		//TODO: 应该由调用控制
		String isStop = cronProperties.getProperty("jobclean_service");
		if (isStop.equals("stop"))
		{
			logger.info("外部User控制不启动服务" + SERVICE_NAME);
			return ServiceState.STATE_NOTSTART;
		}
		
		// 统计分析
		// 本次强制执
		ServiceJob.submitOnceJob(server, SERVICE_NAME+"/start", this.parent.getServiceName(), this);
		// 周期执行 0 0 1 * * ?
		String cron=cronProperties.getProperty("jobclean_cron");
		logger.info(this.SERVICE_NAME + cron);
		ServiceJob.submitCronJob(server, SERVICE_NAME, this.parent.getServiceName(), this, cron);//test 0/10 * * * * ?
		return ServiceState.STATE_STARTED;
	}

	/**
	 * 一个周期任务十分完成？
	 */
	@Override
	public boolean isDone() {
		return isTaskDone.get();
	}

	/**
	 * 状态。
	 */
	@Override
	public ServiceState getState() {
		return ServiceState.STATE_RUNNING;
	}
	/**
	 * 具体的实现。
	 */
public synchronized ServiceState process() {
		try {
			isTaskDone.set(false);
			// 清洗数据
			logger.info("清洗数据开始");
			//清洗所有互联网信息			
			jobDataReposity.cleanJobData();
			//进行岗位分类保存
			String table = hbaseclassify.getProperty("hbasetable");//分类
			String[] strArray = null;
			strArray = table.split(",");
			String JsonContext = ReadFile
					.ReadFile(System.getProperty("user.dir") + "/configuration/hbaseclassify.json");
			JSONObject jsonObject;
			jsonObject = new JSONObject(JsonContext);
			//如何根据名称先分大类
			for (int i = 0; i < strArray.length; i++) {
				JSONArray jsonArray = jsonObject.getJSONArray(strArray[i]);
				//对保存的数据进行分类统计IT
				//	[{"name":"云计算"},{"name":"cloud"},{"name":"openstack"},{"name":"kvm"},{"name":"vmware"},{"name":"ceph"},{"name":"sdn"},{"name":"云"},{"name":"阿里云"},{"name":"腾讯云"},{"name":"云存储"}]
				jobDataReposity.classify(jsonArray, "job_" + strArray[i], strArray[i]);
			}
		
		} catch (Exception e) {
			logger.error(e.toString());
		}
		finally {
			isTaskDone.set(true);
		}
		logger.info("清洗数据结束");
		return ServiceState.STATE_RUNNING;
	}

}
