package com.tanjarine.automation.testcases;

import com.tanjarine.automation.utilityhelpers.DriverInit;
import com.tanjarine.automation.pageobjects.LoginPage;
import com.tanjarine.automation.testscripts.AdminHomeLayoutScripts;
import com.tanjarine.automation.testscripts.GameManagementScripts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;


public class GameManagementTest {



    private WebDriver driver;
    private LoginPage loginPage;
    // script class instance, i.e. instance containing our test methods

    private GameManagementScripts gameMethods;
    private AdminHomeLayoutScripts adminHomeLayoutMethods;


    @BeforeClass
    public void initClass() throws MalformedURLException {
        this.driver = DriverInit.driverSetUp(this.driver);
        loginPage = PageFactory.initElements(this.driver, LoginPage.class);


        gameMethods = new GameManagementScripts(driver);
        adminHomeLayoutMethods = new AdminHomeLayoutScripts(driver);

    }


    @BeforeMethod
    public void setup() throws IOException, InterruptedException {

        gameMethods.webAdminLogin(driver, loginPage);
        gameMethods.navigateToGamesPage();
    }

    /**
     * Author: Eugene Polschikov {ypolshchykau@exadel.com}
     * Test Case ID: PIL-286: Validate that user can save a game
     * Test Case Result: User stays at login page with empty User ID/valid PW and alert msg poped
     * jUnit annotation
     *
     * @Ignore("skipped: need to adjust execution of AutoIt script on a remote machine; current mode:  test is working OK on local machine")
     */
    @Test(enabled = false)
    public void testSaveAGame_QA871_QA982() throws InterruptedException, IOException {
        gameMethods.addGame();

        gameMethods.addGameNavigateToPrimaryMediaSection();

        gameMethods.addToCollectionsNav();

    }

    /**
     * Author: Eugene Polschikov {ypolshchykau@exadel.com}
     * Test Case ID: PIL-4290: Validate that user is able to create a game collection
     * Test Case Result: User stays at login page with empty User ID/valid PW and alert msg poped
     * this test created intentionally for testNG annotation to delete all automation entities; amd provive timing taken to
     * get this suite executed
     */
    @Test
    public void testCreateGameCollection_QA_1803() throws IOException {

        gameMethods.navigateToGameCollection();

        gameMethods.createNewGameCollection();

        gameMethods.enterGameCollectionNameNDesc();

        gameMethods.addThisGame();

        gameMethods.selectAndAddGame();

        gameMethods.saveCollection();
    }


    @Test
    public void automationGarbageGameCollectionsCleanUp() {

        gameMethods.navigateToGameCollection();
        gameMethods.automationGameCollectionsCleanUp();
    }

    @AfterMethod
    public void navigateToHome() {
        adminHomeLayoutMethods.navigateToWebAdminHomeLayout();

    }

    @AfterClass
    public void closeBrowser() {
        gameMethods.printTestSuiteNameOnceExecFinished(this.getClass());
        gameMethods.driverShutDown(driver);
    }
}