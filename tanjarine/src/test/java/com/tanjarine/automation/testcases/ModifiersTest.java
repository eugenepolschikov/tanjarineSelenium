package com.tanjarine.automation.testcases;

import com.tanjarine.automation.utilityhelpers.DriverInit;
import com.tanjarine.automation.pageobjects.LoginPage;
import com.tanjarine.automation.testscripts.Menu30Scripts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by ypolshchykau on 21.11.2014.
 */
public class ModifiersTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private Menu30Scripts menu30Methods;


    @BeforeClass
    public void initClass() throws MalformedURLException {
        this.driver = DriverInit.driverSetUp(this.driver);
        loginPage = PageFactory.initElements(driver, LoginPage.class);

// init of menue method class' instancs
        menu30Methods = new Menu30Scripts(driver);


    }

    @BeforeMethod
    public void webAdminLoginAndEditVenueNavigate() throws IOException, InterruptedException {


        menu30Methods.webAdminLogin(driver, loginPage);
        menu30Methods.navigateToEditVenueLayout();

    }


    @Test
    public void navigateToQAVenue() {
//        menu30Methods.pickUpChainByName();
        menu30Methods.pickUpChainByName(menu30Methods.getEnvironmentData().getChainName());

        menu30Methods.pickUpVenueByName(menu30Methods.getEnvironmentData().getVenueName());


    }


    @Test
    public void createCategoryTest() {
        menu30Methods.pickUpChainByName(menu30Methods.getEnvironmentData().getChainName());

        menu30Methods.pickUpVenueByName(menu30Methods.getEnvironmentData().getVenueName());

//        picking up eMenu section:
        menu30Methods.switchToEmenuSection();

        menu30Methods.addCategory();

        menu30Methods.fillCategoryData();

        menu30Methods.submitNewAutomationCategory();

        menu30Methods.checkThatPassedForCategoryActions();

    }


    @Test
    public void enableDisableCategoriesTest() throws InterruptedException {

        menu30Methods.pickUpChainByName(menu30Methods.getEnvironmentData().getChainName());

        menu30Methods.pickUpVenueByName(menu30Methods.getEnvironmentData().getVenueName());

        //        picking up eMenu section:
        menu30Methods.switchToEmenuSection();


        menu30Methods.waitTillCommonMenuRenders();
//        get list of categories and deactivate it
        menu30Methods.categoryListDeactivation();

//        activate list of categories
        menu30Methods.categoryListActivate();

        menu30Methods.checkThatPassedForCategoryActions();

    }


    @Test
    public void footerTest() throws InterruptedException {
        menu30Methods.pickUpChainByName(menu30Methods.getEnvironmentData().getChainName());

        menu30Methods.pickUpVenueByName(menu30Methods.getEnvironmentData().getVenueName());

        //        picking up eMenu section:
        menu30Methods.switchToEmenuSection();


        menu30Methods.waitTillCommonMenuRenders();
//        add footer

        menu30Methods.pickUpFooter();

//        fill text area with data
        menu30Methods.fillFooterTextArea();
//        submit
        menu30Methods.submitFooter();
        menu30Methods.waitTillCommonMenuRenders();
        menu30Methods.checkThatPassedForCategoryActions();
//        edit footer
        menu30Methods.pickUpFooter();
//        change data
        menu30Methods.editFooterTextArea();
//        submit
        menu30Methods.submitFooter();
        menu30Methods.waitTillCommonMenuRenders();
        menu30Methods.checkThatPassedForCategoryActions();


    }

    @Test
    public void addSubcategoryTest() {

        menu30Methods.pickUpChainByName(menu30Methods.getEnvironmentData().getChainName());

        menu30Methods.pickUpVenueByName(menu30Methods.getEnvironmentData().getVenueName());

        //        picking up eMenu section:
        menu30Methods.switchToEmenuSection();


        menu30Methods.waitTillCommonMenuRenders();

        menu30Methods.selectFirstProductCategory();

        menu30Methods.addNewSubcategory();

        menu30Methods.fillSubcategoryData();

        menu30Methods.submitNewAutomationSubcategory();

        menu30Methods.checkThatPassedForCategoryActions();


    }

    @AfterClass
    public void closeBrowser() {
//@TODO 1: cleanUp automation categories!!!
//@TODO 2: cleanUp automation subCategoris!!!
        menu30Methods.printTestSuiteNameOnceExecFinished(this.getClass());
        menu30Methods.driverShutDown(this.driver);
    }


}
