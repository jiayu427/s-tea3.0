package com.github.lmm.element;

import com.github.lmm.browser.IBrowser;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:33
 * To change this template use File | Settings | File Templates.
 */
public class CheckBox extends Element {
    public CheckBox(IBrowser browser, TempElement tempElement) {
        super(browser, tempElement);
    }

    public CheckBox(IBrowser browser) {
        super(browser);
    }

    public boolean isCheck(){
        return false;
    }

    public void setStatus(boolean status){

    }
}
