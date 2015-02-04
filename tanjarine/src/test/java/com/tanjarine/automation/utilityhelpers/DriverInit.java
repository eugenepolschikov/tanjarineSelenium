package com.tanjarine.automation.utilityhelpers;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by ypolshchykau on 27.11.2014.
 */
public class DriverInit {
    private final static Logger log = LoggerFactory.getLogger(DriverInit.class);
    private static EnvironmentDataLoader environmentData =  new EnvironmentDataLoader();


    public static WebDriver driverSetUp(WebDriver driver) throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        log.info("Google chrome is selected");
        //System.setProperty("webdriver.chrome.driver", System.getProperty("user.home")+"/Documents/Tanjarine/chromedriver");
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        capability.setBrowserName("chrome");
        capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);


        String webDriverURL = "http://" + environmentData.getHubIP() + ":" + environmentData.getHubPort() + "/wd/hub";
        driver = new RemoteWebDriver(new URL(webDriverURL), capability);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));

        return driver;

    }

}
