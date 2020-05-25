package com.park;

import com.park.event.CommandEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Aaron
 * @since
 */
@ComponentScan(basePackages = "com.park")
public class Application {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(Application.class);
		applicationContext.refresh();

		// 构建一个事件
		CommandEvent commandEvent = new CommandEvent("疫情期间请各组织主要好防护措施。");
		// 发布事件
		applicationContext.publishEvent(commandEvent);

		applicationContext.stop();
	}
}
