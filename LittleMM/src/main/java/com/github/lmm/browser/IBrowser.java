package com.github.lmm.browser;

import com.github.lmm.page.ICurrentPage;

import java.util.Set;


/**
 * Created with IntelliJ IDEA.
 * User: celeskyking
 * Date: 13-5-28
 * Time: 下午9:38
 * To change this template use File | Settings | File Templates.
 */
public interface IBrowser {
    public ICurrentPage open();

    public ICurrentPage open(String url);

    public void closeAllWindows();

    public void back();

    public void refresh();

    public void forward();

    public Set<String> getWindows();

    public ICurrentPage selectDefaultWindow();

    public ICurrentPage selectLastOpenedPage();

    public ICurrentPage selectWindowByTitle(String title);

    public ICurrentPage selectWindowByUrl(String url);

    public ICurrentPage selectWindowContainsTitle(String title);

    public ICurrentPage selectWindowContainsUrl(String url);


}
