package com.github.lmm.source;

import com.github.lmm.element.TempElement;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:39
 * To change this template use File | Settings | File Templates.
 */
public interface Source {
    public Map<String,TempElement> loadSource();
    /***/
    public Map<String,TempElement> sourceFilter();
}
