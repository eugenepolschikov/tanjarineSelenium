package com.tanjarine.automation.testscripts;

import com.tanjarine.automation.pageobjects.AdminHomePage;
import com.tanjarine.automation.pageobjects.BundlesPage;
import com.tanjarine.automation.pageobjects.LoginPage;
import com.tanjarine.automation.pageobjects.VenueManagementPage;
import com.tanjarine.automation.pageobjects.menu.food.MenuCategory;
import com.tanjarine.automation.pageobjects.menu.food.MenuCommonPage;
import com.tanjarine.automation.pageobjects.menu.food.MenuItemLayout;
import com.tanjarine.automation.pageobjects.menu.food.MenuModifiers;
import com.tanjarine.automation.constants.HardcodedDataForAutomation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * Created by ypolshchykau on 02.02.2015.
 */
public class LoginPageScripts extends BaseTestDriverFrameworkMethodsOnly {
    public final static Logger log = LoggerFactory.getLogger(LoginPageScripts.class);

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

    //    String variables for logging automation script event(-s) in scope of this class
    private String alertMsg;


    public LoginPageScripts(WebDriver newDriver) {

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

    public void webAdminLogin(WebDriver driver, LoginPage loginPage, String login, String password) {

        // check that eMail input is present
        alertMsg = "Email input is absent";
        checkElementPresent(driver, LoginPage.emailInputCSS, alertMsg);
        loginPage.enterLogin(login);

        //check that pass input is present
        alertMsg = "Password input is absent";
        checkElementPresent(driver, LoginPage.passInputCSS, alertMsg);
        loginPage.enterPassword(password);

//         check that [remeber me] checBox is present
        alertMsg = "Remember me button is absent";
        checkElementPresent(driver, LoginPage.rememberMeCSS, alertMsg);

        // check that login button is present
        alertMsg = "Submit button is absent";
        checkElementPresent(driver, LoginPage.signInButtonCSS, alertMsg);
        log.info("submit login form");
        loginPage.loginFormSubmit();

    }

    public void loginWithEmptyCreds() {
        log.info("QA-942: login with empty credentials\n");
        webAdminLogin(driver, loginPage, HardcodedDataForAutomation.LOGIN_PAGE_EMPTY_LOGIN,
                HardcodedDataForAutomation.LOGIN_PAGE_EMPTY_PASSWORD);
    }

    public void loginWithEmptyPassword() {
        log.info("QA-755: test login with proper user and empty password\n");

        webAdminLogin(driver, loginPage, getEnvironmentData().getTestUser(),
                HardcodedDataForAutomation.LOGIN_PAGE_EMPTY_PASSWORD);

    }

    public void loginWithEmptyLogin() {


        log.info("QA-863: login with empty username and proper password\n");
        webAdminLogin(driver, loginPage, HardcodedDataForAutomation.LOGIN_PAGE_EMPTY_LOGIN,
                getEnvironmentData().getTestUserPass());


    }

    public  void loginWrongPassword(){
        log.info("QA-917: login with proper username and wrong password\n");

        webAdminLogin(driver, loginPage, getEnvironmentData().getTestUser(),
                HardcodedDataForAutomation.LOGIN_PAGE_WRONG_PASSWORD);
    }

    public  void loginWithWrongLogin(){
        log.info("QA-917: login with wrong username and proper password\n");
        webAdminLogin(driver, loginPage, HardcodedDataForAutomation.LOGIN_PAGE_WRONG_LOGIN,
                getEnvironmentData().getTestUserPass());


    }

    public boolean isLoginPage(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean isLoginInputPresent= isElementPresent(driver, By.cssSelector(LoginPage.emailInputCSS));
        boolean isPassInputPresent= isElementPresent(driver, By.cssSelector(LoginPage.passInputCSS));
        boolean isSubmitButtonPresent= isElementPresent(driver, By.cssSelector(LoginPage.signInButtonCSS));
        boolean isRememberMePresent= isElementPresent(driver, By.cssSelector(LoginPage.rememberMeCSS));
                return (isLoginInputPresent && isPassInputPresent && isSubmitButtonPresent && isRememberMePresent);

    }

    public void checkThatUserStillAtLoginPage(){
        Assert.assertTrue(isLoginPage(),"ERROR: user has navigated from login page\n");
    }


}
