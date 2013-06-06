package com.github.lmm.element;

import com.github.lmm.browser.IBrowser;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:31
 * To change this template use File | Settings | File Templates.
 */
public class Image extends Element {

    public Image(IBrowser browser, TempElement tempElement) {
        super(browser, tempElement);
    }

    public Image(IBrowser browser, String By, String id, String value, Integer index) {
        super(browser, By, id, value, index);
    }

    public Image(IBrowser browser) {
        super(browser);
    }

    public String getSrc(){
        return getAttribute("href");
    }
}
