package com.park.spring.learn.domain;

import com.park.spring.learn.annotation.Super;

/**
 * 超级用户
 *
 * @author Aaron
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
