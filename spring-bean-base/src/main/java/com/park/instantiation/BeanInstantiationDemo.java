package com.park.instantiation;

import com.park.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化 方式 常规方式
 * 1. 通过构造方式
 * 2. 通过静态方法
 * 3. 通过 Bean 工厂方法
 * 4. 通过 FactoryBean
 *
 * @author Aaron
 * @date 2020/7/4 16:20
 */
public class BeanInstantiationDemo {
	public static void main(String[] args) {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
		// 1. 使用静态方法实例化 User
		userStaticMethod(beanFactory);
		// 2. 使用 Bean 工厂方式构建 User
		userBeanFactoryMethod(beanFactory);
		// 3. 使用 FactoryBean 方式构建 User
		userFactoryBeanMethod(beanFactory);
	}

	public static void userStaticMethod(BeanFactory beanFactory) {
		User userStaticMethod = (User) beanFactory.getBean("user-static-method");
		System.out.println("使用静态方法构建 User 实例：" + userStaticMethod);
	}

	public static void userBeanFactoryMethod(BeanFactory beanFactory) {
		User userStaticMethod = (User) beanFactory.getBean("user-factory-method");
		System.out.println("使用 Bean 工厂方法构建 User 实例：" + userStaticMethod);
	}

	public static void userFactoryBeanMethod(BeanFactory beanFactory) {
		User userStaticMethod = (User) beanFactory.getBean("user-factory-bean-method");
		System.out.println("使用 FactoryBean 方法构建 User 实例：" + userStaticMethod);
	}
}

interface UserFactory {
	/**
	 * 默认方法创建 User 实例
	 *
	 * @return
	 */
	default User createUser() {
		return User.createUser();
	}
}

class DefaultUserFactory implements UserFactory {

}

class UserFactoryBean implements FactoryBean<User> {

	@Override
	public User getObject() throws Exception {
		return User.createUser();
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}
}
