package com.mkyong;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mkyong.config.AppConfig;
import com.mkyong.hosting.bo.HostingBo;
import com.mkyong.seq.exception.SequenceException;

public class App {

	public static void main(String[] args) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		HostingBo hostingBo = (HostingBo) ctx.getBean("hostingBoImpl");

		try {

			hostingBo.save("cloud.google.com");
			hostingBo.save("heroku.com");
			hostingBo.save("cloudbees.com");

		} catch (SequenceException e) {
			System.out.println(e.getErrMsg());
		}

	}

}