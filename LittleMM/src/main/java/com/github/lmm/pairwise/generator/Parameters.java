package com.github.lmm.pairwise.generator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-27
 * Time: 上午9:52
 * To change this template use File | Settings | File Templates.
 */
public interface Parameters {

    public void generate();

    public List<LineParameters> getParameters();
}
