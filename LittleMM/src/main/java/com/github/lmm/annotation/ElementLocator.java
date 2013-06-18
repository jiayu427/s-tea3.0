package com.github.lmm.annotation;

import com.github.lmm.element.Locator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-17
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ElementLocator {
    Locator locator() default Locator.ID;
    String value() default "";
    int index() default 0;
    String[] locators() default "";
    String commit();

}