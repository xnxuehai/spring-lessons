package com.park.thinking.in.spring.ioc.container.overview.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 超级
 *
 * @author Aaron
 * @date 2020/4/21 21:03
 * @since
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Super {

}
