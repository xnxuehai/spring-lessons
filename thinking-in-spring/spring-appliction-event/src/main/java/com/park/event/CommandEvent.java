package com.park.event;

import org.springframework.context.ApplicationEvent;

/**
 * 命令事件
 *
 * @author Aaron
 * @since
 */
public class CommandEvent extends ApplicationEvent {
	/**
	 * Create a new {@code ApplicationEvent}.
	 *
	 * @param command the object on which the event initially occurred or with
	 *                which the event is associated (never {@code null})
	 */
	public CommandEvent(String command) {
		super(command);
	}

}
