package com.park.annotation;

import com.park.event.CommandEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author Aaron
 * @since
 */
@Component
public class AnnotationListener {

	@EventListener(value = CommandEvent.class)
	public void test1(CommandEvent commandEvent){
		System.out.println(commandEvent.toString());
	}

	@EventListener(value = CommandEvent.class)
	public void test2(CommandEvent commandEvent){
		System.out.println(commandEvent.toString());
	}
}
