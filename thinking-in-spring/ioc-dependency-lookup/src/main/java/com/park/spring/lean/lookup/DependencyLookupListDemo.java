package com.park.spring.lean.lookup;

import com.park.spring.learn.annotation.Super;
import com.park.spring.learn.domain.SuperUser;
import com.park.spring.learn.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Map;

/**
 * 集合类型依赖查找 示例
 *
 * @author Aaron
 * @since
 */
public class DependencyLookupListDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        // 注册配置类
        ctx.register(DependencyLookupListDemo.class);
        // 启动容器
        ctx.refresh();
        // 通过类型获取相同类型 Bean 的名称
        getNamesBySameType(ctx);
        // 通过类型获取相同类型 Bean 的实例
        getInstanceBySameType(ctx);
        // 通过标注 Super 注解类型 Bean 的名称
        getAnnotationNameByAnnotation(ctx);
        // 通过标注 Super 注解类型 Bean 的实例
        getAnnotationInstanceByAnnotation(ctx);
        // 关闭容器
        ctx.close();
    }


    /**
     * 通过标注 Super 注解类型 Bean 的实例
     */
    public static void getAnnotationInstanceByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> beansWithAnnotation = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("获取标注 Super 注解类型 Bean 实例：" + beansWithAnnotation);
        }
    }

    /**
     * 通过标注 Super 注解类型 Bean 的名称
     */
    public static void getAnnotationNameByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            String[] beanNamesForAnnotation = listableBeanFactory.getBeanNamesForAnnotation(Super.class);
            System.out.println("获取标注 Super 注解类型 Bean 名称：" + Arrays.toString(beanNamesForAnnotation));
        }
    }

    /**
     * 获取同类型 Bean 实例列表
     */
    public static void getInstanceBySameType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("获取相同类型 Bean 实例：" + beansOfType);
        }
    }

    /**
     * 获取同类型 Bean 名称列表
     */
    public static void getNamesBySameType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            String[] beanNamesForType = listableBeanFactory.getBeanNamesForType(User.class);
            System.out.println("获取相同类型 Bean 名称：" + beanNamesForType);
        }
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("Aaron");
        return user;
    }

    @Bean
    public SuperUser superUser() {
        SuperUser superUser = new SuperUser();
        superUser.setAddress("中南海");
        superUser.setId(2L);
        superUser.setName("Janet");
        return superUser;
    }
}
