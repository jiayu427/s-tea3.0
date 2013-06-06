package com.github.lmm.element;

import com.github.lmm.exception.NotExistLocatorError;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-31
 * Time: 上午11:32
 * To change this template use File | Settings | File Templates.
 */
public class TempElementLocator {
    private Logger logger = Logger.getLogger(TempElementLocator.class);
    private By by;
    private Integer index;
    private TempElement tempElement;
    public TempElementLocator(TempElement tempElement){
        this.tempElement=tempElement;
        this.by=getByLocatorFromTempElement(tempElement);
        this.index=tempElement.getIndex();
    }

    public By getBy() {
        return by;
    }

    public void setBy(By by) {
        this.by = by;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public TempElement getTempElement() {
        return tempElement;
    }

    public void setTempElement(TempElement tempElement) {
        this.tempElement = tempElement;
    }

    private By getByLocatorFromTempElement(TempElement element){
        String value =element.getValue();
        String by=element.getBy();
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



}
