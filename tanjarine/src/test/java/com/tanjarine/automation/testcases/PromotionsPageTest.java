package com.tanjarine.automation.testcases;

import com.tanjarine.automation.utilityhelpers.DriverInit;
import com.tanjarine.automation.pageobjects.LoginPage;
import com.tanjarine.automation.testscripts.AdminHomeLayoutScripts;
import com.tanjarine.automation.testscripts.PromotionPageScripts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;


public class PromotionsPageTest {
//    private final static Logger log = LoggerFactory.getLogger(PromotionsPageTest.class);


    private WebDriver driver;
    private LoginPage loginPage;


    private PromotionPageScripts promotionMethods;
    private AdminHomeLayoutScripts adminHomeLayoutMethods;


    @BeforeClass
    public void initClass() throws MalformedURLException {
        this.driver = DriverInit.driverSetUp(this.driver);
        loginPage = PageFactory.initElements(driver, LoginPage.class);

// init of menue method class' instancs

        promotionMethods = new PromotionPageScripts(driver);
        adminHomeLayoutMethods = new AdminHomeLayoutScripts(driver);


    }

    @BeforeMethod
    public void webAdminLoginAndEditVenueNavigate() throws IOException, InterruptedException {


        promotionMethods.webAdminLogin(driver, loginPage);
        adminHomeLayoutMethods.navigateToWebAdminHomeLayout();
        promotionMethods.navigateToPromotions(promotionMethods.getEnvironmentData().getChainName());


    }


    /**
     * REL-2916: User is able to create New Marker Based Promotion
     * REL-2916: User is able to create Campaign Promotion
     * PIL-1374 and PIL-871: User is able to create Promotions for Dashboard
     * Promotion Management section test
     */
    @Test()
    public void markerBasedPromoCreate_QA_1571() {

        promotionMethods.createMarkerBasePromotion();

        /**
         * QA-1583: Verify that user is able to add Campaign promotion  ;
         * commented out for debuggin purposes
         */
        // createCampaignPromotion();


    }

    @Test
    public void dashboardPromoCreate_QA_743() {
        promotionMethods.createDashboardPromotion();
    }



    @AfterMethod
    public void navigateToHome() {
        adminHomeLayoutMethods.navigateToWebAdminHomeLayout();

    }

    @AfterClass
    public void closeBrowser() {
        promotionMethods.printTestSuiteNameOnceExecFinished(this.getClass());
        promotionMethods.driverShutDown(driver);
    }
}
