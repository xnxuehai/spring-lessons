package com.park.registry.api;

import com.park.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Api 方式注册 Bean
 * 1. 命名方式
 * 2. 非命名方式
 *
 * @author Aaron
 */
public class ApiBeanRegisterDemo {
	public static void main(String[] args) {
		// java API 方式注册
		javaApiRegistryMethod();
	}

	public static void javaApiRegistryMethod() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		// 命名方式
		register(ctx, "janetUser");
		// 非命名方式
		register(ctx, null);

		// 启动容器
		ctx.refresh();

		// 通过依赖查找的方式获取 bean
		Map<String, User> beansOfType = ctx.getBeansOfType(User.class);
		System.out.println("通过 Java API 方式注册 bean" + beansOfType);
		// 关闭容器
		ctx.close();
	}

	public static void register(BeanDefinitionRegistry beanDefinitionRegistry, String beanName) {
		// 获取一个 BeanDefinitionBuilder 用来构建 BeanDefinition
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		// 为 BeanDefinition 的属性赋值
		beanDefinitionBuilder
				.addPropertyValue("name", "Aaron")
				.addPropertyValue("age", 20);
		// 判断是否传入 bean 的名称
		if (StringUtils.hasText(beanName)) {
			// 使用命名的方式注册
			beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
		} else {
			// 使用非命名的方式注册
			BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), beanDefinitionRegistry);
		}
	}
}
