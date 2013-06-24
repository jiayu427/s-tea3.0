package com.github.lmm.element;

import com.github.lmm.browser.IBrowser;
import com.github.lmm.proxy.ActionListenerProxy;
import com.github.lmm.runtime.RuntimeMethod;
import com.github.lmm.source.xml.ChildElementInfo;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *@author 王天庆
 * 这是一个元素类，单纯封装的一个元素类
 */
public class Element implements IElement {
    private Logger logger = Logger.getLogger(Element.class);
    private IBrowser browser;
    private WebElement element;
    private TempElement tempElement;
    private Actions actions;
    private String id;
    private WebDriver currentwindow;
    private String value;
    private String by;
    private Integer index;
    private By locator;
    private String commit;
    private List<WebElement> frameElements;
    public Element(IBrowser browser,TempElement tempElement){
        this.browser=browser;
        this.currentwindow=browser.getCurrentBrowserDriver();
        this.currentwindow.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actions=new Actions(this.currentwindow);
        commit="["+ com.github.lmm.runtime.RuntimeMethod.getName()+"]";
        this.tempElement= tempElement;
        this.locator=tempElement.getLocator();
        this.value=tempElement.getValue();
        this.id=tempElement.getId();
        this.index=tempElement.getIndex();
        List<WebElement> list=this.currentwindow.findElements(this.locator);
        if(list.size()>0){
            this.element=list.get(this.index);
            selectElementWithChild(this.tempElement);
        }else{
            this.currentwindow.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
            this.frameElements=this.browser.getCurrentBrowserDriver().findElements(By.tagName("iframe"));
            this.frameElements.addAll(this.browser.getCurrentBrowserDriver().findElements(By.tagName("frame")));
            locatorFrameElement(list, this.locator,this.index);
            selectElementWithChild(this.tempElement);
            if(this.element==null){
                logger.error("在转化元素的时候出现了错误，给出的临时元素并不能够转化为网页的元素，请仔细检查元素的定义");
                logger.error("临时元素信息->"+this.tempElement.getId()+":"+this.tempElement.getLocator().toString());
                throw new NoSuchElementException("没有找到定义的元素，请仔细检查元素是否定义正确");
            }
        }
    }

    private void locatorFrameElement(List<WebElement> frameElements,By selectby){
        this.locatorFrameElement(frameElements, selectby,0);
    }
    private void locatorFrameElement(List<WebElement> frameElements,By selectby,Integer findex){
        long start=System.currentTimeMillis();
        boolean isout=false;
        for(WebElement webElement:frameElements){
            if(isout){
                break;
            }
            this.currentwindow.switchTo().frame(webElement);
            this.actions=new Actions(this.currentwindow);
            List<WebElement> elements=this.currentwindow.findElements(selectby);
            if(elements.size()==0){
                List<WebElement> framelist=this.currentwindow.findElements(By.tagName("iframe"));
                framelist.addAll(this.currentwindow.findElements(By.tagName("frame")));
                locatorFrameElement(framelist,selectby,findex);
            }else{
                this.element=elements.get(findex);
                isout=true;
                break;
            }
        }
        long times=System.currentTimeMillis()-start;
        logger.info("解析frame元素消费了-->"+times+"毫秒,每次打印的时间意味着此方法访问了一次frame，在此过程中会通过递归的方式来进行查找元素，" +
                "消费的总时间应该是所有的消费时间的总和。此日志的记录是为了防止因为访问frame性能影响脚本运行");
    }
    public Element(IBrowser browser){
        this.browser=browser;
        this.currentwindow=browser.getCurrentBrowserDriver();
        //this.currentwindow.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        actions=new Actions(this.currentwindow);
        commit="["+ RuntimeMethod.getName()+"]";
        this.id="Element";
        this.frameElements=this.currentwindow.findElements(By.tagName("iframe"));
        //System.out.println(frameElements.size());
        this.frameElements.addAll(this.currentwindow.findElements(By.tagName("frame")));
        this.element=new RemoteWebElement();
    }
    @Override
    public void click() {
        ActionListenerProxy.getDispatcher().beforeClickOn();
        if(isExist()){
            element.click();
            logger.info(this.commit+"["+id+"]点击操作成功");

        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，点击失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，点击失败！");
        }
        this.browser.getWindowSource().windowsCheck();
        ActionListenerProxy.getDispatcher().afterClickOn();
    }

