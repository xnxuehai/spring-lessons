package com.park.spring.lean.lookup;

import com.park.spring.learn.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 单一类型依赖查找 示例
 *
 * @author Aaron
 * @since
 */
@Configuration
public class DependencyLookupSingleDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        // 注册配置类
        ctx.register(DependencyLookupSingleDemo.class);
        // 启动容器
        ctx.refresh();
        // 根据 Bean 的名称查找
        lookupByBeanName(ctx);
        // 根据 Bean 类型实时查找
        realTimeLookupByType(ctx);
        // 根据 Bean 类型来延时查找
        lazyLookupByType(ctx);
        // 根据 Bean 名称和类型查找
        lookupByNameAndType(ctx);
        // 关闭容器
        ctx.close();
    }

    /**
     * 通过名称和类型查找 Bean
     */
    public static void lookupByNameAndType(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user", User.class);
        System.out.println("根据 Bean 名称和类型查找 Bean 对象" + user);
    }

    /**
     * 通过类型 延时查找 Bean
     */
    public static void lazyLookupByType(BeanFactory beanFactory) {
        ObjectProvider<User> beanProvider = beanFactory.getBeanProvider(User.class);
        System.out.println("根据 Bean 类型来延时查找 Bean 对象：" + beanProvider.getObject());
    }

    /**
     * 通过类型 实时查找 Bean
     */
    public static void realTimeLookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("根据 Bean 类型来实时查找 Bean：" + user);
    }

    /**
     * 通过名称查找 Bean
     */
    public static void lookupByBeanName(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("根据 Bean 的名称来查找 Bean：" + user);
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("Aaron");
        return user;
    }
}
