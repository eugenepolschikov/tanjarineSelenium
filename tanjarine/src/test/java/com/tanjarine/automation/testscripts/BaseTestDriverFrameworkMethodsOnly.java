package com.tanjarine.automation.testscripts;

import com.google.common.base.Function;
import com.tanjarine.automation.utilityhelpers.EnvironmentDataLoader;
import com.tanjarine.automation.utilityhelpers.GetCurrentClassName;
import com.tanjarine.automation.utilityhelpers.Timer;
import com.tanjarine.automation.pageobjects.LoginPage;
import com.tanjarine.automation.pageobjects.VenueManagementPage;
import com.tanjarine.automation.constants.HardcodedDataForAutomation;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ypolshchykau on 16.09.2014.
 */
public class BaseTestDriverFrameworkMethodsOnly {
   /* protected String loginbaseUrl;
    protected String testUser;
    protected String testUserPass;
    protected String hubIP;

    protected String hubPort;
    protected String ApkVersion;
    protected String chromeDriverPath;*/


    private final static Logger log = LoggerFactory.getLogger(BaseTestDriverFrameworkMethodsOnly.class);


    protected EnvironmentDataLoader environmentData;

    public BaseTestDriverFrameworkMethodsOnly() {

        try {
            environmentData = new EnvironmentDataLoader();


//            init class-helpers instances
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }



    public EnvironmentDataLoader getEnvironmentData() {
        return environmentData;
    }

    public boolean webAdminLogin(WebDriver driver, LoginPage loginPage) {
        driver.manage().deleteAllCookies();             // deleting all cookies before navigating to login page
        String adminURL = environmentData.getLoginbaseUrl() + "auth/login";  // navigate to login
        log.info("Driver=" + driver);
        driver.get(adminURL);
        log.info("WebAdmin login launched: " + adminURL);
        // check that eMail input is present
        if (isElementPresent(driver, By.cssSelector(LoginPage.emailInputCSS))) {
            loginPage.enterLogin(environmentData.getTestUser());
        } else {
            Assert.assertFalse(true, "login input not found");
        }
        //check that pass input is present
        if (isElementPresent(driver, By.cssSelector(LoginPage.passInputCSS))) {
            loginPage.enterPassword(environmentData.getTestUserPass());
        } else {
            Assert.assertFalse(true, "password input not found");
        }
        // check that login button is present
        if (isElementPresent(driver, By.cssSelector(LoginPage.signInButtonCSS))) {
            loginPage.loginFormSubmit();
        } else {
            Assert.assertFalse(true, "submit button not found");
        }
        return true;
    }

    /**
     * logout() will perform a quick logout by going to auth/logout page.
     */
    public void logout(WebDriver driver) {
        driver.get(environmentData.getLoginbaseUrl() + "auth/logout");
    }

    public WebElement fluentWait(WebDriver driver, final By locator) {
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

    public WebElement waitForElementGetsVisible(WebDriver driver, By locatorKey) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locatorKey)
        );
        return element;
    }

    public boolean isElementPresent(WebDriver driver, By locatorKey) {
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

    public void checkElementPresent(WebDriver driver, String cssLocator, String alertMsg) {
        if (!isElementPresent(driver, By.cssSelector(cssLocator))) {
            Assert.fail(alertMsg);
        }
    }


    public void driverShutDown(WebDriver driver) {
        driver.quit();
    }


    public String getSelectorForChainNeeded(VenueManagementPage venues, String chainPattern) {

        String automationChainName = chainPattern;


        int index = -1;
        List<WebElement> chainList = venues.getVenueManagementPageChainList();
        for (int i = 0; i < chainList.size(); i++) {

            if (chainList.get(i).getText().trim().toLowerCase().contains(automationChainName.toLowerCase())) {
                index = i;
            }
        }

        String selector = "";
        if (index >= 0) {
            selector = "div.col-md-2.chain-list  ul.list-group li.list-group-item:nth-child(" + (index + 2) + ")>a";
        }
        return selector;
    }


    public void pickupFirstVenueUnderChainSelected(WebDriver driver, VenueManagementPage venues) {

        log.info("check that assigned venue list is present");
        boolean isVenueListPresent = isElementPresent(driver, By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS));
        junit.framework.Assert.assertTrue("no venues for the chain selected ", isVenueListPresent);
        venues.getVenueListWithinChainSelected().get(0).click();

//        Thread.sleep(1000);
        fluentWait(driver, By.cssSelector(VenueManagementPage.editVenueButtonCSS));
        waitForElementGetsVisible(driver, By.cssSelector(VenueManagementPage.editVenueButtonCSS));

        venues.editVenueClick();
    }


    public void pickUpVenueByName(WebDriver driver, VenueManagementPage venues, String venueName) {
        log.info("check that assigned venue list is present");
        boolean isVenueListPresent = isElementPresent(driver, By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS));
        junit.framework.Assert.assertTrue("no venues for the chain selected ", isVenueListPresent);
//        venues.getVenueListWithinChainSelected().get(0).click();

//        fluentWait(driver,By.cssSelector(VenueManagementPage.editChainVenuesAssignedCSS));
        List<WebElement> aa = venues.getVenueListWithinChainSelected();
        /*for(int i=0; i<aa.size(); i++){
            String title=aa.get(i).getText();
            if(title.toLowerCase().trim().equals(venueName)){
                aa.get(i).click();
            }
        }*/

        for (WebElement itertr : aa) {
            if (itertr.getText().trim().toLowerCase().equals(venueName)) {
                itertr.click();
                break;
            }
        }

//        Thread.sleep(1000);
        fluentWait(driver, By.cssSelector(VenueManagementPage.editVenueButtonCSS));
        waitForElementGetsVisible(driver, By.cssSelector(VenueManagementPage.editVenueButtonCSS));

        venues.editVenueClick();
    }

    public void pickupAutomationChain(WebDriver driver, VenueManagementPage venues, String chainPattern) {
        String automationChainSelector = getSelectorForChainNeeded(venues, chainPattern);
        driver.findElement(By.cssSelector(automationChainSelector)).click();
//        fluentWait+ wait to get visible to wait element renderers on page
        fluentWait(driver, By.cssSelector(VenueManagementPage.editSelectedChainButtonCSS));
        waitForElementGetsVisible(driver, By.cssSelector(VenueManagementPage.editSelectedChainButtonCSS));
    }


    /**
     * method providing boolean response on checkbox checked/unchecked
     *
     * @param driver
     * @param locator
     * @return
     */

    public boolean isChecked(WebDriver driver, By locator) {
        boolean result = false;
        result = driver.findElement(locator).isSelected();
        return result;
    }


    public void printTestSuiteNameOnceExecFinished(Class classParam) {
        log.info(GetCurrentClassName.getCurrentClassName(classParam)+
                HardcodedDataForAutomation.ON_TEST_SUITE_EXEC_FINISH_MSG+ Timer.getCurrentTimestamp()+"\n");


    }


}
