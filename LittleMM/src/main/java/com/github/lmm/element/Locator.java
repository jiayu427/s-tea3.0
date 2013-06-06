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
    },
    XPATH(){
        public By getLocator(String value){
            return By.xpath(value);
        }
    },
    NAME(){
        public By getLocator(String value){
            return By.name(value);
        }
    },
    CLASSNAME(){
        public By getLocator(String value){
            return  By.className(value);
        }
    },
    LINKTEXT(){
        public By getLocator(String value){
            return By.linkText(value);
        }
    },
    PARTIALINKTEXT(){
        public By getLocator(String value){
            return By.partialLinkText(value);
        }
    },
    CSS(){
        public By getLocator(String value){
            return By.cssSelector(value);
        }
    },
    TAGNAME(){
        public By getLocator(String value){
            return By.tagName(value);
        }
    };

    public By getLocator(String value){
        return null;
    }
}
