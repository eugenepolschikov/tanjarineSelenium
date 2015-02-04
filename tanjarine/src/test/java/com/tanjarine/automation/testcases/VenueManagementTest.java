package com.tanjarine.automation.testcases;

import com.tanjarine.automation.utilityhelpers.DriverInit;
import com.tanjarine.automation.pageobjects.LoginPage;
import com.tanjarine.automation.pageobjects.VenueManagementPage;
import com.tanjarine.automation.testscripts.Menu30Scripts;
import com.tanjarine.automation.testscripts.VenueManagementScripts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.net.MalformedURLException;

import static com.tanjarine.automation.utilityhelpers.ElementLocatorByCriteria.getElementToDelete;

public class VenueManagementTest {



    private WebDriver driver;
    private LoginPage loginPage;
    private VenueManagementPage venuePage;
    // script class instance, i.e. instance containing our test methods
    private Menu30Scripts menu30Methods;
    private VenueManagementScripts venueMethods;


    @BeforeClass
    public void initClass() throws MalformedURLException {
        this.driver = DriverInit.driverSetUp(this.driver);
        loginPage = PageFactory.initElements(this.driver, LoginPage.class);
        venuePage = PageFactory.initElements(this.driver, VenueManagementPage.class);


        // init of menue method class' instancs
        menu30Methods = new Menu30Scripts(driver);
        venueMethods = new VenueManagementScripts(driver);


    }


    @BeforeMethod
    public void navigateToVenuesPage() {

        venueMethods.webAdminLogin(driver, loginPage);

        venueMethods.navigateToVenueMangementPage();
    }

    /**
     * Author: Eugene Polschikov {ypolshchykau@exadel.com}
     * Test Case ID: PIL-314: Validate that user is able to add chains
     * Test Case ID: PIL-320: Validate that user is able to delete chains
     * Test Case Result: Able to add chain
     */
    @Test(priority = 1)
    public void createChain_QA_751() {

        venueMethods.createNewChainFillWithDataAndSubmit();

    }

    @Test(priority = 2)
    public void venueCreate_QA_793() {
        venueMethods.addVenueClick();
        venueMethods.inputVenueDataNSubmit();

    }

    @Test(priority = 2)
    public void deleteChain_QA_955() {
        venueMethods.createNewChainFillWithDataAndSubmit();
        venueMethods.deleteOneChain(getElementToDelete(venuePage));
    }

    @Test(priority = 2)
    public void chainCleanUp() {
        venueMethods.testVenueManagementCleanup();
    }

    /**
     * this test cleans up all automation venues entities that are created under
     * __selenium_doNotTouch  chain:
     * http://gyazo.com/79cbe9db931d85023925f9292ae5ec4a
     */
    @Test(priority = 4)
    public void automationVenuesCleanUp() {
        menu30Methods.pickUpChainByName(venueMethods.getEnvironmentData().getVenueManagementOperationsChain());

        while (venueMethods.checkIfAutomationVenuesExist()) {

//            menu30Methods.pickUpVenueByName("_/automation: abracadabra48Yy");
            venueMethods.pickUpSecondVenueUnderChain();
            venueMethods.editVenueSelected();
            venueMethods.deleteVenue();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            venueMethods.deleteVenueSubmitAlert();

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            menu30Methods.pickUpChainByName(venueMethods.getEnvironmentData().getVenueManagementOperationsChain());

        }


    }

    @AfterMethod
    public void cleanup() {
        venueMethods.logout(this.driver);
    }

    @AfterClass
    public void teardown() {
//        testVenueManagementCleanup();
        venueMethods.printTestSuiteNameOnceExecFinished(this.getClass());
        venueMethods.driverShutDown(this.driver);
    }
}