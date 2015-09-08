package com.biyanzhi.util;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

public class QuartzThread extends Thread {
	int picture_id;
	int picture_publisher_id;

	public QuartzThread(int picture_id, int picture_publisher_id) {
		this.picture_id = picture_id;
		this.picture_publisher_id = picture_publisher_id;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		try {
			JobDetail jobDetail = new JobDetailImpl("job1", "group1",
					QuartzJob.class);
			jobDetail.getJobDataMap().put("picture_id", picture_id);
			jobDetail.getJobDataMap().put("picture_publisher_id",
					picture_publisher_id);
			// 通过simpleTrigger调度
			SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl("trigger1",
					"group1");
			simpleTrigger.setStartTime(new Date(
					System.currentTimeMillis() + 1000 * 3));// 开启时间
			simpleTrigger.setRepeatInterval(1000 * 3);// 控制频率
			simpleTrigger.setRepeatCount(0);// 重复次数
			// 通过SchedulerFactory获取调度器实例
			StdSchedulerFactory ssf = new StdSchedulerFactory();
			Scheduler sd = ssf.getScheduler();
			sd.scheduleJob(jobDetail, simpleTrigger);
			sd.start();
			sleep(1000 * 20);
			System.out.println("shutdown------------执行");
			sd.shutdown(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public static void main(String[] args) {
	// new QuartzThread(0, 0).start();

	// }
}
