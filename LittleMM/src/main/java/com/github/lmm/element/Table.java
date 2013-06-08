package com.github.lmm.element;

import com.github.lmm.browser.IBrowser;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:30
 * To change this template use File | Settings | File Templates.
 */
public class Table extends Element {
    public Table(IBrowser browser, TempElement tempElement) {
        super(browser, tempElement);
    }

    public Table(IBrowser browser) {
        super(browser);
    }

    public IElement getCell(int x,int y){
        return null;
    }
}
