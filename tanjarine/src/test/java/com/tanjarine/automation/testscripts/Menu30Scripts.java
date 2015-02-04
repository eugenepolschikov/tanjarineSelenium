package com.tanjarine.automation.testscripts;

import com.tanjarine.automation.pageobjects.*;
import com.tanjarine.automation.pageobjects.menu.food.MenuCategory;
import com.tanjarine.automation.pageobjects.menu.food.MenuCommonPage;
import com.tanjarine.automation.pageobjects.menu.food.MenuItemLayout;
import com.tanjarine.automation.pageobjects.menu.food.MenuModifiers;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.tanjarine.automation.utilityhelpers.Timer.*;
import static com.tanjarine.automation.pageobjects.menu.food.MenuCategory.addCategoryCSS;
import static com.tanjarine.automation.pageobjects.menu.food.MenuCommonPage.searchInputCSS;
import static com.tanjarine.automation.pageobjects.menu.food.MenuItemLayout.*;
import static com.tanjarine.automation.constants.HardcodedDataForAutomation.*;

/**
 * Created by ypolshchykau on 16.09.2014.
 */
public class Menu30Scripts extends BaseTestDriverFrameworkMethodsOnly {

    public final static Logger log = LoggerFactory.getLogger(Menu30Scripts.class);

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


    public Menu30Scripts(WebDriver newDriver) {

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


    public void navigateToEditVenueLayout() {
        String alertMsg = "Global Admin dropdown is absent";
        checkElementPresent(this.driver, AdminHomePage.headerDownsCSS, alertMsg);
        log.info("click Global Admin header section");
        homePage.globalAdminClick();
        alertMsg = "Venue Management section is absent";
        checkElementPresent(this.driver, AdminHomePage.venueManagementHeaderCSS, alertMsg);

        log.info("click venue management section");
        homePage.venueManagementSectionClick();

        boolean isVneHeaderAppeared = isElementPresent(this.driver, By.cssSelector(VenueManagementPage.venueMgmtLeftColCSS));
        Assert.assertTrue(isVneHeaderAppeared, "header of Venue Management section is absent");
        Assert.assertTrue(isVneHeaderAppeared && venues.getVenueMgmtHeader().getText().trim().equals("Venue management"), "FAILED: cant navigate to venue management section");

    }


    public void pickUpChainByName(String chainName) {
        log.info("pick up automation chain: " + chainName + "\n");
        pickupAutomationChain(this.driver, venues, chainName);

    }

    public void pickUpVenueByName(String venueName) {
        log.info("select venue: " + venueName + "\n");
//        pickupFirstVenueUnderChainSelected(this.driver, venues);
        pickUpVenueByName(this.driver, venues, venueName.toLowerCase());

    }

    public void pickUpFirstVenue() {
        log.info("select first venue in chain\n");
        pickupFirstVenueUnderChainSelected(this.driver, venues);
    }

    public void switchToEmenuSection() {
        log.info("navigate to eMenu section\n");
        menuCommonPage.navigateToeMenuSection();
    }


    public void addCategory() {

        log.info("check that [Add category] button is present in layout\n");
        /*boolean isCategoryPresent=isElementPresent(this.driver,By.cssSelector(MenuCommonPage.addCategoryCSS));
        Assert.assertTrue(isCategoryPresent);*/
        try {

            Thread.sleep(4000);
            waitTillCommonMenuRenders();

        } catch (Throwable e) {
            Assert.assertTrue(true, "[Add category] button is absent in layout\n");
        }
        fluentWait(this.driver, By.cssSelector(MenuCategory.addCategoryCSS));
        log.info("add category within menu navigated\n");
        menuCategory.addCategory();
    }

    public void waitTillCommonMenuRenders() {

        fluentWait(this.driver, By.cssSelector(MenuCommonPage.searchInputCSS));
        menuCommonPage.setFocusOnSearchInput();
        waitForElementGetsVisible(this.driver, By.cssSelector(searchInputCSS));


        fluentWait(this.driver, By.cssSelector(addCategoryCSS));
        waitForElementGetsVisible(this.driver, By.cssSelector(addCategoryCSS));

        fluentWait(this.driver, By.cssSelector(MenuCommonPage.categoryListCSS));
        waitForElementGetsVisible(this.driver, By.cssSelector(MenuCommonPage.categoryListCSS));

    }


    //    fill category layout:
    public void fillCategoryData() {
        log.info("fill category name:\n");
        menuCategory.fillCategoryName(PART_OF_MENU_CATEGORY_NAME + getCurrentTimestamp());

        log.info("fill category description:\n");
        menuCategory.fillCategoryDescription(PART_OF_MENU_CATEGORY_DESCRIPTION + getCurrentTimestamp());


    }

    //    fill subcategory layout:
    public void fillSubcategoryData() {
        log.info("fill subcategory name:\n");

        menuCategory.getAllSubcategoriesNameList().get(menuCategory.getAllSubcategoriesNameList().size() - 1)
                .sendKeys(PART_OF_SUBCATEGORY_NAME + getCurrentTimestamp());

        log.info("fill subcategory description:\n");

        menuCategory.getAllSubcategoriesDescriptionList().get(menuCategory.getAllSubcategoriesDescriptionList().size() - 1)
                .sendKeys(PART_OF_SUBCATEGORY_DESCRIPTION + getCurrentTimestamp());

    }

    public void submitNewAutomationCategory() {
        log.info("submitting new category:\n");
        menuCategory.submitNewCategory();


    }

    public void submitNewAutomationSubcategory() {

        log.info("submitting new subcategory:\n");
       /* if (isElementPresent(this.driver, By.cssSelector(MenuCategory.saveCategoryButtonCSS))) {
            waitForElementGetsVisible(this.driver, By.cssSelector(MenuCategory.saveNewSubcategoryButtonCSS));
            fluentWait(this.driver,By.cssSelector(MenuCategory.saveNewSubcategoryButtonCSS)) ;
            menuCategory.submitNewCategory();
        }*/
        if (isElementPresent(this.driver, By.cssSelector(MenuCategory.saveNewSubcategoryButtonCSS))) {
//            waitForElementGetsVisible(this.driver, By.cssSelector(MenuCategory.saveNewSubcategoryButtonCSS));
            menuCategory.submitNewSubcategory();

        }

    }

    public void checkThatPassedForCategoryActions() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("check that user is at common menu layout\n");
//        indicators for this point :
// 1) check that categories list is present
// 2) check that footer is present;
        boolean firstCondition = isElementPresent(this.driver, By.cssSelector(MenuCommonPage.categoryListCSS));
        boolean secondCondition = isElementPresent(this.driver, By.cssSelector(MenuCommonPage.eMenuFooterCSS));

        Assert.assertTrue(firstCondition && secondCondition, " user is NOT at common menu layout\n");

    }

