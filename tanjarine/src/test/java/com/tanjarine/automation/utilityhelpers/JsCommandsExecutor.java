package com.tanjarine.automation.utilityhelpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by ypolshchykau on 20.01.2015.
 */
public class JsCommandsExecutor {

    public static void jsClick( WebDriver driver,String cssSel) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("var x = $(\'" + cssSel + "\');");
        stringBuilder.append("x.click();");
        js.executeScript(stringBuilder.toString());

    }
}
