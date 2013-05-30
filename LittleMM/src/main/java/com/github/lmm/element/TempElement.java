package com.github.lmm.element;

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

    public TempElement(String id, String value, Integer index, String by, List<TempElement> childTempElement) {
        this.id = id;
        this.value = value;
        this.index = index;
        this.by = by;
        this.childTempElement = childTempElement;
    }
}
