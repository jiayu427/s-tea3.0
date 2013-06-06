package com.github.lmm.source.xml;

import com.github.lmm.element.ElementManager;
import com.github.lmm.element.TempElement;

import java.util.Set;
/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-5
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */
public class BlockElement {
    private String elementName;
    private Set<SingleElement> singleElementSet;
    private BlockElementAttribute blockElementAttribute;

    public BlockElement(String elementName, Set<SingleElement> singleElementList, BlockElementAttribute blockElementAttribute) {
        this.elementName = elementName;
        this.singleElementSet = singleElementList;
        this.blockElementAttribute = blockElementAttribute;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public Set<SingleElement> getSingleElementSet() {
        return this.singleElementSet;
    }

    public void setSingleElementList(Set<SingleElement> singleElementList) {
        this.singleElementSet = singleElementList;
    }

    public BlockElementAttribute getBlockElementAttribute() {
        return blockElementAttribute;
    }

    public void setBlockElementAttribute(BlockElementAttribute blockElementAttribute) {
        this.blockElementAttribute = blockElementAttribute;
    }

    public String getKeyName(){
        return this.blockElementAttribute.getKeyName();
    }

    public String getBelongTo(){
        return this.blockElementAttribute.getBelongTo();
    }

    public ElementManager toElementManager(){
        ElementManager elementManager=new ElementManager();
        for(SingleElement singleElement:getSingleElementSet()){
            TempElement tempElement=singleElement.toTempElement();
            elementManager.addElement(tempElement);
        }
        return elementManager;
    }



}
