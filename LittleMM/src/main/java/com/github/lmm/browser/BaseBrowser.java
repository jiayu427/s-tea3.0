package com.github.lmm.browser;

import com.github.lmm.page.CurrentPage;
import com.github.lmm.page.ICurrentPage;
import com.github.lmm.window.WindowSource;
import com.github.lmm.window.WindowsCollectorListener;
import org.openqa.selenium.WebDriver;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */
public class BaseBrowser implements IBrowser {
    private WindowsCollectorListener windowsCollectorListener;
    private WindowSource windowSource;
    private ICurrentPage currentPage;
    //public LinkedHashMap<String,String> collection;
    private WebDriver driver;
    public BaseBrowser(Browser browser){
        this.driver=browser.browser();
        this.currentPage=new CurrentPage(this);
        this.windowSource=new WindowSource(this);
        this.windowsCollectorListener=new WindowsCollectorListener();
        this.windowSource.addWindowsListener(this.windowsCollectorListener);
    }


    @Override
    public ICurrentPage open(String url) {
        getCurrentPage().open(url);
        return this.currentPage;
    }

    @Override
    public void closeAllWindows() {
        //To change body of implemented methods use File | Settings | File Templates.
        this.getCurrentBrowserDriver().quit();
    }

    @Override
    public void back() {
        this.driver.navigate().back();
    }

    @Override
    public void refresh() {
        this.driver.navigate().refresh();
    }

    @Override
    public void forward() {
        this.driver.navigate().forward();
    }

    @Override
    public Set<String> getWindows() {
        return this.driver.getWindowHandles();
    }

    @Override
    public ICurrentPage selectDefaultWindow() {
        this.driver.switchTo().defaultContent();
        this.currentPage=getCurrentPage();
        return this.currentPage;
    }

    @Override
    public ICurrentPage selectLastOpenedPage() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ICurrentPage selectWindowByTitle(String title) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ICurrentPage selectWindowByUrl(String url) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ICurrentPage selectWindowContainsTitle(String title) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ICurrentPage selectWindowContainsUrl(String url) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ICurrentPage getCurrentPage() {
        if (!this.currentPage.getCurrentWindow().getWindowHandle().equals(this.getCurrentBrowserDriver().getWindowHandle())){
            this.currentPage=new CurrentPage(this);
            this.currentPage.setBrowser(this);
            return this.currentPage;
        }
        return this.currentPage;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public WebDriver getCurrentBrowserDriver() {
        return this.driver;  //To change body of implemented methods use File | Settings | File Templates.
    }



}
