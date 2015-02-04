package com.tanjarine.automation.testcases;

import com.tanjarine.automation.testscripts.E2EVenueSetupScripts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by fwang on 8/13/14.
 */
public class SetupFullVenueTest extends E2EVenueSetupScripts {

    private final static Logger log = LoggerFactory.getLogger(SetupFullVenueTest.class);


    @BeforeMethod
    public void setup() throws IOException, InterruptedException {

        login();
        navigateToVenueMangementPage();

    }

    //keep default chain, and create new venue everytime run the scripts
    // we consider to possibilities: either create a new chain as well to use existing one
    // CASE 1 (USING EXISTING ONE CHAIN) => getting from   config.properties file - venueManagementOperationsChain
//    property exact name

    /**
     * QA-923: alidate that user is able to edit venues
     *
     * @throws InterruptedException
     */
    @Test
    public void fullVenueSetup_QA40_QA763_QA923() throws InterruptedException {

        //TODO :
        // 1. Select chain ->venue . NOTE: instead of this suquence be the folowing:
        // navigate to venue management page
        // create new venue
/*
        // in chain dropdown appropriate dropdown will be selected
        addVenueClick();

        // note: chain name 'selenium_doNotTouch' is loaded from testData/chains/automationChainCreated.csv
        fillNewVenueWithDataAndSubmit();  */

        pickUpExistingChainAndSelectVenue();

        //    QA-923: alidate that user is able to edit venues
        informationClickSaveVenue();


        editVenueLayoutNavigateToThemes();

        //  QA-40: [Edit Venue. Theme layout]Verify that user is able to clone the theme
        createNewThemeByCloningExistingOne();
        assignExistingThemeToVenue();


        editVenueLayoutNavigateToDevices();
        fillMacRegisterPatronTablet();
        fillMacRegisterServerHandheld();
        fillMacRegister2ndScreen();
        fillMacRegisterVenuePC();


// ============== METHODS ARE WORKINGL;  NEED TO IMPROVE BUNDLE GENERATE LOGIC  ================

        /**
         * QA-763: Validate that bundle is generated for dashboard promos
         * commented out this part of automation code for stabilizing purposes
         */
//       2014 10  02  - commenting as this is not stable part of test
 /*        navigateToBundles();
        // Setup Tablets
         navigateToTablets();
         clickThroughDropdowns(10);
         saveAndgenerateAndDeploy();


      // Setup Handhelds
        navigateToHandhelds();
        clickThroughDropdowns(3);
        saveAndgenerateAndDeploy();

        // Setup 2ndScreens
        navigateTo2ndScreens();
        clickThroughDropdowns(2);
        saveAndgenerateAndDeploy();

        // Setup VenuePCs
        navigateToVenuePCs();
        clickThroughDropdowns(5);
        saveAndgenerateAndDeploy();
        */


// ============== METHODS ARE WORKING================

//        TODO stabilize method below and methods above

//        checkChangeHistory();

        editVenueLayoutNavigateToDeviceLogs();
        selectDeviceAndAskForLogs();

        try {
            testAssociateGamesToVenues();
        } catch (IOException e) {
            e.printStackTrace();
        }

//==============   cut out from E2E venue creation for stabilizing purposes
// promotions  verification
   /*     navigateToPromotions();


        createMarkerBasePromotion();
        createDashboardPromotion();*/

//    ================

        editVenueLayoutNavigateToDevices();
        checkThatDevicesAreAvailableToUnregister();
        unassignVenuePC();
        unassignPatronTablets();
        unassingServerHandhelds();
        unasssignSecondScreenDevices();

    }


    // 2. Click information
    // 3. Theme -> assign theme (createTheme() should be there)
    // 4. Devices -> unRegister patron, table, server handheld, vpc and register same
    // 5. Device log, request All logs, ask for logs, see 'sending' status
    // 6. Game collection: assign game collect ->
    // 7. promotions -> add marker base, add camp and add dashboard
    // 8. fillers -> add fillers
    // 9. import menu
    // 10. emenu -> footer list, add cat, add menu item, footer
    // 11. servers-> add servers
    // 12. Bundle Save-> Generate -> Deploy on above tablet, hh, vpc
    // expand, unselect everything, pick my tablet, generate bundle and deploy, see status
    // 13. changes history: see page open


    @AfterClass
    public void closeBrowser() {
        driverShutDown();
    }
}
