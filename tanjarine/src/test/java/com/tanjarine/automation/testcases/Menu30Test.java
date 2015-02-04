package com.tanjarine.automation.testcases;

import com.tanjarine.automation.utilityhelpers.DriverInit;
import com.tanjarine.automation.utilityhelpers.Timer;
import com.tanjarine.automation.pageobjects.LoginPage;
import com.tanjarine.automation.testscripts.Menu30Scripts;
import com.tanjarine.automation.constants.HardcodedDataForAutomation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by ypolshchykau on 19.09.2014.
 */
public class Menu30Test {


    private WebDriver driver;
    private LoginPage loginPage;
    // script class instance, i.e. instance containing our test methods
    private Menu30Scripts menu30Methods;


    @BeforeClass
    public void initClass() throws MalformedURLException {
        this.driver = DriverInit.driverSetUp(this.driver);
        loginPage = PageFactory.initElements(this.driver, LoginPage.class);

        // init of menue method class' instancs
        menu30Methods = new Menu30Scripts(driver);


    }


    @BeforeMethod
    public void webAdminLoginAndEditVenueNavigate() throws IOException, InterruptedException {


        menu30Methods.webAdminLogin(driver, loginPage);
        menu30Methods.navigateToEditVenueLayout();

    }


    @Test
    public void navigateToEmenuAndGetBack() {

//        pick up selenium automation chain and navigate to eMenu section:
//        menu30Methods.pickUpChainByName(menu30Methods.loadSeleniumChainName());
        menu30Methods.pickUpChainByName(menu30Methods.getEnvironmentData().getVenueManagementOperationsChain());
        menu30Methods.pickUpFirstVenue();
        menu30Methods.switchToEmenuSection();
        menu30Methods.leaveEmenu();


    }


    //    test navigate to eMenu under  QAChain->BelQAVenue
    @Test
    public void qaChainBlrQaVenueNavigate() {

        menu30Methods.pickUpChainByName(menu30Methods.getEnvironmentData().getChainName());

        menu30Methods.pickUpVenueByName(menu30Methods.getEnvironmentData().getVenueName());

//        pick up eMenu section within venue navigated
        menu30Methods.switchToEmenuSection();

        menu30Methods.selectFirstProductCategory();


    }

    @Test
    public void addIngredientsInNewMenuItem() {
        menu30Methods.pickUpChainByName(menu30Methods.getEnvironmentData().getChainName());
        menu30Methods.pickUpVenueByName(menu30Methods.getEnvironmentData().getVenueName());

        //        pick up eMenu section within venue navigated
        menu30Methods.switchToEmenuSection();
        menu30Methods.waitTillCommonMenuRenders();
        menu30Methods.selectFirstProductCategory();
        menu30Methods.addNewMenuItem();
//        add ingredients
        menu30Methods.menuItemFillIngredientsArea();
        menu30Methods.checkPassConditionWhileItemCreate();

    }

    @Test
    public void addNutritionInfoInNewMenuItem() {
        menu30Methods.pickUpChainByName(menu30Methods.getEnvironmentData().getChainName());
        menu30Methods.pickUpVenueByName(menu30Methods.getEnvironmentData().getVenueName());

        //        pick up eMenu section within venue navigated
        menu30Methods.switchToEmenuSection();
        menu30Methods.waitTillCommonMenuRenders();
        menu30Methods.selectFirstProductCategory();
        menu30Methods.addNewMenuItem();
//        add nutrition info : calories , calories from FAT only
//        @TODO accomlish all inputs!
        menu30Methods.menuItemFillNutritionArea();


        menu30Methods.checkPassConditionWhileItemCreate();

    }


    @Test
    public void addDescriptorsInNewItemForm() {

        menu30Methods.pickUpChainByName(menu30Methods.getEnvironmentData().getChainName());
        menu30Methods.pickUpVenueByName(menu30Methods.getEnvironmentData().getVenueName());

        //        pick up eMenu section within venue navigated
        menu30Methods.switchToEmenuSection();
        menu30Methods.waitTillCommonMenuRenders();
        menu30Methods.selectFirstProductCategory();
        menu30Methods.addNewMenuItem();


        menu30Methods.menuItemFillDescriptorsArea();
        menu30Methods.checkPassConditionWhileItemCreate();


    }

    //    selecting new item created with some fields  filled
    @Test
    public void itemTemplateCreateInEmenu() {

        menu30Methods.pickUpChainByName(menu30Methods.getEnvironmentData().getChainName());
        menu30Methods.pickUpVenueByName(menu30Methods.getEnvironmentData().getVenueName());


//        pick up eMenu section within venue navigated
        menu30Methods.switchToEmenuSection();
        menu30Methods.waitTillCommonMenuRenders();

//        @TODO add one category if category list is empty
        /*menu30Methods.addCategory();
        menu30Methods.fillCategoryData();
        menu30Methods.submitNewAutomationCategory();
        menu30Methods.checkThatPassedForCategoryActions();*/


        menu30Methods.selectFirstProductCategory();
        menu30Methods.addNewMenuItem();
        menu30Methods.newMenuItemFillWithData(HardcodedDataForAutomation.NEW_ITEM_NAME_TEMPLATE + Timer.getCurrentTimestamp());

    }

//     bundles deploy related TCs


    @Test(enabled = false)
    public void generateBundleAndTabletDeploy() {

        menu30Methods.pickUpChainByName(menu30Methods.getEnvironmentData().getChainName());

        menu30Methods.pickUpVenueByName(menu30Methods.getEnvironmentData().getVenueName());


        menu30Methods.navigateToBundlesSection();
        menu30Methods.navigateToTabletsSubsection();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        menu30Methods.printLastBundleVersionDeployed();

//        generated new bundle with changes
        menu30Methods.generateBundleAfterChanges();
        menu30Methods.devicesAvailableForBundle();
        menu30Methods.listAllDevicesAvailable();
        menu30Methods.pickUpAppropriateTabletForDeploy(menu30Methods.getEnvironmentData().getMacBorqs());


    }


    @AfterClass
    public void closeBrowser() {
        menu30Methods.printTestSuiteNameOnceExecFinished(this.getClass());
        menu30Methods.driverShutDown(this.driver);
    }

}
