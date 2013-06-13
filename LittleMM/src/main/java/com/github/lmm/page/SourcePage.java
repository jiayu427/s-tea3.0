package com.github.lmm.page;

import com.github.lmm.annotation.Commit;
import com.github.lmm.browser.IBrowser;
import com.github.lmm.element.ElementManager;
import com.github.lmm.element.TempElement;
import com.github.lmm.source.Source;
import com.github.lmm.element.Element;
import java.lang.reflect.Field;

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
    public SourcePage(IBrowser browser) {
        super(browser);
        this.pageCommit=this.getClass().getAnnotation(Commit.class).value();
        this.elementManager=new ElementManager();
    }

    public SourcePage(IBrowser browser,Source source){
        super(browser);
        this.elementManager=new ElementManager();
        loadSource(source);
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
