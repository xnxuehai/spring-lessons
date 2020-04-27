package com.park.spring.learn.injection;

import com.park.spring.learn.domain.UserHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 使用 XML 的 Setter 方式进行依赖注入
 *
 * @author Aaron
 * @since
 */
public class XmlDependencyInjectionDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection.xml");
        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();
    }
}
