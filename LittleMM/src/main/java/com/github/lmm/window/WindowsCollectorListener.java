package com.github.lmm.window;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-30
 * Time: 下午5:46
 * To change this template use File | Settings | File Templates.
 */
public class WindowsCollectorListener implements WindowsListener {
    private Logger logger = Logger.getLogger(WindowsCollectorListener.class);
    @Override
    public void windowsCollecter(WindowsCollecter windowsCollecter) {
        windowsCollecter.updateWindows();

    }
}