    public void checkPassConditionWhileItemCreate() {

        boolean firstCondition = isElementPresent(this.driver, By.cssSelector(newItemFormNewItemNameCSS));
        boolean secondCondition = isElementPresent(this.driver, By.cssSelector(newemenuItemModuleDescriptionCSS));
        boolean thirdCondition = isElementPresent(this.driver, By.cssSelector(newmenuItemDetailDescriptionCSS));

        Assert.assertTrue(firstCondition && secondCondition && thirdCondition, " user is NOT at add new menu item layout\n");
    }


    //    deactivate list of categories
    public void categoryListDeactivation() throws InterruptedException {
        log.info("deactivate categories list\n");

        /*for (WebElement a: menuCommonPage.getCategoryActivateDeactivateButtonList()) {
            a.click();
            confirmCategoryDeactivation();
            fluentWait(this.driver, By.cssSelector(MenuCommonPage.categoryListCSS));
            waitForElementGetsVisible(this.driver, By.cssSelector(MenuCommonPage.categoryListCSS));
//            @todo   write function  wait element get clickable!!!!
//            @todo pick up any item from categories list

        } */
        menuCommonPage.getCategoryActivateDeactivateButtonList().get(0).click();
        Thread.sleep(2500);
        confirmCategoryDeactivation();
        Thread.sleep(2500);

    }

//    activate list of categories

    public void categoryListActivate() {
     /*   log.info("activate categories list\n");
        for (WebElement a: menuCommonPage.getCategoryActivateDeactivateButtonList()) {
            a.click();
            fluentWait(this.driver, By.cssSelector(MenuCommonPage.categoryListCSS));
            waitForElementGetsVisible(this.driver, By.cssSelector(MenuCommonPage.categoryListCSS));


 }
*/

        menuCommonPage.getCategoryActivateDeactivateButtonList().get(0).click();


    }


