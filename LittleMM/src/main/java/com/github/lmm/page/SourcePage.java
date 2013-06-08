package com.github.lmm.page;

import com.github.lmm.annotation.FrameLocator;
import com.github.lmm.browser.BaseBrowser;
import com.github.lmm.browser.Browser;
import com.github.lmm.browser.IBrowser;
import com.github.lmm.element.ElementManager;
import com.github.lmm.element.TempElement;
import com.github.lmm.source.Source;
import com.github.lmm.element.Element;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-30
 * Time: 上午10:08
 * To change this template use File | Settings | File Templates.
 */
public class SourcePage extends CurrentPage {
    private ElementManager elementManager;
    private Source source;
    private String pageCommit;
    public SourcePage(IBrowser browser,String pageCommit) {
        super(browser);
        this.pageCommit=pageCommit;
        this.elementManager=new ElementManager();
    }

    public SourcePage(IBrowser browser,String pageCommit,Source source){
        super(browser);
        this.elementManager=new ElementManager();
        loadSource(source);
    }

//    public SourcePage(IBrowser browser){
//        super(browser);
//    }

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

    public TempElement getFrameLocator(String commit){
        return null;
    }

    public void frameLocator(String commit){
        Field[] fields=this.getClass().getDeclaredFields();
        for(Field field:fields){
            if(field.getType()==Frame.class){
                try {
                    if(((Frame)field.get(this)).getCommit().equals(commit)){
                        System.out.println("Yes");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

        }
    }

//    Frame newFrame = new Frame(this.getBrowser(),"good",this) {
//
//    };
//
//    public static void main(String[] args){
//        new SourcePage(new BaseBrowser(Browser.FIREFOX),"xiaohua").frameLocator("good");
//    }
}
