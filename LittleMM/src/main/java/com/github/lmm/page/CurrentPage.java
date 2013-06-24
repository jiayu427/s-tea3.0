package com.github.lmm.page;

import com.github.lmm.browser.IBrowser;
import com.github.lmm.element.*;
import com.github.lmm.proxy.ActionListenerProxy;
import com.github.lmm.runtime.RuntimeMethod;
import com.github.lmm.source.Source;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.lang.reflect.InvocationTargetException;
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
    private static Object currentpage;
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
        this.name= com.github.lmm.runtime.RuntimeMethod.getName();
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
    public <T> T frame(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor(ICurrentPage.class).newInstance(this);
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    @Override
    public ElementManager getElementManager() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Frame frame(int index) {
        return new DefaultFrame(this,index);
    }

    @Override
    public Frame frame(String nameOrId) {
        return new DefaultFrame(this,nameOrId);
    }

    @Override
    public Frame frame(By by) {
        return new DefaultFrame(this,by);
    }

    @Override
    public Frame frame(By by, int index) {
        return new DefaultFrame(this,by,index);
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

    public static <T> T page(Class<T> clazz) {
        CurrentPage.currentpage=null;
        try {
            currentpage=Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("没有找到这个class类"+clazz.getName()+",请检查类是否被加载或者类名是否正确");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("没有找到这个class类"+clazz.getName()+",请检查类是否被加载或者类名是否正确");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("没有找到这个class类"+clazz.getName()+",请检查类是否被加载或者类名是否正确");
        }
        return (T)currentpage;
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
        return new Element(this.browser,tempElement);

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
    public Table table() {
        return new Table(this.browser);
    }

    @Override
    public Table table(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        return new Table(this.browser,tempElement);
    }

    @Override
    public Table table(By by) {
        Table table = new Table(this.browser);
        table.addLocator(by);
        return table;
    }

    @Override
    public Table table(By by, Integer index) {
        Table table = new Table(this.browser);
        table.addLocator(by,index);
        return table;
    }

    @Override
    public Button button() {
        Button button=new Button(this.browser);
        return button;
    }

    @Override
    public Button button(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        Button button=new Button(this.browser,tempElement);
        return button;
    }

    @Override
    public Button button(By by) {
        Button button=new Button(this.browser);
        button.addLocator(by);
        return button;
    }

    @Override
    public Button button(By by, Integer index) {
        Button button=new Button(this.browser);
        button.addLocator(by,index);
        return button;
    }

    @Override
    public CheckBox checkBox() {
        return new CheckBox(this.browser);
    }

    @Override
    public CheckBox checkBox(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        CheckBox checkBox=new CheckBox(this.browser,tempElement);
        return checkBox;
    }

    @Override
    public CheckBox checkBox(By by) {
        CheckBox checkBox=new CheckBox(this.browser);
        checkBox.addLocator(by);
        return checkBox;
    }

    @Override
    public CheckBox checkBox(By by, Integer index) {
        CheckBox checkBox=new CheckBox(this.browser);
        checkBox.addLocator(by,index);
        return checkBox;
    }

    @Override
    public Form form() {
        return new Form(this.browser);
    }

    @Override
    public Form form(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        Form form=new Form(this.browser,tempElement);
        return form;
    }

    @Override
    public Form form(By by) {
        Form form=new Form(this.browser);
        form.addLocator(by);
        return form;
    }

    @Override
    public Form form(By by, Integer index) {
        Form form=new Form(this.browser);
        form.addLocator(by,index);
        return form;
    }

    @Override
    public Image image() {
        return new Image(this.browser);
    }

    @Override
    public Image image(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        Image image=new Image(this.browser,tempElement);
        return image;
    }

    @Override
    public Image image(By by) {
        Image image=new Image(this.browser);
        image.addLocator(by);
        return image;
    }

    @Override
    public Image image(By by, Integer index) {
        Image image=new Image(this.browser);
        image.addLocator(by, index);
        return image;
    }

    @Override
    public Link link() {
        Link link = new Link(this.browser);
        return link;
    }

    @Override
    public Link link(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        Link link=new Link(this.browser,tempElement);
        return link;
    }

    @Override
    public Link link(By by) {
        Link link=new Link(this.browser);
        link.addLocator(by);
        return link;
    }

    @Override
    public Link link(By by, Integer index) {
        Link link=new Link(this.browser);
        link.addLocator(by, index);
        return link;
    }

    @Override
    public RadioButton radioButton() {
        return new RadioButton(this.browser);
    }

    @Override
    public RadioButton radioButton(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        RadioButton radioButton=new RadioButton(this.browser,tempElement);
        return radioButton;
    }

    @Override
    public RadioButton radioButton(By by) {
        RadioButton radioButton=new RadioButton(this.browser);
        radioButton.addLocator(by);
        return radioButton;
    }

    @Override
    public RadioButton radioButton(By by, Integer index) {
        RadioButton radioButton=new RadioButton(this.browser);
        radioButton.addLocator(by);
        return radioButton;
    }

    @Override
    public RichTextField richTextField() {
        return new RichTextField(this.browser);
    }

    @Override
    public RichTextField richTextField(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        RichTextField richTextField=new RichTextField(this.browser,tempElement);
        return richTextField;
    }

    @Override
    public RichTextField richTextField(By by) {
        RichTextField richTextField=new RichTextField(this.browser);
        richTextField.addLocator(by);
        return richTextField;
    }

    @Override
    public RichTextField richTextField(By by, Integer index) {
        RichTextField richTextField=new RichTextField(this.browser);
        richTextField.addLocator(by, index);
        return richTextField;
    }

    @Override
    public Select select() {
        return new Select(this.browser);
    }

    @Override
    public Select select(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        Select select=new Select(this.browser,tempElement);
        return select;
    }

    @Override
    public Select select(By by) {
        Select select = new Select(this.browser);
        select.addLocator(by);
        return select;
    }

    @Override
    public Select select(By by, Integer index) {
        Select select=new Select(this.browser);
        select.addLocator(by, index);
        return select;
    }

    @Override
    public TextField textField() {
        return new TextField(this.browser);
    }

    @Override
    public TextField textField(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        TextField textField= new TextField(this.browser,tempElement);
        return textField;
    }

    @Override
    public TextField textField(By by) {
        TextField textField=new TextField(this.browser);
        textField.addLocator(by);
        return textField;
    }

    @Override
    public TextField textField(By by, Integer index) {
        TextField textField=new TextField(this.browser);
        textField().addLocator(by,index);
        return textField;
    }

    @Override
    public <T> T selfConfigElement(T clazz) {
        return clazz;
    }

    @Override
    public void assertAlert() {
        try{
            this.browser.getCurrentBrowserDriver().switchTo().alert();
            logger.info("["+ com.github.lmm.runtime.RuntimeMethod.getName()+"]"+"当前页面找到了alert，校验成功！");
        }catch (NoAlertPresentException e){
            logger.error("["+ com.github.lmm.runtime.RuntimeMethod.getName()+"]"+"当前页面没有找到了alert，校验失败！");
            Assert.fail();
        }
    }

    @Override
    public void assertTextNotPresent(String text) {
        if(getPageSource().contains(text)){
            logger.info("["+ com.github.lmm.runtime.RuntimeMethod.getName()+"]"+"当前页面找到指定的内容，校验成功！");
        }else{
            logger.error("["+ com.github.lmm.runtime.RuntimeMethod.getName()+"]"+"当前页面没有找到指定内容"+text+"，校验失败！");
            Assert.fail();
        }
    }

    @Override
    public void assertTitle(String title) {
        if(getTitle().equals(title)){
            logger.info("["+ com.github.lmm.runtime.RuntimeMethod.getName()+"]"+"当前页面的title值["+title+"]校验成功，校验成功！");
        }else{
            logger.error("["+ com.github.lmm.runtime.RuntimeMethod.getName()+"]"+"当前页面title值["+title+"]校验失败，校验成功！");
            Assert.fail();
        }
    }

    @Override
    public void assertTextPresent(String text) {
        if(getPageSource().contains(text)){
            logger.info("["+ com.github.lmm.runtime.RuntimeMethod.getName()+"]"+"当前页面没有找到指定的内容，校验成功！");
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
        runJavaScript("window.open(\""+url+"\")");
        logger.info("在新的窗口打开了链接"+url);
        try {
            Thread.sleep(3000);
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
