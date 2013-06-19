package com.github.lmm.page;

import com.github.lmm.annotation.Commit;
import com.github.lmm.annotation.ElementLocator;
import com.github.lmm.browser.IBrowser;
import com.github.lmm.core.Auto;
import com.github.lmm.element.ElementManager;
import com.github.lmm.element.Locator;
import com.github.lmm.source.ElementInfo;
import com.github.lmm.source.Source;
import com.github.lmm.element.Element;
import com.github.lmm.source.TempChainElement;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-30
 * Time: 上午10:08
 * To change this template use File | Settings | File Templates.
 */
public class SourcePage extends CurrentPage {
    private Logger logger = Logger.getLogger(SourcePage.class);
    private ElementManager elementManager;
    private Source source;
    private String pageCommit;
    public SourcePage(IBrowser browser) {
        super(browser);
        if(this.getClass().isAnnotationPresent(Commit.class)){
            this.pageCommit=this.getClass().getAnnotation(Commit.class).value();
        }
        this.elementManager=new ElementManager();
        Field[] fields=this.getClass().getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(ElementLocator.class)){
                ElementInfo elementInfo=new ElementInfo();
                ElementLocator elementLocator=field.getAnnotation(ElementLocator.class);
                String commit = elementLocator.commit();
                Locator locator=elementLocator.locator();
                String value=elementLocator.value();
                Integer index=elementLocator.index();
                elementInfo.setLocator(locator.getLocator(value));
                elementInfo.setId(commit);
                elementInfo.setIndex(index);
                TempChainElement tempChainElement=new TempChainElement(elementInfo);
                this.elementManager.addElement(tempChainElement);
                logger.info("收集了当前页面的注解属性元素"+commit);
            }
        }
       // System.out.println(this.elementManager.size());

    }

    public SourcePage(IBrowser browser,Source source){
        super(browser);
        if(this.getClass().isAnnotationPresent(Commit.class)){
            this.pageCommit=this.getClass().getAnnotation(Commit.class).value();
        }
        this.elementManager=new ElementManager();
        loadSource(source);
        Field[] fields=this.getClass().getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(ElementLocator.class)){
                ElementInfo elementInfo=new ElementInfo();
                ElementLocator elementLocator=field.getAnnotation(ElementLocator.class);
                String commit = elementLocator.commit();
                Locator locator=elementLocator.locator();
                String value=elementLocator.value();
                Integer index=elementLocator.index();
                elementInfo.setLocator(locator.getLocator(value));
                elementInfo.setId(commit);
                elementInfo.setIndex(index);
                TempChainElement tempChainElement=new TempChainElement(elementInfo);
                this.elementManager.addElement(tempChainElement);
                logger.info("收集了当前页面的注解属性元素"+commit);
            }
        }
    }

    public SourcePage(){
        this(Auto.browser());
    }

    private void loadSource(Source source){
        this.source=source;
        this.elementManager.loadPageSource(source,this);
    }

    public Element element(String id){
        return new Element(getBrowser(),this.elementManager.getTempElement(id));
    }

    public ElementManager getElementManager() {
        return elementManager;
    }

    public void setElementManager(ElementManager elementManager) {
        this.elementManager = elementManager;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getPageCommit() {
        return pageCommit;
    }

    public void setPageCommit(String pageCommit) {
        this.pageCommit = pageCommit;
    }


}
