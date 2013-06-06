package com.github.lmm.core;

import com.github.lmm.browser.BaseBrowser;
import com.github.lmm.browser.Browser;
import com.github.lmm.browser.Firefox;
import com.github.lmm.browser.IBrowser;
import com.github.lmm.page.ICurrentPage;
import org.openqa.selenium.WebDriver;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-6
 * Time: 上午9:32
 * To change this template use File | Settings | File Templates.
 */
public enum Auto {
    Firefox(Browser.Firefox),
    IE(Browser.IE),
    HtmlUnit(Browser.HtmlUnit),
    Safari(Browser.Safari),
    Opera(Browser.Opera),
    Chrome(Browser.Chrome);
    private  static ThreadLocal<BrowserManager> local = new ThreadLocal<BrowserManager>(){
        public BrowserManager initialValue(){
            return new BrowserManager();
        }
    };
    private volatile Browser browser;
    private Auto(Browser browser) {
        this.browser=browser;
    }

    public IBrowser browser(){
        if(local.get().getBrowser()==null){
            local.get().setBrowser(new BaseBrowser(browser));
            return local.get().getBrowser();
        }else{
            return local.get().getBrowser();
        }


    }

    public ICurrentPage open(String url){
        return browser().open(url);
    };

    public void maxWindow(){
        browser().maxWindow();
    };

    public void closeAllWindows(){
        browser().closeAllWindows();
    };

    public void back(){
        browser().back();
    };

    public void refresh(){
        browser().refresh();
    };

    public void forward(){
        browser().forward();
    };

    public Set<String> getWindows(){
        return browser().getWindows();
    };

    public ICurrentPage selectDefaultWindow(){
        return browser().selectDefaultWindow();
    };

    public ICurrentPage selectLastOpenedPage(){
        return  browser().selectLastOpenedPage();
    };

    public ICurrentPage selectWindowByTitle(String title){
        return browser().selectWindowByTitle(title);
    };

    public ICurrentPage selectWindowByUrl(String url){
        return browser().selectWindowByUrl(url);
    };

    //public ICurrentPage selectWindowContainsTitle(String title);

    public ICurrentPage selectWindowContainsUrl(String url){
        return browser().selectWindowContainsUrl(url);
    };

    public ICurrentPage getCurrentPage(){
        return browser().getCurrentPage();
    };

    public WebDriver getCurrentBrowserDriver(){
        return browser().getCurrentBrowserDriver();
    };

    public Object runJavaScript(String js,Object... objects){
        return browser().runJavaScript(js, objects);
    };

    public Object runAsynJavaScript(String js,Object... objects){
        return browser().runAsynJavaScript(js, objects);
    };

    public void takeScreetShot(String path){
        browser().takeScreetShot(path);
    };

    public boolean isClosed(){
        return browser().isClosed();
    };

    public void setClosed(boolean isclose){
        browser().setClosed(isClosed());
    };

    public ICurrentPage currentage(){
        return getCurrentPage();
    };
}
