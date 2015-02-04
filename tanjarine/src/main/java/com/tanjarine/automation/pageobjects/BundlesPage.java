package com.tanjarine.automation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by ypolshchykau on 28.09.2014.
 */
public class BundlesPage {

    public final static String bundlesButtonCSS = "div.row div.col-md-2 ul.nav li.sidebar-venue-bundles>a";

    @FindBy(how = How.CSS, using = bundlesButtonCSS)
    private WebElement bundlesButton;

    public void navigateToBundlesSection() {
        bundlesButton.click();
    }


    public final static String tabletSubsectionCSS = "a[href=\"/web-ui/admin/venue/update-service/tablet\"]";
    @FindBy(how = How.CSS, using = tabletSubsectionCSS)
    private WebElement tabletSubsection;

    public void selectTablets() {
        tabletSubsection.click();
    }


    //    APK  assigment form
    public final static String apkAssignmentFormCSS = "div.saveAPKsAssignmentContainer";


    public final static String lastBundleVersionDeployedCSS = "table#dataBundlesTable.table tbody#events-delegator-container tr.enclosing-select-devices-btn-container:nth-child(1)>td:nth-child(2)";

    public WebElement getLastBundleVersionDeployed() {
        return lastBundleVersionDeployed;
    }

    @FindBy(how = How.CSS, using = lastBundleVersionDeployedCSS)
    private WebElement lastBundleVersionDeployed;


    public final static String devicesButtonAgainstLastGeneratedBundleCSS = "tbody#events-delegator-container tr.enclosing-select-devices-btn-container:nth-child(1) td div.btn-group:nth-child(2)";
    @FindBy(how = How.CSS, using = devicesButtonAgainstLastGeneratedBundleCSS)
    private WebElement devicesButtonAgainstLastGeneratedBundle;

    public void clickDevicesAvailableForBundleGenerated() {
        devicesButtonAgainstLastGeneratedBundle.click();
    }


    public final static String dataBundlesTableCSS = "#dataBundlesTable";


    public final static String listOfAvailableDevicesCSS = "tbody#events-delegator-container tr.selective-update-devices-holder:nth-child(2) td.available-devices-table-wrapper";


    public final static String generateBundleButtonCSS = "button#generateDataBundleBtn";
    @FindBy(how = How.CSS, using = generateBundleButtonCSS)
    private WebElement generateBundleButton;

    public void generateBundle() {

        generateBundleButton.click();
    }


    public final static String untickAllTabletsCSS = "table#DataTables_Table_0.table thead tr th.col-md-1 input.devices-check-all";
    @FindBy(how = How.CSS, using = untickAllTabletsCSS)
    private WebElement untickAllTablets;

    public void untickAllTablets() {
        untickAllTablets.click();
    }


    public final static String macListOfDevicesAvailableCSS = "table#DataTables_Table_0.table tbody>tr td[class*=\"monospace\"]";

    public List<WebElement> getMacListOfDevicesAvailable() {
        return macListOfDevicesAvailable;
    }

    @FindBy(how = How.CSS, using = macListOfDevicesAvailableCSS)
    private List<WebElement> macListOfDevicesAvailable;


    public final static String tickBoxListForDevicesAvailableCSS = "table#DataTables_Table_0.table tbody.available-devices-body td.sorting_1>input";

    public List<WebElement> getTickBoxListForDevicesAvailable() {
        return tickBoxListForDevicesAvailable;
    }

    @FindBy(how = How.CSS, using = tickBoxListForDevicesAvailableCSS)
    private List<WebElement> tickBoxListForDevicesAvailable;


    public final static String deployButtonForTheLatestBundleCSS = "table#dataBundlesTable.table tbody#events-delegator-container tr.enclosing-select-devices-btn-container:nth-child(1) td div.btn-group a.btn";
    @FindBy(how = How.CSS, using = deployButtonForTheLatestBundleCSS)
    private WebElement deployButtonForTheLatestBundle;

    public void deployLatest() {
        deployButtonForTheLatestBundle.click();
    }

    public final static String deployOnDemandSubselectCSS = "tbody#events-delegator-container tr.enclosing-select-devices-btn-container:nth-child(1) td div.btn-group ul.dropdown-menu li a.deployOnDemand";



    public final static String deviceRecordsForTheBundleDropdownCSS="div#DataTables_Table_0_wrapper.dataTables_wrapper  label select";
    @FindBy(how = How.CSS, using = deviceRecordsForTheBundleDropdownCSS)
    private WebElement deviceRecordsForTheBundleDropdown;

    public void rollDownDevicesCount(){
        deviceRecordsForTheBundleDropdown.click();
    }

    public final static String selectAlldevicesAvailableOptionCSS="div#DataTables_Table_0_wrapper.dataTables_wrapper  label select option[value=\"-1\"]";
    @FindBy(how = How.CSS, using =selectAlldevicesAvailableOptionCSS)
    private WebElement selectAlldevicesAvailableOption;

    public void tickAllDevices(){
        selectAlldevicesAvailableOption.click();
    }






}
