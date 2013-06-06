package com.github.lmm.element;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-30
 * Time: 下午1:24
 * To change this template use File | Settings | File Templates.
 */
public class TempElement {
    private String id;
    private String value;
    private Integer index;
    private String by;
    private List<TempElement> childTempElement;
    private TempElementLocator locator;

    public List<TempElement> getChildTempElement() {
        return childTempElement;
    }

    public void setChildTempElement(List<TempElement> childTempElement) {
        this.childTempElement = childTempElement;
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public TempElement(String id, String by, String value, Integer index) {
        this.id = id;
        this.value = value;
        this.index = index;
        this.by = by;
        this.locator=new TempElementLocator(this);
        this.childTempElement=new ArrayList<TempElement>();
    }

    public TempElementLocator getLocator() {
        return locator;
    }

    public void setLocator(TempElementLocator locator) {
        this.locator = locator;
    }

    public TempElement(String by,String value,Integer index){
        this.by=by;
        this.value=value;
        this.index=index;
        this.locator=new TempElementLocator(this);
        this.childTempElement=new ArrayList<TempElement>();
    }
}
