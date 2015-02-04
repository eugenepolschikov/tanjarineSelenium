package com.tanjarine.automation.testscripts;

import com.tanjarine.automation.utilityhelpers.JsCommandsExecutor;
import com.tanjarine.automation.pageobjects.*;
import com.tanjarine.automation.pageobjects.menu.food.MenuCategory;
import com.tanjarine.automation.pageobjects.menu.food.MenuCommonPage;
import com.tanjarine.automation.pageobjects.menu.food.MenuItemLayout;
import com.tanjarine.automation.pageobjects.menu.food.MenuModifiers;
import com.tanjarine.automation.constants.HardcodedDataForAutomation;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static com.tanjarine.automation.utilityhelpers.AutoItScriptExecutor.*;
import static com.tanjarine.automation.utilityhelpers.Timer.*;


public class GameManagementScripts extends BaseTestDriverFrameworkMethodsOnly {
    public final static Logger log = LoggerFactory.getLogger(GameManagementScripts.class);

    private LoginPage loginPage;
    private VenueManagementPage venues;
    private AdminHomePage homePage;
    private MenuCommonPage menuCommonPage;
    private MenuCategory menuCategory;
    private MenuItemLayout menuItemForm;
    private MenuModifiers menuModifiers;
    private AdminHomePage adminhome;
    private BundlesPage bundles;

    private GameManagementPage games;

    //    this is local driver, for usage within this class ONLY
    private WebDriver driver;

//    String variables for logging automation script event(-s) in scope of this class
    private String alertMsg;



    public GameManagementScripts(WebDriver newDriver) {

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

        games = PageFactory.initElements(this.driver, GameManagementPage.class);

    }



    public void navigateToGamesPage() {
        alertMsg = "Global Admin dropdown is absent";
        checkElementPresent(this.driver,AdminHomePage.headerDownsCSS, alertMsg);
        log.info("click Global Admin (header section)");  //works for UI mode ONLY
        homePage.globalAdminClick();

        alertMsg = "Game Catalog section is absent";
        checkElementPresent(this.driver,AdminHomePage.gameCatalogHeaderCSS, alertMsg);
        log.info("click game catalog section");
        homePage.gameCatalogSelectingInDropdown();


        boolean isExpectedVneHeader = isElementPresent(this.driver,By.cssSelector(GameManagementPage.gameManagementButtonLeftColumnCSS));
        Assert.assertTrue(isExpectedVneHeader, "[Game Management] button is present");

        String expectedTextHeader = "Game management";
        boolean isNavigatedToGamesPage = games.getGameManagementButtonLeftColumn().getText().trim().equals(expectedTextHeader);
        Assert.assertTrue(isExpectedVneHeader && isNavigatedToGamesPage, "FAILED: user failed to navigate to Games Catalogue page");
    }




    public void addGameNavigateToPrimaryMediaSection() throws InterruptedException, IOException {
        // wait till add game layout page with all appropriate inputs appears
        fluentWait(this.driver, By.cssSelector(GameManagementPage.addGameLayoutHeaderCSS));
        fillInNewGameBaseTabInputs();
        // navigating to Primary media tab
        alertMsg = "Primary media section is present";
        checkElementPresent(this.driver,GameManagementPage.addGamePrimaryMediaSectionCSS, alertMsg);
        log.info("navigate to Add Game: Primary Media section");
        games.addGameNavigateToPrimaryMediaSection();
        // filling in Primary media section
        fillInNewGamePrimaryMediaTabInputs();

    }

    public void addGame() {

        alertMsg = "[Add Game] button is absemt";
        checkElementPresent(this.driver, GameManagementPage.addGameButtonCSS, alertMsg); // calculating overall test timeExecution
        log.info("click [Add Game] button in left column");
        games.addGame();
    }

    public void enterGameCollectionNameNDesc() throws IOException {

        alertMsg = "input collection name is absent";
        checkElementPresent(this.driver, GameManagementPage.newCollectionNameCSS, alertMsg);
        log.info("enter collection name");
        games.enterCollectionName(HardcodedDataForAutomation.GAME_COLLECTION_NAME + getCurrentTimestamp());

        alertMsg = "collection description is absent";
        checkElementPresent(this.driver, GameManagementPage.newCollectionDescriptionCSS, alertMsg);
        log.info("enter collection description description");
        games.enterCollectionDescription(HardcodedDataForAutomation.GAME_COLLECTION_DESCRIPTION + getCurrentTimestamp());
    }

