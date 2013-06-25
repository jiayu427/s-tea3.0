package com.github.lmm.runner;

import org.databene.benerator.DefaultPlatformDescriptor;
/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-25
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public class PlatformDescriptor extends DefaultPlatformDescriptor{
    private static boolean formattedByDefault = false;
    public static boolean isFormattedByDefault() {
        return formattedByDefault;
    }
    public static void setFormattedByDefault(boolean formattedByDefault) {
        PlatformDescriptor.formattedByDefault = formattedByDefault;
    }
    public PlatformDescriptor() {
        super(PlatformDescriptor.class.getName());
    }
}
