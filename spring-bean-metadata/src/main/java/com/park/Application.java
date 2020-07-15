package com.park;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Aaron
 * @date 2020/6/27 11:09
 */
public class Application {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(Application.class);

		applicationContext.refresh();
		applicationContext.close();
	}

	@Bean
	public String str() {
		return new String();
	}
}
