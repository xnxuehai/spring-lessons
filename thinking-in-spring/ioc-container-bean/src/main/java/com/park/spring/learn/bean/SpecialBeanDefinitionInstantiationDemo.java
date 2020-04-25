package com.park.spring.learn.bean;

import com.park.spring.learn.bean.factory.UserFactory;
import com.park.spring.learn.bean.factory.impl.DefaultUserFactory;
import com.park.spring.learn.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特别的方式实例化 Bean
 *
 * @author Aaron
 * @since
 */
public class SpecialBeanDefinitionInstantiationDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-special-instantiation-context.xml");
        // ServiceLoaderFactoryBean 方式获取对象
        userFactoryServiceLoader(beanFactory);
        // 使用 AutowireCapableBeanFactory#createBean 方式实例化对象
        autowireCapableBeanFactory(beanFactory);
    }

    public static void autowireCapableBeanFactory(BeanFactory beanFactory) {
        if (beanFactory instanceof ApplicationContext) {
            ApplicationContext applicationContext = (ApplicationContext) beanFactory;
            AutowireCapableBeanFactory ApplicationContext = applicationContext.getAutowireCapableBeanFactory();
            UserFactory userFactory = ApplicationContext.createBean(DefaultUserFactory.class);
            System.out.println("使用 AutowireCapableBeanFactory 的 createBean 方法实例化Bean " + userFactory.createUser());
        }
    }

    public static void userFactoryServiceLoader(BeanFactory beanFactory) {
        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);

        Iterator<UserFactory> iterator = serviceLoader.iterator();

        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println("使用 ServiceLoaderFactoryBean 方式实例化对象 " + userFactory.createUser());
        }
    }


    /**
     * java 中 {@link ServiceLoader} 使用示例
     */
    public static void serviceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, SpecialBeanDefinitionInstantiationDemo.class.getClassLoader());

        Iterator<UserFactory> iterator = serviceLoader.iterator();

        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }
}
