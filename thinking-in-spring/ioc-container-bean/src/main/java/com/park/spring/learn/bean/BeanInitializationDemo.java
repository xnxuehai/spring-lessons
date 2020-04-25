package com.park.spring.learn.bean;

import com.park.spring.learn.bean.factory.UserFactory;
import com.park.spring.learn.bean.factory.impl.DefaultUserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Bean 初始化示例
 *
 * @author Aaron
 * @since
 */
public class BeanInitializationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(BeanInitializationDemo.class);
        // 启动容器
        annotationConfigApplicationContext.refresh();

        // 通过依赖查找获取 Bean
        UserFactory defaultUserFactory = annotationConfigApplicationContext.getBean("defaultUserFactory", UserFactory.class);

        // 关闭容器
        annotationConfigApplicationContext.close();
    }

    @Bean(initMethod = "customerInit")
    public DefaultUserFactory defaultUserFactory() {
        return new DefaultUserFactory();
    }
}