    @Override
    public void doubleClick() {
        ActionListenerProxy.getDispatcher().beforedoubleClick();
        if(isExist()){
            actions.doubleClick().build().perform();
            logger.info(this.commit+"["+id+"]双击操作成功");
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，双击击失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，双击失败！");
        }
        this.browser.getWindowSource().windowsCheck();
        ActionListenerProxy.getDispatcher().afterdoubleClick();

    }

    @Override
    public void keyDown(Keys key) {
        ActionListenerProxy.getDispatcher().beforekeyDown();
        if(isExist()){
            actions.keyDown(key).build().perform();
            logger.info(this.commit+"["+id+"]按下键盘按钮["+key+"]成功");
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，按下按键["+key+"]失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，按下按钮["+key+"]失败！");
        }
        this.browser.getWindowSource().windowsCheck();
        ActionListenerProxy.getDispatcher().afterkeyDown();
    }

    @Override
    public void keyUp(Keys key) {
        ActionListenerProxy.getDispatcher().beforekeyUp();
        if(isExist()){
            actions.keyDown(key).build().perform();
            logger.info(this.commit+"["+id+"]按下键盘按钮"+key+"成功");
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，松开按键"+key+"失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，松开按键"+key+"失败！");
        }
        this.browser.getWindowSource().windowsCheck();
        ActionListenerProxy.getDispatcher().afterkeyUp();
    }

    @Override
    public void assertAttribute(String attr,String value) {
        if(getAttribute(attr).equals(value)){
            logger.info(this.commit+"["+id+"]这个元素的属性["+attr+"]的值[="+value+"]断言正确");
        }else{
            logger.error(this.commit+"["+id+"]这个元素的属性["+attr+"]的值[="+value+"]断言失败");
            Assert.fail();
        }
    }

    @Override
    public void assertEditable() {
        if(isEnable()){
            logger.info(this.commit+"["+id+"]这个元素可编辑的断言成功");
        }else{
            logger.error(this.commit+"["+id+"]这个元素可编辑性断言失败");
            Assert.fail();
        }
    }

    @Override
    public void assertNotEditable() {
        if(!isEnable()){
            logger.info(this.commit+"["+id+"]这个元素不可编辑的断言成功");
        }else{
            logger.error(this.commit+"["+id+"]这个元素不可编辑性断言失败");
            Assert.fail();
        }
    }

    @Override
    public void assertSelected() {
        if(isSelected()){
            logger.info(this.commit+"["+id+"]这个元素可选择性断言成功");
        }else{
            logger.error(this.commit+"["+id+"]这个元素可选择性断言失败");
            Assert.fail();
        }
    }

    @Override
    public void assertIsExist() {
        if(isExist()){
            logger.info(this.commit+"["+id+"]这个元素存在性断言成功");
        }else{
            logger.error(this.commit+"["+id+"]这个元素存在性断言失败");
            Assert.fail();
        }
    }

    @Override
    public void assertText(String text) {
        if(getText().equals(text)){
            logger.info(this.commit+"["+id+"]这个元素的文本值["+text+"]断言成功");
        }else{
            logger.error(this.commit+"["+id+"]这个元素的文本值["+text+"]断言失败");
            Assert.fail();
        }
    }

    @Override
    public void assertValue(String value) {
        if(getAttribute("value").equals(value)){
            logger.info(this.commit+"["+id+"]这个元素的value值["+value+"]断言成功");
        }else{
            logger.error(this.commit+"["+id+"]这个元素的value值["+value+"]断言失败");
            Assert.fail();
        }
    }

    @Override
    public IElement childElement(By by) {
        this.element=this.element.findElement(by);
        return this;
    }

    @Override
    public IElement childElement(By by, Integer integer) {
        this.element=this.element.findElements(by).get(integer);
        return this;
    }

    @Override
    public IElement childElement(Locator locator, String value) {
        this.element=this.element.findElement(locator.getLocator(value));
        return this;
    }

    @Override
    public IElement childElement(Locator locator, String value, Integer integer) {
        this.element=this.element.findElements(locator.getLocator(value)).get(integer);
        return this;
    }

