package com.github.lmm.annotation;

import java.lang.annotation.*;

/**
 * @author 王天庆
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Inherited
public @interface Retry {
    int value() default 1;
}
