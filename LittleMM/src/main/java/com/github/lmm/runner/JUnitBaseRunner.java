package com.github.lmm.runner;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
class JUnitBaseRunner extends BlockJUnit4ClassRunner{
    public JUnitBaseRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }
}
