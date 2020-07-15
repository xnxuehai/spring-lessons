package com.park.domain;

/**
 * @author Aaron
 * @date 2020/6/8 21:18
 */
public class User {

	private String name;

	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	public static User createUser() {
		User user = new User();
		user.setAge(1);
		user.setName("Aaron");
		return user;
	}
}