    @Override
    public void clear() {
        ActionListenerProxy.getDispatcher().beforeclear();
        if(isExist()){
            element.clear();
            logger.info(this.commit+"["+id+"]清空操作成功");

        }else{
            logger.error(this.commit + "[" + id + "]元素查找失败，可能这个元素不存在，清空失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，清空失败！");
        }
        ActionListenerProxy.getDispatcher().afterclear();
    }

    @Override
    public void input(String text) {
        ActionListenerProxy.getDispatcher().beforeSendkeys();
        if(isExist()){
            element.sendKeys(text);
            logger.info(this.commit+"["+id+"]输入["+text+"]值操作成功");

        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，输入["+text+"]失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，输入["+text+"]失败！");
        }
        ActionListenerProxy.getDispatcher().afterSendkeys();
    }

    @Override
    public void focus() {
        this.element.sendKeys("");
        this.browser.getWindowSource().windowsCheck();
    }

    @Override
    public String getAttribute(String attr) {
        if(isExist()){
            String attrvalue = this.element.getAttribute(attr);
            logger.info(this.commit+"["+id+"]获取属性[attr]的值[="+attrvalue+"]成功操作成功");
            return attrvalue;
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，获取属性"+attr+"失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，获取属性["+attr+"]的值失败！");
        }
    }

    @Override
    public String getText() {
        if(isExist()){
            String text=this.element.getText();
            logger.info(this.commit+"["+id+"]获取文本值["+text+"]操作成功");
            return text;
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，获取文本值失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，获取文本值失败！");
        }
    }

    @Override
    public String getCssValue(String name) {
        if(isExist()){
            String cssvalue = this.element.getCssValue(name);
            logger.info(this.commit+"["+id+"]元素获取css["+name+"]的值[="+cssvalue+"]操作成功");
            return cssvalue;
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，获取Css["+name+"]值失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，获取css["+name+"]失败！");
        }
    }

    @Override
    public String getId() {
        return this.getId();
    }

    @Override
    public void setId(String id) {
        this.id=id;
    }

    @Override
    public Point getLocation() {
        if(isExist()){
            Point point=this.element.getLocation();
            logger.info(this.commit+"["+id+"]获取元素位置操作成功");
            return point;
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，获取位置失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，获取位置失败！");
        }
    }

    @Override
    public ListElements getOptions(String tagname) {
        List<Element> elist = new ArrayList<Element>();
        List<WebElement> welist = this.currentwindow.findElements(By.tagName(tagname));
        for(WebElement we:welist){
            Element e=new Element(this.browser);
            e.element=we;
            elist.add(e);
        }
        return new ListElements(elist);
    }

    @Override
    public ListElements getOptionsById(String id) {
        List<Element> elist = new ArrayList<Element>();
        List<WebElement> welist = this.currentwindow.findElements(By.id(id));
        for(WebElement we:welist){
            Element e=new Element(this.browser);
            e.element=we;
            elist.add(e);
        }
        return new ListElements(elist);
    }

    @Override
    public ListElements getOptionsByXpath(String xpath) {
        List<Element> elist = new ArrayList<Element>();
        List<WebElement> welist = this.currentwindow.findElements(By.xpath(xpath));
        for(WebElement we:welist){
            Element e=new Element(this.browser);
            e.element=we;
            elist.add(e);
        }
        return new ListElements(elist);
    }

    @Override
    public ListElements getOptionsByName(String name) {
        List<Element> elist = new ArrayList<Element>();
        List<WebElement> welist = this.currentwindow.findElements(By.name(name));
        for(WebElement we:welist){
            Element e=new Element(this.browser);
            e.element=we;
            elist.add(e);
        }
        return new ListElements(elist);
    }

    @Override
    public ListElements getOptionsByClassName(String classname) {
        List<Element> elist = new ArrayList<Element>();
        List<WebElement> welist = this.currentwindow.findElements(By.className(classname));
        for(WebElement we:welist){
            Element e=new Element(this.browser);
            e.element=we;
            elist.add(e);
        }
        return new ListElements(elist);
    }

    @Override
    public ListElements getOptionsByLinkText(String linktext) {
        List<Element> elist = new ArrayList<Element>();
        List<WebElement> welist = this.currentwindow.findElements(By.linkText(linktext));
        for(WebElement we:welist){
            Element e=new Element(this.browser);
            e.element=we;
            elist.add(e);
        }
        return new ListElements(elist);
    }

