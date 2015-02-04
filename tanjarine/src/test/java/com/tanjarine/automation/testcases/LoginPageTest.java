package com.tanjarine.automation.testcases;

import com.tanjarine.automation.utilityhelpers.DriverInit;
import com.tanjarine.automation.pageobjects.LoginPage;
import com.tanjarine.automation.testscripts.LoginPageScripts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


public class LoginPageTest {


    private WebDriver driver;
    private LoginPage loginPage;
    // script class instance, i.e. instance containing our test methods
    private LoginPageScripts loginPageMethods;


    @BeforeClass
    public void initClass() throws MalformedURLException {
        this.driver = DriverInit.driverSetUp(this.driver);
        loginPage = PageFactory.initElements(this.driver, LoginPage.class);

        // init of menue method class' instancs
        loginPageMethods = new LoginPageScripts(driver);


    }


    @BeforeMethod
    public void setup() {
        loginPageMethods.webAdminLogin(driver, loginPage);
        loginPageMethods.logout(driver);
    }


    /**
     * Author: Fan Wang {fwang@touchtunes.com}
     * Test Case ID: PIL-4001, PIL-240: Validate that user cannot log into the app with empty User ID field and valid Password
     * Test Case Result: User stays at login page with empty User ID/valid PW and alert msg poped
     */
    @Test(groups = "P2")
    public void testLoginWithEmptyCredentials_QA_942() {
        loginPageMethods.loginWithEmptyCreds();
        loginPageMethods.checkThatUserStillAtLoginPage();
    }

    /**
     * Author: Eugene Polschikov {ypolshchykau@exadel.com}
     * Test Case ID: PIL-262: Validate that user cannot log into the app with existing User ID and empty Password field
     * Test Case Result: User stays at login page with empty User ID/valid PW and alert msg poped
     * PIL262()
     */
    @Test
    public void testLoginWithEmptyPassword_QA_755() {
        loginPageMethods.loginWithEmptyPassword();
        loginPageMethods.checkThatUserStillAtLoginPage();
    }

    /**
     * Author: Fan Wang {fwang@touchtunes.com}
     * Test Case ID: PIL-263: Validate that user cannot log into the app with empty User ID field and valid Password
     * Test Case Result: User stays at login page with empty User ID/valid PW and alert msg poped
     */
    @Test
    public void testLoginWithEmptyUsername_QA_863() {
        loginPageMethods.loginWithEmptyLogin();
        loginPageMethods.checkThatUserStillAtLoginPage();
    }


    @Test
    public void loginWithWrongPassword_QA_917() {
        loginPageMethods.loginWrongPassword();
        loginPageMethods.checkThatUserStillAtLoginPage();
    }

    @Test
    public void loginWithWrongUserID_QA_833() {
        loginPageMethods.loginWithWrongLogin();
        loginPageMethods.checkThatUserStillAtLoginPage();
    }


    @AfterClass
    public void tearDown() {
        loginPageMethods.printTestSuiteNameOnceExecFinished(this.getClass());
        loginPageMethods.driverShutDown(this.driver);
    }
}