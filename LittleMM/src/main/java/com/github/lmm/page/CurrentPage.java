package com.github.lmm.page;

import com.github.lmm.element.IElement;
import com.github.lmm.source.Source;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */
public class CurrentPage implements ICurrentPage {
    private Source source;

    @Override
    public Cookie[] getAllCookies() {
        return new Cookie[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteAllCookies() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getTitle() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getUrl() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getCookieByName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IElement currentElement() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T> T load(Class<T> clazz) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public void addElement(IElement element) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addElementBySource(String id, Source source) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addElements(Source source) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IElement element(String id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IElement element(By by) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IElement element(By by, Integer index) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void assertAlert() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void assertTextNotPresent(String text) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void assertTitle(String title) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void assertContainsContent() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<String, String> getHeaders() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> getJavaScriptURL() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getHeaderByName(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getPageSource() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Integer getStatusCode() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> getAllCssURLByLinked() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isGzip() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String dealAlert() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String dealConfirm(boolean isyes) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String dealPrompt(boolean isyes, String text) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object runJavaScript(String js, Object... objects) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object runAsynJavaScript(String js, Object... objects) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ICurrentPage openNewWindow(String url) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IFrame frame(IFrame frame) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
