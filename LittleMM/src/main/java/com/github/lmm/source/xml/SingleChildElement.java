package com.github.lmm.source.xml;

import com.github.lmm.element.TempElement;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-5
 * Time: 上午10:59
 * To change this template use File | Settings | File Templates.
 */
public class SingleChildElement {
    private String name;
    private SingleChildElementAttribute singleChildElementAttribute;

    public SingleChildElement(String name, SingleChildElementAttribute singleChildElementAttribute) {
        this.name = name;
        this.singleChildElementAttribute = singleChildElementAttribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SingleChildElementAttribute getSingleChildElementAttribute() {
        return singleChildElementAttribute;
    }

    public void setSingleChildElementAttribute(SingleChildElementAttribute singleChildElementAttribute) {
        this.singleChildElementAttribute = singleChildElementAttribute;
    }

    public String getBy(){
        return this.singleChildElementAttribute.getBy();
    }

    public String getValue(){
        return this.singleChildElementAttribute.getValue();
    }

    public Integer getIndex(){
        return this.singleChildElementAttribute.getIndex();
    }

    public TempElement toTempElement(){
        return new TempElement(getBy(),getValue(),getIndex());
    }

}
