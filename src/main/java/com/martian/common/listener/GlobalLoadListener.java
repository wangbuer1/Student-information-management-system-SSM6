package com.martian.common.listener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 
 */
public class GlobalLoadListener implements ServletContextListener {
	/**
	 * 获取Spring托管的Bean
	 */
	public Object getBean(ServletContext servletContext, String beanName) {
		return WebApplicationContextUtils.getWebApplicationContext(
				servletContext).getBean(beanName);
	}
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		/**
		 *  设置根目录别名
		 */
		servletContext.setAttribute("ctx", servletContext.getContextPath());
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}
}
