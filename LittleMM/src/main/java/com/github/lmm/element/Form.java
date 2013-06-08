package com.github.lmm.element;

import com.github.lmm.browser.IBrowser;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:32
 * To change this template use File | Settings | File Templates.
 */
public class Form extends Element {
    public Form(IBrowser browser, TempElement tempElement) {
        super(browser, tempElement);
    }

    public Form(IBrowser browser) {
        super(browser);
    }

    public Set<IElement> addElement(){
          return null;
    }
}
