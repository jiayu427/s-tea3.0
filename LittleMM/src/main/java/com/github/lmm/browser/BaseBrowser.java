package com.github.lmm.browser;

import com.github.lmm.element.ElementManager;
import com.github.lmm.page.CurrentPage;
import com.github.lmm.page.ICurrentPage;
import com.github.lmm.proxy.ActionListenerProxy;
import com.github.lmm.runtime.RuntimeMethod;
import com.github.lmm.window.WindowInfo;
import com.github.lmm.window.WindowSource;
import com.github.lmm.window.WindowsCollectorListener;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import com.github.lmm.source.Source;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */
public class BaseBrowser implements IBrowser {
    private Logger logger = Logger.getLogger(BaseBrowser.class);
    private boolean isClosed;
    private WindowsCollectorListener windowsCollectorListener;
    private WindowSource windowSource;
    private ICurrentPage currentPage;
    private WebDriver driver;
    private String commit;
    private URL url;
    private ElementManager elementManager;
    public BaseBrowser(Browser browser){
        this.commit= com.github.lmm.runtime.RuntimeMethod.getName();
        this.driver=browser.browser();
        maxWindow();
        this.elementManager=new ElementManager();
        this.currentPage=new CurrentPage(this);
        this.windowSource=new WindowSource(this);
        this.windowsCollectorListener=new WindowsCollectorListener();
        this.windowSource.addWindowsListener(this.windowsCollectorListener);
        logger.info("["+this.commit+"]初始化了浏览器"+browser.toString()+"来进行自动化测试");
    }

    public BaseBrowser(Browser browser,URL url){
        this.commit= com.github.lmm.runtime.RuntimeMethod.getName();
        this.url=url;
        if(url==null){
            this.driver=browser.browser();
        }else{
            this.driver=browser.browser(url);
        }
        maxWindow();
        this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        this.driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        this.currentPage=new CurrentPage(this);
        this.windowSource=new WindowSource(this);
        this.windowsCollectorListener=new WindowsCollectorListener();
        this.windowSource.addWindowsListener(this.windowsCollectorListener);
        logger.info("["+this.commit+"]初始化了浏览器"+browser.toString()+"来进行自动化测试");
    }

    public static BaseBrowser  creatBrowser(Browser browser){
        BaseBrowser baseBrowser=new BaseBrowser(browser);
        return baseBrowser;
    }

    @Override
    public ICurrentPage open(String url) {
        this.getCurrentBrowserDriver().get(url);
        this.setClosed(false);
        //logger.info("打开了http地址"+url);
        this.currentPage.setBrowser(this);
        this.windowSource.getWindowsCollecter().updateWindows();
        logger.info("["+ com.github.lmm.runtime.RuntimeMethod.getName()+"]"+"当前初始化页面信息：URL--->"+this.getCurrentPage().getUrl());
        logger.info("["+ com.github.lmm.runtime.RuntimeMethod.getName()+"]"+"当前初始化页面信息：Title--->"+this.getCurrentPage().getTitle());
        logger.info("["+ RuntimeMethod.getName()+"]"+"当前初始化页面信息：窗口句柄数--->"+this.getWindows().size());
        return this.currentPage;
    }

    @Override
    public void maxWindow() {
        this.driver.manage().window().maximize();
    }

    @Override
    public void closeAllWindows() {
        ActionListenerProxy.getDispatcher().beforecloseAllWindows();
        if(this.driver!=null){
            this.getCurrentBrowserDriver().quit();
            this.setClosed(true);
            this.windowSource.setRun(false);
            logger.info("["+this.commit+"]关闭了浏览器");
        }else{
            logger.warn("["+this.commit+"]与浏览器交互的session值可能已经中断了，请检查程序是否编写正确，程序还将继续运行下去");
        }
        ActionListenerProxy.getDispatcher().aftercloseAllWindows();
    }

    @Override
    public void back() {
        ActionListenerProxy.getDispatcher().beforeback();
        this.driver.navigate().back();
        logger.info("["+this.commit+"]浏览器进行了后退操作");
        ActionListenerProxy.getDispatcher().afterback();
    }

