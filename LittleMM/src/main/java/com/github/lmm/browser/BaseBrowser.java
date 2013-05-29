package com.github.lmm.browser;

import com.github.lmm.page.ICurrentPage;

import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */
public class BaseBrowser implements IBrowser {

    public LinkedHashMap<String,String> collection;
    @Override
    public ICurrentPage open() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ICurrentPage open(String url) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void closeAllWindows() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void back() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void refresh() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void forward() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<String> getWindows() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ICurrentPage selectDefaultWindow() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ICurrentPage selectLastOpenedPage() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ICurrentPage selectWindowByTitle(String title) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ICurrentPage selectWindowByUrl(String url) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ICurrentPage selectWindowContainsTitle(String title) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ICurrentPage selectWindowContainsUrl(String url) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    protected void updateWindow(){

    }

}
