package com.park.spring.learn.bean.factory.impl;

import com.park.spring.learn.bean.factory.UserFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * {@link com.park.spring.learn.domain.User} 创建的默认工厂
 *
 * @author Aaron
 * @since
 */
public class DefaultUserFactory implements UserFactory, InitializingBean {

    @PostConstruct
    public void init() {
        System.out.println("使用 @PostConstruct 方式初始化 Bean");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("通过实现 InitializingBean 接口的 afterPropertiesSet 方法初始化");
    }

    public void customerInit() {
        System.out.println("自定义方式实现 Bean 的初始化");
    }
}
