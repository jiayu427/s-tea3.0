package com.github.lmm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这个注解是加一个自定义的JUnit的监听器
 * 用此注解的话就是永久加入这个监听器
 * */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface RunnerListener {
	Class<?>[] value();
}
