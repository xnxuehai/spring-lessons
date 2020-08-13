package com.park;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 元信息解析阶段
 * 1. 元信息解析接口 {@link BeanDefinitionReader}
 * 2. 面向 xml 资源的元信息解析类 {@link XmlBeanDefinitionReader}
 * 3. 面向 注解 资源的元信息解析类 {@link AnnotatedBeanDefinitionReader}
 *
 * @author Aaron
 * @date 2020/8/13 22:21
 */
public class BeanAnalyzeDemo {
	public static void main(String[] args) {
		// 面向 注解的元信息解析 demo
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);

		// 当前 beanFactory 中 BeanDefinition 个数
		int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();

		// 注册一个 bean 组件
		beanDefinitionReader.register(BeanAnalyzeDemo.class);

		// 注册之后 BeanDefinition 个数
		int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();

		System.out.println(beanDefinitionCountAfter - beanDefinitionCountBefore);

		System.out.println(beanFactory.getBean("beanAnalyzeDemo"));

		beanDefinitionReader.registerBean(String.class);

		// 注册之后 BeanDefinition 个数
		beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();

		System.out.println(beanDefinitionCountAfter - beanDefinitionCountBefore);

	}
}
