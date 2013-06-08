package com.github.lmm.page;

import com.github.lmm.browser.IBrowser;
import com.github.lmm.element.Element;
import com.github.lmm.element.ElementManager;
import com.github.lmm.element.IElement;
import com.github.lmm.element.TempElement;
import com.github.lmm.proxy.ActionListenerProxy;
import com.github.lmm.runtime.RuntimeMethod;
import com.github.lmm.source.Source;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */
public class CurrentPage implements ICurrentPage {
    private ElementManager elementManager;
    private Logger logger = Logger.getLogger(CurrentPage.class);
    private String url;
    private String title;
    private static Object page;
    private String name;
    public WebDriver getCurrentwindow() {
        return currentwindow;
    }

    private WebDriver currentwindow;

    public IBrowser getBrowser() {
        return browser;
    }

    private IBrowser browser;
    public CurrentPage(IBrowser browser){
        this.name=RuntimeMethod.getName();
        this.browser=browser;
        this.currentwindow=browser.getCurrentBrowserDriver();
        elementManager=new ElementManager();
        this.elementManager.addElements(this.browser.getElementManager());
    }
    public CurrentPage(WebDriver driver){
        this.currentwindow=driver;
    }

    public void setBrowser(IBrowser browser){
        this.browser=browser;
        this.currentwindow=browser.getCurrentBrowserDriver();
    }


    @Override
    public Set<Cookie> getAllCookies() {
        return this.currentwindow.manage().getCookies();
    }

    @Override
    public void deleteAllCookies() {
        this.currentwindow.manage().deleteAllCookies();
        logger.info("["+this.name+"]进行了删除所有cookie的操作");
    }

    @Override
    public String getTitle() {
        return this.currentwindow.getTitle();
    }

    @Override
    public String getUrl() {
        return this.currentwindow.getCurrentUrl();
    }

    @Override
    public String getCookieByName(String name) {
        return this.currentwindow.manage().getCookieNamed(name).getValue();
    }

    @Override
    public IElement currentElement() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public static <T> T load(Class<T> clazz) {
        CurrentPage.page=null;
        try {
            page=Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("没有找到这个class类"+clazz.getName()+",请检查类是否被加载或者类名是否正确");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("没有找到这个class类"+clazz.getName()+",请检查类是否被加载或者类名是否正确");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("没有找到这个class类"+clazz.getName()+",请检查类是否被加载或者类名是否正确");
        }
        return (T)page;
    }


    @Override
    public void open(String url) {
        this.currentwindow.get(url);
        logger.info("["+this.name+"]页面跳转到了页面"+url);
    }

    @Override
    public void addElement(TempElement element) {
        this.elementManager.addElement(element);
    }


    @Override
    public void addElements(Source source) {
        this.elementManager.addElements(source.loadSource(this.browser));
    }

    @Override
    public IElement element() {
        return new Element(this.browser);
    }

    @Override
    public IElement element(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        if(!tempElement.isFrameElement()){
            this.browser.selectDefaultWindow();
            return new Element(this.browser,tempElement);
        }else{
            return null;
        }
    }

    @Override
    public IElement element(By by) {
        Element element = new Element(this.browser);
        element.addLocator(by);
        return element;
    }

    @Override
    public IElement element(By by, Integer index) {
        Element element = new Element(this.browser);
        element.addLocator(by,index);
        return element;
    }

    @Override
    public void assertAlert() {
        try{
            this.browser.getCurrentBrowserDriver().switchTo().alert();
            logger.info("["+ RuntimeMethod.getName()+"]"+"当前页面找到了alert，校验成功！");
        }catch (NoAlertPresentException e){
            logger.error("["+ RuntimeMethod.getName()+"]"+"当前页面没有找到了alert，校验失败！");
            Assert.fail();
        }
    }

    @Override
    public void assertTextNotPresent(String text) {
        if(getPageSource().contains(text)){
            logger.info("["+ RuntimeMethod.getName()+"]"+"当前页面找到指定的内容，校验成功！");
        }else{
            logger.error("["+ RuntimeMethod.getName()+"]"+"当前页面没有找到指定内容"+text+"，校验失败！");
            Assert.fail();
        }
    }

    @Override
    public void assertTitle(String title) {
        if(getTitle().equals(title)){
            logger.info("["+ RuntimeMethod.getName()+"]"+"当前页面的title值["+title+"]校验成功，校验成功！");
        }else{
            logger.error("["+ RuntimeMethod.getName()+"]"+"当前页面title值["+title+"]校验失败，校验成功！");
            Assert.fail();
        }
    }

