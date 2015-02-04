package com.tanjarine.automation.testcases;

import com.tanjarine.automation.utilityhelpers.DriverInit;
import com.tanjarine.automation.pageobjects.LoginPage;
import com.tanjarine.automation.testscripts.AdminHomeLayoutScripts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;


public class AdminHomePageTest {

    private WebDriver driver;
    private LoginPage loginPage;

    private AdminHomeLayoutScripts adminHomeLayoutMethods;


    @BeforeClass
    public void initClass() throws MalformedURLException {
        this.driver = DriverInit.driverSetUp(this.driver);
        loginPage = PageFactory.initElements(this.driver, LoginPage.class);

        adminHomeLayoutMethods = new AdminHomeLayoutScripts(driver);


    }


    @BeforeMethod
    public void webAdminLoginAndEditVenueNavigate() throws IOException, InterruptedException {


        adminHomeLayoutMethods.webAdminLogin(driver, loginPage);


    }


    //    https://jira-csv.touchtunes.com/browse/QA-1781 TEST  execution
    @Test
    public void loginTest_QA_1781() {
        adminHomeLayoutMethods.logout(driver);
        adminHomeLayoutMethods.webAdminLogin(driver, loginPage);

    }


    //    https://jira-csv.touchtunes.com/browse/QA-1782  TEST execution
    @Test
    public void testGetAdminVersion_QA_844() throws IOException {

        adminHomeLayoutMethods.clickAdminVersionandCheck();
    }

    @AfterMethod
    public void navigateToHome() {
        adminHomeLayoutMethods.navigateToWebAdminHomeLayout();
        adminHomeLayoutMethods.logout(driver);
    }

    @AfterClass
    public void teardown() {
        adminHomeLayoutMethods.printTestSuiteNameOnceExecFinished(this.getClass());
        adminHomeLayoutMethods.driverShutDown(driver);
    }
}