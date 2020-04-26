package com.park.spring.lean.lookup;

import com.park.spring.learn.domain.SuperUser;
import com.park.spring.learn.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 延迟加载 示例
 *
 * @author Aaron
 * @since
 */
public class ObjectProviderDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();

        // 1. 通过 ObjectFactory 延迟获取实例
        lookupByGetObject(applicationContext);
        // 2. 通过 ObjectProvider 延迟获取实例
        lookupByIfAvailable(applicationContext);
        lookupByStream(applicationContext);

        applicationContext.close();
    }

    private static void lookupByStream(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        beanProvider.stream().forEach(System.out::println);
    }

    private static void lookupByGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) applicationContext.getBean("objectFactory");
        System.out.println("使用 ObjectFactory 获取实例：" + objectFactory.getObject());
    }

    private static void lookupByIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        System.out.println("使用 ObjectProvider 获取实例：" + beanProvider.getIfAvailable(User::createUser));
    }

    @Bean
    public ObjectFactoryCreatingFactoryBean objectFactory() {
        ObjectFactoryCreatingFactoryBean objectFactory = new ObjectFactoryCreatingFactoryBean();
        objectFactory.setTargetBeanName("user");
        return objectFactory;
    }

    @Bean
    @Primary
    public User user() {
        User user = new User();
        user.setId(2L);
        user.setName("Janet");
        return user;
    }

    @Bean
    public User superUser() {
        SuperUser superUser = new SuperUser();
        superUser.setId(3L);
        superUser.setName("Tom");
        superUser.setAddress("中南海");
        return superUser;
    }
}
