package com.park;

import com.park.domain.User;
import com.park.domain.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 使用 字段注入 方式进行依赖注入
 *
 * @author Aaron
 * @date 2020/7/15 22:16
 */
public class DependenceInjectionMethodDemo {
	private User user;
	private UserHolder userHolder;

	/**
	 * 方法名称使用 a 是为了 区分 setter 方法
	 */
	@Autowired
	public void a(User user){
		this.user = user;
	}

	/**
	 * 方法名称使用 b 是为了 区分 setter 方法
	 */
	@Resource
	public void b(UserHolder userHolder){
		this.userHolder = userHolder;
	}


	public static void main(String[] args) {
		Injection();
	}

	/**
	 * 字段 注入方式
	 */
	public static void Injection() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(DependenceInjectionMethodDemo.class);

		applicationContext.refresh();

		DependenceInjectionMethodDemo bean = applicationContext.getBean(DependenceInjectionMethodDemo.class);

		User u = bean.user;
		UserHolder uh = bean.userHolder;

		System.out.println(u);
		System.out.println(uh);

		applicationContext.close();
	}

	@Bean
	public User user() {
		User user = new User();
		user.setName("Aaron");
		user.setAge(25);
		return user;
	}

	@Bean
	public UserHolder userHolder(User user) {
		UserHolder userHolder = new UserHolder();
		userHolder.setUser(user);
		return userHolder;
	}

}