    public void confirmCategoryDeactivation() {
        log.info("confirming category deactivation\n");
        try {
            fluentWait(this.driver, By.cssSelector(MenuCommonPage.confirmCategoryDeactivationButtonCSS));
            menuCommonPage.confirmDeactivation();
        } catch (Throwable e) {
            Assert.assertFalse(true, "deactivation confirm layout didn't appear\n");
        }
    }

    public void leaveEmenu() {
        log.info("check that [Admin home] button is present\n");
        isElementPresent(this.driver, By.cssSelector(MenuCommonPage.menuLayoutAdminHomeButtonCSS));
//        fluentWait(this.driver, By.cssSelector(MenuCommonPage.menuLayoutAdminHomeButtonCSS));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("navigate to admin home section");
        menuCommonPage.navigateBackToAdminHome();
        log.info("check that 'welcome to tanjarine admin' msg is shown'");
        fluentWait(this.driver, By.cssSelector(AdminHomePage.adminHomeVelcomeCSS));
        boolean actualValueMsg = adminhome.getAdminHomeVelcome().getText().trim().contains("Welcome to Tanjarine Admin");
        Assert.assertTrue(actualValueMsg, "user didn't navigate from emEnu section\n");
    }

    public void selectFirstProductCategory() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        menuCommonPage.getEmenuCategoryList().get(0).click();
        log.info("following product category selected: " + menuCommonPage.getEmenuCategoryList().get(0).getText().trim() + "\n");
        log.info("wait till category elements will render\n");

//        wait till product items sublist appear
        fluentWait(this.driver, By.cssSelector(MenuCommonPage.tableMenuItemsCSS));
        waitForElementGetsVisible(this.driver, By.cssSelector(MenuCommonPage.tableMenuItemsCSS));
    }

    public void addNewSubcategory() {
        log.info("add new subcategory within category selected:\n");
        menuCommonPage.addSubcateogry();

    }

    public void addNewMenuItem() {
        log.info("add new menu item and wait till new item layout appears\n");
        menuCommonPage.addNewItem();
        fluentWait(this.driver, By.cssSelector(addNewItemFormCSS));
        waitForElementGetsVisible(this.driver, By.cssSelector(addNewItemFormCSS));

    }


    public void newMenuItemFillWithData(String itemName) {
        menuItemForm.fillNewItemName(itemName);

        menuItemForm.itemTypeDropdownRolldown();
        waitForElementGetsVisible(this.driver, By.cssSelector(itemType2x1CSS));

        menuItemForm.item2x1Click();

//      possible selections of items of other type(-s)
//        menuCommonPage.item1x1Click();
//        menuCommonPage.item2x2Click();
        menuItemForm.fillModuleDescription(PART_OF_MODULE_DESCRIPTION + itemName + ": " + RandomStringUtils.randomAlphanumeric(25) + getCurrentTimestamp());
        menuItemForm.fillDetailDecription(PART_OF_DETAILED_DESCRIPTION + itemName + ": " + RandomStringUtils.randomAlphanumeric(25) + getCurrentTimestamp());


        menuItemForm.makeImagesSectionAppear();

        menuModifiers.addModifier();


    }

    public void menuItemFillIngredientsArea() {
        log.info("set focus to ingredients area\n");
        menuItemForm.setFocusToIngredientsArea();

        fluentWait(this.driver,By.cssSelector(ingredientsInputCSS));
        log.info("fill ingredients area with data\n");
        menuItemForm.fillIngredients(INGREDIENTS+ getCurrentTimestamp());

    }

    public void menuItemFillNutritionArea(){
        log.info("set focus to nutrition area\n");
        menuItemForm.setFocusToNutritionArea();

        fluentWait(this.driver, By.cssSelector(caloriesInputCSS));
        fluentWait(this.driver, By.cssSelector(caloriesFromFatCSS));

        log.info("fill calories input\n");
        menuItemForm.fillInCalories(RandomStringUtils.randomNumeric(2));

        log.info("fill calories from fat\n");
        menuItemForm.fillInCaloriesFromFat(RandomStringUtils.randomNumeric(2));

        log.info("fill in total fat in grams\n");

    }


    public void menuItemFillDescriptorsArea(){
        log.info("set focus to desctiptor area\n");
        menuItemForm.setFocusToDesctiptorsArea();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("pick Up two descriptors in a random way");
        menuItemForm.selectOneRandomDescriptor();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        menuItemForm.selectOneRandomDescriptor();

    }


    //========= footer methods ========
    public void pickUpFooter() throws InterruptedException {
        Thread.sleep(1000);

        log.info("add footer:\n");
        menuCommonPage.addFooter();

    }

    public void fillFooterTextArea() {
        log.info("fill footer\n");
        menuCommonPage.getFooterTextArea().sendKeys(COMMON_MENU_FOOTER_TEXT_AREA_DESCRIPTION);
    }

    public void editFooterTextArea() {
        log.info("clear footer contents\n");
        menuCommonPage.getFooterTextArea().clear();
        log.info("editing footer's text area:\n");
        menuCommonPage.getFooterTextArea().sendKeys("EDITED" + COMMON_MENU_FOOTER_TEXT_AREA_DESCRIPTION);

    }

    public void submitFooter() {

        log.info("submit footer:\n");
        menuCommonPage.submitFooter();
    }


    //==============================================================================================