    @Override
    public ListElements getOptionsByCss(String css) {
        List<Element> elist = new ArrayList<Element>();
        List<WebElement> welist = this.currentwindow.findElements(By.cssSelector(css));
        for(WebElement we:welist){
            Element e=new Element(this.browser);
            e.element=we;
            elist.add(e);
        }
        return new ListElements(elist);
    }

    @Override
    public int[] getSize() {
        if(isExist()){
            Dimension ds= this.element.getSize();
            int[] size=new int[]{ds.getHeight(),ds.getWidth()};
            logger.info(this.commit+"["+id+"]获取元素大小操作成功");
            return size;
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，获取元素大小失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，获取元素大小失败！");
        }
    }

    @Override
    public String getTagName() {
        if(isExist()){
            String tagname=this.element.getTagName();
            logger.info(this.commit+"["+id+"]获取元素标签名["+tagname+"]操作成功");
            return tagname;
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，获取元素标签名失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，获取元素标签名失败！");
        }
    }

    @Override
    public void scroll() {
        ActionListenerProxy.getDispatcher().beforescroll();
        if(isExist()){
            Point point=getLocation();
            int x=point.getX();
            int y=point.getY();
            this.browser.runJavaScript("window.scrollTo("+x+","+y+")");
            logger.info(this.commit+"["+id+"]视角移动到了["+x+","+y+"]的位置");
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，移动视角失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，移动视角失败！");
        }
        ActionListenerProxy.getDispatcher().afterscroll();
    }

    @Override
    public void mouseOver() {
        ActionListenerProxy.getDispatcher().beforeMouseOver();
        if(isExist()){
            this.actions.moveToElement(this.element).build().perform();
            logger.info(this.commit+"["+id+"]元素处鼠标悬浮成功！");
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，鼠标悬浮失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，鼠标悬浮失败！");
        }
        this.browser.getWindowSource().windowsCheck();
        ActionListenerProxy.getDispatcher().afterMouseOver();
    }

    @Override
    public void submit() {
        ActionListenerProxy.getDispatcher().beforesubmit();
        if(isExist()){
            this.element.submit();
            logger.info(this.commit+"["+id+"]元素提交表单成功！");
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，提交表单失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，提交表单失败！");
        }
        this.browser.getWindowSource().windowsCheck();
        ActionListenerProxy.getDispatcher().aftersubmit();
    }

    @Override
    public boolean isDisplay() {
        if(isExist()){
            return this.element.isDisplayed();
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在,判断元素是否可见失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，判断元素是否可见失败！");
        }
    }

    @Override
    public boolean isEnable() {
        if(isExist()){
            return this.element.isEnabled();
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，判断元素是否可编辑失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，判断元素是否可编辑失败！");
        }
    }


    @Override
    public boolean isSelected() {
        if(isExist()){
            return this.element.isSelected();
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，判断元素是否被选择失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，判断元素是否被选择失败！");
        }
    }

    @Override
    public void dragAndDrop(IElement e) {
        ActionListenerProxy.getDispatcher().beforedragAndDrop();
        if(isExist()){
            this.actions.dragAndDropBy(this.element,e.getLocation().getX(),e.getLocation().getY()).build().perform();
            logger.info(this.commit+"["+id+"]元素拖拽成功");
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，元素拖拽失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，元素拖拽失败！");
        }
        this.browser.getWindowSource().windowsCheck();
        ActionListenerProxy.getDispatcher().afterdragAndDrop();
    }

    @Override
    public void dragAndDrop(Point point) {
        ActionListenerProxy.getDispatcher().beforedragAndDrop();
        if(isExist()){
            this.actions.dragAndDropBy(this.element,point.getX(),point.getY());
            logger.info(this.commit+"["+id+"]元素拖拽成功");
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，元素拖拽失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，元素拖拽失败！");
        }
        this.browser.getWindowSource().windowsCheck();
        ActionListenerProxy.getDispatcher().afterdragAndDrop();
    }

