package com.github.lmm.page;

import com.github.lmm.annotation.Bys;
import com.github.lmm.annotation.Commit;
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
 * @author 王天庆
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
            if(field.isAnnotationPresent(Bys.class)){
                ElementInfo elementInfo=new ElementInfo();
                Bys elementLocator=field.getAnnotation(Bys.class);
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

    public SourcePage(IBrowser browser,Source source){
        super(browser);
        if(this.getClass().isAnnotationPresent(Commit.class)){
            this.pageCommit=this.getClass().getAnnotation(Commit.class).value();
        }
        this.elementManager=new ElementManager();
        loadSource(source);
        Field[] fields=this.getClass().getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(Bys.class)){
                ElementInfo elementInfo=new ElementInfo();
                Bys bys=field.getAnnotation(Bys.class);
                Locator locator=bys.locator();
                int index = bys.index();
                String commit=bys.commit();
                String value = bys.value();
                elementInfo.setIndex(index);
                elementInfo.setLocator(locator.getLocator(value));
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
