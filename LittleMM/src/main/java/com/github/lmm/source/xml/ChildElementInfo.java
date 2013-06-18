package com.github.lmm.source.xml;

import com.github.lmm.exception.NotExistLocatorError;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-7
 * Time: 下午5:06
 * To change this template use File | Settings | File Templates.
 */
public class ChildElementInfo {
    private Logger logger= Logger.getLogger(ChildElementInfo.class);
    private String by;
    private String value;
    private Integer index;
    private By locator;
    public ChildElementInfo(String by, String value, Integer index) {
        this.by = by;
        this.value = value;
        this.index = index;
        this.locator=getByLocator();
    }
    public ChildElementInfo(){

    }
    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    private By getByLocator(){
        String value =this.value;
        String by=this.by;
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
        }else if(by.toLowerCase().equals("tagName")){
            return By.tagName(value);
        }
        logger.error(by+"这种定位方式不存在，请选择正确的定位方式");
        throw new NotExistLocatorError(by+"这种定位方式不存在，请选择正确的定位方式");
    }

    public By getLocator() {
        return locator;
    }
}