    public void saveCollection() {
        alertMsg = "[Save collection] button is absent";
        checkElementPresent(this.driver, GameManagementPage.saveCollectionButtonCSS, alertMsg);
        log.info("click [save collection] button ");
        games.saveCollection();

        fluentWait(this.driver, By.cssSelector(GameManagementPage.collectionsTableCSS));
        // check that game collections table is shown
        boolean isGameCollectionList = isElementPresent(this.driver, By.cssSelector(GameManagementPage.collectionsTableCSS));
        Assert.assertTrue(isGameCollectionList, "game collections table is Absent");
    }

    public void selectAndAddGame() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        alertMsg = "choose games layout didn't appear";
        checkElementPresent(this.driver, GameManagementPage.chooseGamesHeaderLabelCSS, alertMsg);

        List<WebElement> gamelistToChoos = games.getGamesListToChoose();
        Random randomGeneratorr = new Random();
        int randomInt = randomGeneratorr.nextInt(gamelistToChoos.size());
        log.info("selecting the game category: " + gamelistToChoos.get(randomInt).getText().trim());
        gamelistToChoos.get(randomInt).click();

        waitForElementGetsVisible(this.driver, By.cssSelector(GameManagementPage.gameCategoryListCSS_partial + randomInt + " >li:first-child div.row div.col-md-10>h4"));
        //TODO: ADD CHECK

