package com.tanjarine.automation.utilityhelpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

/**
 * Created by ypolshchykau on 16.01.2015.
 * this class contains methods
 */
public class JsAlertManipulator {

    /**
     * isAlertPresent method checks if an Alert is shown or not
     *
     * @return true or false
     */
    public static boolean isAlertPresent(WebDriver driver) {
        boolean presentFlag;
        try {
            // Check the presence of alert
            Alert alert = driver.switchTo().alert();
            // Alert present; set the flag
            presentFlag = true;
            // if present consume the alert
            // alert.accept();

        } catch (NoAlertPresentException ex) {
            // Alert not present
            // ex.printStackTrace();
            presentFlag = false;
        }
        return presentFlag;
    }

    /**
     *
     * accepting js alert appeared
     * @param driver
     */
    public static void jsAlertAccept(WebDriver driver) {
        Alert alert=driver.switchTo().alert();
        alert.accept();
    }



}
