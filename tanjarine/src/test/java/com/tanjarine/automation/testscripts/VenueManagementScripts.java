package com.tanjarine.automation.testscripts;

import com.tanjarine.automation.pageobjects.AdminHomePage;
import com.tanjarine.automation.pageobjects.BundlesPage;
import com.tanjarine.automation.pageobjects.LoginPage;
import com.tanjarine.automation.pageobjects.VenueManagementPage;
import com.tanjarine.automation.pageobjects.menu.food.MenuCategory;
import com.tanjarine.automation.pageobjects.menu.food.MenuCommonPage;
import com.tanjarine.automation.pageobjects.menu.food.MenuItemLayout;
import com.tanjarine.automation.pageobjects.menu.food.MenuModifiers;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.tanjarine.automation.utilityhelpers.ElementLocatorByCriteria.*;
import static com.tanjarine.automation.utilityhelpers.JsAlertManipulator.*;


public class VenueManagementScripts extends BaseTestDriverFrameworkMethodsOnly {
    public final static Logger log = LoggerFactory.getLogger(VenueManagementScripts.class);

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


    public VenueManagementScripts(WebDriver newDriver) {

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

    // string variable for logging actions (activities) within the class
    private String alertMsg;

    public static Logger getLog() {
        return log;
    }

    /**
     * ***************************private methods for testcases*******************************************************
     */

    public void inputVenueDataNSubmit() {
        String code = "atmtn" + RandomStringUtils.randomNumeric(3);     // 8 symbols
        String name = "automation: abracadabra" + RandomStringUtils.randomAlphanumeric(4);
        String description = "this venue is created for testing purposes by autotest";
        String expectedHeader = "Add Venue";
        String chainsTableHeaderExpected = "Chains";

        checkIfCreateVenueLayoutPresent(expectedHeader);
        alertMsg = "create venue layout, code input is absent";
        checkElementPresent(this.driver, VenueManagementPage.venueCreateLayoutCodeCSS, alertMsg);
        log.info("entering venue code:" + code);
        venues.enterVenueCode(code);

        alertMsg = "create venue layout - Name input is absent";
        checkElementPresent(this.driver, VenueManagementPage.venueCreateLayoutTitleCSS, alertMsg);
        log.info("entering venue name: " + name);
        venues.enterVenueTitle(name);

        log.info("select chain created");
        selectChainCreated();

        alertMsg = "description input is absent";

        checkElementPresent(this.driver, VenueManagementPage.venueCreateLayoutDescriptionCSS, alertMsg);
        log.info("entering description: " + description);
        venues.enterVenueDescription(description);
        // filling in address section
        createVenueFillAddress();

        // filling Working hours section
        alertMsg = "create venue: working hours section absent";
        checkElementPresent(this.driver, VenueManagementPage.createVenueWorkingHrsColCSS, alertMsg);
        log.info("expanding 'Working hours' section");
        venues.workingHoursClick();

        WebElement workingHrsCheckbox = waitForElementGetsVisible(this.driver, By.cssSelector(VenueManagementPage.createVenueWorkHrs247ChkboxCSS));
        boolean isChecked = isChecked(this.driver, By.cssSelector(VenueManagementPage.createVenueWorkHrs247ChkboxCSS));
        Assert.assertFalse(isChecked, "create venue: 'working hours' checkbox is checked by default");
        log.info("checking 'working hrs 24/7' checkbox");
        workingHrsCheckbox.click();
        log.info("check that checkBox is in checked state after checking action");
        isChecked = isChecked(this.driver, By.cssSelector(VenueManagementPage.createVenueWorkHrs247ChkboxCSS));
        Assert.assertTrue(isChecked, "create venue;'working hours' section:  checkBox is still unchecked state after checking action");

        // fillling in Contacts section
        createVenueFillInContactsSection();

        // adjusting netWork config block
        alertMsg = "create venue: internal network config section is absent";
        checkElementPresent(this.driver, VenueManagementPage.createVenueNetworkCfgColCSS, alertMsg);
        log.info("expanding 'Internal network config' section");
        venues.netWorkConfigBlockClick();

        WebElement ignoreNetWorkConfig = waitForElementGetsVisible(this.driver, By.cssSelector(VenueManagementPage.createVenueNetworkCfgIgnoreCfgChkboxCSS));
        boolean isIgnoreConfigChecked = isChecked(this.driver, By.cssSelector(VenueManagementPage.createVenueNetworkCfgIgnoreCfgChkboxCSS));
        Assert.assertFalse(isIgnoreConfigChecked, "create venue: 'Ignore venue network config' checkbox is checked by default");

        log.info("click 'ignore network config' checkbox ");
        ignoreNetWorkConfig.click();
        isIgnoreConfigChecked = isChecked(this.driver, By.cssSelector(VenueManagementPage.createVenueNetworkCfgIgnoreCfgChkboxCSS));
        Assert.assertTrue(isIgnoreConfigChecked, "create venue: 'Ignore venue network config' checkbox is still unchecked");

        // filling in Second screen block
        alertMsg = "create venue: 'Second screen' is absent";
        checkElementPresent(this.driver, VenueManagementPage.createVenue2ndScreenColCSS, alertMsg);
        log.info("expanding 'Second screen' section");
        venues.secondScreenClick();

        WebElement calendarDropDown = waitForElementGetsVisible(this.driver, By.cssSelector(VenueManagementPage.createVenueSecondScreenCalDropdownCSS));
        //TODO: Need to add check here -- calendarDropDown shown
        log.info("click on 'Calendar  to use' dropdown");
        calendarDropDown.click();

        WebElement calendarToUseOption = waitForElementGetsVisible(this.driver, By.cssSelector(VenueManagementPage.randomOptionCalendarDropdownCSS));
        //TODO: Need to add check here -- calendar to use shown
        log.info("selecting option from the 'Calendar to use' dropdown");
        calendarToUseOption.click();
        // checking that submit button is present
        alertMsg = "create new venue: [Save] button is absent";
        checkElementPresent(this.driver, VenueManagementPage.addVenueLayoutSaveButtonCSS, alertMsg);
        // click submit button
        log.info("click [Save] button: sbumit new venue");
        venues.submitNewVenue();

        checkIfVenueCreationSuccess(chainsTableHeaderExpected);
    }

    /**
     * method responsible for  fillling in  all test user data in Contacts block (create venue)
     */
    public void createVenueFillInContactsSection() {
        // test data
        String name = "test name - Eugene";
        String phone = "1" + RandomStringUtils.randomNumeric(7);
        String venueWebSite = "http://www.test.com";

        log.info("check that Contacts collapsible block is present");
        alertMsg = "Contacts collapsible block is absent";
        checkElementPresent(this.driver, VenueManagementPage.createVenueContactsColCSS, alertMsg);

        log.info("click on Contacts collapsible block");
        venues.contactsBlockClick();

        fluentWait(this.driver, By.cssSelector(VenueManagementPage.createVenueContactsNameInputCSS)); // waiting for inputs to appear
        WebElement nameInput = waitForElementGetsVisible(this.driver, By.cssSelector(VenueManagementPage.createVenueContactsNameInputCSS));
        //TODO: need to add check Nameinput
        log.info("Contacts block: entering name" + name);
        venues.createVenueFillName(name);

        alertMsg = "create venue contacts block: phone input is absent!";
        checkElementPresent(this.driver, VenueManagementPage.createVenueContactsPhoneInputCSS, alertMsg);
        log.info("fill in phone field" + phone);
        venues.createVenueFillPhone(phone);

        alertMsg = "create venue contacts block: venue website input is absent";
        checkElementPresent(this.driver, VenueManagementPage.createVenueContactsWebsiteInputCSS, alertMsg);
        log.info("fill in venue website" + venueWebSite);
        venues.createVenueFillWebsite(venueWebSite);
    }

    /**
     * method responsible for selecting   chain in dropdown containing 'automation'
     */
    public void createVenueFillAddress() {
        String address = "test_" + RandomStringUtils.randomAlphanumeric(4) + " avenue";
        String city = "test-city-Minsk";
        String state = "abracadabra";
        String GPSLatitute = "67";
        String GPSLongtitude = "67";
        String postalCode = RandomStringUtils.randomNumeric(6);
        String country = "test-country-Belarus";

        // String GPSLatitute = RandomStringUtils.randomNumeric(2);
        // String GPSLongtitude = RandomStringUtils.randomNumeric(2);
        alertMsg = "Address collapsable block is absent";
        checkElementPresent(this.driver, VenueManagementPage.addressCollabpsableCSS, alertMsg);
        log.info("click on Address collapsable block");
        venues.clickAddressSection();

        log.info("waiting for Address input to appear");
        WebElement addressInput = waitForElementGetsVisible(this.driver, By.cssSelector(VenueManagementPage.createVenueStreetAddrCSS));
        log.info("entering Address: " + address);
        venues.createVenueFilAddress(address);

        alertMsg = "create venue: city input is absent";
        checkElementPresent(this.driver, VenueManagementPage.createVenueAddrCityCSS, alertMsg);
        log.info("entering City: " + city);
        venues.createVenueFillCity(city);

        alertMsg = "create venue: state input is absent";
        checkElementPresent(this.driver, VenueManagementPage.createVenueAddrStateCSS, alertMsg);
        log.info("entering State: " + state);
        venues.createVenueFillState(state);
        alertMsg = "create venue: address block is absent";
        checkElementPresent(this.driver, VenueManagementPage.createVenueAddrPostCodeCSS, alertMsg);
        log.info("entering Postal code: " + postalCode);
        venues.createVenueFillPostalCode(postalCode);

        alertMsg = "create venue: country input is absent";
        checkElementPresent(this.driver, VenueManagementPage.createVenueAddrCountryCSS, alertMsg);
        log.info("entering Country: " + country);
        venues.createVenueFillCountry(country);

        alertMsg = "create venue: GPS latitude input is absent";
        checkElementPresent(this.driver, VenueManagementPage.createVenueAddrGPSLatCSS, alertMsg);
        log.info("entering GPS latitude: " + GPSLatitute);
        venues.createVenueFillGPSLatitude(GPSLatitute);

        alertMsg = "create venue: GPS longtitude input is absent";
        checkElementPresent(this.driver, VenueManagementPage.createVenueAddrGPSlongCSS, alertMsg);
        log.info("entering GPS longtitude: " + GPSLongtitude);
        venues.createVenueFillGPSLongtitude(GPSLongtitude);
    }

    /**
     * method responsible for selecting   chain in dropdown containing 'automation'
     */
    private void selectChainCreated() {
        alertMsg = "chain dropdown is absent";
        checkElementPresent(this.driver, VenueManagementPage.venueCreateChainDropdownCSS, alertMsg);
        log.info("click chain dropdown");
        venues.clickChainDropdown();

        waitForElementGetsVisible(this.driver, By.cssSelector(VenueManagementPage.createVenueRandChainInListCSS));
        //TODO: need to add check after waitForElementGetsVisible
//        int indexToSelect = getTableElementIndexByString(venues.getCreateVenueChainList(), "automation");

//        METHOD CALL BEFORE REFACTORING: !!!!
//        int indexToSelect = getTableElementIndexByString(venues.getCreateVenueChainList(), "selenium");

        int indexToSelect = getTableElementIndexByString(venues.getCreateVenueChainList(), "selenium");

        Assert.assertTrue(indexToSelect >= 0, "no chain to select");
        venues.getCreateVenueChainList().get(indexToSelect).click();
    }

    /**
     * method creating new chain and filling it with test data
     * returns string containing logging activities withing chain creation action
     */
    public void createNewChainFillWithDataAndSubmit() {
        alertMsg = "[Add chain] in the left column is absent";
        checkElementPresent(this.driver, VenueManagementPage.addChainCSS, alertMsg);
        log.info("click on add chain");
        venues.addChain();

        alertMsg = "Check that add chain layout appeared";
        checkElementPresent(this.driver, VenueManagementPage.chainLayoutCSS, alertMsg);
        // chain layout fill with test data
        chainFormDataFill();

        alertMsg = "submit button is absent";
        checkElementPresent(this.driver, VenueManagementPage.addChainSaveButton, alertMsg);
        log.info("click Save button");
        venues.createChainSubmit();

        alertMsg = "chains table is absent, error after new chain submit";
        checkElementPresent(this.driver, VenueManagementPage.chainsTableHeaderCSS, alertMsg);

        checkIfChainTableHeaderExpectedVal();
    }

    /**
     * method deleting all chains containing 'automation' in its name, i.e. deleting all automation entities
     */
    public void chainAutomationEntitiesCleanup() {
        int chainToDeleteIndex = getTableElementIndexByString(venues.getChainsTable(), "automation");
        while (chainToDeleteIndex >= 0) {
            deleteOneChain(chainToDeleteIndex);
            chainToDeleteIndex = getTableElementIndexByString(venues.getChainsTable(), "automation");
        }
    }

    /**
     * method deleteOneChain
     * returns   string containing all logged activities during chain deletion
     *
     * @param indexOfChainInChaingListToDelete index in chain list of chain that is supposed to be deleted
     */
    public void deleteOneChain(int indexOfChainInChaingListToDelete) {
        // clicking on chain => opening chain details layout
        venues.getChainsTable().get(indexOfChainInChaingListToDelete).click();
        log.info("click the chain venues");
        alertMsg = "No venue exists for the selected chain";
        boolean isVenueListOfChainSelected = isElementPresent(this.driver, By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS));
        if (isVenueListOfChainSelected) {
            WebElement venueList = waitForElementGetsVisible(this.driver, By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS));
            // TODO: Add Check
            venueList.click();
        }
        WebElement editSelectedChainButton = waitForElementGetsVisible(this.driver, By.cssSelector(VenueManagementPage.editSelectedChainButtonCSS));
        //TODO: ADD CHECK

