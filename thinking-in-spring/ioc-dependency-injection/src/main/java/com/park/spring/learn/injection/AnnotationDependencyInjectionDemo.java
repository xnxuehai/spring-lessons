package com.park.spring.learn.injection;


import com.park.spring.learn.domain.User;
import com.park.spring.learn.domain.UserHolder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 使用 注解 的 Setter 方式进行依赖注入
 *
 * @author Aaron
 * @since
 */
public class AnnotationDependencyInjectionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionDemo.class);

        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();
    }

    @Bean
    public User user() {
        User u = new User();
        u.setId(1L);
        u.setName("Aaron");
        return u;
    }

    @Bean
    public UserHolder userHolder(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }
}
