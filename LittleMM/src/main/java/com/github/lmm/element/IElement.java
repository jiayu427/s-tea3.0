package com.github.lmm.element;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 上午11:25
 * To change this template use File | Settings | File Templates.
 */
public interface IElement {
    public IElement addLocator(By by);
    public IElement addLocator(Locator locator,String value);
    public IElement addLocator(By by,Integer index);
    public IElement addLocator(Locator locator,String value,Integer index);
    public void click();
    public void doubleClick();
    public void keyDown(Keys key);
    public void keyUp(Keys key);
    public void assertAttribute(String attr,String value);
    public void assertEditable();
    public void assertNotEditable();
    public void assertSelected();
    public void assertIsExist();
    public void assertText(String text);
    public void assertValue(String value);
    public IElement childElement(By by);
    public IElement childElement(By by,Integer integer);
    public IElement childElement(Locator locator,String value);
    public IElement childElement(Locator locator,String value,Integer integer);
    public void clear();
    public void input(String text);
    public void focus();
    public String getAttribute(String attr);
    public String getText();
    public String getCssValue(String name);
    public String getId();
    public void setId(String id);
    public Point getLocation();
    public ListElements getOptions(String tagname);
    public ListElements getOptionsById(String id);
    public ListElements getOptionsByXpath(String xpath);
    public ListElements getOptionsByName(String name);
    public ListElements getOptionsByClassName(String classname);
    public ListElements getOptionsByLinkText(String linktext);
    public ListElements getOptionsByCss(String css);
    public int[] getSize();
    public String getTagName();
    public void scroll();
    public void mouseOver();
    public void submit();
    public boolean isExist();
    public boolean isDisplay();
    public boolean isEnable();
    //public boolean isEditable();
    public boolean isSelected();
    public void dragAndDrop(IElement element);
    public void dragAndDrop(Point point);
    public void leftDown();
    public void leftUp();
   // public void rightClick();
}
