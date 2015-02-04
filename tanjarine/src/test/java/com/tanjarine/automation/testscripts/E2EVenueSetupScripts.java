package com.tanjarine.automation.testscripts;

import com.tanjarine.automation.utilityhelpers.EnvironmentDataLoader;
import com.tanjarine.automation.utilityhelpers.FileOperations;
import com.tanjarine.automation.utilityhelpers.Timer;
import com.tanjarine.automation.pageobjects.PromotionsPage;
import com.tanjarine.automation.pageobjects.VenueManagementPage;
import com.tanjarine.automation.constants.HardcodedDataForAutomation;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by ypolshchykau on 26.08.2014.
 */
public class E2EVenueSetupScripts extends BaseTest {
    public final static String VENUE_PREFIX = "vCode";
    public final static Logger log = LoggerFactory.getLogger(E2EVenueSetupScripts.class);

    private static EnvironmentDataLoader environmentData = new EnvironmentDataLoader();



    private String alertMsg;
    private WebDriver driver;
    private FileOperations testData;
    private VenueManagementPage venues;
    private PromotionsPage promotions;
    private String apkVersion;


    public E2EVenueSetupScripts() {
        driver = super.driver;
        testData = new FileOperations();
        venues = getVenues();
        apkVersion = getApkVersion();
        promotions = getPromotionPage();
    }



    public void addVenueClick() {
        log.info("[Add venue] button is present");
        checkElementPresent(VenueManagementPage.addVenueCSS, "no Add venue button");

        log.info("clicking on  [Add venue] button");
        venues.addVenue();
    }
//========================================================================

    /**
     * fill New Venue with data method
     */
    public void fillNewVenueWithDataAndSubmit() {
        String code = VENUE_PREFIX + RandomStringUtils.randomNumeric(3);     // 8 symbols
        String name = "automation: abracadabra" + RandomStringUtils.randomAlphanumeric(4);
        String description = "this venue is created for testing purposes by autotest";
        String expectedHeader = "Add Venue";
        String chainsTableHeaderExpected = "Chains";

        checkIfCreateVenueLayoutPresent(expectedHeader);
        alertMsg = "create venue layout, code input is absent";
        checkElementPresent(VenueManagementPage.venueCreateLayoutCodeCSS, alertMsg);
        log.info("entering venue code:" + code);
        venues.enterVenueCode(code);

        alertMsg = "create venue layout - Name input is absent";
        checkElementPresent(VenueManagementPage.venueCreateLayoutTitleCSS, alertMsg);
        log.info("entering venue name: " + name);
        venues.enterVenueTitle(name);

        log.info("select chain created");
        selectChainCreated(environmentData.getVenueManagementOperationsChain());

        alertMsg = "description input is absent";

        checkElementPresent(VenueManagementPage.venueCreateLayoutDescriptionCSS, alertMsg);
        log.info("entering description: " + description);
        venues.enterVenueDescription(description);
        // filling in address section
        createVenueFillAddress();

        // filling Working hours section
        alertMsg = "create venue: working hours section absent";
        checkElementPresent(VenueManagementPage.createVenueWorkingHrsColCSS, alertMsg);
        log.info("expanding 'Working hours' section");
        venues.workingHoursClick();

        WebElement workingHrsCheckbox = waitForElementGetsVisible(By.cssSelector(VenueManagementPage.createVenueWorkHrs247ChkboxCSS));
        boolean isChecked = isChecked(By.cssSelector(VenueManagementPage.createVenueWorkHrs247ChkboxCSS));
        Assert.assertFalse(isChecked, "create venue: 'working hours' checkbox is checked by default");
        log.info("checking 'working hrs 24/7' checkbox");
        workingHrsCheckbox.click();
        log.info("check that checkBox is in checked state after checking action");
        isChecked = isChecked(By.cssSelector(VenueManagementPage.createVenueWorkHrs247ChkboxCSS));
        Assert.assertTrue(isChecked, "create venue;'working hours' section:  checkBox is still unchecked state after checking action");

        // fillling in Contacts section
        createVenueFillInContactsSection();

        // adjusting netWork config block
        alertMsg = "create venue: internal network config section is absent";
        checkElementPresent(VenueManagementPage.createVenueNetworkCfgColCSS, alertMsg);
        log.info("expanding 'Internal network config' section");
        venues.netWorkConfigBlockClick();

        WebElement ignoreNetWorkConfig = waitForElementGetsVisible(By.cssSelector(VenueManagementPage.createVenueNetworkCfgIgnoreCfgChkboxCSS));
        boolean isIgnoreConfigChecked = isChecked(By.cssSelector(VenueManagementPage.createVenueNetworkCfgIgnoreCfgChkboxCSS));
        Assert.assertFalse(isIgnoreConfigChecked, "create venue: 'Ignore venue network config' checkbox is checked by default");

        log.info("click 'ignore network config' checkbox ");
        ignoreNetWorkConfig.click();
        isIgnoreConfigChecked = isChecked(By.cssSelector(VenueManagementPage.createVenueNetworkCfgIgnoreCfgChkboxCSS));
        Assert.assertTrue(isIgnoreConfigChecked, "create venue: 'Ignore venue network config' checkbox is still unchecked");

        // filling in Second screen block
        alertMsg = "create venue: 'Second screen' is absent";
        checkElementPresent(VenueManagementPage.createVenue2ndScreenColCSS, alertMsg);
        log.info("expanding 'Second screen' section");
        venues.secondScreenClick();

        WebElement calendarDropDown = waitForElementGetsVisible(By.cssSelector(VenueManagementPage.createVenueSecondScreenCalDropdownCSS));
        //TODO: Need to add check here -- calendarDropDown shown
        log.info("click on 'Calendar  to use' dropdown");
        calendarDropDown.click();

        WebElement calendarToUseOption = waitForElementGetsVisible(By.cssSelector(VenueManagementPage.randomOptionCalendarDropdownCSS));
        //TODO: Need to add check here -- calendar to use shown
        log.info("selecting option from the 'Calendar to use' dropdown");
        calendarToUseOption.click();
        // checking that submit button is present
        alertMsg = "create new venue: [Save] button is absent";
        checkElementPresent(VenueManagementPage.addVenueLayoutSaveButtonCSS, alertMsg);
        // click submit button
        log.info("click [Save] button: sbumit new venue");
        venues.submitNewVenue();

        checkIfVenueCreationSuccess(chainsTableHeaderExpected);

    }

