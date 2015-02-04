package com.tanjarine.automation.testscripts;

import com.tanjarine.automation.utilityhelpers.ElementLocatorByCriteria;
import com.tanjarine.automation.utilityhelpers.FileAttacherRobotImplementation;
import com.tanjarine.automation.utilityhelpers.Timer;
import com.tanjarine.automation.pageobjects.*;
import com.tanjarine.automation.pageobjects.menu.food.MenuCategory;
import com.tanjarine.automation.pageobjects.menu.food.MenuCommonPage;
import com.tanjarine.automation.pageobjects.menu.food.MenuItemLayout;
import com.tanjarine.automation.pageobjects.menu.food.MenuModifiers;
import com.tanjarine.automation.constants.HardcodedDataForAutomation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class PromotionPageScripts extends BaseTestDriverFrameworkMethodsOnly {

    public final static Logger log = LoggerFactory.getLogger(PromotionPageScripts.class);


    private LoginPage loginPage;
    private VenueManagementPage venues;
    private AdminHomePage homePage;
    private MenuCommonPage menuCommonPage;
    private MenuCategory menuCategory;
    private MenuItemLayout menuItemForm;
    private MenuModifiers menuModifiers;
    private AdminHomePage adminhome;
    private BundlesPage bundles;
    private PromotionsPage promotions;

    //    this is local driver, for usage within this class ONLY
    private WebDriver driver;

    private VenueManagementScripts venueMethods;


    public PromotionPageScripts(WebDriver newDriver) {

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
        promotions = PageFactory.initElements(this.driver, PromotionsPage.class);

        venueMethods = new VenueManagementScripts(driver);

    }


    // string variable for logging actions (activities) within the class
    private String alertMsg;


    /**
     * method verifying that all inputs/elements on marker based promotion page is present on the page
     */
    public void checkForAllFieldsPresentAtMarkerBasePromotionsPage() {
        alertMsg = "title input for promotion is absent";
        checkElementPresent(this.driver, PromotionsPage.newPromotionTitleInputCSS, alertMsg);

        alertMsg = "[Start date] calendar button is absent";
        checkElementPresent(this.driver, PromotionsPage.newPromotionStartDateButtonCSS, alertMsg);

        alertMsg = "[End date] calendar button is absent";
        checkElementPresent(this.driver, PromotionsPage.newPromotionEndDateButtonCSS, alertMsg);

        alertMsg = "[Games] button is absent";
        checkElementPresent(this.driver, PromotionsPage.markerBasedPromotionGamesButtonToBeSelectedCSS, alertMsg);

        alertMsg = "[menu items] button is absent";
        checkElementPresent(this.driver, PromotionsPage.markerBasedPromotionMenuItemsButtonToBeSelectedCSS, alertMsg);

        alertMsg = "Marker type dropdown is absent";
        checkElementPresent(this.driver, PromotionsPage.markerBasedPromotionMarkerTypeDropdownCSS, alertMsg);
        alertMsg = "[Choose items] button is absent";
        checkElementPresent(this.driver, PromotionsPage.newPromotionChooseItemsButtonCSS, alertMsg);

        alertMsg = "[Save] button is absent";
        checkElementPresent(this.driver, PromotionsPage.newPromotionSaveButtonCSS, alertMsg);

        alertMsg = "[Cancel] button is absent";
        checkElementPresent(this.driver, PromotionsPage.newPromotionCancelButtonCSS, alertMsg);
    }

    /**
     * method verifying that all inputs/elements  on campaign promotions page is present on the page
     */
    public void checkForAllFieldsPresentAtCampaignPromotionPage() {
        alertMsg = "Title input is absent";
        checkElementPresent(this.driver, PromotionsPage.newPromotionTitleInputCSS, alertMsg);

        alertMsg = "[Start date] calendar buttons is absent";
        checkElementPresent(this.driver, PromotionsPage.newPromotionStartDateButtonCSS, alertMsg);
        alertMsg = "[End date] calendar buttons is absent";
        checkElementPresent(this.driver, PromotionsPage.newPromotionEndDateButtonCSS, alertMsg);

        alertMsg = "[Select Image] button is absent";
        checkElementPresent(this.driver, PromotionsPage.newPromotionSelectImageButtonCSS, alertMsg);

        alertMsg = "[Choose Items] button is absent";
        checkElementPresent(this.driver, PromotionsPage.campaignPromotionChooseItemsButtonCSS, alertMsg);

        alertMsg = "[Save] button is absent";
        checkElementPresent(this.driver, PromotionsPage.newPromotionSaveButtonCSS, alertMsg);

        alertMsg = "[Cancel] button is absent";
        checkElementPresent(this.driver, PromotionsPage.newPromotionCancelButtonCSS, alertMsg);
    }


    public void checkForAllFieldsPresentAtDashboardPromotionPage() {
        alertMsg = "title input for promotion is absent";
        checkElementPresent(this.driver, PromotionsPage.newPromotionTitleInputCSS, alertMsg);

//    cuttinh these checks out due to https://jira-csv.touchtunes.com/browse/GEN-1613
/*

        alertMsg = "[Start date] calendar buttons are absent";
        checkElementPresent(PromotionsPage.newPromotionStartDateButtonCSS,  alertMsg);
        alertMsg = "[End date] calendar buttons are absent";
        checkElementPresent(PromotionsPage.newPromotionEndDateButtonCSS,  alertMsg);
*/

        alertMsg = "[Image] button is absent";
        checkElementPresent(this.driver, PromotionsPage.dashboardPromotionImageButtonCSS, alertMsg);

        alertMsg = "[Video] button is absent";
        checkElementPresent(this.driver, PromotionsPage.dashboardPromotionVideoButtonCSS, alertMsg);

        alertMsg = "[Select image] button is absent";
        checkElementPresent(this.driver, PromotionsPage.newPromotionSelectImageButtonCSS, alertMsg);

        alertMsg = "[Venue] button is absent";
        checkElementPresent(this.driver, PromotionsPage.dashboardPromotionVenueButtonCSS, alertMsg);

        alertMsg = "[Games] button is absent";
        checkElementPresent(this.driver, PromotionsPage.dashboardPromotionGamesButtonCSS, alertMsg);

        /*alertMsg = "[menu Items] button is absent";
        checkElementPresent(PromotionsPage.dashboardPromotionMenuItemsButtonCSS,  alertMsg);*/

        alertMsg = "[Music] button is absent";
        checkElementPresent(this.driver, PromotionsPage.dashboardPromotionMusicButtonCSS, alertMsg);

        alertMsg = "[Loalty] button is absent";
        checkElementPresent(this.driver, PromotionsPage.dashboardPromotionLoaltyButtonCSS, alertMsg);


        alertMsg = "[Zone one] button is empty";
        checkElementPresent(this.driver, PromotionsPage.dashboardPromoZoneOneButtonCSS, alertMsg);

        alertMsg = "[Zone two] button is empty";
        checkElementPresent(this.driver, PromotionsPage.dashboardPromoZoneTwoButtonCSS, alertMsg);

        alertMsg = "[Zone three] button is empty";
        checkElementPresent(this.driver, PromotionsPage.dashboardPromoZoneThreeButtonCSS, alertMsg);

        alertMsg = "[Save ] is absent";
        checkElementPresent(this.driver, PromotionsPage.newPromotionSaveButtonCSS, alertMsg);

        alertMsg = "[Cancel] button is present ont he page";
        checkElementPresent(this.driver, PromotionsPage.newPromotionCancelButtonCSS, alertMsg);
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
        promotions.markerBasedPromoMenuItemsSelect();

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
        waitForElementGetsVisible(this.driver, By.cssSelector(PromotionsPage.firstItemAtChooseItemsFormCSS));
        //TODO: NEED to add check

        log.info("select several items from the list");
        List<WebElement> availableItemsList = promotions.getAvailableItemList();
        for (int kk = 0; kk < 5; kk++) {
            availableItemsList.get(kk).click();
        }

        log.info("press [Apply] button");
        promotions.applySelectedItems();
        log.info("wait till new promotions form gets rendered back");
        waitForElementGetsVisible(this.driver, By.cssSelector(PromotionsPage.newPromotionSaveButtonCSS));
    }

    /**
     * method moving back start date on 3 months;  1 click = 1 month
     *
     * @param promotions
     */
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

    public void checkThatPromoListIsDisplayedAndAllTabIsActive(PromotionsPage promotions) {

        log.info("wait till all elements on the page get rendered");
        waitForElementGetsVisible(this.driver, By.cssSelector(PromotionsPage.promotionsAllTabCSS));

        alertMsg = "the Promotion list is absent";
        checkElementPresent(this.driver, PromotionsPage.promotionsAllTabCSS, alertMsg);

        boolean isActive = promotions.getPromotionsAllTab().getAttribute("class").trim().contains("active");
        Assert.assertTrue(isActive, "All tab is not active");
    }


    /**
     * method fillling campaign promotion page
     *
     * @param promotions
     * @return
     */
    public void fillCampaignPromotionForm(PromotionsPage promotions) {
        //   List<String> readValuesFromCSV = getCVSDataFile();

        log.info("read Campaign promotion name form CSV file");
        // this chain obtain test  marker based promo name
        String campaignPromoName = HardcodedDataForAutomation.CAMPAIGN_PROMO_NAME + Timer.getCurrentTimestamp();
        log.info("entering promotion title");
        promotions.fillPromotionTitle(campaignPromoName);

        log.info("select [Start date]");

        selectStartDate(promotions);

        log.info("select [End date]");
        selectEndDate(promotions);

        log.info("select Image for Campaing promotion");
        promotions.selectImageButtonClick();


        driver.manage().window().maximize();
        log.info("path to campaign promotion: " + HardcodedDataForAutomation.FILE_PATH_TO_PROMOTION_IMAGE);
        FileAttacherRobotImplementation.fileAttachmentUsingRobot(HardcodedDataForAutomation.FILE_PATH_TO_PROMOTION_IMAGE);

        log.info("click on [Choose Items] button");
        promotions.chooseItemsClick();

        waitForElementGetsVisible(this.driver, By.cssSelector(PromotionsPage.firstItemAtChooseItemsFormCSS));

        log.info("select several items from the list");
        List<WebElement> availableItemsList = promotions.getAvailableItemList();
        for (int kk = 0; kk < 5; kk++) {
            availableItemsList.get(kk).click();
        }

        log.info("press [Apply] button");
        promotions.applySelectedItems();

        log.info("wait for [Save] button gets visible");
        waitForElementGetsVisible(this.driver, By.cssSelector(PromotionsPage.newPromotionSaveButtonCSS));

        log.info("wait for [Cancel] button gets visible");
        waitForElementGetsVisible(this.driver, By.cssSelector(PromotionsPage.newPromotionCancelButtonCSS));

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    /**
     * this test DISABLED as     need to adjust File upload operation when running through CI:   hudson/jenkins
     *
     * @throws java.io.IOException
     */
    public void createCampaignPromotion() {
        alertMsg = "[Campaign Promotion] Button is absent";
        checkElementPresent(this.driver, PromotionsPage.addCampaignPromotionButtonCSS, alertMsg);
        log.info("pressing [Campaign Promotion] Button to create Campaign promotion");
        promotions.addCampaignPromotion();

        checkForAllFieldsPresentAtCampaignPromotionPage();
        fillCampaignPromotionForm(promotions);
        log.info("submit promotion filled with test data");
        promotions.submitNewPromotion();
        // check that after submit, 'All' tab is present on the active page
        checkThatPromoListIsDisplayedAndAllTabIsActive(promotions);
    }

    public void createDashboardPromotion() {
        alertMsg = "Add Dashboard] Button is absent";
        checkElementPresent(this.driver, PromotionsPage.addDashboardPromoButtonCSS, alertMsg);
        log.info("pressing [Add Dashboard] Button to create new Dashboard promotion");
        promotions.addDashboardPromotion();
        checkForAllFieldsPresentAtDashboardPromotionPage();

        //markerBasedPageIsProperlyDisplayed
        alertMsg = "[Marker Based Promotion] Button is absent";
        checkElementPresent(this.driver, PromotionsPage.addMarkerBasedPromotionButtonCSS, alertMsg);
        log.info("pressing [Marker Based Promotion] Button to create new marker based promotion");
        promotions.addMarkerBasedPromotion();

        checkForAllFieldsPresentAtMarkerBasePromotionsPage();
    }


    public void createMarkerBasePromotion() {
        alertMsg = "[Marker Based Promotion] Button is absent";
        checkElementPresent(this.driver, PromotionsPage.addMarkerBasedPromotionButtonCSS, alertMsg);

        log.info("pressing [Marker Based Promotion] Button to create new marker based promotion");
        promotions.addMarkerBasedPromotion();
        checkForAllFieldsPresentAtMarkerBasePromotionsPage();
        fillMarkerBasedPromotionForm(promotions);

        log.info("submit promotion filled with test data");
        promotions.submitNewPromotion();
        // check that after submit, 'All' tab is present on the active page
        checkThatPromoListIsDisplayedAndAllTabIsActive(promotions);
    }


    /**
     * this method represents user navigation to promotions section
     */
    public void navigateToPromotions(String chainNeeded) {
        log.info("Promotions section navigation for Chain and Venue selected");
        log.info("chain name that all testing actions on promotions tobe performed: " + chainNeeded);
        // navigate to venue management

        venueMethods.navigateToVenueMangementPage();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("searching chain in chain list: " + chainNeeded);
        // search needed chain
//        int chainToSelectIndex = getTableElementIndexByString(venues.getChainsTable(), chainNeeded);
        int chainToSelectIndex = ElementLocatorByCriteria.getTableElementIndexByString(venues.getChainsTable(), chainNeeded);
        // showing logs in case chain to operate with promotions NOT found
        if (chainToSelectIndex < 0) {
            Assert.assertTrue(true, "Not found chain to select index");
        }
        Assert.assertTrue(chainToSelectIndex >= 0, "chain to work with promotions NOT found in chain list");

        log.info("index of chain found to work with promotions: " + chainToSelectIndex);

        log.info("clicking on the chain found in chain list");
        venues.getChainsTable().get(chainToSelectIndex).click();

        log.info("check if venue list assigned to the  chain selected exists");
        boolean isVenueListAssignedToChainSelected = isElementPresent(this.driver, By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS));

        // if : venue list is shown =>   click on the first that is shown on chain details window
        if (isVenueListAssignedToChainSelected) {
            log.info(" at list 1 venue exists for the selected chain. Wait till the whole venue list assigned to the chain selected render");
            /*WebElement venueList =*/
            waitForElementGetsVisible(this.driver, By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS));
            // TODO: ADD CHECK
            // after  venue list renders => click on any venue
            log.info(" clicking on the  venue: " + venues.getVenueListWithinChainSelected().get(0).getText().trim());
            venues.getVenueListWithinChainSelected().get(0).click();
        } else {
            Assert.assertTrue(isVenueListAssignedToChainSelected, "venue assigned to the chain selected is absent");
        }
        // check that  'Edit' venue button is present
        alertMsg = "selected vnuee: Edit button is absent";
        checkElementPresent(this.driver, VenueManagementPage.editVenueButtonCSS, alertMsg);
        log.info("click on Edit venue button");
        venues.editVenueClick();

        // checking that create venue layout appeared!!!
        alertMsg = "Edit venue layout is absent";
        checkElementPresent(this.driver, VenueManagementPage.editVenueLayoutCSS, alertMsg);
        // checking that Promotions button is present
        alertMsg = "[Promotions] button is absent";
        checkElementPresent(this.driver, VenueManagementPage.editVenuePromotionButtonCSS, alertMsg);
        log.info("click on [Promotions] button");
        venues.editVenueNavigateToPromotions();
    }
}