    @Override
    public void leftDown() {
        ActionListenerProxy.getDispatcher().beforeleftDown();
        if(isExist()){
            this.actions.clickAndHold(this.element);
            logger.info(this.commit+"["+id+"]元素处按住左键");
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，元素处按住左键失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，元素处按住左键失败！");
        }
        this.browser.getWindowSource().windowsCheck();
        ActionListenerProxy.getDispatcher().afterleftDown();
    }

    @Override
    public void leftUp() {
        ActionListenerProxy.getDispatcher().beforeleftUp();
        if(isExist()){
            this.actions.release(this.element);
            logger.info(this.commit+"["+id+"]元素处松开左键");
        }else{
            logger.error(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，元素处松开左键失败！");
            throw new NoSuchElementException(this.commit+"["+id+"]元素查找失败，可能这个元素不存在，元素处按住松开失败！");
        }
        this.browser.getWindowSource().windowsCheck();
        ActionListenerProxy.getDispatcher().afterleftUp();
    }


    public boolean isExist(){
        if(this.element==null){
            return false;
        }else{
            return true;
        }

    }

    public Element addLocator(Locator style,String value){
        try{
            this.currentwindow.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
            this.element=this.currentwindow.findElement(style.getLocator(value));
        }catch (NoSuchElementException e){
            this.currentwindow.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
            locatorFrameElement(this.frameElements,style.getLocator(value));
            if(element==null){
                logger.error("定位器位置的元素不存在，没有获取到任何元素，请检查定义的元素");
                throw new NoSuchElementException("元素加载定位器的时候出现了错误！没有指定位置的元素");
            }
        }
        this.id=style.getLocator(value).toString();
        return this;
    }

    public Element addLocator(Locator style,String value,Integer eindex){
        this.currentwindow.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        List<WebElement> webElementList =this.currentwindow.findElements(style.getLocator(value));
        if(webElementList.size()==0){
            this.currentwindow.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
            locatorFrameElement(this.frameElements,style.getLocator(value));
            if(this.element==null){
                logger.error("定位器位置的元素不存在，没有获取到任何元素，请检查定义的元素");
                throw new NoSuchElementException("元素加载定位器的时候出现了错误！没有指定位置的元素");
            }
        }else{
            this.element= webElementList.get(eindex);
        }
        this.id=style.getLocator(value).toString()+",index->"+eindex;
        return this;
    }

    public IBrowser getBrowser() {
        return browser;
    }

    public Element addLocator(By byLocator){
        try{
            this.currentwindow.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
            this.element=this.currentwindow.findElement(byLocator);
        }catch (NoSuchElementException e){
            this.currentwindow.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
            //System.out.println("没有路过这么？");
            locatorFrameElement(this.frameElements,byLocator);
            if(this.element==null){
                logger.error("定位器位置的元素不存在，没有获取到任何元素，请检查定义的元素");
                throw new NoSuchElementException("元素加载定位器的时候出现了错误！没有指定位置的元素");
            }
        }
        this.id=byLocator.toString();
        return this;
    }

    public Element addLocator(By byLocator,Integer eindex){
        this.currentwindow.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        List<WebElement> webElementList=this.currentwindow.findElements(byLocator);
        if(webElementList.size()==0){
            this.currentwindow.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
            locatorFrameElement(this.frameElements, byLocator);
            if(this.element==null){
                logger.error("定位器位置的元素不存在，没有获取到任何元素，请检查定义的元素");
                throw new NoSuchElementException("元素加载定位器的时候出现了错误！没有指定位置的元素");
            }
        }else{
            this.element= webElementList.get(eindex);
        }
        this.id=byLocator.toString()+",index->"+eindex+1;
        return this;
    }

    public TempElement getTempElement() {
        return tempElement;
    }

    public String getValue() {
        return value;
    }

    public String getBy() {
        return by;
    }

    public Integer getIndex() {
        return index;
    }

    public By getLocator() {
        return locator;
    }
    /**如果临时元素还有子元素的话，会继续遍历找到元素为止*/
    private void selectElementWithChild(TempElement tempElement){
        if(tempElement.getElementInfo().getChileElementInfo().size()!=0){
            List<ChildElementInfo> childElementInfos=tempElement.getElementInfo().getChileElementInfo();
            for(int i=0;i<childElementInfos.size();i++){
                this.element=this.element.findElements(childElementInfos.get(i).getLocator()).get(childElementInfos.get(i).getIndex());
            }
        }

    }
}