    @Override
    public void refresh() {
        ActionListenerProxy.getDispatcher().beforerefresh();
        this.driver.navigate().refresh();
        logger.info("["+this.commit+"]浏览器进行了刷新操作");
        ActionListenerProxy.getDispatcher().afterrefresh();
    }

    @Override
    public void forward() {
        ActionListenerProxy.getDispatcher().beforeforward();
        this.driver.navigate().forward();
        logger.info("["+this.commit+"]浏览器进行了前进操作");
        ActionListenerProxy.getDispatcher().afterforward();
    }

    @Override
    public Set<String> getWindows() {
        return this.driver.getWindowHandles();
    }

    @Override
    public ICurrentPage selectDefaultWindow() {
        this.driver.switchTo().defaultContent();
        this.currentPage.setBrowser(this);
        return this.currentPage;
    }

    @Override
    public ICurrentPage selectFrame(By by) {
        this.driver.switchTo().frame(this.driver.findElement(by));
        this.currentPage.setBrowser(this);
        return this.currentPage;
    }

    @Override
    public ICurrentPage selectFrame(int index) {
        this.driver.switchTo().frame(index);
        this.currentPage.setBrowser(this);
        return this.currentPage;
    }

    @Override
    public ICurrentPage selectFrame(By by, int index) {
        this.driver.switchTo().frame(this.driver.findElements(by).get(index));
        this.currentPage.setBrowser(this);
        return this.currentPage;
    }

    @Override
    public ICurrentPage selectLastOpenedPage() {
        ActionListenerProxy.getDispatcher().beforeselectWindow();
        this.windowSource.getWindowsCollecter().updateWindows();
        String windowhandle = this.windowSource.getWindowsCollecter().getLastWindowhandle();
        this.driver.switchTo().window(windowhandle);
        this.currentPage.setBrowser(this);
        ActionListenerProxy.getDispatcher().afterselectWindow();
        logger.info("["+this.commit+"]当前页面信息：URL--->"+this.getCurrentPage().getUrl());
        logger.info("["+this.commit+"]当前页面信息：Title--->"+this.getCurrentPage().getTitle());
        logger.info("["+this.commit+"]当前页面信息：窗口句柄数--->"+this.getWindows().size());
        return this.currentPage;
    }

    @Override
    public ICurrentPage selectWindowByTitle(String title) {
        ActionListenerProxy.getDispatcher().beforeselectWindow();
        this.windowSource.getWindowsCollecter().updateWindows();
        String windowhandle=this.windowSource.getWindowsCollecter().getWindowInfoMap().get(title).getWindowHandle();
        this.driver.switchTo().window(windowhandle);
        logger.info("[" + this.commit + "]当前页面切换到了-------->" + title);
        ActionListenerProxy.getDispatcher().afterselectWindow();
        this.currentPage.setBrowser(this);
        logger.info("["+this.commit+"]当前页面信息：URL--->"+this.getCurrentPage().getUrl());
        logger.info("["+this.commit+"]当前页面信息：Title--->"+this.getCurrentPage().getTitle());
        logger.info("["+this.commit+"]当前页面信息：窗口句柄数--->"+this.getWindows().size());
        return this.currentPage;
    }

    @Override
    public ICurrentPage selectWindowByUrl(String url) {
        ActionListenerProxy.getDispatcher().beforeselectWindow();
        this.windowSource.getWindowsCollecter().updateWindows();
        for(Map.Entry<String,WindowInfo> info:this.windowSource.getWindowsCollecter().getWindowInfoMap().entrySet()){
            if(info.getValue().getUrl().equals(url)){
                this.driver.switchTo().window(info.getValue().getWindowHandle());
                logger.info("["+this.commit+"]当前页面切换到了--------->"+info.getValue().getTitle());
                break;
            }
        }
        ActionListenerProxy.getDispatcher().afterselectWindow();
        this.currentPage.setBrowser(this);
        logger.info("["+this.commit+"]当前页面信息：URL--->"+this.getCurrentPage().getUrl());
        logger.info("["+this.commit+"]当前页面信息：Title--->"+this.getCurrentPage().getTitle());
        logger.info("["+this.commit+"]当前页面信息：窗口句柄数--->"+this.getWindows().size());
        return this.currentPage;
    }

