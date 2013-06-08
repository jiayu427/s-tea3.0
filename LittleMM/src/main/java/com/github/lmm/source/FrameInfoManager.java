package com.github.lmm.source;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-7
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
public class FrameInfoManager {
    private Stack<FrameInfo> frameInfoStack;
    public FrameInfoManager(){
        this.frameInfoStack=new Stack<FrameInfo>();
    }

    public void addFrameInfo(FrameInfo frameInfo){
        this.frameInfoStack.push(frameInfo);
    }

    public boolean isEmpty(){
        return this.frameInfoStack.empty();
    }

    public FrameInfo topFrameInfo(){
        return this.frameInfoStack.peek();
    }

    public FrameInfo topFrameInfoAndDrop(){
        return this.frameInfoStack.pop();
    }

    public FrameInfo getFrameInfo(Integer index){
        return this.frameInfoStack.get(index);
    }

    public Stack<FrameInfo> getFrameInfoStack() {
        return frameInfoStack;
    }

    public void setFrameInfoStack(Stack<FrameInfo> frameInfoStack) {
        this.frameInfoStack = frameInfoStack;
    }
}
