package com.github.lmm.element;

import com.github.lmm.browser.IBrowser;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:33
 * To change this template use File | Settings | File Templates.
 */
public class RadioButton extends Element {

    public RadioButton(IBrowser browser, TempElement tempElement) {
        super(browser, tempElement);
    }

    public RadioButton(IBrowser browser) {
        super(browser);
    }

    public boolean isChecked(){
        return false;
    }

    public void setStatus(boolean status){
    }

}
