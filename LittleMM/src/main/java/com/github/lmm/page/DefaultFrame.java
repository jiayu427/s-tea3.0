package com.github.lmm.page;

import org.openqa.selenium.By;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-13
 * Time: 下午1:30
 * To change this template use File | Settings | File Templates.
 */
public class DefaultFrame extends Frame {
    public DefaultFrame(ICurrentPage page) {
        super(page);
    }

    public DefaultFrame(Frame frame,Integer frameIndex){
        super(frame,frameIndex);
    }

    public DefaultFrame(Frame frame,String nameOrId){
        super(frame,nameOrId);
    }

    public DefaultFrame(Frame frame,By by){
        super(frame, by);
    }

    public DefaultFrame(Frame frame,By by,Integer index){
        super(frame,by,index);
    }
    public DefaultFrame(ICurrentPage page,Integer frameIndex){
        super(page,frameIndex);
    }

    public DefaultFrame(ICurrentPage page,String nameOrId){
        super(page,nameOrId);
    }

    public DefaultFrame(ICurrentPage page,By by){
        super(page, by);
    }

    public DefaultFrame(ICurrentPage page,By by,Integer index){
        super(page,by,index);
    }
}
