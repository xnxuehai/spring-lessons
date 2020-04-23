package com.park.spring.learn.bean;

import com.park.spring.learn.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * SpringBean 别名示例
 *
 * @author Aaron
 * @since
 */
public class BeanAliasDemo {
    public static void main(String[] args) {
        // 使用 xml 方式配置别名
        xmlAlias();
        // 使用注解方式配置别名
        annotationAlias();
    }

    public static void xmlAlias() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        // 通过 bean 名称获取 bean
        User user = (User) beanFactory.getBean("user");
        // 通过 bean 别名获取 bean
        User aaronUser = (User) beanFactory.getBean("aaronUser");
        System.out.println("xml 配置方式验证 aaronUser 是否与 user 相同：" + (user == aaronUser));
    }

    public static void annotationAlias() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        // 设置配置类
        annotationConfigApplicationContext.register(BeanAliasDemo.class);
        // 启动容器
        annotationConfigApplicationContext.refresh();
        // 通过 bean 名称获取 bean
        User user = (User) annotationConfigApplicationContext.getBean("user");
        // 通过 bean 别名获取 bean
        User aaronUser = (User) annotationConfigApplicationContext.getBean("aaronUser");
        System.out.println("注解配置方式验证 aaronUser 是否与 user 相同：" + (user == aaronUser));
        // 关闭容器
        annotationConfigApplicationContext.close();
    }

    @Bean(name = {"user", "aaronUser"})
    public User user() {
        return new User();
    }
}
