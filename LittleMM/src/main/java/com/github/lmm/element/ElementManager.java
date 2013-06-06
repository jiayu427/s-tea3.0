package com.github.lmm.element;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.github.lmm.browser.IBrowser;
import com.github.lmm.page.ICurrentPage;
import com.github.lmm.page.SourcePage;
import com.github.lmm.source.Source;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-30
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */
public class ElementManager{
    //private IBrowser browser;
    /**key存的是这个元素的id值，所有的id推荐都要有id值，这样方便元素的唯一化*/
    public Map<String, TempElement> elementMap;
    public ElementManager(){
        //this.browser=browser;
        elementMap=new HashMap<String, TempElement>();
    }

    public void addElement(TempElement element){
       elementMap.put(element.getId(),element);
    }

    public Map<String,TempElement> getAllElementsMap(){
        return elementMap;
    }

    public TempElement[] getAllElements(){
        Set<Map.Entry<String,TempElement>> entrySet = elementMap.entrySet();
        TempElement[] elements=new TempElement[entrySet.size()];
        int i=0;
        for(Map.Entry<String,TempElement> entry:entrySet){
            elements[i]= entry.getValue();
            i++;
        }
        return elements;
    }

    public ElementManager removeElement(TempElement element){
        this.elementMap.remove(element);
        return this;
    }

    public void loadSource(Source source){
        this.elementMap.putAll(source.loadSource());
    }

    public TempElement getTempElement(String id){
        return this.elementMap.get(id);
    }

    public void loadPageSource(Source source,SourcePage page){
        this.elementMap.putAll(source.loadPageSource(page));
    }

}
