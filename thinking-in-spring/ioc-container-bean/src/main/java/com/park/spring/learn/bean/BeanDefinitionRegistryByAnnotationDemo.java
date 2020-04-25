package com.park.spring.learn.bean;

import com.park.spring.learn.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * SpringBean 注册示例
 *
 * @author Aaron
 * @since
 */
@Import(value = {BeanDefinitionRegistryByAnnotationDemo.Config.class}) // @Import 方式注册
public class BeanDefinitionRegistryByAnnotationDemo {
    public static void main(String[] args) {
        // java 注解注册
        annotationRegistryMethod();
    }

    public static void annotationRegistryMethod() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        // 注册配置类
        ctx.register(BeanDefinitionRegistryByAnnotationDemo.class);
        // 启动容器
        ctx.refresh();

        // 通过依赖查找的方式获取 bean
        User user = ctx.getBean(User.class);
        System.out.println("通过注解方式获取 user:" + user);
        Map<String, Config> beansOfType = ctx.getBeansOfType(Config.class);
        System.out.println("通过注解方式获取 beansOfType" + beansOfType);

        // 关闭容器
        ctx.close();
    }

    /**
     * @Component 注册组件
     */
    @Component
    public static class Config {

        // @Bean 方式注册 bean
        @Bean
        public User user() {
            new User();
            return new User();
        }
    }
}
