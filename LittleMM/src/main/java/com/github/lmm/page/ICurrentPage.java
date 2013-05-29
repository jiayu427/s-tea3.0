package com.github.lmm.page;

import com.github.lmm.element.IElement;
import com.github.lmm.source.Source;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 上午10:58
 * To change this template use File | Settings | File Templates.
 */
public interface ICurrentPage extends IPage {

    public void addElement(IElement element);

    public void addElementBySource(String id,Source source);

    public void addElements(Source source);

    public IElement element(String id);

    public IElement element(By by);

    public IElement element(By by,Integer index);

    public void assertAlert();

    public void assertTextNotPresent(String text) ;

    public void assertTitle(String title);

    public void assertContainsContent();

    public Map<String, String> getHeaders() ;

    public List<String> getJavaScriptURL();

    public String getHeaderByName(String name);

    public String getPageSource();

    public Integer getStatusCode();

    public List<String> getAllCssURLByLinked();

    public boolean isGzip();

    public String dealAlert();

    public String dealConfirm(boolean isyes);

    public String dealPrompt(boolean isyes,String text);

    public Object runJavaScript(String js,Object... objects);

    public Object runAsynJavaScript(String js,Object... objects);

    //public Set<String> getWindows();

    //public ICurrentPage selectLateOpenedWindow();

    public ICurrentPage openNewWindow(String url);

    public IFrame frame(IFrame frame);


}
