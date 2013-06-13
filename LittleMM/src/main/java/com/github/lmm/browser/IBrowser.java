package com.github.lmm.browser;

import com.github.lmm.element.ElementManager;
import com.github.lmm.page.ICurrentPage;
import com.github.lmm.window.WindowSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import java.util.Set;


/**
 * Created with IntelliJ IDEA.
 * User: celeskyking
 * Date: 13-5-28
 * Time: 下午9:38
 * To change this template use File | Settings | File Templates.
 */
public interface IBrowser {

    public ICurrentPage open(String url);

    public void maxWindow();

    public void closeAllWindows();

    public void back();

    public void refresh();

    public void forward();

    public Set<String> getWindows();

    public ICurrentPage selectDefaultWindow();

    public ICurrentPage selectFrame(By by);

    public ICurrentPage selectFrame(int index);

    public ICurrentPage selectFrame(By by,int index);

    public ICurrentPage selectLastOpenedPage();

    public ICurrentPage selectWindowByTitle(String title);

    public ICurrentPage selectWindowByUrl(String url);

    //public ICurrentPage selectWindowContainsTitle(String title);

    public ICurrentPage selectWindowContainsUrl(String url);

    public ICurrentPage getCurrentPage();

    public WebDriver getCurrentBrowserDriver();

    public Object runJavaScript(String js,Object... objects);

    public Object runAsynJavaScript(String js,Object... objects);

    public void takeScreetShot(String path);

    public boolean isClosed();

    public void setClosed(boolean isclose);

    public ElementManager getElementManager();

    public void setElementManager(ElementManager elementManager);

    public WindowSource getWindowSource();
}
