package com.github.lmm.source.xml;

import java.util.Stack;
import com.github.lmm.exception.SameIDSourceError;
import org.dom4j.Element;
import java.util.List;
import com.github.lmm.source.*;
/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-7
 * Time: 下午3:26
 * To change this template use File | Settings | File Templates.
 */
public class XMLChainElement extends TempChainElement {
    private boolean isFrameElement=false;
    private PageInfo pageInfo;
    private ElementInfo elementInfo;
    public Dom4jTools dom4jTools;
    public XMLChainElement(Dom4jTools dom4jTools,String id){
        this.dom4jTools=dom4jTools;
        stack=new Stack<Element>();
        this.pageInfo=new PageInfo();
        this.elementInfo=new ElementInfo();
        //this.frameInfoManager=new FrameInfoManager();
        init(id);
    }
    private Stack<Element> stack;
    private Stack<Element> init(String id){
        List<Element> elist = dom4jTools.getDocument().selectNodes("//element[@id='"+id+"']");
        if(elist.size()>1){
            throw new SameIDSourceError("资源中有重名的ID，请检查资源定义或者规范资源定义规则");
        }
        Element e= elist.get(0);
        stack.add(e);
        while(!e.getParent().isRootElement()){
            if(e.getName().equals("element")){
                this.elementInfo=this.dom4jTools.elementToElementInfo(e);
            }
//            if(e.getName().equals("frame")){
//                this.isFrameElement=true;
//                FrameInfo frameInfo = new FrameInfo();
//                String keyname=e.attributeValue("commit");
//                String evalue = e.attributeValue("value");
//                String eby = e.attributeValue("by");
//                if(e.attributeValue("frameIndex")!=null){
//                    Integer frameindex=Integer.parseInt(e.attributeValue("frameIndex"));
//                    frameInfo.setFrameIndex(frameindex);
//                }
//                Integer eindex =0;
//                if(e.attributeValue("index")!=null){
//                    eindex=Integer.parseInt(e.attributeValue("index"));
//                }
//                frameInfo.setIndex(eindex);
//                frameInfo.setValue(evalue);
//                frameInfo.setBy(eby);
//                frameInfo.setKeyname("classname");
//                frameInfoManager.addFrameInfo(frameInfo);
//            }
            if(e.getName().equals("page")){
                String keyname=e.attributeValue("commit");
                pageInfo.setKeyname(keyname);
            }
            e=e.getParent();
            stack.add(e);
        }
        return stack;
    }

    public boolean isFrameElement() {
        return isFrameElement;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public ElementInfo getElementInfo() {
        return elementInfo;
    }

    public void setElementInfo(ElementInfo elementInfo) {
        this.elementInfo = elementInfo;
    }

    public Dom4jTools getDom4jTools() {
        return dom4jTools;
    }

    public void setDom4jTools(Dom4jTools dom4jTools) {
        this.dom4jTools = dom4jTools;
    }

    public Stack<Element> getStack() {
        return stack;
    }

    public void setStack(Stack<Element> stack) {
        this.stack = stack;
    }
}
