package com.github.lmm.source;

import com.github.lmm.element.TempElement;
import com.github.lmm.exception.NotExistLocatorError;
import com.github.lmm.source.xml.ChildElementInfo;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-7
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
public class ElementInfo {
    private Logger logger = Logger.getLogger(ElementInfo.class);
    private String id;
    private String value;
    private String by;
    private Integer index;
    private List<ChildElementInfo> clist;
    private By locator;
    public ElementInfo(String id, String value, String by, Integer index) {
        this.id = id;
        this.value = value;
        this.by = by;
        this.index = index;
        this.clist=new ArrayList<ChildElementInfo>();
        this.locator=getByLocatorFromTempElement();
    }
    public ElementInfo(){
        this.clist=new ArrayList<ChildElementInfo>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public TempElement ToTempElement(){
        return null;
    }

    public List<ChildElementInfo> getChileElementInfo() {
        return clist;
    }

    public void setChildElementInfo(List<ChildElementInfo> clist) {
        this.clist = clist;
    }

    private By getByLocatorFromTempElement(){
        String value =this.getValue();
        String by=this.getBy();
        if(by.toLowerCase().equals("id")){
            return By.id(value);
        }else if(by.toLowerCase().equals("name")){
            return By.name(value);
        }else if(by.toLowerCase().equals("xpath")){
            return By.xpath(value);
        }else if(by.toLowerCase().equals("classname")){
            return By.className(value);
        }else if(by.toLowerCase().equals("linktext")){
            return  By.linkText(value);
        }else if(by.toLowerCase().equals("partiallinktext")){
            return By.partialLinkText(value);
        }else if(by.toLowerCase().equals("css")){
            return By.cssSelector(value);
        }else if(by.toLowerCase().equals("tagname")){
            return By.tagName(value);
        }
        logger.error(by+"这种定位方式不存在，请选择正确的定位方式");
        throw new NotExistLocatorError(by+"这种定位方式不存在，请选择正确的定位方式");
    }

    public By getLocator() {
        return locator;
    }

    public void setLocator(By locator) {
        this.locator = locator;
    }
}