    public void checkIfCreateVenueLayoutPresent(String expectedHeader) {
        // checking that create venue layout appeared!!!
        log.info("checking that Create venue layout appeared");
        alertMsg = "create venue layout did not appear";
        checkElementPresent(VenueManagementPage.createVenueHeaderCSS, alertMsg);

        boolean isExpectedHeaderShown = venues.getCreateVenueHeader().getText().trim().equals(expectedHeader);
        log.info("check that header title of create venue layout equals 'Add Venue' ");
        Assert.assertTrue(isExpectedHeaderShown, "open layout is not create venue layout");
    }

    private void selectChainCreated(String chainPatterToSearch) {
        alertMsg = "chain dropdown is absent";
        checkElementPresent(VenueManagementPage.venueCreateChainDropdownCSS, alertMsg);
        log.info("click chain dropdown");
        venues.clickChainDropdown();

        waitForElementGetsVisible(By.cssSelector(VenueManagementPage.createVenueRandChainInListCSS));
        //TODO: need to add check after waitForElementGetsVisible
        int indexToSelect = getTableElementIndexByString(venues.getCreateVenueChainList(), chainPatterToSearch);
        Assert.assertTrue(indexToSelect >= 0, "no chain to select");
        venues.getCreateVenueChainList().get(indexToSelect).click();
    }

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
        checkElementPresent(VenueManagementPage.addressCollabpsableCSS, alertMsg);
        log.info("click on Address collapsable block");
        venues.clickAddressSection();

        log.info("waiting for Address input to appear");
        WebElement addressInput = waitForElementGetsVisible(By.cssSelector(VenueManagementPage.createVenueStreetAddrCSS));
        log.info("entering Address: " + address);
        venues.createVenueFilAddress(address);

        alertMsg = "create venue: city input is absent";
        checkElementPresent(VenueManagementPage.createVenueAddrCityCSS, alertMsg);
        log.info("entering City: " + city);
        venues.createVenueFillCity(city);

        alertMsg = "create venue: state input is absent";
        checkElementPresent(VenueManagementPage.createVenueAddrStateCSS, alertMsg);
        log.info("entering State: " + state);
        venues.createVenueFillState(state);
        alertMsg = "create venue: address block is absent";
        checkElementPresent(VenueManagementPage.createVenueAddrPostCodeCSS, alertMsg);
        log.info("entering Postal code: " + postalCode);
        venues.createVenueFillPostalCode(postalCode);

        alertMsg = "create venue: country input is absent";
        checkElementPresent(VenueManagementPage.createVenueAddrCountryCSS, alertMsg);
        log.info("entering Country: " + country);
        venues.createVenueFillCountry(country);

        alertMsg = "create venue: GPS latitude input is absent";
        checkElementPresent(VenueManagementPage.createVenueAddrGPSLatCSS, alertMsg);
        log.info("entering GPS latitude: " + GPSLatitute);
        venues.createVenueFillGPSLatitude(GPSLatitute);

        alertMsg = "create venue: GPS longtitude input is absent";
        checkElementPresent(VenueManagementPage.createVenueAddrGPSlongCSS, alertMsg);
        log.info("entering GPS longtitude: " + GPSLongtitude);
        venues.createVenueFillGPSLongtitude(GPSLongtitude);
    }

    public void createVenueFillInContactsSection() {
        // test data
        String name = "test name - Eugene";
        String phone = "1" + RandomStringUtils.randomNumeric(7);
        String venueWebSite = "http://www.test.com";

        log.info("check that Contacts collapsible block is present");
        alertMsg = "Contacts collapsible block is absent";
        checkElementPresent(VenueManagementPage.createVenueContactsColCSS, alertMsg);

        log.info("click on Contacts collapsible block");
        venues.contactsBlockClick();

        fluentWait(By.cssSelector(VenueManagementPage.createVenueContactsNameInputCSS)); // waiting for inputs to appear
        WebElement nameInput = waitForElementGetsVisible(By.cssSelector(VenueManagementPage.createVenueContactsNameInputCSS));
        //TODO: need to add check Nameinput
        log.info("Contacts block: entering name" + name);
        venues.createVenueFillName(name);

        alertMsg = "create venue contacts block: phone input is absent!";
        checkElementPresent(VenueManagementPage.createVenueContactsPhoneInputCSS, alertMsg);
        log.info("fill in phone field" + phone);
        venues.createVenueFillPhone(phone);

        alertMsg = "create venue contacts block: venue website input is absent";
        checkElementPresent(VenueManagementPage.createVenueContactsWebsiteInputCSS, alertMsg);
        log.info("fill in venue website" + venueWebSite);
        venues.createVenueFillWebsite(venueWebSite);
    }

    public void checkIfVenueCreationSuccess(String chainsTableHeaderExpected) {
        // verification that chain table is shown after new venue submit
        alertMsg = "chains table is absent, error after new chain submit";
        checkElementPresent(VenueManagementPage.chainsTableHeaderCSS, alertMsg);
        log.info("validating that chains table is present");

        boolean isExpectedTable = venues.getChainTableHeader().getText().trim().equals(chainsTableHeaderExpected);
        Assert.assertTrue(isExpectedTable, "table has improper header");
    }

    //    ===============Click Information & Save page (verify message "Venue info was successfully updated")
