package com.github.lmm.page;

import com.github.lmm.browser.IBrowser;
import com.github.lmm.element.ElementManager;
import org.openqa.selenium.WebDriver;
import com.github.lmm.element.Element;
/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午2:09
 * To change this template use File | Settings | File Templates.
 */
public abstract class Frame extends SourcePage{
    private WebDriver currentFrame;
    private String commit;
    private SourcePage page;
    private ElementManager elementManager;
    public Frame(IBrowser browser,String commit,SourcePage page) {
        super(browser,commit);
        this.page=page;
        this.elementManager=page.getElementManager();
    }
    public Frame(IBrowser browser,SourcePage page){
        super(browser,"");
        this.page=page;
    }

    public ElementManager getElementManager(){
        return this.elementManager;
    }

    public WebDriver getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(WebDriver currentFrame) {
        this.currentFrame = currentFrame;
    }

    public SourcePage getPage() {
        return page;
    }

    public void setPage(SourcePage page) {
        this.page = page;
    }

    public void setElementManager(ElementManager elementManager) {
        this.elementManager = elementManager;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }
}
