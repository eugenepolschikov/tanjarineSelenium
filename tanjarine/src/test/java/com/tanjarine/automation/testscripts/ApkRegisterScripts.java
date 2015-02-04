package com.tanjarine.automation.testscripts;

import com.tanjarine.automation.pageobjects.*;
import com.tanjarine.automation.pageobjects.menu.food.MenuCategory;
import com.tanjarine.automation.pageobjects.menu.food.MenuCommonPage;
import com.tanjarine.automation.pageobjects.menu.food.MenuItemLayout;
import com.tanjarine.automation.pageobjects.menu.food.MenuModifiers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;


public class ApkRegisterScripts extends BaseTestDriverFrameworkMethodsOnly {

    public final static Logger log = LoggerFactory.getLogger(ApkRegisterScripts.class);

    private ApkRegisterPage apkPage;

    //    this is local driver, for usage within this class ONLY
    private WebDriver driver;

    //    String variables for logging automation script event(-s) in scope of this class
    private String alertMsg;


    public ApkRegisterScripts(WebDriver newDriver) {

        this.driver = newDriver;

        apkPage = PageFactory.initElements(this.driver, ApkRegisterPage.class);

    }


    public void rolldownApkDropdownList(ApkRegisterPage apkPage, WebDriver driver) {
        log.info("rolldown APKs dropdown");
        apkPage.rollDownApkList();
        apkPage.rollDownApkList();
        log.info("wait till dropdown appears");
        waitForElementGetsVisible(this.driver, By.cssSelector(ApkRegisterPage.firstApkInDropDownCSS));
        //TODO: ADD check
    }

    public void rolldownVersionDropdownList(ApkRegisterPage apkPage, WebDriver driver) {
        log.info("rolldown version dropdown");
        apkPage.rollDownVersionList();
        apkPage.rollDownVersionList();
        if (isElementPresent(this.driver, By.cssSelector(ApkRegisterPage.firstVersionInDropdownCSS))) {
            log.info("wait till dropdown appears");
            waitForElementGetsVisible(this.driver, By.cssSelector(ApkRegisterPage.firstVersionInDropdownCSS));
            //TODO: add check
        } else {
            Assert.assertTrue(true, "dropdown didn't appear");
        }
    }


    //    prerequisites: enter String pattern in accordance to which APKs are supposed to be selected
    //steps
    //    3. select all items 1 by 1 :
    //   count ALL items in dropdown
    //  select 1st item ; increase total item count
    //    click for version INPUT

    public void versionSearchAndRegisterForOneApk(ApkRegisterPage apkPage, String apkSelectedForVersionToChoose) throws InterruptedException {
        String toSearch = getEnvironmentData().getApkVersion();
        log.info("Apk to search: " + getEnvironmentData().getApkVersion());
        //  flag indicating that needed version for APK selected in dropdown found
        int flag = -1;
        String fullVersionLabel = "";
        rolldownVersionDropdownList(apkPage, driver);

        for (int i = 0; i < apkPage.getVersionsList().size(); i++) {
            if (apkPage.getVersionsList().get(i).getText().trim().contains(toSearch)) {
                fullVersionLabel = apkPage.getVersionsList().get(i).getText().trim();
                apkPage.getVersionsList().get(i).click();
                flag = i;
                break;
            }
        }
        Thread.sleep(1000);
        apkPage.newApksFormClick();

        if (flag < 0) {
            log.info("for APK: ".toUpperCase() + apkSelectedForVersionToChoose.toUpperCase() + " looked version: ".toUpperCase() + toSearch.toUpperCase() + "NOT FOUND");
            log.info("OR likely this version has been already registered".toUpperCase());
        }

        if (flag >= 0) {
            alertMsg = "[Register] button is absent";
            checkElementPresent(this.driver, ApkRegisterPage.registerButtonCSS, alertMsg);
            apkPage.registerActionPerform();
            log.info("APK: " + apkSelectedForVersionToChoose + " of version " + fullVersionLabel + " SUCCESSFULLY registered".toUpperCase());
            Thread.sleep(6500);
            //TODO: need to replace the sleep wit better wait
            log.info("wait till APK management form  gets refreshed");
            waitForElementGetsVisible(this.driver, By.cssSelector(ApkRegisterPage.registerNewApkFormCSS));
            fluentWait(this.driver, By.cssSelector(ApkRegisterPage.registerNewApkFormCSS));
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            if (apkSelectedForVersionToChoose.toLowerCase().contains("catalogue") ||
                    apkSelectedForVersionToChoose.toLowerCase().contains("tablet dashboard") || apkSelectedForVersionToChoose.toLowerCase().contains("venue pc")) {
                Thread.sleep(3000);
            }

            if (apkSelectedForVersionToChoose.toLowerCase().contains("mongodb")) {
                Thread.sleep(13000);
            }
            apkPage.rollDownVersionList();
            apkPage.rollDownApkList();
            apkPage.rollDownVersionList();
            apkPage.rollDownApkList();
        }
    }

    public void registerAllItemswithLatestVersion() throws InterruptedException {
        apkPage.rollDownVersionList();
        apkPage.rollDownApkList();
        apkPage.rollDownVersionList();
        apkPage.rollDownApkList();
        apkPage.rollDownVersionList();
        apkPage.rollDownApkList();
        apkPage.rollDownVersionList();
        apkPage.rollDownApkList();

        for (int count = 0; count < apkPage.getApksList().size(); count++) {
            // roll down APK list from name dropdown
            rolldownApkDropdownList(apkPage, this.driver);
            String apkName = apkPage.getApksList().get(count).getText().trim();
            // condition to exclude APKs with NO version!!!
            if (apkName.toLowerCase().contains("snapcall") || apkName.toLowerCase().contains("trivia") || apkName.toLowerCase().contains("color") ||
                    apkName.toLowerCase().contains("analytics")) {
                continue;
            }

            log.info(apkName + " selected");

            apkPage.getApksList().get(count).click();
            // wait till page gets refreshed
            Thread.sleep(1000);
            apkPage.newApksFormClick();
            // ==========  END of waits =========
            versionSearchAndRegisterForOneApk(this.apkPage, apkName);
        }
    }
}
