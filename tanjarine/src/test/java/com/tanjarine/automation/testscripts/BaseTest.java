package com.tanjarine.automation.testscripts;

import com.google.common.base.Function;
import com.tanjarine.automation.utilityhelpers.FileOperations;
import com.tanjarine.automation.pageobjects.*;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    private String loginbaseUrl;
    protected String testUser;
    protected String testUserPass;
    private String hubIP;
    private String hubPort;
    private String alertMsg;
    private String ApkVersion;
    private String chromeDriverPath;
    private org.openqa.selenium.Platform platformSetUp;
    private String OS = System.getProperty("os.name").toLowerCase();
    private LoginPage loginPage;
    private VenueManagementPage venues;
    private AdminHomePage homePage;
    private GameManagementPage games;
    private PromotionsPage promotions;
    private ApkRegisterPage apkPage;

    private FileOperations fileAnalysis = new FileOperations();


    private final static Logger log = LoggerFactory.getLogger(BaseTest.class);

    public BaseTest() {

        try {
            init();
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

    /**
     * init will get test property, get systemOS and set related environment. The webdriver will
     * be initialized here and be used by all child classes like TestScrtpts and testcases
     *
     * @throws IOException
     */
    public void init() throws IOException {

        getTestProperty();
        getSystemOS();
        DesiredCapabilities capability = DesiredCapabilities.chrome();

        log.info(loginbaseUrl.toString() + "\n");
        log.info(testUser.toString() + "\n");
        log.info(testUserPass.toString() + "\n" );
        log.info(hubIP.toString() + "\n" );
        log.info(hubPort.toString() + "\n");
        log.info(ApkVersion.toString() + "\n");
        log.info( chromeDriverPath.toString() + "\n" + platformSetUp.toString() + "\n");
        log.info("chromeDriverPath: " + chromeDriverPath);

        log.info("Google chrome is selected");
        //System.setProperty("webdriver.chrome.driver", System.getProperty("user.home")+"/Documents/Tanjarine/chromedriver");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/chromedriver");
        capability.setBrowserName("chrome");
        capability.setPlatform(platformSetUp);
        String webDriverURL = "http://" + hubIP + ":" + hubPort + "/wd/hub";
        log.info("WebDriverURL=" + webDriverURL);
        driver = new RemoteWebDriver(new URL(webDriverURL), capability);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        venues = PageFactory.initElements(driver, VenueManagementPage.class);
        homePage = PageFactory.initElements(driver, AdminHomePage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        promotions = PageFactory.initElements(driver, PromotionsPage.class);
        games = PageFactory.initElements(driver, GameManagementPage.class);
        apkPage = PageFactory.initElements(driver, ApkRegisterPage.class);
    }

/*
    public List getCVSDataFile()
    {
        return readValuesFromCSV;
    }
  */


    public VenueManagementPage getVenues() {
        return venues;
    }

    public PromotionsPage getPromotionPage() {
        return promotions;
    }

    public AdminHomePage getHomePage() {
        return homePage;
    }

    public GameManagementPage getGames() {

        return games;
    }

    public ApkRegisterPage getApkPage() {

        return apkPage;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }


    /**
     * login() will navigate to login page and perform login
     * The login username and password will come from config.properties
     */
    public void login() {
        boolean isWebAdminLogin = webAdminLogin();
        Assert.assertTrue(isWebAdminLogin, "webAdmin login failed");
    }

    /**
     * logout() will perform a quick logout by going to auth/logout page.
     */
    public void logout() {
        this.driver.get(this.loginbaseUrl + "auth/logout");
    }

    /**
     * isElementPresent() method provides us a possbility to check for presence of any element on the page.
     *
     * @param locatorKey: element to check present
     * @return
     */
    public boolean isElementPresent(By locatorKey) {
        driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    /**
     * fluent Wait method implementation
     *
     * @param locator: element to be find
     * @return WebElement that contains locator object
     */
    public WebElement fluentWait(final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                        // .pollingEvery(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                        // .ignoring(NoSuchElementException.class);
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        WebElement foo = wait.until(
                new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(locator);
                    }
                }
        );
        return foo;
    }

    /**
     * checkElementPresent method checks whether an element is present on the page and fail the test with alertMsg
     *
     * @param cssLocator
     * @param alertMsg
     */
    public void checkElementPresent(String cssLocator, String alertMsg) {
        if (!isElementPresent(By.cssSelector(cssLocator))) {
            Assert.fail(alertMsg);
        }
    }

    /**
     * method checkElementPresentBool checks whether an element presents on the page and
     * fail with alertMsg if element not found
     *
     * @param cssLocator
     * @return true or false
     */
    public boolean checkElementPresentBool(String cssLocator) {
        if (!isElementPresent(By.cssSelector(cssLocator))) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * method waitForElementGetsVisible wait for elements presents, timeout after 10 seconds
     *
     * @param locatorKey
     * @return WebDriver
     */
    public WebElement waitForElementGetsVisible(By locatorKey) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locatorKey)
        );
        return element;
    }


    /**
     * getTableElementIndexByStringed method providing boolean result on the following: whether checkbox being considered is checked or not
     *
     * @param locator
     * @return true or false
     */
    public boolean isChecked(By locator) {
        boolean result = false;
        result = driver.findElement(locator).isSelected();
        return result;
    }

    /**
     * isAlertPresent method checks if an Alert is shown or not
     *
     * @return true or false
     */
    public boolean isAlertPresent() {
        boolean presentFlag;
        try {
            // Check the presence of alert
            Alert alert = driver.switchTo().alert();
            // Alert present; set the flag
            presentFlag = true;
            // if present consume the alert
            // alert.accept();

        } catch (NoAlertPresentException ex) {
            // Alert not present
            // ex.printStackTrace();
            presentFlag = false;
        }
        return presentFlag;
    }

    public void jsAlerAccept() {
        Alert alert;
        alert = driver.switchTo().alert();
        alert.accept();
    }

    public void driverShutDown() {

        driver.quit();

    }

    public void navigateToHomePage() {

        driver.get(loginbaseUrl + "home");

    }

    public void navigateToTabletUpdateStatus() {

        driver.get(loginbaseUrl + "venue/update-service/tablet/update-status");

    }


    public void navigateToVenueMangementPage() {
        alertMsg = "Global Admin dropdown is absent";
        checkElementPresent(AdminHomePage.headerDownsCSS, alertMsg);
        log.info("click Global Admin header section");
        homePage.globalAdminClick();
        alertMsg = "Venue Management section is absent";
        checkElementPresent(AdminHomePage.venueManagementHeaderCSS, alertMsg);

        log.info("click venue management section");
        homePage.venueManagementSectionClick();

        boolean isVneHeaderAppeared = isElementPresent(By.cssSelector(VenueManagementPage.venueMgmtLeftColCSS));
        Assert.assertTrue(isVneHeaderAppeared, "header of Venue Management section is absent");
        Assert.assertTrue(isVneHeaderAppeared && venues.getVenueMgmtHeader().getText().trim().equals("Venue management"), "FAILED: cant navigate to venue management section");
    }

   /* public void navigateToGamesPage() {
        alertMsg = "Global Admin dropdown is absent";
        checkElementPresent(AdminHomePage.headerDownsCSS, alertMsg);
        log.info("click Global Admin (header section)");  //works for UI mode ONLY
        homePage.globalAdminClick();

        alertMsg = "Game Catalog section is absent";
        checkElementPresent(AdminHomePage.gameCatalogHeaderCSS, alertMsg);
        log.info("click game catalog section");
        homePage.gameCatalogSelectingInDropdown();

        boolean isExpectedVneHeader = isElementPresent(By.cssSelector(GameManagementPage.gameManagementButtonLeftColumnCSS));
        Assert.assertTrue(isExpectedVneHeader, "[Game Management] button is present");

        String expectedTextHeader = "Game management";
        boolean isNavigatedToGamesPage = games.getGameManagementButtonLeftColumn().getText().trim().equals(expectedTextHeader);
        Assert.assertTrue(isExpectedVneHeader && isNavigatedToGamesPage, "FAILED: user failed to navigate to Games Catalog page");
    }
*/

    /**
     * this method represents user navigation to promotions section
     */
   /* public void navigateToPromotions(String chainNeeded) {
        log.info("Promotions section navigation for Chain and Venue selected");
        log.info("chain name that all testing actions on promotions tobe performed: " + chainNeeded);
        // navigate to venue management

        navigateToVenueMangementPage();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("searching chain in chain list: " + chainNeeded);
        // search needed chain
        int chainToSelectIndex = getTableElementIndexByString(venues.getChainsTable(), chainNeeded);
        // showing logs in case chain to operate with promotions NOT found
        if (chainToSelectIndex < 0) {
            Assert.assertTrue(true, "Not found chain to select index");
        }
        Assert.assertTrue(chainToSelectIndex >= 0, "chain to work with promotions NOT found in chain list");

        log.info("index of chain found to work with promotions: " + chainToSelectIndex);

        log.info("clicking on the chain found in chain list");
        venues.getChainsTable().get(chainToSelectIndex).click();

        log.info("check if venue list assigned to the  chain selected exists");
        boolean isVenueListAssignedToChainSelected = isElementPresent(By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS));

        // if : venue list is shown =>   click on the first that is shown on chain details window
        if (isVenueListAssignedToChainSelected) {
            log.info(" at list 1 venue exists for the selected chain. Wait till the whole venue list assigned to the chain selected render");
            *//*WebElement venueList =*//*
            waitForElementGetsVisible(By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS));
            // TODO: ADD CHECK
            // after  venue list renders => click on any venue
            log.info(" clicking on the  venue: " + venues.getVenueListWithinChainSelected().get(0).getText().trim());
            venues.getVenueListWithinChainSelected().get(0).click();
        } else {
            Assert.assertTrue(isVenueListAssignedToChainSelected, "venue assigned to the chain selected is absent");
        }
        // check that  'Edit' venue button is present
        alertMsg = "selected vnuee: Edit button is absent";
        checkElementPresent(VenueManagementPage.editVenueButtonCSS, alertMsg);
        log.info("click on Edit venue button");
        venues.editVenueClick();

        // checking that create venue layout appeared!!!
        alertMsg = "Edit venue layout is absent";
        checkElementPresent(VenueManagementPage.editVenueLayoutCSS, alertMsg);
        // checking that Promotions button is present
        alertMsg = "[Promotions] button is absent";
        checkElementPresent(VenueManagementPage.editVenuePromotionButtonCSS, alertMsg);
        log.info("click on [Promotions] button");
        venues.editVenueNavigateToPromotions();
    }
*/
   /* public void navigateToAppManagement() {
        alertMsg = "[Application management] button is absent";
        checkElementPresent(AdminHomePage.applicationManagementCSS, alertMsg);
        homePage.navigateToApplicationManagement();
    }*/

   /* public void logInWithParms(String login, String pass) {
        alertMsg = "Email input is absent";
        checkElementPresent(LoginPage.emailInputCSS, alertMsg);
        loginPage.enterLogin(login);

        alertMsg = "Password input is absent";
        checkElementPresent(LoginPage.passInputCSS, alertMsg);
        loginPage.enterPassword(pass);

        alertMsg = "Submit button is absent";
        checkElementPresent(LoginPage.signInButtonCSS, alertMsg);
        log.info("submit login form");
        loginPage.loginFormSubmit();

        alertMsg = "Remember me button is absent";
        checkElementPresent(LoginPage.rememberMeCSS, alertMsg);
    }*/

    /**
     * method involving 3rd party script execution -  taking  relative path to file  and making it work
     *
     * @param relativePath
     * @param fileName
     * @return
     * @throws InterruptedException
     */
