package com.park.spring.learn.injection;


import com.park.spring.learn.domain.User;
import com.park.spring.learn.domain.UserHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 使用 注解 的 Setter 方式进行依赖注入
 *
 * @author Aaron
 * @since
 */
public class ApiDependencyInjectionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("userHolder", getUserHolderBeanDefinition());
        beanFactory.registerBeanDefinition("user", getUserBeanDefinition());
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }

    public static BeanDefinition getUserHolderBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        beanDefinitionBuilder.addPropertyReference("user", "user");
        return beanDefinitionBuilder.getBeanDefinition();
    }

    public static BeanDefinition getUserBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 1L);
        beanDefinitionBuilder.addPropertyValue("name", "Aaron");
        return beanDefinitionBuilder.getBeanDefinition();
    }
}
