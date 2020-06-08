package com.park.creation;

import com.park.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * 构建 {@link BeanDefinition}
 *
 * @author Aaron
 * @since
 */
public class CreationBeanDefinition {
	public static void main(String[] args) {
		beanDefinitionBuilder();
		abstractBeanDefinition();
	}

	/**
	 * BeanDefinitionBuilder 方式构建 BeanDefinition
	 */
	public static void beanDefinitionBuilder() {
		// 1. 通过 BeanDefinitionBuilder 构建
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		// 设置属性
		beanDefinitionBuilder.addPropertyValue("id", 1);
		beanDefinitionBuilder.addPropertyValue("name", "Aaron");
		// 获取一个 BeanDefinition 实例
		BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
		System.out.println("使用 BeanDefinitionBuilder 构建的 BeanDefinition：" + beanDefinition);
		// BeanDefinition 并非bean的终态 可以自定义修改
		beanDefinition.setBeanClassName("update");
		System.out.println("使用 BeanDefinitionBuilder 构建的 BeanDefinition 修改 BeanClassName 后：" + beanDefinition);
	}

	/**
	 * AbstractBeanDefinition 派生类构建 BeanDefinition
	 */
	public static void abstractBeanDefinition() {

		// 2. 通过 AbstractBeanDefinition 以及派生构建
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		// 构建 MutablePropertyValues 实例添加属性
		MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
		mutablePropertyValues
				.add("id", 1)
				.add("name", "Aaron");
		// 设置 MutablePropertyValues 实例
		genericBeanDefinition.setPropertyValues(mutablePropertyValues);
		System.out.println("使用 GenericBeanDefinition 构建的 BeanDefinition：" + genericBeanDefinition);
	}

}