//    PIL-305: Validate that user is able to edit venues
    public void pickUpExistingChainAndSelectVenue() throws InterruptedException {
/*
//        hover appriate chain needed
       hoverElement(By.cssSelector(getSelectorForChainNeeded()));
//        hover and click on appropriate first venue;
       hoverElemAndClick(VenueManagementPage.firstVenueUnderNeededChainCSS);
//        press [EDIT] button  for venue selected
       fluentWait(By.cssSelector(VenueManagementPage.editVenueButtonCSS)).click();*/


        driver.findElement(By.cssSelector(getSelectorForChainNeeded())).click();
        fluentWait(By.cssSelector(VenueManagementPage.editSelectedChainButtonCSS));
        Thread.sleep(1000);
        log.info("check that assigned venue list is present");
        boolean isVenueListPresent = isElementPresent(By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS));
        junit.framework.Assert.assertTrue("no venues for the chain selected ", isVenueListPresent);

        venues.getVenueListWithinChainSelected().get(0).click();
        Thread.sleep(1000);

        venues.editVenueClick();

    }


    public void informationClickSaveVenue() {
        log.info("informtion button clicking\n");
        venues.informationButtonCLick();

        log.info("click [Save] button: sbumit new venue\n");
        venues.saveExistingVenue();

        log.info("check that 'Venue info was successfully updated' msg appeared\n");
        waitForElementGetsVisible(By.cssSelector(VenueManagementPage.alertMsgOnSaveVenueCSS));
        boolean isPresent = isElementPresent(By.cssSelector(VenueManagementPage.alertMsgOnSaveVenueCSS));
        junit.framework.Assert.assertTrue("\"Venue info was successfully updated\"  mas didn't appear\n", isPresent);
        log.info("PIL-305: Validate that user is able to edit venues  test case PASSED\n");

    }


    public String getSelectorForChainNeeded() {
        int index = -1;
        List<WebElement> chainList = venues.getVenueManagementPageChainList();
        for (int i = 0; i < chainList.size(); i++) {
            if (chainList.get(i).getText().trim().toLowerCase().contains(environmentData.getVenueManagementOperationsChain())) {
                index = i;
            }
        }

        String selector = "";
        if (index >= 0) {
            selector = "div.col-md-2.chain-list  ul.list-group li.list-group-item:nth-child(" + (index + 2) + ") a";
        }
        return selector;
    }

    //QA-40: [Edit Venue. Theme layout]Verify that user is able to clone the theme
    public void editVenueLayoutNavigateToThemes() {
        log.info("[EDIT venue layout]: navigating to themes section");
        venues.themesButtonCLick();

    }

    //    QA-40: [Edit Venue. Theme layout]Verify that user is able to clone the theme
    public void createNewThemeByCloningExistingOne() {
        log.info("[EDIT venue layout]: create new  theme");
        venues.themeCreateButtonClick();

        cloneTheme();


    }

    private void cloneTheme() {
        venues.cloneTheme();
        waitForElementGetsVisible(By.cssSelector(VenueManagementPage.cloneThemeModalDialogCSS));
        fluentWait(By.cssSelector(VenueManagementPage.themeDropdownCSS));
        venues.rollDownThemeDropdown();
        List<WebElement> themeList = venues.getThemesList();
        Assert.assertTrue(themeList.size() > 0, "theme list dropdown is empty\n");

//        random selectom of theme to apply
        Random generator = new Random();
        int i = generator.nextInt(themeList.size()) + 1;
        themeList.get(i - 1).click();

        venues.cloneClick();
//        String name = "automation_" +getCurrentTimestamp();
        String name = RandomStringUtils.randomAlphabetic(3);
        venues.enterThemeTitle(name);
        venues.themeSubmit();
    }

    public void assignExistingThemeToVenue() {

        fluentWait(By.cssSelector(VenueManagementPage.assignThemeToVenueLayoutCSS));
        venues.selectThemeOfExistingOnes();


        List<WebElement> existingThemes = venues.getExistingThemeList();
        Assert.assertTrue(existingThemes.size() > 1, "theme list dropdown is empty\n");

        // random selectom of theme to apply
        Random generator = new Random();
        int i = generator.nextInt(existingThemes.size()) + 1;
        if (i == 1) {
            existingThemes.get(i).click();
        } else {
            existingThemes.get(i - 1).click();
        }

        venues.assignSelectedThemeToVenue();

        log.info("check that msg 'Theme assigned successfully ' appeared\n");
        log.info(venues.getSuccessfulAssignmentAlert().getText().trim());
        Assert.assertTrue(venues.getSuccessfulAssignmentAlert().getText().trim().equals("Theme assigned successfully"), "'Theme assigned successfully ' NOT appeared\n ");
    }


    public void editVenueLayoutNavigateToDevices() {

        log.info("[EDIT venue layout]: navigating to Devices section\n");
//        venues.devicesButtonClick();
        fluentWait(By.cssSelector(VenueManagementPage.editVenueDevicesButtonCSS)).click();

    }


    public void fillMacRegisterPatronTablet() {


        log.info("filling in mac of device\n");

        venues.fillMacOfTheDevice(randomMACAddress());
//        fluentWait(By.cssSelector(VenueManagementPage.macAddressInputCSS)).sendKeys(randomMACAddress());
        log.info("selecting device type\n");
        venues.rolldownDeviceTypeDropdown();

        log.info("selecting patron tablet device type\n");
        venues.getDeviceTypesValues().get(1).click();

        log.info("saving new device\n");
        venues.saveNewDevice();
        fluentWait(By.cssSelector(VenueManagementPage.alertMsgOnDeviceRegisterCSS));
        log.info("check that msg '\"Device has been successfully registered\"' appeared after device registration ");
        Assert.assertTrue(venues.getAlertMsgOnDeviceRegister().getText().trim().contains("Device has been successfully registered"), "msg '\"Device has been successfully registered\"' didn't appear ");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

//  clarification on mac address generation: http://stackoverflow.com/questions/24261027/make-a-random-mac-address-generator-generate-just-unicast-macs

    private String randomMACAddress() {
        Random rand = new Random();
        byte[] macAddr = new byte[6];
        rand.nextBytes(macAddr);

        macAddr[0] = (byte) (macAddr[0] & (byte) 254);  //zeroing last 2 bytes to make it unicast and locally adminstrated

        StringBuilder sb = new StringBuilder(18);
        for (byte b : macAddr) {

            if (sb.length() > 0)
                sb.append("-");

            sb.append(String.format("%02x", b));
        }


        return sb.toString();
    }


    public void fillMacRegisterServerHandheld() {

        log.info("filling in mac of device\n");

        venues.fillMacOfTheDevice(randomMACAddress());
//        fluentWait(By.cssSelector(VenueManagementPage.macAddressInputCSS)).sendKeys(randomMACAddress());

        log.info("selecting device type\n");
        venues.rolldownDeviceTypeDropdown();

        log.info("selecting server handheld device type\n");
        venues.getDeviceTypesValues().get(2).click();

        log.info("saving new device\n");
        venues.saveNewDevice();
        fluentWait(By.cssSelector(VenueManagementPage.alertMsgOnDeviceRegisterCSS));
        log.info("check that msg '\"Device has been successfully registered\"' appeared after device registration ");
        log.info(venues.getAlertMsgOnDeviceRegister().getText().trim());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(venues.getAlertMsgOnDeviceRegister().getText().trim().contains("Device has been successfully registered"), "msg '\"Device has been successfully registered\"' didn't appear ");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void fillMacRegister2ndScreen() {
        log.info("filling in mac of device\n");

        venues.fillMacOfTheDevice(randomMACAddress());
//        fluentWait(By.cssSelector(VenueManagementPage.macAddressInputCSS)).sendKeys(randomMACAddress());

        log.info("selecting device type\n");
        venues.rolldownDeviceTypeDropdown();

        log.info("selecting 2nd screen device type\n");
        venues.getDeviceTypesValues().get(3).click();

        log.info("saving new device\n");
        venues.saveNewDevice();
        fluentWait(By.cssSelector(VenueManagementPage.alertMsgOnDeviceRegisterCSS));
        log.info("check that msg '\"Device has been successfully registered\"' appeared after device registration ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(venues.getAlertMsgOnDeviceRegister().getText().trim().contains("Device has been successfully registered"), "msg '\"Device has been successfully registered\"' didn't appear ");

        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void fillMacRegisterVenuePC() {

        log.info("assigning VenuePc in case it still not assigned\n");

        if (venues.getVenuePcDevicesList().size() == 0) {

            log.info("filling in mac of device\n");

            venues.fillMacOfTheDevice(randomMACAddress());
//            fluentWait(By.cssSelector(VenueManagementPage.macAddressInputCSS)).sendKeys(randomMACAddress());

            log.info("selecting device type\n");
            venues.rolldownDeviceTypeDropdown();

            log.info("selecting Venue PC device type\n");
            venues.getDeviceTypesValues().get(4).click();

            log.info("saving new device\n");
            venues.saveNewDevice();
            fluentWait(By.cssSelector(VenueManagementPage.alertMsgOnDeviceRegisterCSS));
            log.info("check that msg '\"Device has been successfully registered\"' appeared after device registration ");
            Assert.assertTrue(venues.getAlertMsgOnDeviceRegister().getText().trim().contains("Device has been successfully registered"), "msg '\"Device has been successfully registered\"' didn't appear ");

            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public void checkThatDevicesAreAvailableToUnregister() {
        log.info("checking that venues PCs are listed in table\n");
        Assert.assertTrue(venues.getVenuePcDevicesList().size() > 0, "no venue PC devices to unregister");

        log.info("checking that Patron tablets are listed in the table\n");
        Assert.assertTrue(venues.getPatronTabletsDevicesList().size() > 0, "no patron tablets to unregister");


        log.info("checking that Server  handhelds are listed in the table\n");
        Assert.assertTrue(venues.getServerHandheldsDevicesList().size() > 0, "no Server  handhelds  to unregister");

        log.info("checking that Server 2nd screen devices are listed in the table\n");
        Assert.assertTrue(venues.getSecondScreenDevicesList().size() > 0, "no Second screen devices to unregister");


    }


    public void unassignVenuePC() {
        log.info("select venue PC to deassign\n");
        venues.tickVenueCheckbox();
        log.info("unassign venue PC \n");
        venues.unassignVnePC();
        if (isAlertPresent()) {
            jsAlerAccept();
        }

        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("check that msg \"Devices has been successfully unassigned\" appeared\n");
        Assert.assertTrue(venues.getVenuePCBeenDeassignedMsg().getText().trim().contains("Devices has been successfully unassigned"), "\"Devices has been successfully unassigned\" msg didn't appear\n");
    }

    public void unassignPatronTablets() {
        log.info("selecting tablets checkbox \n");
        venues.tickPatronTabletsCheckbox();
        log.info("unassign patron tablets \n");
        venues.unassignPatronTablets();
        if (isAlertPresent()) {
            jsAlerAccept();
        }

        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("check that msg \"Devices has been successfully unassigned\" appeared\n");
        Assert.assertTrue(venues.getVenuePCBeenDeassignedMsg().getText().trim().contains("Devices has been successfully unassigned"), "\"Devices has been successfully unassigned\" msg didn't appear\n");


    }

    public void unassingServerHandhelds() {
        log.info("selecting server handhelds \n");

        venues.tickServerHandheldsCheckbox();
        log.info("unassign  server handhelds\n");
        venues.unassignServerHandelds();

        if (isAlertPresent()) {
            jsAlerAccept();
        }

        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("check that msg \"Devices has been successfully unassigned\" appeared\n");
        Assert.assertTrue(venues.getVenuePCBeenDeassignedMsg().getText().trim().contains("Devices has been successfully unassigned"), "\"Devices has been successfully unassigned\" msg didn't appear\n");

    }

    public void unasssignSecondScreenDevices() {
        log.info("selecting 2nd screen devices \n");
        venues.tickSecondScreenDevicesCheckbox();

        log.info("unassign second screen devices\n");
        venues.unassign2ndScreenDevices();
        if (isAlertPresent()) {
            jsAlerAccept();
        }

        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("check that msg \"Devices has been successfully unassigned\" appeared\n");
        Assert.assertTrue(venues.getVenuePCBeenDeassignedMsg().getText().trim().contains("Devices has been successfully unassigned"), "\"Devices has been successfully unassigned\" msg didn't appear\n");

    }


    public void navigateToBundles() {
        fluentWait(By.cssSelector(VenueManagementPage.bundlesButtonCSS)).click();
    }


    public void navigateToTablets() {
        fluentWait(By.cssSelector(VenueManagementPage.tabletsCSS)).click();
    }


    public void navigateToHandhelds() {
        fluentWait(By.cssSelector(VenueManagementPage.handheldsCSS)).click();
    }

    public void navigateTo2ndScreens() {
        fluentWait(By.cssSelector(VenueManagementPage.SecondScreensCSS)).click();
    }

    public void navigateToVenuePCs() {
        fluentWait(By.cssSelector(VenueManagementPage.VenuePCsCSS)).click();
    }


    public void clickThroughDropdowns(int IndexNum) {
        // fluentWait(By.cssSelector(VenueManagementPage. clickThroughDrodowns()))
        if (IndexNum == 10) {
            while (IndexNum > 0) {
                if (IndexNum == 10) {
                    venues.DropdownSelect1.click();
                    selectAValue(1);
                } else if (IndexNum == 9) {
                    venues.DropdownSelect2.click();
                    selectAValue(2);
                } else if (IndexNum == 8) {
                    venues.DropdownSelect3.click();
                    selectAValue(3);
                } else if (IndexNum == 7) {
                    venues.DropdownSelect4.click();
                    selectAValue(4);
                } else if (IndexNum == 6) {
                    venues.DropdownSelect5.click();
                    selectAValue(5);
                } else if (IndexNum == 5) {
                    venues.DropdownSelect6.click();
                    selectAValue(6);
                } else if (IndexNum == 4) {
                    venues.DropdownSelect7.click();
                    selectAValue(7);
                } else if (IndexNum == 3) {
                    venues.DropdownSelect8.click();
                    selectAValue(8);
                } else if (IndexNum == 2) {
                    venues.DropdownSelect9.click();
                    selectAValue(9);
                } else if (IndexNum == 1) {
                    venues.DropdownSelect10.click();
                    selectAValue(10);
                }
                IndexNum = IndexNum - 1;
            }
        } else if (IndexNum == 9) {
            while (IndexNum > 0) {
                if (IndexNum == 9) {
                    venues.DropdownSelect1.click();
                    selectAValue(1);
                } else if (IndexNum == 8) {
                    venues.DropdownSelect2.click();
                    selectAValue(2);
                } else if (IndexNum == 7) {
                    venues.DropdownSelect3.click();
                    selectAValue(3);
                } else if (IndexNum == 6) {
                    venues.DropdownSelect4.click();
                    selectAValue(4);
                } else if (IndexNum == 5) {
                    venues.DropdownSelect5.click();
                    selectAValue(5);
                } else if (IndexNum == 4) {
                    venues.DropdownSelect6.click();
                    selectAValue(6);
                } else if (IndexNum == 3) {
                    venues.DropdownSelect7.click();
                    selectAValue(7);
                } else if (IndexNum == 2) {
                    venues.DropdownSelect8.click();
                    selectAValue(8);
                } else if (IndexNum == 1) {
                    venues.DropdownSelect9.click();
                    selectAValue(9);
                }
                IndexNum = IndexNum - 1;
            }
        } else if (IndexNum == 4) {
            while (IndexNum > 0) {
                if (IndexNum == 4) {
                    venues.DropdownSelect1.click();
                    selectAValue(1);
                } else if (IndexNum == 3) {
                    venues.DropdownSelect2.click();
                    selectAValue(2);
                } else if (IndexNum == 2) {
                    venues.DropdownSelect3.click();
                    selectAValue(3);
                } else if (IndexNum == 1) {
                    venues.DropdownSelect4.click();
                    selectAValue(4);
                }
                IndexNum = IndexNum - 1;
            }
        } else if (IndexNum == 5) {
            while (IndexNum > 0) {
                if (IndexNum == 5) {
                    venues.DropdownSelect1.click();
                    selectAValue(1);
                } else if (IndexNum == 4) {
                    venues.DropdownSelect2.click();
                    selectAValue(2);
                } else if (IndexNum == 3) {
                    venues.DropdownSelect3.click();
                    selectAValue(3);
                } else if (IndexNum == 2) {
                    venues.DropdownSelect4.click();
                    selectAValue(4);
                } else if (IndexNum == 1) {
                    venues.DropdownSelect4.click();
                    selectAValue(5);
                }
                IndexNum = IndexNum - 1;
            }
        } else if (IndexNum == 3) {
            while (IndexNum > 0) {
                if (IndexNum == 3) {
                    venues.DropdownSelect1.click();
                    selectAValue(1);
                } else if (IndexNum == 2) {
                    venues.DropdownSelect2.click();
                    selectAValue(2);
                } else if (IndexNum == 1) {
                    venues.DropdownSelect3.click();
                    selectAValue(3);
                }
                IndexNum = IndexNum - 1;
            }
        } else if (IndexNum == 2) {
            while (IndexNum > 0) {
                if (IndexNum == 2) {
                    venues.DropdownSelect1.click();
                    selectAValue(1);
                } else if (IndexNum == 1) {
                    venues.DropdownSelect2.click();
                    selectAValue(2);
                }
                IndexNum = IndexNum - 1;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void selectAValue(int Index) {
        Select select;
        if (Index == 1) {
            select = new Select(driver.findElement(By.xpath(venues.firstDropdownSelectXPATH)));
        } else if (Index == 2) {
            select = new Select(driver.findElement(By.xpath(venues.secondDropdownSelectXPATH)));
        } else if (Index == 3) {
            select = new Select(driver.findElement(By.xpath(venues.thirdDropdownSelectXPATH)));
        } else if (Index == 4) {
            select = new Select(driver.findElement(By.xpath(venues.fourthDropdownSelectXPATH)));
        } else if (Index == 5) {
            select = new Select(driver.findElement(By.xpath(venues.fifthDropdownSelectXPATH)));
        } else if (Index == 6) {
            select = new Select(driver.findElement(By.xpath(venues.sixthDropdownSelectXPATH)));
        } else if (Index == 7) {
            select = new Select(driver.findElement(By.xpath(venues.seventhDropdownSelectXPATH)));
        } else if (Index == 8) {
            select = new Select(driver.findElement(By.xpath(venues.eighthDropdownSelectXPATH)));
        } else if (Index == 9) {
            select = new Select(driver.findElement(By.xpath(venues.ninethDropdownSelectXPATH)));
        } else if (Index == 10) {
            select = new Select(driver.findElement(By.xpath(venues.tenthDropdownSelectXPATH)));
        } else {
            select = null;
        }

        List<WebElement> opts = select.getOptions();
        for (WebElement thisOpts : opts) {
            if (thisOpts.getText().contains(apkVersion)) {
                thisOpts.click();
                return;
            }
        }
        // if not apkVersion find, click 1 contains 2, for snapcall etc.
        for (WebElement thisOpts : opts) {
            if (thisOpts.getText().contains("2")) {
                thisOpts.click();
                return;
            }
        }

        // if not apkVersion find, nor   contains 2, trivia
        for (WebElement thisOpts : opts) {
            if (thisOpts.getText().contains("1")) {
                thisOpts.click();
                return;
            }
        }
    }

    //checkChangeHistory -- Check if element presents
    public void checkChangeHistory(){

        venues.changeHistoryButton.click();

        alertMsg = "Table first col type is absent";
        checkElementPresent(venues.ChangeHistoryFirstColCSS, alertMsg);

        alertMsg = "Table second col type is absent";
        checkElementPresent(venues.ChangeHistorySecondColCSS, alertMsg);

        alertMsg = "Table third col type is absent";
        checkElementPresent(venues.ChangeHistory3rdCol, alertMsg);

        alertMsg = "Table fourth col type is absent";
        checkElementPresent(venues.ChangeHistory4thCol, alertMsg);
    }

    // Save and generate bundle
    public void saveAndgenerateAndDeploy() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        log.info("Click SAVE bundle btn");
        venues.SaveBundleButton.click();
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        if (isAlertPresent()) {
            jsAlerAccept();
        }
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        log.info("Click generate bundle btn");
        venues.generateDataBundleBtn.click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        String GeneratedVerString = "0";
        if (isAlertPresent()) {
            jsAlerAccept();
            log.info("Alert present");
            String CurrentVerString = venues.WebCurrentVerString.getText();
            GeneratedVerString = CurrentVerString;

        } else {
            log.info("No Alert present");
            String CurrentVerString = venues.WebCurrentVerString.getText();
            if (CurrentVerString.isEmpty()) {
                GeneratedVerString = "1";
            } else {
                int GeneratedVerInt = (Integer.parseInt(CurrentVerString) + 1);
                GeneratedVerString = Integer.toString(GeneratedVerInt);
            }
        }

        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        log.info("Current bundle Version tobe generated: " + GeneratedVerString);
        if (venues.WebCurrentVerString.getText().contains(GeneratedVerString)) {
            log.info("Bundle version" + GeneratedVerString + " is generated");
        }

        log.info("Click Devices button");
        List<WebElement> bundlesDevicesList = venues.getBundleSectionDevicesList();
        bundlesDevicesList.get(0).click();

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        //luentWait(By.cssSelector(VenueManagementPage.bundleSectionFirstDeviceButtonCSS)).click();
        log.info("Click Unselect ALL button");
        venues.unClickAllDevice.click();

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        log.info("Click First device to deploy");
        List<WebElement> ClickOneDeviceListL = venues.getClickOneDeviceList();
        try {
            ClickOneDeviceListL.get(0).click();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                log.info(e.getMessage());
            }

            log.info("Click Deploy button");
            List<WebElement> ClickOneDeployListL = venues.getClickDeployList();
            ClickOneDeployListL.get(0).click();
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            log.info("Click Ondemand button");
            List<WebElement> getClickOndemandListL = venues.getClickOndemandList();
            getClickOndemandListL.get(0).click();
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            if (isAlertPresent()) {
                jsAlerAccept();
            }

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            if (isAlertPresent()) {
                jsAlerAccept();
            }
            log.info("Check status button");
            venues.ClickUpdateInfo.click();
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            String FirstDeviceUpdateStats = venues.UpdatingStatusFirstDevice.getText();
            if (venues.UpdatingStatusFirstDevice.getText().contains("Pending")) {
                log.info("Pending stats for first device found -- PASS");
            }
        } catch (IndexOutOfBoundsException e) {
            log.info("There is NO device registered");
        }
    }


    public void editVenueLayoutNavigateToDeviceLogs() {
        log.info("navigating to devices logs section\n");
        venues.navigateToDevicesLogs();
    }


    public void selectDeviceAndAskForLogs() {
        log.info("roll down devices dropdown\n");
        venues.rollDownDevicesDropdown();
        List<WebElement> deviceList = venues.getDevicesWithLogsList();
        log.info("check that device list is not empty\n");
        Assert.assertTrue(deviceList.size() >= 1, "devices list is not empty");
        log.info("pick up device in a random way\n");
        Random generator = new Random();
        int i = generator.nextInt(deviceList.size()) + 1;
        deviceList.get(i - 1).click();

        log.info("request log for the device selected\n");
        venues.logRequest();

        log.info("check that appropriate msg appeared\n");
        Assert.assertTrue(venues.getActualStatusObtained().getText().trim().equals("Sending..."), "status not equal to 'Sending...'\n");


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public void operateOnDevicesExamples() {
        List<WebElement> bundlesDevicesList = venues.getBundleSectionDevicesList();
        bundlesDevicesList.get(0).click();

//        list size
        int sizeee = bundlesDevicesList.size();
//        1st element
        bundlesDevicesList.get(0).click();

//        4th element
        bundlesDevicesList.get(5).click();
        Assert.assertTrue(bundlesDevicesList.get(5).getText().trim().contains("balbalblalba"));

//        ===========================================================================
//2nd approach call:
        venues.clickOnAppropriateDevice();

//        3dr approach
//        in case you need to wait some time for this element to appear
        fluentWait(By.cssSelector(VenueManagementPage.bundleSectionFirstDeviceButtonCSS)).click();


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isAlertPresent();

        if (isAlertPresent()) {


            jsAlerAccept();
//             continue workflow
            fluentWait(By.cssSelector("div>legend"));
        } else {

//            continue workflow
            fluentWait(By.cssSelector("div>legend"));
        }


        isElementPresent(By.cssSelector("el1"));
        isElementPresent(By.cssSelector("el2"));
        isElementPresent(By.cssSelector("el3"));

//        checkElementPresent();
    }


//    public void testAssociateGamesToVenues() throws IOException, InterruptedException {

    public void testAssociateGamesToVenues() throws IOException, InterruptedException {
        String testCaseID = "PIL-309";
        log.info(testCaseID + "is started");
        /*createNewChain();
        addVenueToChainSimplified();
        //  walk throug chains  one by one
        //  walk through venues assgned to chains being considered
        //        if found any    stop seach  and open it.
        //        assign game
        //        save
        //        check that layout expected apperead
        List<Integer> chainIndexes = getAutomationIndexesInChainTable(venues.getChainsTable(), "automation");
        Assert.assertTrue(chainIndexes.size() > 0, "no chain to select");
        for (int count = 0; count < chainIndexes.size(); count++) {
            venues.getChainsTable().get(chainIndexes.get(count)).click();

            log.info("check if venue list assigned to chain exists");
            boolean isVenueListAssignedToChainSelectedExists = isElementPresent(By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS), this.driver);
            if (isVenueListAssignedToChainSelectedExists) {
                log.info(" list 1 venue exists for the selected chain. Wait till the whole venue list assigned to the chain selected render");
                waitForElementGetsVisible(By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS), this.driver);  // WebElement venueList

                log.info("clicking on the first venue: " + venues.getVenueListWithinChainSelected().get(0).getText().trim()); // after venue list renders, click on any venue
                venues.getVenueListWithinChainSelected().get(0).click();
                break;
            } else {
                continue;
            }
        }
        alertMsg = "selected venue: Edit button is absent";
        checkElementPresent(VenueManagementPage.editVenueButtonCSS, this.driver, alertMsg);
        log.info("click on Edit venue button");
        venues.editVenueClick();

        alertMsg =  "Edit venue layout did not appear";
        checkElementPresent(VenueManagementPage.editVenueLayoutCSS, this.driver, alertMsg);

        alertMsg = "[Game collection] button did not appear";
        checkElementPresent(VenueManagementPage.editVenueGameColButtonCSS, this.driver, alertMsg);*/
        log.info("click on [Game collection] button");
        venues.editVenueNavigateToGameCollection();

        alertMsg = "Game collection layout didn't appear";
        checkElementPresent(VenueManagementPage.editVenueGameCollectionCSS, alertMsg);

        log.info("assigning  a game collection to the venue in a random way");

        List<WebElement> gameCollections = venues.getGameCollectionsRadiobuttonsList();
        int gameCollectionSize = gameCollections.size();


        Random randomGeneratorr = new Random();   //randomzing selection of game Collections radiobutton
        int randomInt = randomGeneratorr.nextInt(gameCollectionSize - 1);
        gameCollections.get(randomInt).click();
        alertMsg = "[Save] button is absent on tha page";
//        checkElementPresent(VenueManagementPage.gameCollectionSubmitButtonCSS, this.driver, alertMsg);
        checkElementPresent(VenueManagementPage.gameCollectionSubmitButtonCSS, alertMsg);

        log.info("submiting the Game Collection selected");
        venues.editVenueGameCollectionSelectedSubmit();

        log.info("wait for alert appears");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());
        boolean isExpectedAlertAppeared = isAlertPresent();
        Assert.assertTrue(isExpectedAlertAppeared, "expected alert didn't appear");
        log.info("click OK on the alert appeared");
        driver.switchTo().alert().accept();
        // stopping timer()
        log.info(testCaseID + ": PASSED");

    }


    public void navigateToPromotions() {
        log.info("Promotions section navigation for Chain and Venue selected");

        alertMsg = "[Promotions] button is absent";
        checkElementPresent(VenueManagementPage.editVenuePromotionButtonCSS, alertMsg);
        log.info("click on [Promotions] button");
        venues.editVenueNavigateToPromotions();
    }

    public void createMarkerBasePromotion() {
        alertMsg = "[Marker Based Promotion] Button is absent";
        checkElementPresent(PromotionsPage.addMarkerBasedPromotionButtonCSS, alertMsg);

        log.info("pressing [Marker Based Promotion] Button to create new marker based promotion");
        promotions.addMarkerBasedPromotion();
        checkForAllFieldsPresentAtMarkerBasePromotionsPage();
        fillMarkerBasedPromotionForm(promotions);

        log.info("submit promotion filled with test data");
        promotions.submitNewPromotion();
        // check that after submit, 'All' tab is present on the active page
        checkThatPromoListIsDisplayedAndAllTabIsActive(promotions);
    }

    public void checkForAllFieldsPresentAtMarkerBasePromotionsPage() {
        alertMsg = "title input for promotion is absent";
        checkElementPresent(PromotionsPage.newPromotionTitleInputCSS, alertMsg);

        alertMsg = "[Start date] calendar button is absent";
        checkElementPresent(PromotionsPage.newPromotionStartDateButtonCSS, alertMsg);

        alertMsg = "[End date] calendar button is absent";
        checkElementPresent(PromotionsPage.newPromotionEndDateButtonCSS, alertMsg);

        alertMsg = "[Games] button is absent";
        checkElementPresent(PromotionsPage.markerBasedPromotionGamesButtonToBeSelectedCSS, alertMsg);

        alertMsg = "[menu items] button is absent";
        checkElementPresent(PromotionsPage.markerBasedPromotionMenuItemsButtonToBeSelectedCSS, alertMsg);

        alertMsg = "Marker type dropdown is absent";
        checkElementPresent(PromotionsPage.markerBasedPromotionMarkerTypeDropdownCSS, alertMsg);
        alertMsg = "[Choose items] button is absent";
        checkElementPresent(PromotionsPage.newPromotionChooseItemsButtonCSS, alertMsg);

        alertMsg = "[Save] button is absent";
        checkElementPresent(PromotionsPage.newPromotionSaveButtonCSS, alertMsg);

        alertMsg = "[Cancel] button is absent";
        checkElementPresent(PromotionsPage.newPromotionCancelButtonCSS, alertMsg);
    }

    public void fillMarkerBasedPromotionForm(PromotionsPage promotions) {
        // List<String> readValuesFromCSV =getCVSDataFile();

        log.info("reading marker based promotion name form CSV file");
        //        this chain obtain test  marker based promo name
        String markerBasedName = HardcodedDataForAutomation.MARKER_BASED_PROMO_NAME + Timer.getCurrentTimestamp();
        log.info("entering promotion title");
        promotions.fillPromotionTitle(markerBasedName);

        log.info("select [Start date]");
        selectStartDate(promotions);

        log.info("select [End date]");
        selectEndDate(promotions);

        log.info("click on [Menu Items] button");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        promotions.markerBasedPromoMenuItemsSelect();
        fluentWait(By.cssSelector(PromotionsPage.markerBasedPromotionMenuItemsButtonToBeSelectedCSS)).click();

        log.info("select marker type dropdown in a random way");
        Random generator = new Random();
        int i = generator.nextInt(3);

        if (i == 0) {
            markerPopularSelect(promotions);
        } else if (i == 1) {
            markerNewSelect(promotions);
        } else {
            markerSpecialSelect(promotions);
        }

        log.info("click on [Choose Items] button");
        promotions.chooseItemsClick();

        log.info("wait until promotion list gets rendered");
        waitForElementGetsVisible(By.cssSelector(PromotionsPage.firstItemAtChooseItemsFormCSS));
        fluentWait(By.cssSelector(PromotionsPage.firstItemAtChooseItemsFormCSS));
        //TODO: NEED to add check

        log.info("select several items from the list");
        List<WebElement> availableItemsList = promotions.getAvailableItemList();
        for (int kk = 0; kk < 5; kk++) {
            availableItemsList.get(kk).click();
        }

        log.info("press [Apply] button");
        promotions.applySelectedItems();
        log.info("wait till new promotions form gets rendered back");
        waitForElementGetsVisible(By.cssSelector(PromotionsPage.newPromotionSaveButtonCSS));
    }

    public void checkThatPromoListIsDisplayedAndAllTabIsActive(PromotionsPage promotions) {

        log.info("wait till all elements on the page get rendered");
        waitForElementGetsVisible(By.cssSelector(PromotionsPage.promotionsAllTabCSS));

        alertMsg = "the Promotion list is absent";
        checkElementPresent(PromotionsPage.promotionsAllTabCSS, alertMsg);

        boolean isActive = promotions.getPromotionsAllTab().getAttribute("class").trim().contains("active");
        Assert.assertTrue(isActive, "All tab is not active");
    }

    public void selectStartDate(PromotionsPage promotions) {
        //click on start date calendar
        promotions.startDateButtonClick();

        //        roll back calendar 3 times
        promotions.startDatePrevClick();
        promotions.startDatePrevClick();
        promotions.startDatePrevClick();
        randomCalendarDaySelect(promotions);
    }

    public void selectEndDate(PromotionsPage promotions) {
        //         click on end date calendar button
        promotions.endDateButtonClick();

        //  roll forward   future calendar
        promotions.endDateNextClick();
        promotions.endDateNextClick();
        promotions.endDateNextClick();
        randomCalendarDaySelect(promotions);
    }

    public void markerPopularSelect(PromotionsPage promotions) {
        promotions.markerTypeDropdownClick();
        promotions.popularClick();
    }

    public void markerNewSelect(PromotionsPage promotions) {
        promotions.markerTypeDropdownClick();
        promotions.newClick();
    }

    public void markerSpecialSelect(PromotionsPage promotions) {
        promotions.markerTypeDropdownClick();
        promotions.specialClick();
    }

    private void randomCalendarDaySelect(PromotionsPage promotions) {
        // get list of  elements on calendar  page
        List<WebElement> daysOnCalendarPage = promotions.getCalendarDaysList();
        // get the whoe days number being displayed
        int daysCalendar = daysOnCalendarPage.size();
        // random day selection
        Random generator = new Random();
        int i = generator.nextInt(daysCalendar);
        // select day on calendar in a random way
        daysOnCalendarPage.get(i).click();
    }

    public void createDashboardPromotion() {
        alertMsg = "Add Dashboard] Button is absent";
        checkElementPresent(PromotionsPage.addDashboardPromoButtonCSS, alertMsg);
        log.info("pressing [Add Dashboard] Button to create new Dashboard promotion");
        promotions.addDashboardPromotion();
        checkForAllFieldsPresentAtDashboardPromotionPage();

        //markerBasedPageIsProperlyDisplayed
        alertMsg = "[Marker Based Promotion] Button is absent";
        checkElementPresent(PromotionsPage.addMarkerBasedPromotionButtonCSS, alertMsg);
        log.info("pressing [Marker Based Promotion] Button to create new marker based promotion");
        promotions.addMarkerBasedPromotion();

        checkForAllFieldsPresentAtMarkerBasePromotionsPage();
    }

    public void checkForAllFieldsPresentAtDashboardPromotionPage() {
        alertMsg = "title input for promotion is absent";
        checkElementPresent(PromotionsPage.newPromotionTitleInputCSS,  alertMsg);

//  cut this out due to https://jira-csv.touchtunes.com/browse/GEN-1613   ticket
        /*alertMsg = "[Start date] calendar buttons are absent";
        checkElementPresent(PromotionsPage.newPromotionStartDateButtonCSS,  alertMsg);
        alertMsg = "[End date] calendar buttons are absent";
        checkElementPresent(PromotionsPage.newPromotionEndDateButtonCSS,  alertMsg);
*/
        alertMsg = "[Image] button is absent";
        checkElementPresent(PromotionsPage.dashboardPromotionImageButtonCSS,  alertMsg);

        alertMsg = "[Video] button is absent";
        checkElementPresent( PromotionsPage.dashboardPromotionVideoButtonCSS,  alertMsg);

        alertMsg = "[Select image] button is absent";
        checkElementPresent(PromotionsPage.newPromotionSelectImageButtonCSS,  alertMsg);

        alertMsg = "[Venue] button is absent";
        checkElementPresent(PromotionsPage.dashboardPromotionVenueButtonCSS,  alertMsg);

        alertMsg = "[Games] button is absent";
        checkElementPresent(PromotionsPage.dashboardPromotionGamesButtonCSS,  alertMsg);


        alertMsg = "[Music] button is absent";
        checkElementPresent(PromotionsPage.dashboardPromotionMusicButtonCSS,  alertMsg);


        alertMsg = "[Save ] is absent";
        checkElementPresent(PromotionsPage.newPromotionSaveButtonCSS,  alertMsg);

        alertMsg = "[Cancel] button is present ont he page";
        checkElementPresent(PromotionsPage.newPromotionCancelButtonCSS,  alertMsg);
    }

}
