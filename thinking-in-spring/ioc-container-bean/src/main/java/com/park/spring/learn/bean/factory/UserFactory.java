package com.park.spring.learn.bean.factory;

import com.park.spring.learn.domain.User;

/**
 * user 工厂接口
 *
 * @author Aaron
 * @since
 */
public interface UserFactory {

    /**
     * 默认方法创建 User 实例
     *
     * @return
     */
    default User createUser() {
        return User.createUser();
    }
}
