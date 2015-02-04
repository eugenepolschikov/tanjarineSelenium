package com.tanjarine.automation.testcases;

import com.tanjarine.automation.pageobjects.LoginPage;
import com.tanjarine.automation.testscripts.AdminHomeLayoutScripts;
import com.tanjarine.automation.testscripts.ApkRegisterScripts;
import com.tanjarine.automation.utilityhelpers.DriverInit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;

public class ApkRegisterTest {

    private WebDriver driver;
    private LoginPage loginPage;

    private ApkRegisterScripts apkRegistrationLayoutMethods;
    private AdminHomeLayoutScripts adminHomeLayoutMethods;


    @BeforeClass
    public void initClass() throws MalformedURLException {
        this.driver = DriverInit.driverSetUp(this.driver);
        loginPage = PageFactory.initElements(this.driver, LoginPage.class);

        apkRegistrationLayoutMethods = new ApkRegisterScripts(driver);
        adminHomeLayoutMethods = new AdminHomeLayoutScripts(driver);


    }


    @BeforeMethod
    public void webAdminLoginAndEditVenueNavigate() throws IOException, InterruptedException {


        apkRegistrationLayoutMethods.webAdminLogin(driver, loginPage);
        adminHomeLayoutMethods.navigateToWebAdminHomeLayout();
        adminHomeLayoutMethods.navigateToApplicationManagement();

    }


    //registering apks  of the version specified in   config.properties file
    @Test
    public void testRegisterLatestApks() throws IOException, InterruptedException {
          apkRegistrationLayoutMethods.registerAllItemswithLatestVersion();
    }



    @AfterMethod
    public void navigateToHome() {
        adminHomeLayoutMethods.navigateToWebAdminHomeLayout();

    }

    @AfterClass
    public void closeBrowser() {
        apkRegistrationLayoutMethods.printTestSuiteNameOnceExecFinished(this.getClass());
        apkRegistrationLayoutMethods.driverShutDown(driver);
    }
}
