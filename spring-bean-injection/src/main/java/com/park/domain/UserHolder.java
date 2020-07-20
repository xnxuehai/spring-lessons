package com.park.domain;

/**
 * @author Aaron
 * @date 2020/7/16 20:28
 */
public class UserHolder {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserHolder{" +
				"user=" + user +
				'}';
	}
}
