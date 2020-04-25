package com.park.spring.learn.bean;

import com.park.spring.learn.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * SpringBean 实例化示例
 *
 * @author Aaron
 * @since
 */
public class BeanBeanDefinitionInstantiationDemo {
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