        alertMsg = "no 'Edit' button for chain selected";
        checkElementPresent(this.driver, VenueManagementPage.editSelectedChainButtonCSS, alertMsg);
        log.info("click on [Edit] selected chain button");
        editSelectedChainButton.click();

        alertMsg = "chain layout on Edit action appeared";
        checkElementPresent(this.driver, VenueManagementPage.chainLayoutCSS, alertMsg);

        alertMsg = "[delete] button is absent";
        checkElementPresent(this.driver, VenueManagementPage.deleteChainButton, alertMsg);
        Assert.assertFalse(isAlertPresent(this.driver), "no alert shown before clicking [Delete] chain button");
        log.info("Press [Delete] button for chain selected");
        venues.deleteChainSelected();
        boolean isAlertPresent = isAlertPresent(this.driver);
        Assert.assertTrue(isAlertPresent, "alert is not present");
        log.info("switch to alert. Click on alert popUp.");
        jsAlertAccept(this.driver);
        // checking that chain list (table) is still shown after deleting 1 chain entity ( check that table with 'Chains'  title appeared)
        alertMsg = "chains table is absent, error after new chain submit";
        fluentWait(this.driver, By.cssSelector(VenueManagementPage.chainsTableHeaderCSS));
        checkElementPresent(this.driver, VenueManagementPage.chainsTableHeaderCSS, alertMsg);
    }

    public void chainFormDataFill() {
        String chainName = "Automation" + RandomStringUtils.randomAlphanumeric(3);
        String description = "abracadabra";
        String phone = "+18" + RandomStringUtils.randomNumeric(9);
        String webSiteURL = "http://www.tut.by";

        alertMsg = "chain name input is absent";
        checkElementPresent(this.driver, VenueManagementPage.addChainNameInputCSS, alertMsg);
        log.info("input chain name: " + chainName);
        venues.enterChainName(chainName);

        alertMsg = "description input is absent";
        checkElementPresent(this.driver, VenueManagementPage.addChainDescInputCSS, alertMsg);
        log.info("input chain description: " + description);
        venues.enterChainDscription(description);

        alertMsg = "contacts collapsable section is absent";
        checkElementPresent(this.driver, VenueManagementPage.addChainContactsColCSS, alertMsg);
        log.info("click on 'Contacts' collapsable section");
        venues.contactsClick();

        alertMsg = "phone input is absent";
        checkElementPresent(this.driver, VenueManagementPage.addChainPhoneInputCSS, alertMsg);
        log.info("enter phone number: " + phone);
        venues.fillPhone(phone);

        alertMsg = "website input is absent";
        checkElementPresent(this.driver, VenueManagementPage.addChainWebSiteInputCSS, alertMsg);
        log.info("enter webSite URL: " + webSiteURL);
        venues.enterEmail(webSiteURL);
    }

    // method returning ALL indexes of automation entities
    public List<Integer> getAutomationIndexesInChainTable(List<WebElement> elemenentsTable, String pattern) {
        List<Integer> thisList = new ArrayList<Integer>();
        for (int count = 0; count < elemenentsTable.size(); count++) {
            String textCurrentElement = elemenentsTable.get(count).getText().toLowerCase().trim();             // get text of the element being analyzed
            if (textCurrentElement.contains(pattern)) {
                thisList.add(count);
            }
        }
        return thisList;
    }

    public void associateGameToVenue() {
        //  walk throug chains  one by one
        //  walk through venues assgned to chains being considered
        //        if found any stop seach  and open it.
        //        assign game
        //        save
        //        check that layout expected apperead
        //

        List<Integer> chainIndexes = getAutomationIndexesInChainTable(venues.getChainsTable(), "automation");
        Assert.assertTrue(chainIndexes.size() > 0, "no chain to select");
        for (int count = 0; count < chainIndexes.size(); count++) {
            venues.getChainsTable().get(chainIndexes.get(count)).click();
            log.info("check if venue list assigned to chain exists");
            boolean isVenueListAssignedToChainSelectedExists = isElementPresent(this.driver, By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS));
            if (isVenueListAssignedToChainSelectedExists) {
                log.info(" list 1 venue exists for the selected chain. Wait till the whole venue list assigned to the chain selected render");
                waitForElementGetsVisible(this.driver, By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS));  // WebElement venueList

                log.info("clicking on the first venue: " + venues.getVenueListWithinChainSelected().get(0).getText().trim()); // after venue list renders, click on any venue
                venues.getVenueListWithinChainSelected().get(0).click();
                break;
            } else {
                continue;
            }
        }

        alertMsg = "selected venue: Edit button is absent";
        checkElementPresent(this.driver, VenueManagementPage.editVenueButtonCSS, alertMsg);
        log.info("click on Edit venue button");

        venues.editVenueClick();

        alertMsg = "Edit venue layout did not appear";
        checkElementPresent(this.driver, VenueManagementPage.editVenueLayoutCSS, alertMsg);

        alertMsg = "[Game collection] button did not appear";
        checkElementPresent(this.driver, VenueManagementPage.editVenueGameColButtonCSS, alertMsg);
        log.info("click on [Game collection] button");

        venues.editVenueNavigateToGameCollection();

        alertMsg = "Game collection layout didn't appear";
        checkElementPresent(this.driver, VenueManagementPage.editVenueGameCollectionCSS, alertMsg);
        log.info("assigning  a game collection to the venue in a random way");

        List<WebElement> gameCollections = venues.getGameCollectionsRadiobuttonsList();
        int gameCollectionSize = gameCollections.size();

        Random randomGeneratorr = new Random();   //randomzing selection of game Collections radiobutton
        int randomInt = randomGeneratorr.nextInt(gameCollectionSize - 1);
        gameCollections.get(randomInt).click();
        alertMsg = "[Save] button is absent on tha page";
        checkElementPresent(this.driver, VenueManagementPage.gameCollectionSubmitButtonCSS, alertMsg);

        log.info("submiting the Game Collection selected");
        venues.editVenueGameCollectionSelectedSubmit();

        waitForAlertAndClose();
    }

    public void addVenueToChain() {
        String expectedHeader = "Add Venue";
        log.info("clicking on  [Add venue] button");
        venues.addVenue();
        alertMsg = "create venue layout is absent";
        checkElementPresent(this.driver, VenueManagementPage.createVenueHeaderCSS, alertMsg);

        boolean isExpectedHeaderShown = venues.getCreateVenueHeader().getText().trim().equals(expectedHeader);
        Assert.assertTrue(isExpectedHeaderShown, "open layout is not create venue layout");
        inputVenueDataNSubmit();
    }


    public void checkIfChainTableHeaderExpectedVal() {
        String chainsTableHeaderExpectedValue = "Chains";
        boolean isExpectedTableAppeared = venues.getChainTableHeader().getText().trim().equals(chainsTableHeaderExpectedValue);
        Assert.assertTrue(isExpectedTableAppeared, "Table has improper header");
    }

    public void checkIfAddVenueButtonPresent() {
        log.info("[Add venue] button is present");
        alertMsg = "no Add venue button";
        checkElementPresent(this.driver, VenueManagementPage.addVenueCSS, alertMsg);
    }

    public void addVenueClick() {
        checkIfAddVenueButtonPresent();

        log.info("clicking on  [Add venue] button");
        venues.addVenue();
    }


    public void checkIfCreateVenueLayoutPresent(String expectedHeader) {
        // checking that create venue layout appeared!!!
        log.info("checking that Create venue layout appeared");
        alertMsg = "create venue layout did not appear";
        checkElementPresent(this.driver, VenueManagementPage.createVenueHeaderCSS, alertMsg);

        boolean isExpectedHeaderShown = venues.getCreateVenueHeader().getText().trim().equals(expectedHeader);
        log.info("check that header title of create venue layout equals 'Add Venue' ");
        Assert.assertTrue(isExpectedHeaderShown, "open layout is not create venue layout");
    }

    public void checkIfVenueCreationSuccess(String chainsTableHeaderExpected) {
        // verification that chain table is shown after new venue submit

//        artificially added a portions of wait (wait for element's visibility)
        waitForElementGetsVisible(this.driver, By.cssSelector(VenueManagementPage.chainsTableHeaderCSS));

        alertMsg = "chains table is absent, error after new chain submit";
        checkElementPresent(this.driver, VenueManagementPage.chainsTableHeaderCSS, alertMsg);
        log.info("validating that chains table is present");

        boolean isExpectedTable = venues.getChainTableHeader().getText().trim().equals(chainsTableHeaderExpected);
        Assert.assertTrue(isExpectedTable, "table has improper header");
    }

    public void waitForAlertAndClose() {
        log.info("wait for alert appears");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());
        boolean isExpectedAlertAppeared = isAlertPresent(this.driver);
        Assert.assertTrue(isExpectedAlertAppeared, "expected alert didn't appear");
        log.info("click OK on the alert appeared");
        driver.switchTo().alert().accept();
    }

    public void testVenueManagementCleanup() {
//        login();
        webAdminLogin(this.driver, loginPage);
        navigateToVenueMangementPage();
        if (isElementPresent(this.driver, By.cssSelector(VenueManagementPage.chainsTableHeaderCSS))) {
            String chainsTableHeaderExpectedValue = "Chains";
            boolean isChainTableShown = venues.getChainTableHeader().getText().trim().equals(chainsTableHeaderExpectedValue);
            if (isChainTableShown) {
                log.info(this.getClass().getSimpleName() + " CLEANING UP automation entities");
                chainAutomationEntitiesCleanup();
            }
        }

    }

    public void navigateToVenueMangementPage() {
        alertMsg = "Global Admin dropdown is absent";
        checkElementPresent(this.driver, AdminHomePage.headerDownsCSS, alertMsg);
        log.info("click Global Admin header section");
        homePage.globalAdminClick();
        alertMsg = "Venue Management section is absent";
        checkElementPresent(this.driver, AdminHomePage.venueManagementHeaderCSS, alertMsg);

        log.info("click venue management section");
        homePage.venueManagementSectionClick();

//        add sleep waiter to wait until layout appears
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean isVneHeaderAppeared = isElementPresent(this.driver, By.cssSelector(VenueManagementPage.venueMgmtLeftColCSS));
        Assert.assertTrue(isVneHeaderAppeared, "header of Venue Management section is absent");
        Assert.assertTrue(isVneHeaderAppeared && venues.getVenueMgmtHeader().getText().trim().equals("Venue management"), "FAILED: cant navigate to venue management section");
    }


    public void pickUpAutomationChaingForVenueManagementPurposes() {
        //        navigate to automation chain
    }