    public ICurrentPage selectWindowByIndex(Integer index) {
        this.windowSource.getWindowsCollecter().updateWindows();
        String windowhandle=this.windowSource.getWindowsCollecter().getWindowhandleByIndex(index);
        this.driver.switchTo().window(windowhandle);
        logger.info("[" + this.commit + "]当前页面切换到了-------->" + this.driver.getTitle());
        this.currentPage.setBrowser(this);
        logger.info("["+this.commit+"]当前页面信息：URL--->"+this.getCurrentPage().getUrl());
        logger.info("["+this.commit+"]当前页面信息：Title--->"+this.getCurrentPage().getTitle());
        logger.info("["+this.commit+"]当前页面信息：窗口句柄数--->"+this.getWindows().size());
        return this.currentPage;
    }

    @Override
    public ICurrentPage selectWindowContainsUrl(String url) {
        ActionListenerProxy.getDispatcher().beforeselectWindow();
        this.windowSource.getWindowsCollecter().updateWindows();
        for(Map.Entry<String,WindowInfo> info:this.windowSource.getWindowsCollecter().getWindowInfoMap().entrySet()){
            if(info.getValue().getUrl().contains(url)){
                this.driver.switchTo().window(info.getValue().getWindowHandle());
                logger.info("["+this.commit+"]当前页面切换到了--------->"+info.getValue().getTitle());
                break;
            }
        }
        this.currentPage.setBrowser(this);
        logger.info("["+this.commit+"]当前页面信息：URL--->"+this.getCurrentPage().getUrl());
        logger.info("["+this.commit+"]当前页面信息：Title--->"+this.getCurrentPage().getTitle());
        logger.info("["+this.commit+"]当前页面信息：窗口句柄数--->"+this.getWindows().size());
        ActionListenerProxy.getDispatcher().afterselectWindow();
        return this.currentPage;
    }

    @Override
    public ICurrentPage getCurrentPage() {
        this.currentPage.setBrowser(this);
        return this.currentPage;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public WebDriver getCurrentBrowserDriver() {
        return this.driver;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object runJavaScript(String js, Object... objects) {
        ActionListenerProxy.getDispatcher().beforerunJS();
        Object obj= ((JavascriptExecutor)this.driver).executeScript(js,objects);
        logger.info("["+this.commit+"]浏览器执行了javascript--> "+js);
        ActionListenerProxy.getDispatcher().afterrunJS();
        return obj;
    }

    @Override
    public Object runAsynJavaScript(String js, Object... objects) {
        ActionListenerProxy.getDispatcher().beforerunJS();
        Object obj= ((JavascriptExecutor)this.driver).executeAsyncScript(js, objects);
        logger.info("["+this.commit+"]浏览器执行了异步javascript--> "+js);
        ActionListenerProxy.getDispatcher().afterrunJS();
        return obj;
    }

    public void takeScreetShot(String path){
        ActionListenerProxy.getDispatcher().beforetakeScreenShot();
        TakesScreenshot tss = (TakesScreenshot)this.driver;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String time= sdf.format(new Date());
        File file = tss.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,new File(path+File.separator+time+".png"));
            logger.info("["+this.commit+"]浏览器当前页面截屏成功！截屏路径->"+path);
        } catch (IOException e) {
            logger.error("["+this.commit+"]浏览器当前页面截屏失败！可能是因为文件路径不正确");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        ActionListenerProxy.getDispatcher().aftertakeScreenShot();

    }

    public boolean isClosed() {
        return isClosed=false;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public void loadSource(Source source){
        this.elementManager.addElements(source.loadSource(this));
    }

    public ElementManager getElementManager() {
        return elementManager;
    }

    public void setElementManager(ElementManager elementManager) {
        this.elementManager = elementManager;
    }

    public WindowSource getWindowSource() {
        return windowSource;
    }
}
