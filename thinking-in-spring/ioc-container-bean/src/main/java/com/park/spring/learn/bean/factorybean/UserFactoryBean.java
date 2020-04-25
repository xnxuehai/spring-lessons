package com.park.spring.learn.bean.factorybean;

import com.park.spring.learn.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link com.park.spring.learn.domain.User} 的FactoryBean {@link org.springframework.beans.factory.FactoryBean}
 *
 * @author Aaron
 * @since
 */
public class UserFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
