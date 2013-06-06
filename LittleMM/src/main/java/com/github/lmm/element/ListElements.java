package com.github.lmm.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午2:42
 * To change this template use File | Settings | File Templates.
 */
public class ListElements<T extends IElement> {
    private List<T> slist;// = new ArrayList<Element>();
    public ListElements(List<T> elist){
        this.slist=elist;
    }

    public T get(int index){
        return slist.get(index);
    }

    public T getByRandom(){
        Random r = new Random();
        int max = this.slist.size();
        int random = r.nextInt(max);
        return get(random);
    }

    public Integer getSize(){
        return this.slist.size();
    }







}
