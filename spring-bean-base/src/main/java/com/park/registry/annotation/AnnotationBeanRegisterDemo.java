package com.park.registry.annotation;

import com.park.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * 注解方式 Spring Bean Register Demo
 * 1. @Bean 方式
 * 2. @Component 方式
 * 3. @Import 方式
 *
 * @author Aaron
 */
public class AnnotationBeanRegisterDemo {
	public static void main(String[] args) {
		beanRegisterDemo();

		componentRegisterDemo();

		importRegisterDemo();
	}

	/**
	 * @Bean 方式注册 Bean
	 */
	public static void beanRegisterDemo() {
		applicationContext(BeanRegisterDemo.class, "user", "@Bean 方式注册 Bean：");
	}

	/**
	 * @Component 方式注册 Bean
	 */
	public static void componentRegisterDemo() {
		applicationContext(ComponentRegisterDemo.class, "componentRegisterDemo", "@Component 方式注册 Bean：");
	}

	/**
	 * @Import 方式注册 Bean
	 */
	public static void importRegisterDemo() {
		applicationContext(ImportRegisterDemo.class, "user", "@Import 方式注册 Bean：");
	}


	/**
	 * 公用方法
	 *
	 * @param clazz    Class
	 * @param beanName bean 名称
	 * @param desc     描述信息
	 */
	private static void applicationContext(Class<?> clazz, String beanName, String desc) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		applicationContext.register(clazz);

		applicationContext.refresh();

		BeanDefinition user = applicationContext.getBeanDefinition(beanName);
		System.out.println(desc + user);

		applicationContext.close();
	}

}

class BeanRegisterDemo {

	@Bean
	public User user() {
		User user = new User();
		return user;
	}
}

@Component
class ComponentRegisterDemo {

}

@Import(ImportBean.class)
class ImportRegisterDemo {

}

@Configuration
class ImportBean {
	@Bean
	public User user() {
		User user = new User();
		return user;
	}
}

