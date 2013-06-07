package com.github.lmm.source.xml;

import com.github.lmm.element.TempElement;

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
    private String id;
    private String value;
    private String by;
    private Integer index;
    private List<ChildElementInfo> clist;
    public ElementInfo(String id, String value, String by, Integer index) {
        this.id = id;
        this.value = value;
        this.by = by;
        this.index = index;
        this.clist=new ArrayList<ChildElementInfo>();
    }
    public ElementInfo(){

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
}
