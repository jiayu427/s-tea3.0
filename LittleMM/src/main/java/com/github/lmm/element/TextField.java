package com.github.lmm.element;

import com.github.lmm.browser.IBrowser;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:31
 * To change this template use File | Settings | File Templates.
 */
public class TextField extends Element {
    public TextField(IBrowser browser, TempElement tempElement) {
        super(browser, tempElement);
    }


    public TextField(IBrowser browser) {
        super(browser);
    }
}
