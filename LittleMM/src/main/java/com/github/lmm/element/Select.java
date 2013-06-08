package com.github.lmm.element;

import com.github.lmm.browser.IBrowser;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:30
 * To change this template use File | Settings | File Templates.
 */
public class Select extends Element {
    public Select(IBrowser browser, TempElement tempElement) {
        super(browser, tempElement);
    }

    public Select(IBrowser browser) {
        super(browser);
    }
}
