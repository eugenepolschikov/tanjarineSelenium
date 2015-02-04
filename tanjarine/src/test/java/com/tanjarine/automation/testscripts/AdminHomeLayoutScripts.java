package com.tanjarine.automation.testscripts;

import com.tanjarine.automation.pageobjects.AdminHomePage;
import com.tanjarine.automation.pageobjects.BundlesPage;
import com.tanjarine.automation.pageobjects.LoginPage;
import com.tanjarine.automation.pageobjects.VenueManagementPage;
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

/**
 * Created by ypolshchykau on 30.01.2015.
 */
public class AdminHomeLayoutScripts extends  BaseTestDriverFrameworkMethodsOnly {
    public final static Logger log = LoggerFactory.getLogger(BaseTestDriverFrameworkMethodsOnly.class);


    private LoginPage loginPage;
    private VenueManagementPage venues;
    private AdminHomePage homePage;
    private MenuCommonPage menuCommonPage;
    private MenuCategory menuCategory;
    private MenuItemLayout menuItemForm;
    private MenuModifiers menuModifiers;
    private AdminHomePage adminhome;
    private BundlesPage bundles;

    //    this is local driver, for usage within this class ONLY
    private WebDriver driver;

//   local String var to log actions withing the class
    String alertMsg;


    public AdminHomeLayoutScripts(WebDriver newDriver) {

        this.driver = newDriver;

        loginPage = PageFactory.initElements(this.driver, LoginPage.class);
        venues = PageFactory.initElements(this.driver, VenueManagementPage.class);
        homePage = PageFactory.initElements(this.driver, AdminHomePage.class);
        menuCommonPage = PageFactory.initElements(this.driver, MenuCommonPage.class);
        adminhome = PageFactory.initElements(this.driver, AdminHomePage.class);
        bundles = PageFactory.initElements(this.driver, BundlesPage.class);
        menuCategory = PageFactory.initElements(this.driver, MenuCategory.class);
        menuModifiers = PageFactory.initElements(this.driver, MenuModifiers.class);
        menuItemForm = PageFactory.initElements(this.driver, MenuItemLayout.class);


    }



    public void navigateToWebAdminHomeLayout() {

        driver.get(super.environmentData.getLoginbaseUrl() + "home");

    }


//    @TODO: implement   : navigate to Game management
//    @TODO: implement   : navigate to Device management
//    @TODO: implement   : navigate to Account management
//    @TODO: implement   : navigate to Venue management

    public void navigateToApplicationManagement() {
        alertMsg = "[Application management] button is absent";
        checkElementPresent(driver, AdminHomePage.applicationManagementCSS, alertMsg);
        homePage.navigateToApplicationManagement();
    }

//    @TODO: implement   : navigate to  Global Settings

    public void clickAdminVersionandCheck() {
        alertMsg = "help layout absent in admin page header";
        checkElementPresent(this.driver,AdminHomePage.headerWebAdminHelpCSS, alertMsg);
        log.info("open help window for Admin version");
        homePage.openAboutAdmin();
        alertMsg = "help layout does not appear";
        checkElementPresent(this.driver,AdminHomePage.helpFormLayoutCSS, alertMsg);

        waitForElementGetsVisible(this.driver,By.cssSelector(AdminHomePage.adminVersionCSS));
        boolean isVersionFound = fluentWait(this.driver,By.cssSelector(AdminHomePage.adminVersionCSS)).getText().trim().contains("version:");
        Assert.assertTrue(isVersionFound, "extracted data does not contain 'version:'");
        String foundVersion = fluentWait(this.driver,By.cssSelector(AdminHomePage.adminVersionCSS)).getText().trim();
        log.info("WebAdmin Version found: " + foundVersion);

        alertMsg = "help layout does not contain [Close] button";
        checkElementPresent(this.driver,AdminHomePage.helpLayoutCloseButtonCSS, alertMsg);
        log.info("press [Close] button on Help popout");
        homePage.closeHelp();
    }



}
