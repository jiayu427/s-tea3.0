package com.github.lmm.source;

import com.github.lmm.element.TempElement;
import com.github.lmm.exception.NotExistLocatorError;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-7
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public class FrameInfo {
    private Logger logger = Logger.getLogger(FrameInfo.class);
    private String keyname;
    //private String id;
    private String by;
    private String value;
    private Integer index;
    private Integer frameIndex;
    public FrameInfo(String keyname, String by, String value, Integer index) {
        this.keyname = keyname;
        this.by = by;
        this.value = value;
        this.index = index;
    }
    public FrameInfo(){
    }

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
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

    public By getLocator(){
        return getByLocatorFromTempElement();
    }

    private By getByLocatorFromTempElement(){
        String value =getValue();
        String by=getBy();
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

    public Integer getFrameIndex() {
        return frameIndex;
    }

    public void setFrameIndex(Integer frameIndex) {
        this.frameIndex = frameIndex;
    }
}
