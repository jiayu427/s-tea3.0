package com.github.lmm.page;

import com.github.lmm.browser.IBrowser;
import com.github.lmm.element.ElementManager;
import com.github.lmm.source.Source;
import org.openqa.selenium.WebDriver;

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
        this.elementManager.loadSource(source);
        this.elementManager=new ElementManager();
    }

    protected void loadSource(Source source){
        this.source=source;
        this.elementManager.loadPageSource(source,this);
    }
}
