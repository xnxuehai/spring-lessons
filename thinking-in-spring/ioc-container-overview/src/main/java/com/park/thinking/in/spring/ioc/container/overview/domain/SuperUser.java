package com.park.thinking.in.spring.ioc.container.overview.domain;

import com.park.thinking.in.spring.ioc.container.overview.annotation.Super;

/**
 * 超级用户
 *
 * @author Aaron
 * @date 2020/4/21 20:58
 * @since
 */
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
