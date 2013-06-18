package com.github.lmm.proxy;

import org.junit.runner.notification.RunListener;
import java.util.HashSet;

import java.util.Set;

/**
 *  @author 王天庆
 *  这个类是为了增加运行监听器的注册器，只提供注册功能。
 */
public class RunnerListenerProxy {
    static Set<RunListener> eventsSet = new HashSet<RunListener>();

    public static void register(RunListener runlistener){
        eventsSet.add(runlistener);
    }

    public static Set<RunListener> dispatcher(){
        return eventsSet;
    }
}
