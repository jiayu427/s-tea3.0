package com.github.lmm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**这个是一个注册本地动作监听器的注解，定义一个本地监听器之后加上注解就能够自动开启了*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Register {
	String value() default "";
}
