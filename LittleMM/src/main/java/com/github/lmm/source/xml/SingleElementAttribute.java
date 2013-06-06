package com.github.lmm.source.xml;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-5
 * Time: 上午10:04
 * To change this template use File | Settings | File Templates.
 */
public class SingleElementAttribute {
    private String id;
    private String by;
    private String value;
    private Integer index;

    public SingleElementAttribute(String id, String by, String value, Integer index) {
        this.id = id;
        this.by = by;
        this.value = value;
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
