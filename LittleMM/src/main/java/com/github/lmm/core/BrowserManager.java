package com.github.lmm.core;

import com.github.lmm.browser.BaseBrowser;
import com.github.lmm.browser.Browser;
import com.github.lmm.browser.IBrowser;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-6
 * Time: 下午12:48
 * To change this template use File | Settings | File Templates.
 */
public class BrowserManager {
    private IBrowser baseBrowser;
    public IBrowser getBrowser(){
        return baseBrowser;
    }
    public void setBrowser(IBrowser browser){
        this.baseBrowser=browser;
    }
}
