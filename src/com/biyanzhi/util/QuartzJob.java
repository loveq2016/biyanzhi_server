package com.biyanzhi.util;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.biyanzhi.controller.PictureController;

public class QuartzJob implements Job {

	public void execute(JobExecutionContext jobContext)
			throws JobExecutionException {
		int picture_id;
		int picture_publisher_id;
		JobDataMap data = jobContext.getJobDetail().getJobDataMap();
		picture_id = data.getInt("picture_id");
		picture_publisher_id = data.getInt("picture_publisher_id");
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"/spring/applicationContext.xml");
			PictureController pictureController = (PictureController) context
					.getBean("pictureController");
			pictureController.systemPlayScore(picture_id, picture_publisher_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
