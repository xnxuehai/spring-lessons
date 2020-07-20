package com.park;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 使用 Aware 接口进行依赖注入
 *
 * @author Aaron
 * @date 2020/7/16 21:09
 */
public class AwareInjectionDemo implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(AwareInjectionDemo.class);

		applicationContext.refresh();

		AwareInjectionDemo bean = applicationContext.getBean(AwareInjectionDemo.class);

		ApplicationContext u = bean.applicationContext;

		System.out.println(u);

		applicationContext.close();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
