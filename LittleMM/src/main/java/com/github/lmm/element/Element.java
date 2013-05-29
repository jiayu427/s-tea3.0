package com.github.lmm.element;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:29
 * To change this template use File | Settings | File Templates.
 */
public class Element implements IElement {
    @Override
    public void click() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doubleClick() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyDown(Keys key) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyUp(Keys key) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void assertAttribute(String attr) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void assertEditable() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void assertNotEditable() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void assertSelected() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void assertIsExist() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void assertText(String text) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void assertValue(String value) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IElement childElement(By by) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IElement childElement(By by, Integer integer) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IElement childElement(Locator locator, String value) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IElement childElement(Locator locator, String value, Integer integer) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clear() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void input() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void focus() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getAttribute(String attr) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getText() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getCssValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setId() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Point getLocation() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListElements getOptions(String tagname) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListElements getOptionsById(String id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListElements getOptionsByXpath(String xpath) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListElements getOptionsByName(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListElements getOptionsByClassName(String classname) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListElements getOptionsByLinkText(String linktext) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListElements getOptionsByCss(String css) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int[] getSize() {
        return new int[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getTagName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void scroll() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseOver() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void submit() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isExist() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isDisplay() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isEnable() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isSelected() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dragAndDrop(IElement element) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dragAndDrop(Point point) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
