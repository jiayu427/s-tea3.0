package com.github.lmm.source.xml;

import com.github.lmm.element.TempElement;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-7
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public class FrameInfo {
    private String keyname;
    //private String id;
    private String by;
    private String value;
    private Integer index;

    public FrameInfo(String keyname, String by, String value, Integer index) {
        this.keyname = keyname;
        this.by = by;
        this.value = value;
        this.index = index;
    }
    public FrameInfo(){}

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

    public TempElement ToTempElement(){
        return new TempElement(getKeyname(),getBy(),getValue(),getIndex());
    }
}