        List<WebElement> fullGameLissst = driver.findElements(By.cssSelector(GameManagementPage.gameCategoryListCSS_partial + randomInt + " div.row h4"));
        for (int count = 0; count < fullGameLissst.size(); count++) {
            WebElement gameTemp = fullGameLissst.get(count);
            log.info("selecting Game: clicking " + gameTemp.getText().trim());
            gameTemp.click();
        }
        alertMsg = "[Add selected button] is absent";
        checkElementPresent(this.driver, GameManagementPage.addSelectedButtonCSS, alertMsg);
        log.info("click [Add selected] button");
        games.clickAddSelected();

    }

    public void addThisGame() {

        alertMsg = "[Add games] button is absent";
        checkElementPresent(this.driver, GameManagementPage.addGamesButtonCSS, alertMsg);
        log.info("Press [Add games] button");
        games.addGames();
    }

    public void createNewGameCollection() {

        alertMsg = "[Create new] button collection  is absent";
        checkElementPresent(this.driver, GameManagementPage.createNewGameCollectionCSS, alertMsg);
        log.info("press [Create new ] collection button");
        games.createNewGameCollection();
    }

    public void navigateToGameCollection() {
        alertMsg = "[Game collections] button is absent";

        checkElementPresent(this.driver, GameManagementPage.gameCollectionButtonCSS, alertMsg);
        log.info("Navigate to Game collections");
        games.gameCollectionsNavigate();
    }

    /**
     * ***************************private methods for testcases*******************************************************
     */


    public void automationGameCollectionsCleanUp() {
        final String automationNamePatternToFind = "automationtest";
//        List<WebElement>  gameColectntsList = games.getGameCollectionList();
        List<WebElement> editButtonList = games.getEditGameCollectionButtonList();
        for (int i = games.getGameCollectionList().size() - 1; i >= 0; i--) {
            if (games.getGameCollectionList().get(i).getText().toLowerCase().trim().contains(automationNamePatternToFind)) {
                editButtonList.get(i).click();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                JsCommandsExecutor.jsClick(this.driver, GameManagementPage.gameCollectionDeleteHiddenLocatorCSS);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void addToCollectionsNav() {
        // navigating to  Add to collections
        alertMsg = "Add to collections section is absent";
        checkElementPresent(this.driver, GameManagementPage.addGameAddToCollectionsSectionCSS, alertMsg);
        log.info("navigate to Add Game: Add to collections section");
        games.addToCollectionsNavigate();
        alertMsg = "Add Game-> Add to collections: 'Select ALl' button is absent";
        checkElementPresent(this.driver, GameManagementPage.addGameAddToCollectionsSelectAllCSS, alertMsg);
        log.info("click Select All button, Add Game-> Add to collections");
        games.addToCollectionsSelectAllClick();

        alertMsg = "submit button [Save] is absent";
        checkElementPresent(this.driver, GameManagementPage.submitButtonCSS, alertMsg);
        log.info("click [Save] button to Add Game-> Add to collections");
        games.submitGame();
        // wait till game list will be shown
        fluentWait(this.driver, By.cssSelector(GameManagementPage.activeTabCSS));
        //  check that active tab os shown
        alertMsg = "active tab in the Games table is absent";
        checkElementPresent(this.driver, GameManagementPage.activeTabCSS, alertMsg);
        log.info("Add game: appropriate tab is shown after game submit");
        boolean isExpected = isElementPresent(this.driver, By.cssSelector(GameManagementPage.activeTabCSS));
        Assert.assertTrue(isExpected, "active tab is absent");
    }

    public void fillInNewGameBaseTabInputs() throws InterruptedException {
        String title = "automation" + "_testGame_" + RandomStringUtils.randomAlphabetic(4);
        String description = "this game was added by automation     for testing  purposes";
        String tag = "test";

        alertMsg = "Add Game: title input is absent";
        checkElementPresent(this.driver, GameManagementPage.addGameLayoutTitleInputCSS, alertMsg);
        log.info("ADD game: enter title: " + title);
        games.addGameEnterTitle(title);

        alertMsg = "Add Game: description input is absent";
        checkElementPresent(this.driver, GameManagementPage.addGameLayoutDescriptionCSS, alertMsg);
        log.info("ADD game: enter description: " + description);
        games.addGameEnterDescription(description);

        // "wait till Genre radioButtons sectoin becoms visible and clickable"
        // wait till genre RadioButton become visible and clickabe
        WebElement genreItem = waitForElementGetsVisible(this.driver, By.cssSelector(GameManagementPage.addGameLayoutLastGenreCSS));
        // TODO: add check
        log.info("click on the last genre item");
        genreItem.click();

        // randomizing Genre Item Selection
        int sizeGenreList = games.getAddGameLayoutGenreList().size();
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(sizeGenreList - 1);
        // click on random genre item
        log.info("click on the random genre item");
        games.getAddGameLayoutGenreList().get(randomInt).click();

        // selecting audience
        alertMsg = "'Teen' audience radiobutton is absent";
        checkElementPresent(this.driver, GameManagementPage.addGameLayoutTeenAudienceRadiobuttonSelectCSS, alertMsg);
        log.info("select 'Teen' radiobutton in Audience section");
        games.selectAudience();

        // randominzing    mode selection
        Random randomModeGenerator = new Random();
        int randomInt2 = randomModeGenerator.nextInt(2);
        if (randomInt == 1) {
            alertMsg = "Single mode radioButton is absent";
            checkElementPresent(this.driver, GameManagementPage.addGameLayoutModeSingleCSS, alertMsg);
            log.info("clicknig on Single mode radiobutton");
            games.clickSingleMode();
        } else {
            alertMsg = "Multiplayer mode radioButton is absent";
            checkElementPresent(this.driver, GameManagementPage.addGameLayoutModeMultiplayerCSS, alertMsg);
            log.info("clicknig on Multiplayer mode radiobutton");
            games.clickMultiplayerMode();
        }
        // "Add game:  click in Select file input area to get the control focus there ''"
        //  games.selectFileInputClick()
        log.info("press [Selet file] for .apk game selection");
        games.selectApkGameClick();
        // additional wait to wait till browser 'Open file popUp appears'
        // driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        // ABSOLUTE path to file that is expected to be added:
        // String gamePathToApkFile = "file://E:/exadel_projects/TouchtunesAutomation/TanjarineSeleniumTestsDevelopment/web101/automation/selenium/testData/games/filesForUpload/rootexplorer.apk";
        autoItScriptExecutor("testData/games/filesForUpload", "rootexplorer.apk");

        alertMsg = "Create game: tag input is absent";
        checkElementPresent(this.driver,GameManagementPage.addGameLayoutTagsInputCSS, alertMsg);
        log.info("entering Create Game: Tag input value: " + tag);
        games.inputTags(tag);
        // alternative solution:
        // WebElement tagInput=waitForElementGetsVisible(By.cssSelector(GameManagementPage.addGameLayoutTagsInputCSS));
        // tagInput.sendKeys(tag);
        // tagInput.sendKeys(tag2);
    }

    public void fillInNewGamePrimaryMediaTabInputs() throws InterruptedException, IOException {
        waitForElementGetsVisible(this.driver, By.cssSelector(GameManagementPage.primaryMediaTitleCSS));
        //TODO: ADD CHECK FOR WAIT
        String expectedTitleName = "Primary Media";
        boolean isFoundPrimaryMedia = games.getPrimaryMediaTitle().getText().trim().equals(expectedTitleName);
        Assert.assertTrue(isFoundPrimaryMedia, "label for Primary Media is improper");

        alertMsg = "[Select image] button for logo selecting is absent";
        checkElementPresent(this.driver,GameManagementPage.addGamePrimaryTabSelectLogoCSS, alertMsg);
        log.info("click on [select image] - to select Logo item");
        games.clickSelectImageLogo();

        autoItScriptExecutor("testData / games / filesForUpload", "Logo.png");
        alertMsg = "[Select image ] button is absent";
        checkElementPresent(this.driver, GameManagementPage.addGamePrimaryTabSelectLogoCSS, alertMsg);
        log.info("click on [select image] - to select primary screen");
        games.clickSelectPrimaryScreen();
        autoItScriptExecutor("testData / games / filesForUpload", "3.png");
    }
}
