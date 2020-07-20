package com.park;

import com.park.domain.User;
import com.park.domain.UserHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 使用 Setter 方式进行依赖注入
 *
 * @author Aaron
 * @date 2020/7/15 22:16
 */
public class DependenceInjectionSetterDemo {

	public static void main(String[] args) {
		xmlSetterInjection();
		xmlSetterAutoInjection();
		annotationSetterInjection();
		javaApiSetterInjection();
	}

	/**
	 * 基于 xml 的方式 进行依赖注入
	 */
	public static void xmlSetterInjection() {
		String path = "classpath:/META-INF/spring-dependence-injection-setter.xml";
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(path);
		classPathXmlApplicationContext.refresh();

		Object userHolder = classPathXmlApplicationContext.getBean("userHolder");
		System.out.println(userHolder);

		classPathXmlApplicationContext.close();
	}

	/**
	 * 自动注入
	 */
	public static void xmlSetterAutoInjection() {
		String path = "classpath:/META-INF/spring-dependence-auto-injection-setter.xml";
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(path);
		classPathXmlApplicationContext.refresh();

		Object userHolder1 = classPathXmlApplicationContext.getBean("userHolder1");
		Object userHolder2 = classPathXmlApplicationContext.getBean("userHolder2");
		System.out.println(userHolder1);
		System.out.println(userHolder2);

		classPathXmlApplicationContext.close();
	}

	/**
	 * 基于 Annotation 的方式进行依赖注入
	 */
	public static void annotationSetterInjection() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(DependenceInjectionSetterDemo.class);

		applicationContext.refresh();

		UserHolder bean = applicationContext.getBean(UserHolder.class);

		System.out.println(bean);

		applicationContext.close();
	}

	/**
	 * 基于 java api 方式进行依赖注入
	 */
	public static void javaApiSetterInjection() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		BeanDefinitionBuilder beanDefinitionBuilderUser = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		beanDefinitionBuilderUser.addPropertyValue("name", "Aaron")
				.addPropertyValue("age", 25);

		beanFactory.registerBeanDefinition("user", beanDefinitionBuilderUser.getBeanDefinition());

		BeanDefinitionBuilder beanDefinitionBuilderUserHolder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
		beanDefinitionBuilderUserHolder.addPropertyReference("user", "user");

		beanFactory.registerBeanDefinition("userHolder", beanDefinitionBuilderUserHolder.getBeanDefinition());

		UserHolder bean = beanFactory.getBean(UserHolder.class);

		System.out.println(bean);
	}

	@Bean
	public UserHolder userHolder(User user) {
		UserHolder userHolder = new UserHolder();
		userHolder.setUser(user);
		return userHolder;
	}

	@Bean
	public User user() {
		User user = new User();
		user.setName("Aaron");
		user.setAge(25);
		return user;
	}
}
