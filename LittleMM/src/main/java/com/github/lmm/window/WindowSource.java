package com.github.lmm.window;

import com.github.lmm.browser.IBrowser;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-30
 * Time: 下午3:45
 * To change this template use File | Settings | File Templates.
 */
public class WindowSource {
    private Vector repository =new Vector();
    private WindowsListener windowsListener;
    private WindowsCollecter windowsCollecter;
    private IBrowser browser;
    public void addWindowsListener(WindowsListener windowsListener){
        this.repository.add(windowsListener);
    }

    public void notifyWindowsCollecter(WindowsCollecter windowsCollecter){
        Enumeration enumeration=repository.elements();
        while(enumeration.hasMoreElements())
        {
            windowsListener = (WindowsListener)enumeration.nextElement();
            windowsListener.windowsCollecter(windowsCollecter);
        }
    }

    public void removeWindowListener(WindowsListener windowsListener){
        this.repository.remove(windowsListener);
    }

    public void WindowsHaveChanged(){
        boolean bool=false;
        if(this.windowsCollecter.windowNums!=browser.getWindows().size()){
            bool=true;
        }
        if(bool){
            notifyWindowsCollecter(this.windowsCollecter);
        }
    }

    public WindowSource(IBrowser browser){
        this.browser=browser;
        this.windowsCollecter=new WindowsCollecter(this,browser);
    }


}
