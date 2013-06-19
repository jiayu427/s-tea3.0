package com.github.lmm.page;

import com.github.lmm.browser.IBrowser;
import com.github.lmm.element.*;
import com.github.lmm.source.Source;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 上午10:58
 * To change this template use File | Settings | File Templates.
 */
public interface ICurrentPage extends IPage {

    public IBrowser getBrowser();

    public void open(String url);

    public void addElement(TempElement element);

    public void addElements(Source source);

    public IElement element();

    public IElement element(String id);

    public IElement element(By by);

    public IElement element(By by,Integer index);

    public Table table();

    public Table table(String id);

    public Table table(By by);

    public Table table(By by,Integer index);

    public Button button();

    public Button button(String id);

    public Button button(By by);

    public Button button(By by,Integer index);

    public CheckBox checkBox();

    public CheckBox checkBox(String id);

    public CheckBox checkBox(By by);

    public CheckBox checkBox(By by,Integer index);

    public Form form();

    public Form form(String id);

    public Form form(By by);

    public Form form(By by,Integer index);

    public Image image();

    public Image image(String id);

    public Image image(By by);

    public Image image(By by,Integer index);

    public Link link();

    public Link link(String id);

    public Link link(By by);

    public Link link(By by,Integer index);

    public RadioButton radioButton();

    public RadioButton radioButton(String id);

    public RadioButton radioButton(By by);

    public RadioButton radioButton(By by,Integer index);

    public RichTextField richTextField();

    public RichTextField richTextField(String id);

    public RichTextField richTextField(By by);

    public RichTextField richTextField(By by,Integer index);

    public Select select();

    public Select select(String id);

    public Select select(By by);

    public Select select(By by,Integer index);

    public TextField textField();

    public TextField textField(String id);

    public TextField textField(By by);

    public TextField textField(By by,Integer index);

    public <T>T selfConfigElement(T clazz);

    public void assertAlert();

    public void assertTextNotPresent(String text) ;

    public void assertTitle(String title);

    public void assertTextPresent(String text);

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

    public WebDriver getCurrentWindow();

    public void setBrowser(IBrowser browser);

    public <T> T frame(Class<T>clazz);

    public ElementManager getElementManager();

    public Frame frame(int index);

    public Frame frame(String nameOrId);

    public Frame frame(By by);

    public Frame frame(By by,int index);

}