/*
    public boolean autoItScriptExecutor(String relativePath, String fileName) throws InterruptedException {
        // commenting due to execution this test on a remote machine: 192.168.0.69
        String exeName = "fileUpload.exe";
        // commenting due to execution this test on a remote machine: 192.168.0.69
        String finalRes = "file://" + Paths.get("").toAbsolutePath().toString() + File.separator;

        String[] parts = relativePath.split("/");
        for (int count = 0; count < parts.length; count++) {
            finalRes += parts[count] + File.separator;
        }
        finalRes += fileName;
        log.info("file " + fileName + " to upload PATH  :" + finalRes);

        log.info("execution of AutoIt script:  automatic attachment of " + fileName);

        fileUploadAutoItExeCall(exeName, finalRes);

        Thread.sleep(3300);
        return true;
    }
*/

    /**
     * method generating timeStamp
     *
     * @return
     */
    public String getCurrentTimestamp() {
        java.util.Date date = new java.util.Date();
        return (new Timestamp(date.getTime())).toString();

    }


 /*   public static void fileUploadAutoItExeCall(String exeName, String filePathToUpload) {
        try {
            String[] dialog = new String[]{exeName, filePathToUpload};
            Runtime.getRuntime().exec(dialog);
        } catch (IOException e) {
        }
    }*/

    /**
     * method returning index of element in table of elements being search by string parameter 'pattern'
     *
     * @param elemenentsTable
     * @param pattern
     * @return
     * @throws InterruptedException
     */
    public static int getTableElementIndexByString(List<WebElement> elemenentsTable, String pattern) {
        int result = -1;
        for (int count = 0; count < elemenentsTable.size(); count++) {
            // get text of the element being analyzed
            String textCurrentElement = elemenentsTable.get(count).getText().toLowerCase().trim();
            if (textCurrentElement.contains(pattern)) {
                if (textCurrentElement.contains(pattern)) {
                    result = count;
                    return result;
                }
            }
        }
        return result;
    }


    /**
     * *******************Local Private Methods**********************************
     */
    private void getSystemOS() throws IOException {
        // This Method will get System OS --- To setUp variables based on the OS
        if (_isWindows()) {
            log.info("Running Test on Windows" + OS);
            platformSetUp = org.openqa.selenium.Platform.WINDOWS;
        } else if (_isMac()) {
            log.info("Running Test on Mac"+ OS);
            platformSetUp = org.openqa.selenium.Platform.MAC;
        } else {
            log.info("Your OS is not support!! Use default Windows Platform" + OS);
            platformSetUp = org.openqa.selenium.Platform.WINDOWS;
        }
    }

    private void getTestProperty() throws IOException {
        Properties prop = new Properties();
        String propFileName = "config.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inputStream == null) {
            throw new FileNotFoundException("Property File '" + propFileName + "' not found in the classpath");
        }
        prop.load(inputStream);
        Date time = new Date(System.currentTimeMillis());
        // get the property value and print it out
        loginbaseUrl = prop.getProperty("loginbaseUrl");
        testUser = prop.getProperty("testUser");
        testUserPass = prop.getProperty("testUserPass");
        hubIP = prop.getProperty("hubIP");
        hubPort = prop.getProperty("hubPort");
        ApkVersion = prop.getProperty("ApkVersion");
        chromeDriverPath = prop.getProperty("chromeDriverPath");
    }

    private boolean _isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    private boolean _isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    private boolean _isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    }

    public boolean webAdminLogin() {
        driver.manage().deleteAllCookies();             // deleting all cookies before navigating to login page
        String adminURL = loginbaseUrl + "auth/login";  // navigate to login
        log.info("Driver=" + driver);
        driver.get(adminURL);
        log.info("WebAdmin login launched: " + adminURL);
        // check that eMail input is present
       /* if (isElementPresent(By.cssSelector(LoginPage.emailInputCSS))) {
            loginPage.enterLogin(testUser);
        } else {
            Assert.assertFalse(true, "login input not found");
        }*/
        fluentWait(By.cssSelector(LoginPage.emailInputCSS));
        loginPage.enterLogin(testUser);
        //check that pass input is present
        /*if (isElementPresent(By.cssSelector(LoginPage.passInputCSS))) {
            loginPage.enterPassword(testUserPass);
        } else {
            Assert.assertFalse(true, "password input not found");
        }*/
        fluentWait(By.cssSelector(LoginPage.passInputCSS));
        loginPage.enterPassword(testUserPass);

        // check that login button is present
        /*if (isElementPresent(By.cssSelector(LoginPage.signInButtonCSS))) {
            loginPage.loginFormSubmit();
        } else {
            Assert.assertFalse(true, "submit button not found");
        }
        */
        fluentWait(By.cssSelector(LoginPage.signInButtonCSS));
        loginPage.loginFormSubmit();

        return true;
    }

    private boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }

    public String getLoginbaseUrl() {

        return loginbaseUrl;
    }


    public String getApkVersion() {
        return ApkVersion;
    }


    public void hoverElement(By id) {
        fluentWait(id);
        WebElement elem = driver.findElement(id);//Menu Item
//        WebElement elems1=driver.findElement(By.xpath("//li[@id='item-465']/a"));//Menu
        Actions builder = new Actions(driver);
        Actions hoverOverRegistrar = builder.moveToElement(elem);
        hoverOverRegistrar.perform();
    }

    public void hoverElemAndClick(String cssSel) {
        fluentWait(By.cssSelector(cssSel));
        WebElement elem = driver.findElement(By.cssSelector(cssSel));//Menu Item
//        WebElement elems1=driver.findElement(By.xpath("//li[@id='item-465']/a"));//Menu
        Actions builder = new Actions(driver);
        Actions hoverOverRegistrar = builder.moveToElement(elem);
        hoverOverRegistrar.perform();
//        hoverOverRegistrar.click();
//        jsClickOnElem(cssSel);
//        driver.findElement(By.cssSelector(cssSel)).click();
        fluentWait(By.cssSelector(cssSel)).click();
    }

    public void jsClickOnElem(String cssSel) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("var x = $(\'" + cssSel + "\');");
        stringBuilder.append("x.click();");
        js.executeScript(stringBuilder.toString());

    }


}