//    METHOD(-s) responsible for cleaning up "selenium Do not touch automation CHAIN"

    public boolean checkIfAutomationVenuesExist() {
        log.info("check that assigned venue list is present");
        boolean isVenueListPresent = isElementPresent(this.driver, By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS));
        Assert.assertTrue(isVenueListPresent, "no venues for the chain selected ");

        return venues.getVenueListWithinChainSelected().size() > 1;
    }

    public void pickUpSecondVenueUnderChain() {
        log.info("check that assigned venue list is present");
        boolean isVenueListPresent = isElementPresent(this.driver, By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS));
        Assert.assertTrue(isVenueListPresent, "no venues for the chain selected ");

        venues.getVenueListWithinChainSelected().get(1).click();

    }

    public void editVenueSelected() {
        log.info("press [Edit] venue button\n");
        venues.editVenueClick();
    }

    public void deleteVenue() {
        fluentWait(this.driver, By.cssSelector(VenueManagementPage.deleteVenueButtonCSS));
        waitForElementGetsVisible(this.driver, By.cssSelector(VenueManagementPage.deleteVenueButtonCSS));
        log.info("press [Delete] venue button\n");
        venues.deleteVenue();
    }

    public void deleteVenueSubmitAlert() {
        log.info("check that alert is present and shown\n");
        isAlertPresent(this.driver);
        log.info("submit the alert");
        jsAlertAccept(this.driver);

    }

}
