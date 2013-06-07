package com.github.lmm.annotation;

import com.github.lmm.browser.Browser;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-6
 * Time: 下午7:02
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Browsers {
    Browser[] value();
}
