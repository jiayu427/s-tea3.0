package com.github.lmm.element;

import org.openqa.selenium.By;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午2:29
 * To change this template use File | Settings | File Templates.
 */
public enum Locator {
    ID(){
         public By getLocator(String value){
             return By.id(value);
         }
         public By value(String value){
            return getLocator(value);
        }
    },
    XPATH(){
        public By getLocator(String value){
            return By.xpath(value);
        }
        public By value(String value){
            return getLocator(value);
        }
    },
    NAME(){
        public By getLocator(String value){
            return By.name(value);
        }
        public By value(String value){
            return getLocator(value);
        }
    },
    CLASSNAME(){
        public By getLocator(String value){
            return  By.className(value);
        }
        public By value(String value){
            return getLocator(value);
        }
    },
    LINKTEXT(){
        public By getLocator(String value){
            return By.linkText(value);
        }
        public By value(String value){
            return getLocator(value);
        }
    },
    PARTIALINKTEXT(){
        public By getLocator(String value){
            return By.partialLinkText(value);
        }
        public By value(String value){
            return getLocator(value);
        }
    },
    CSS(){
        public By getLocator(String value){
            return By.cssSelector(value);
        }
        public By value(){
            return null;
        }
    },
    TAGNAME(){
        public By getLocator(String value){
            return By.tagName(value);
        }
        public By value(){
            return null;
        }
    };

    public By getLocator(String value){
        return null;
    }

    public By value(String value){
        return null;
    }
}
