package com.tanjarine.automation.utilityhelpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * Created by ypolshchykau on 26.11.2014.
 */
public class EnvironmentDataLoader {
    private final static Logger log = LoggerFactory.getLogger(EnvironmentDataLoader.class);


    protected String loginbaseUrl;
    protected String testUser;
    protected String testUserPass;
    protected String hubIP;
    protected String hubPort;
    protected String ApkVersion;
    protected String chromeDriverPath;
    protected String chainName;
    protected String venueName;
    protected String macBorqs;

    protected String venueManagementOperationsChain;

    public EnvironmentDataLoader() {

        try {
            environmentInit();


//            init class-helpers instances
        } catch (IOException e) {
            log.info(e.getMessage());
        }

    }




    //    method resposible for parsing ENVIRONMENT   config from  .properties  file
    public void environmentInit() throws IOException {
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
        chainName = prop.getProperty("automationChain");
        venueName = prop.getProperty("automationVenue");
        macBorqs = prop.getProperty("tabletMacToDeployBuundleOn");
        venueManagementOperationsChain = prop.getProperty("venueManagementOperationsChain");
        inputStream.close();

    }

// a list of getters for properties


    public String getLoginbaseUrl() {
        return loginbaseUrl;
    }

    public String getTestUser() {
        return testUser;
    }

    public String getTestUserPass() {
        return testUserPass;
    }

    public String getHubIP() {
        return hubIP;
    }

    public String getHubPort() {
        return hubPort;
    }


    public String getApkVersion() {
        return ApkVersion;
    }

    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public String getChainName() {
        return chainName;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getMacBorqs() {
        return macBorqs;
    }
    public String getVenueManagementOperationsChain() {
        return venueManagementOperationsChain;
    }

}
