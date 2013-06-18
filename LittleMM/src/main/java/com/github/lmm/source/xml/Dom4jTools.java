package com.github.lmm.source.xml;

import com.github.lmm.element.ElementManager;
import com.github.lmm.exception.NotXMLFileException;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import java.io.File;
import org.dom4j.Element;
import com.github.lmm.source.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-5
 * Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 */
public class Dom4jTools {
    private Set<String> pagecommit;
    private Document document;
    private Element root;
    private Logger logger = Logger.getLogger(Dom4jTools.class);
    public Dom4jTools(String file){
        this.pagecommit=new HashSet<String>();
        if(!file.endsWith("xml")){
            logger.error("不是xml文件，请检查文件的后缀名是否是xml");
            throw new NotXMLFileException("不是xml文件，请检查文件的后缀名是否是xml");
        }
        SAXReader sr=new SAXReader();
        try {
            document=sr.read(new File(file));
            document.setXMLEncoding("UTF-8");
            this.root=document.getRootElement();
            //logger.info("XML的资源的根元素是"+this.root.getName());
            List<Element> pages = this.root.elements("page");
            for(Element e:pages){
                pagecommit.add(e.attributeValue("commit"));
                logger.info("收集了页面["+e.attributeValue("commit")+"]的元素资源");
            }
        } catch (DocumentException e) {
            logger.error("程序加载文件的时候出现了错误，请查看文件是否被损坏！");
            throw new RuntimeException("程序加载文件的时候出现了错误，请查看文件是否被损坏！");
        }
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Element getRoot(){
        return this.root;
    }

    public List<Element> getPageElement(){
        return root.elements("page");
    }

    public List<Element> getWebElement(){
        List<Element> elist=root.elements("element");
        if(getPageElement().size()!=0){
            for(Element page:getPageElement()){
                elist.addAll(page.elements("element"));
            }
        }
        return elist;
    }

    public List<Element> getChildElments(Element e){
        List<Element> clist = new ArrayList<Element>();
        Element element=e;
        while(element.element("childElement")!=null){
            clist.add(e.element("childElement"));
            element=e.element("childElement");
        }
        return clist;
    }

    public List<Element> getFrameElement(Element pageElement){
        return pageElement.elements("frame");
    }

    public boolean isHaveChild(Element e){
        if(e.element("childElement")!=null){
            return true;
        }else{
            return false;
        }
    }

    public ElementInfo elementToElementInfo(Element selement){
        String by=selement.attributeValue("by");
        String value=selement.attributeValue("value");
        String id = selement.attributeValue("id");
        Integer index =0;
        if(selement.attributeValue("index")!=null){
            index = Integer.parseInt(selement.attributeValue("index"));
        }
        ElementInfo elementInfo=new ElementInfo(id,value,by,index);
        if(isHaveChild(selement)){
            List<Element> clist = getChildElments(selement);
            List<ChildElementInfo> celist = new ArrayList<ChildElementInfo>();
            for(Element ce:clist){
                String cvalue = ce.attributeValue("value");
                String cby = ce.attributeValue("by");
                Integer cindex =0;
                if(ce.attributeValue("index")!=null){
                    index = Integer.parseInt(ce.attributeValue("index"));
                }
                ChildElementInfo childElementInfo=new ChildElementInfo(cby,cvalue,cindex);
                celist.add(childElementInfo);
            }
            elementInfo.setChildElementInfo(celist);
            return elementInfo;
        }
        return elementInfo;
    }

    public List<Element> getAllElements(){
        List<Element> elementList=this.document.selectNodes("//s-tea:element");
        return elementList;
    }

    private XMLChainElement ToChainElement(Element e){
        return new XMLChainElement(this,e.attributeValue("id"));
    }

    public List<XMLChainElement> getAllChainElements(){
        List<XMLChainElement> chainElements=new ArrayList<XMLChainElement>();
        for(Element e: getAllElements()){
            chainElements.add(ToChainElement(e));
        }
        return chainElements;
    }
    /**这个方法会按照这个xml文件中所有的元素自动的分配归属
     * 如果属于page则分配到page的Elementmanager中
     * 如果是Frame则分配到Frame的ElementManager中
     * 如果是Browser的元素，则分配到指定的名为browser的ElementManager中
     * */
    public Map<String,ElementManager> getPageElementManager(){
        String browser="browser";
        Map<String,ElementManager> managerMap=new HashMap<String,ElementManager>();
        ElementManager browserManager=new ElementManager();
        List<XMLChainElement> chainElements=getAllChainElements();
        if(getPageCommit().size()!=0){
            for(String pc:getPageCommit()){
                ElementManager elementManager=new ElementManager();
                Iterator<XMLChainElement> iter = chainElements.iterator();
                while(iter.hasNext()){
                    XMLChainElement xc = iter.next();
                    if(xc.getPageInfo().getKeyname().equals(pc)){
                        elementManager.addElement(xc);
                        logger.info("收集了Page["+pc+"]元素->"+xc.getElementInfo().getId());
                        iter.remove();
                    }
                    if(xc.getPageInfo().getKeyname()==null){
                        browserManager.addElement(xc);
                        logger.info("收集了Browser["+pc+"]元素->"+xc.getElementInfo().getId());
                        iter.remove();
                    }
                }
                managerMap.put(pc,elementManager);
            }
            managerMap.put(browser,browserManager);
        }else{
            for(XMLChainElement xc:getAllChainElements()){
                browserManager.addElement(xc);
            }
            managerMap.put(browser,browserManager);
        }
        return managerMap;
    }

    private Set<String> getPageCommit(){
        return this.pagecommit;
    }

}
