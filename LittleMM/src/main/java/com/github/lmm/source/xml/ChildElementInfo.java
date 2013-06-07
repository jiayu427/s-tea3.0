package com.github.lmm.source.xml;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-7
 * Time: 下午5:06
 * To change this template use File | Settings | File Templates.
 */
public class ChildElementInfo {
    private String by;
    private String value;
    private Integer index;

    public ChildElementInfo(String by, String value, Integer index) {
        this.by = by;
        this.value = value;
        this.index = index;
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
}
