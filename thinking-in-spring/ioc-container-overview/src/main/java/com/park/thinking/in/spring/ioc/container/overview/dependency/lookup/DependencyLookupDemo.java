package com.park.thinking.in.spring.ioc.container.overview.dependency.lookup;

import com.park.thinking.in.spring.ioc.container.overview.annotation.Super;
import com.park.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找示例
 *
 * @author Aaron
 * @date 2020/4/21 19:14
 * @since
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        // 加载配置文件并且启动spring上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

        // 根据Bean名称获取对象（实时加载）
        lookupRealTimeByName(beanFactory);
        // 根据Bean名称获取对象（懒加载方式）
        lookupLazyByName(beanFactory);

        // 根据Bean类型获取单个对象
        lookupByType(beanFactory);
        // 根据Bean类型获取集合对象
        lookupCollectionByType(beanFactory);

        // 根据Bean名称和类型获取对象
        lookupNameAndType(beanFactory);

        // 获取所有标注@Super注解的对象
        lookupByAnnotation(beanFactory);

    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory defaultListableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beans = (Map) defaultListableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("获取所有标注@Super注解的对象：" + beans);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory defaultListableBeanFactory = (ListableBeanFactory) beanFactory;
            // 返回的集合是以bean的id为key，bean的实力对象为value
            Map<String, User> beans = defaultListableBeanFactory.getBeansOfType(User.class);
            System.out.println("通过bean类型加载所有对象：" + beans);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User bean = beanFactory.getBean(User.class);
        System.out.println("通过bean类型加载对象：" + bean);
    }

    private static void lookupNameAndType(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user", User.class);
        System.out.println("通过bean名称和类型加载对象：" + user);
    }

    private static void lookupLazyByName(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        System.out.println("通过bean名称懒加载对象：" + objectFactory.getObject());
    }

    private static void lookupRealTimeByName(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("通过bean名称实时家载对象：" + user);
    }


}
