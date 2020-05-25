package com.park.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 华为监听者
 *
 * @author Aaron
 * @since
 */
@Component
public class AlibabaListener implements ApplicationListener<CommandEvent> {

	@Override
	public void onApplicationEvent(CommandEvent event) {
		System.out.printf("阿里巴巴企业接收到通知消息，消息内容为:%s\n", event.getSource().toString());
	}
}
