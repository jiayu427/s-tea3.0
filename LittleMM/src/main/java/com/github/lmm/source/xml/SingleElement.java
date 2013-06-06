package com.github.lmm.source.xml;

import com.github.lmm.element.TempElement;

import java.util.ArrayList;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-5
 * Time: 上午9:55
 * To change this template use File | Settings | File Templates.
 */
public class SingleElement {
    private SingleElementAttribute attr;
    private String elementName;
    private List<SingleChildElement> singleChildElementList;
    public SingleElement(String elementName,SingleElementAttribute attr){
        this.elementName=elementName;
        this.attr=attr;
        this.singleChildElementList=new ArrayList<SingleChildElement>();
    }

    public SingleElement(String elementName,SingleElementAttribute attr,List<SingleChildElement> singleChildElements){
        this.singleChildElementList=singleChildElements;
        this.elementName=elementName;
        this.attr=attr;
    }

    public String getId(){
        return this.attr.getId();
    }

    public String getValue(){
        return this.attr.getValue();
    }

    public String getBy(){
        return this.attr.getBy();
    }

    public Integer getIndex(){
        return this.attr.getIndex();
    }

    public TempElement toTempElement(){
        TempElement tempElement= new TempElement(getId(),getBy(),getValue(),getIndex());
        if(singleChildElementList.size()!=0){
            tempElement.setChildTempElement(childElementToTempElementList());
        }
        return tempElement;
    }

    public SingleElementAttribute getAttr() {
        return attr;
    }

    public void setAttr(SingleElementAttribute attr) {
        this.attr = attr;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }


    private List<TempElement> childElementToTempElementList(){
        List<TempElement> tempElements=new ArrayList<TempElement>();
        for(SingleChildElement singleChildElement : this.singleChildElementList){
            TempElement tempElement=singleChildElement.toTempElement();
            tempElements.add(tempElement);
        }
        return tempElements;
    }
}