    @Override
    public void assertTextPresent(String text) {
        if(getPageSource().contains(text)){
            logger.info("["+ RuntimeMethod.getName()+"]"+"当前页面没有找到指定的内容，校验成功！");
        }else{
            logger.error("["+ RuntimeMethod.getName()+"]"+"当前页面找到了指定内容"+text+"，校验失败！");
            Assert.fail();
        }
    }

    @Override
    public Map<String, String> getHeaders() {
        PageResponse pageResponse=new PageResponse(this);
        Map<String,String> headermap=pageResponse.getHeaderMap();
        pageResponse.close();
        return headermap;
    }

    @Override
    public List<String> getJavaScriptURL() {
        PageResponse pageResponse=new PageResponse(this);
        List<String> jslist=pageResponse.getJavaScriptURL();
        pageResponse.close();
        return jslist;
    }

    @Override
    public String getHeaderByName(String name) {
        return getHeaders().get(name);
    }

    @Override
    public String getPageSource() {
        return this.currentwindow.getPageSource();
    }

    @Override
    public Integer getStatusCode() {
        PageResponse pageResponse=new PageResponse(this);
        Integer code = pageResponse.getStatusCode();
        pageResponse.close();
        return code;
    }

    @Override
    public List<String> getAllCssURLByLinked() {
        PageResponse pageResponse=new PageResponse(this);
        List<String> csslist=pageResponse.getAllCssUrlByLinked();
        pageResponse.close();
        return csslist;
    }

    @Override
    public boolean isGzip() {
        PageResponse pageResponse=new PageResponse(this);
        boolean bool=pageResponse.isGzip();
        pageResponse.close();
        return bool;
    }

    @Override
    public String dealAlert() {
        ActionListenerProxy.getDispatcher().beforedealAlert();
        String alerMessage=null;
        try{
            Alert alert=this.browser.getCurrentBrowserDriver().switchTo().alert();
            alerMessage=alert.getText();
            alert.accept();
            ActionListenerProxy.getDispatcher().afterdealAlert();
            return alerMessage;
        }catch(NoAlertPresentException e){
            ActionListenerProxy.getDispatcher().afterdealAlert();
            logger.warn("["+this.name+"]没有找到alert窗口，程序将继续运行，可能会出现异常，请查看代码是否正确");
            return null;
        }
    }

    @Override
    public String dealConfirm(boolean isyes) {
        ActionListenerProxy.getDispatcher().beforedealConfirm();
        String alerMessage=null;
        try{
            Alert alert=this.browser.getCurrentBrowserDriver().switchTo().alert();
            alerMessage=alert.getText();
            if(isyes){
                alert.accept();
            }else{
                alert.dismiss();
            }
            ActionListenerProxy.getDispatcher().afterdealConfirm();
            return alerMessage;
        }catch(NoAlertPresentException e){
            ActionListenerProxy.getDispatcher().afterdealConfirm();
            logger.warn("["+this.name+"]没有找到comfirm窗口，程序将继续运行，可能会出现异常，请查看代码是否正确");
            return null;
        }
    }

    @Override
    public String dealPrompt(boolean isyes, String text) {
        ActionListenerProxy.getDispatcher().beforedealConfirm();
        String alerMessage=null;
        try{
            Alert alert=this.browser.getCurrentBrowserDriver().switchTo().alert();
            alert.sendKeys(text);
            alerMessage=alert.getText();
            if(isyes){
                alert.accept();
            }else{
                alert.dismiss();
            }
            ActionListenerProxy.getDispatcher().afterdealConfirm();
            return alerMessage;
        }catch(NoAlertPresentException e){
            ActionListenerProxy.getDispatcher().afterdealConfirm();
            logger.warn("["+this.name+"]没有找到prompt窗口，程序将继续运行，可能会出现异常，请查看代码是否正确");
            return null;
        }
    }

    @Override
    public Object runJavaScript(String js, Object... objects) {
        return ((JavascriptExecutor)this.currentwindow).executeScript(js, objects);
    }

    @Override
    public Object runAsynJavaScript(String js, Object... objects) {
        return ((JavascriptExecutor)this.currentwindow).executeAsyncScript(js, objects);
    }

    @Override
    public ICurrentPage openNewWindow(String url) {
        runJavaScript("window.open(\"\",\"_blank\")");
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.browser.selectLastOpenedPage();
        return this.browser.getCurrentPage();
    }

    @Override
    public WebDriver getCurrentWindow() {
        return this.browser.getCurrentBrowserDriver();
    }
}
