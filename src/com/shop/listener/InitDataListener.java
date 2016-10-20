package com.shop.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shop.utils.FileUpload;
import com.shop.utils.impl.ProductTimerTask;

//@Component //监听器是web层的组件，它是tomcat实例化的，不是Spring实例化的。不能放到Spring中  
public class InitDataListener implements ServletContextListener {
	private ProductTimerTask productTimerTask = null; // 定义一个ProductTimerTask对象
	private ApplicationContext context = null;
	private FileUpload fileUpload = null;

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.print("服务器结束");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		context = WebApplicationContextUtils.getWebApplicationContext(event
				.getServletContext());
		productTimerTask = (ProductTimerTask) context
				.getBean("productTimerTask");// 从配置文件中获取ProductTimerTask对象
		productTimerTask.setApplication(event.getServletContext());
		// 通过设置定时器，让首页的数据每个一小时同步一次（配置为守护线程）
		new Timer(true).schedule(productTimerTask, 0, 1000 * 60 * 60);// 每个一小时执行一次productTimerTask任务，即更新一下后台数据
		
		// 将存储银行图片的数组放到application中，项目启动的时候加载
		fileUpload = (FileUpload) context.getBean("fileUpload");
		event.getServletContext().setAttribute("bankImageList",
				fileUpload.getBankImage());
	}

}