//    =============== BUNDLES  AND DEVICE MANAGEMENT ACTIVITIES ================================
    public void navigateToBundlesSection() {
        bundles.navigateToBundlesSection();
    }

    public void navigateToTabletsSubsection() {
        bundles.selectTablets();
        fluentWait(this.driver, By.cssSelector(BundlesPage.apkAssignmentFormCSS));
        waitForElementGetsVisible(this.driver, By.cssSelector(BundlesPage.apkAssignmentFormCSS));
    }

    public void printLastBundleVersionDeployed() {
        log.info("last bundle version deployed on tablet(-s) is: " + bundles.getLastBundleVersionDeployed().getText().trim() + "\n");
    }

    public void devicesAvailableForBundle() {
//        bundles.clickDevicesAvailableForBundleGenerated();
        fluentWait(this.driver, By.cssSelector(BundlesPage.dataBundlesTableCSS));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        fluentWait(this.driver, By.cssSelector(BundlesPage.devicesButtonAgainstLastGeneratedBundleCSS)).click();

        fluentWait(this.driver, By.cssSelector(BundlesPage.listOfAvailableDevicesCSS));
        waitForElementGetsVisible(this.driver, By.cssSelector(BundlesPage.listOfAvailableDevicesCSS));

        log.info("bundle version just GENERATED to be PUSHED on tablet NOW: " + bundles.getLastBundleVersionDeployed().getText().trim() + "\n");
    }

    public void generateBundleAfterChanges() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bundles.generateBundle();

        fluentWait(this.driver, By.cssSelector(BundlesPage.dataBundlesTableCSS));
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


    }


    public void pickUpAppropriateTabletForDeploy(String macOfTablet) {

//        untick all devices
        bundles.untickAllTablets();

        List<WebElement> macList = bundles.getMacListOfDevicesAvailable();
        List<WebElement> tickBoxList = bundles.getTickBoxListForDevicesAvailable();

        int foundIndex = -1;
        for (int i = 0; i < macList.size(); i++) {
            log.info(macList.get(i).getText().trim().toLowerCase() + "\n");
            if (macList.get(i).getText().trim().toLowerCase().contains(macOfTablet.toLowerCase())) {
                foundIndex = i;
            }
        }

        if (foundIndex < 0) {
            log.info("no device found with MAC expected for this venue\n");
        }

        if (foundIndex > 0) {

            log.info("selecting MAC:" + macOfTablet + " with index " + (foundIndex + 1) + " \n");
            tickBoxList.get(foundIndex).click();

            //        deploy on tablet
            bundles.deployLatest();
            waitForElementGetsVisible(this.driver, By.cssSelector(BundlesPage.deployOnDemandSubselectCSS));
            fluentWait(this.driver, By.cssSelector(BundlesPage.deployOnDemandSubselectCSS)).click();

        }


    }


    public void listAllDevicesAvailable() {
        bundles.rollDownDevicesCount();
        bundles.tickAllDevices();
        waitForElementGetsVisible(this.driver, By.cssSelector(BundlesPage.deviceRecordsForTheBundleDropdownCSS));

    }
//==============================================================================================
//=================ENDOF:  BUNDLES  AND DEVICE MANAGEMENT ACTIVITIES ================================




//    @TODO implement finalizing methods
    public void cleanUpAutomationCategories(){

    }


    public void cleanUpAutomationSubcategories(){

    }

}



