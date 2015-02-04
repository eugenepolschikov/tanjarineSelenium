package com.tanjarine.automation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by ypolshchykau on 18.04.2014.
 */
public class VenueManagementPage {
    public final static String venueMgmtLeftColCSS = ".nav-header>a[href=\"/web-ui/admin/venue/list\"]";

    public WebElement getVenueMgmtHeader() {
        return venueManagementHeader;
    }

    @FindBy(how = How.CSS, using = venueMgmtLeftColCSS)
    private WebElement venueManagementHeader;

    public final static String addChainCSS = "a[href=\"/web-ui/admin/chain/new\"]";
    @FindBy(how = How.CSS, using = addChainCSS)
    private WebElement addChainButton;

    public void addChain() {
        addChainButton.click();
    }

    public final static String chainLayoutCSS = "#chainBean";

    public final static String addChainNameInputCSS = "#chainBean #name";

    @FindBy(how = How.CSS, using = addChainNameInputCSS)
    private WebElement createChainNameInput;

    public void enterChainName(String name) {
        createChainNameInput.sendKeys(name);
    }

    public final static String addChainDescInputCSS = "#chainBean #desc";

    @FindBy(how = How.CSS, using = addChainDescInputCSS)
    private WebElement createChainDescrInput;

    public void enterChainDscription(String desc) {
        createChainDescrInput.sendKeys(desc);
    }

    public final static String addChainContactsColCSS = "a#collapseContactHeader";

    @FindBy(how = How.CSS, using = addChainContactsColCSS)
    private WebElement createChainContacts;

    public void contactsClick() {
        createChainContacts.click();
    }

    public final static String addChainPhoneInputCSS = "#chainBean #phoneNumber";

    @FindBy(how = How.CSS, using = addChainPhoneInputCSS)
    private WebElement createChainPhoneInput;

    public void fillPhone(String phone) {
        createChainPhoneInput.sendKeys(phone);
    }

    public final static String addChainWebSiteInputCSS = "#chainBean #websiteURL";

    @FindBy(how = How.CSS, using = addChainWebSiteInputCSS)
    private WebElement createChainWebSiteInput;

    public void enterEmail(String eMail) {
        createChainWebSiteInput.sendKeys(eMail);
    }

    public final static String addChainSaveButton = "#chainBean div.btn[onclick=\"createNewChain()\"]";

    @FindBy(how = How.CSS, using = addChainSaveButton)
    private WebElement createChainSubmit;

    public void createChainSubmit() {
        createChainSubmit.click();
    }

    public final static String addChainCancelButton = "a.btn[href=\"/web-ui/admin/venue/list\"]";

    public final static String chainsTableHeaderCSS = "ul.list-group li>h4";

    public WebElement getChainTableHeader() {
        return chainTableHeader;
    }

    @FindBy(how = How.CSS, using = chainsTableHeaderCSS)
    private WebElement chainTableHeader;

    public final static String chainsTableCSS = "ul.list-group li>a";

    public List<WebElement> getChainsTable() {
        return chainsTable;
    }

    @FindBy(how = How.CSS, using = chainsTableCSS)
    private List<WebElement> chainsTable;

    public final static String editChainVenuesAssignedCSS = "#itemContainer div.row-fluid ul";
    @FindBy(how = How.CSS, using = editChainVenuesAssignedCSS)
    private WebElement editChainVenuesAssigned;

    public final static String editChainVenuesAssignedListCSS = "#itemContainer div.row-fluid ul li a";
    @FindBy(how = How.CSS, using = editChainVenuesAssignedListCSS)
    private List<WebElement> editChainVenuesAssignedList;

    public List<WebElement> getVenueListWithinChainSelected() {
        return editChainVenuesAssignedList;
    }

    public final static String editSelectedChainButtonCSS = "div.row-fluid a.btn[href*=\"/web-ui/admin/chain/edit\"]";

    @FindBy(how = How.CSS, using = editSelectedChainButtonCSS)
    private WebElement editChainButton;

    public void editChainClick() {
        editChainButton.click();
    }

    public final static String deleteChainButton = "#btn-chain-delete";
    @FindBy(how = How.CSS, using = deleteChainButton)
    private WebElement deleteCurrentChain;

    public void deleteChainSelected() {
        deleteCurrentChain.click();
    }

    public final static String addVenueCSS = "a[href=\"/web-ui/admin/venue/new\"]";

    @FindBy(how = How.CSS, using = addVenueCSS)
    private WebElement addVenue;

    public void addVenue() {
        addVenue.click();
    }

    //    [Edit] venue button declaration and initilization
    public final static String editVenueButtonCSS = "div#itemContainer div.row-fluid a[href*=\"/web-ui/admin/venue/edit\"]";
    @FindBy(how = How.CSS, using = editVenueButtonCSS)
    private WebElement editVenueButton;

    public void editVenueClick() {
        editVenueButton.click();
    }


//    [Delete] venue button declaration and initialization
    public final static String deleteVenueButtonCSS="#delete-venue-btn";
    @FindBy(how = How.CSS, using = deleteVenueButtonCSS)
    private WebElement deleteVenueButton;

    public void deleteVenue() {
        deleteVenueButton.click();
    }

    public final static String createVenueHeaderCSS = "div.panel-body legend";

    public WebElement getCreateVenueHeader() {
        return createVenueLayoutHeader;
    }

    @FindBy(how = How.CSS, using = createVenueHeaderCSS)
    private WebElement createVenueLayoutHeader;

    public final static String editVenueLayoutCSS = "form#venueBean>div.panel-body";

    @FindBy(how = How.CSS, using = editVenueLayoutCSS)
    private WebElement editVenueLayout;

    // ==============    Add venue layOut description  ========================
    public final static String venueCreateLayoutCodeCSS = "#code";
    public final static String venueCreateLayoutTitleCSS = "#title";

    public final static String venueCreateLayoutDescriptionCSS = "#desc";

    @FindBy(how = How.CSS, using = venueCreateLayoutCodeCSS)
    private WebElement createVenueCodeInput;

    public void enterVenueCode(String toInput) {
        createVenueCodeInput.sendKeys(toInput);
    }

    @FindBy(how = How.CSS, using = venueCreateLayoutTitleCSS)
    private WebElement createVenueTitleInput;

    public void enterVenueTitle(String toInput) {
        createVenueTitleInput.sendKeys(toInput);
    }

    @FindBy(how = How.CSS, using = venueCreateLayoutDescriptionCSS)
    private WebElement createVenueDescriptionInput;

    public void enterVenueDescription(String toInput) {
        createVenueDescriptionInput.sendKeys(toInput);
    }

    public final static String venueCreateChainDropdownCSS = "#chain";

    @FindBy(how = How.CSS, using = venueCreateChainDropdownCSS)
    private WebElement createVenueChainDropdown;

    public void clickChainDropdown() {
        createVenueChainDropdown.click();
    }

    public final static String createVenueChainListCSS = "#chain option";

    public List<WebElement> getCreateVenueChainList() {
        return createVenueChainList;
    }

    @FindBy(how = How.CSS, using = createVenueChainListCSS)
    private List<WebElement> createVenueChainList;

    public final static String createVenueRandChainInListCSS = "#chain:nth-child(1)";

    //    =====================================================================
    //  ==================================== Addreess  block ==================================

    public final static String addressCollabpsableCSS = "#collapseAddressHeader div.col-md-12";


    @FindBy(how = How.CSS, using = addressCollabpsableCSS)
    private WebElement createVenueAddressCollabpsable;

    public void clickAddressSection() {
        createVenueAddressCollabpsable.click();
    }

    public final static String createVenueStreetAddrCSS = "#locationBean-streetAddress";
    @FindBy(how = How.CSS, using = createVenueStreetAddrCSS)
    private WebElement createVenueAddressBlockStreetAddress;

    public void createVenueFilAddress(String toInput) {
        createVenueAddressBlockStreetAddress.sendKeys(toInput);
    }

    public final static String createVenueAddrCityCSS = "#locationBean-city";
    @FindBy(how = How.CSS, using = createVenueAddrCityCSS)
    private WebElement createVenueAddressBlockCity;

    public void createVenueFillCity(String toInput) {
        createVenueAddressBlockCity.sendKeys(toInput);
    }

    public final static String createVenueAddrStateCSS = "#locationBean-state";
    @FindBy(how = How.CSS, using = createVenueAddrStateCSS)
    private WebElement createVenueAddressBlockState;

    public void createVenueFillState(String toInput) {
        createVenueAddressBlockState.sendKeys(toInput);
    }

    public final static String createVenueAddrPostCodeCSS = "#locationBean-postalCode";
    @FindBy(how = How.CSS, using = createVenueAddrPostCodeCSS)
    private WebElement createVenueAddressBlockPostalCode;

    public void createVenueFillPostalCode(String toInput) {
        createVenueAddressBlockPostalCode.sendKeys(toInput);
    }

    public final static String createVenueAddrCountryCSS = "#locationBean-country";
    @FindBy(how = How.CSS, using = createVenueAddrCountryCSS)
    private WebElement createVenueAddressBlockCountry;

    public void createVenueFillCountry(String toInput) {
        createVenueAddressBlockCountry.sendKeys(toInput);
    }

    public final static String createVenueAddrGPSLatCSS = "#locationBean-gpsCoordsLatitude";
    @FindBy(how = How.CSS, using = createVenueAddrGPSLatCSS)
    private WebElement createVenueAddressBlockGPSLatitude;

    public void createVenueFillGPSLatitude(String toInput) {
        createVenueAddressBlockGPSLatitude.sendKeys(toInput);
    }

    public final static String createVenueAddrGPSlongCSS = "#locationBean-gpsCoordsLongtitude";
    @FindBy(how = How.CSS, using = createVenueAddrGPSlongCSS)
    private WebElement createVenueAddressBlockGPSlongtitude;

    public void createVenueFillGPSLongtitude(String toInput) {
        createVenueAddressBlockGPSlongtitude.sendKeys(toInput);
    }

    //    -========================= working hours block =================
    public final static String createVenueWorkingHrsColCSS = "#collapseSixHeader div.col-md-12";
    @FindBy(how = How.CSS, using = createVenueWorkingHrsColCSS)
    private WebElement createVenueWorkingHoursCollapsable;

    public void workingHoursClick() {
        createVenueWorkingHoursCollapsable.click();
    }

    public final static String createVenueWorkHrs247ChkboxCSS = "input[id=\"workSchedule.alwaysOpened1\"]";
    @FindBy(how = How.CSS, using = createVenueWorkHrs247ChkboxCSS)
    private WebElement createVenueWorkingHours24_7Checkbox;

    /* public void checkUncheckVenueWorkingCheckbox(){
         createVenueWorkingHours24_7Checkbox.click();
     }*/
    //    ===================  contacts section =========================
    public final static String createVenueContactsColCSS = "#collapseContactHeader div.col-md-12";
    @FindBy(how = How.CSS, using = createVenueContactsColCSS)
    private WebElement createVenueContactsCollapsable;

    public void contactsBlockClick() {
        createVenueContactsCollapsable.click();
    }

    public final static String createVenueContactsNameInputCSS = "#contactName";
    @FindBy(how = How.CSS, using = createVenueContactsNameInputCSS)
    private WebElement createVenueContactsBlockNameInput;

    public void createVenueFillName(String toInput) {
        createVenueContactsBlockNameInput.sendKeys(toInput);
    }

    public final static String createVenueContactsPhoneInputCSS = "#phoneNumber";
    @FindBy(how = How.CSS, using = createVenueContactsPhoneInputCSS)
    private WebElement createVenueContactsBlockPhoneInput;

    public void createVenueFillPhone(String toInput) {
        createVenueContactsBlockPhoneInput.sendKeys(toInput);
    }

    public final static String createVenueContactsWebsiteInputCSS = "#websiteURL";
    @FindBy(how = How.CSS, using = createVenueContactsWebsiteInputCSS)
    private WebElement createVenueContactsBlockWebsiteInput;

    public void createVenueFillWebsite(String toInput) {
        createVenueContactsBlockWebsiteInput.sendKeys(toInput);
    }

    //    ==================internal network config   block ===================
    public final static String createVenueNetworkCfgColCSS = "#collapseNetworkHeader div.col-md-12";
    @FindBy(how = How.CSS, using = createVenueNetworkCfgColCSS)
    private WebElement createVenueNetworkConfigCollapsable;

    public void netWorkConfigBlockClick() {
        createVenueNetworkConfigCollapsable.click();
    }

    public final static String createVenueNetworkCfgIgnoreCfgChkboxCSS = "[id=\"networkBean.ignored\"]";
    @FindBy(how = How.CSS, using = createVenueNetworkCfgIgnoreCfgChkboxCSS)
    private WebElement createVenueNetworkConfigIgnoreConfigCheckbox;

    // =================================second screen   block ====================
    public final static String createVenue2ndScreenColCSS = "#collapseSssHeader";
    @FindBy(how = How.CSS, using = createVenue2ndScreenColCSS)
    private WebElement createVenue2ndScreenCollapsable;

    public void secondScreenClick() {
        createVenue2ndScreenCollapsable.click();
    }

    public final static String createVenueSecondScreenCalDropdownCSS = "[id=\"secondScreenBean.calendarId\"]";
    @FindBy(how = How.CSS, using = createVenueSecondScreenCalDropdownCSS)
    private WebElement createVenueSecondScreenSectionCalendarDropdown;

    public final static String randomOptionCalendarDropdownCSS = "[id=\"secondScreenBean.calendarId\"] option:nth-child(2)";
    @FindBy(how = How.CSS, using = randomOptionCalendarDropdownCSS)
    private WebElement randomOptionInCalendarToUseDropdown;

    //submit button
    public final static String addVenueLayoutSaveButtonCSS = "div[onclick=\"createNewVenue()\"]";
    @FindBy(how = How.CSS, using = addVenueLayoutSaveButtonCSS)
    private WebElement addVenueLayoutSaveButton;

    public void submitNewVenue() {
        addVenueLayoutSaveButton.click();
    }

    //save venue button

    public final static String saveEditedVenueCSS = "div.btn.btn-primary.input-medium[onclick=\"updateVenue()\"]";

    @FindBy(how = How.CSS, using = saveEditedVenueCSS)
    private WebElement saveEditedVenue;

    public void saveExistingVenue() {
        saveEditedVenue.click();
    }

    //    =========================Edit venue:  Game collection  section    ======
    public final static String editVenueGameColButtonCSS = "li.sidebar-game-collection>a[href=\"/web-ui/admin/game/game-collections-choose\"]";
    @FindBy(how = How.CSS, using = editVenueGameColButtonCSS)
    private WebElement editVenueGameCollectionButton;

    public void editVenueNavigateToGameCollection() {
        editVenueGameCollectionButton.click();
    }

    //  Edit venue: Game  collection layout
    public final static String editVenueGameCollectionCSS = "#assignGamesToVenue>div.row:nth-child(1)>div.col-md-12";
    @FindBy(how = How.CSS, using = editVenueGameCollectionCSS)
    private WebElement editVenueGameCollectionLayout;

    //  game collection radioButton list
    public final static String gameCollectionsRadioBtnListCSS = "#assignGamesToVenue>div.row:nth-child(1)>div.col-md-12 input";

    public List<WebElement> getGameCollectionsRadiobuttonsList() {
        return gameCollectionsRadiobuttonsList;
    }

    @FindBy(how = How.CSS, using = gameCollectionsRadioBtnListCSS)
    private List<WebElement> gameCollectionsRadiobuttonsList;

    //edit venue game collection submit button
    public final static String gameCollectionSubmitButtonCSS = "#assignGamesToVenue div.col-md-12 button[type=\"submit\"]";
    @FindBy(how = How.CSS, using = gameCollectionSubmitButtonCSS)
    private WebElement gameCollectionSubmitButton;

    public void editVenueGameCollectionSelectedSubmit() {
        gameCollectionSubmitButton.click();
    }

    // ========================= edit venue : promotions  section =====================
    public final static String editVenuePromotionButtonCSS = "li.sidebar-venue-promotions>a[href=\"/web-ui/admin/promotion/commonList\"]";
    @FindBy(how = How.CSS, using = editVenuePromotionButtonCSS)
    private WebElement editVenuePromotionButton;

    public void editVenueNavigateToPromotions() {
        editVenuePromotionButton.click();
    }


    //    ===================================================================================
    public final static String venueManagementPageChainListCSS = "div.col-md-2.chain-list  ul.list-group li.list-group-item>a";

    public List<WebElement> getVenueManagementPageChainList() {
        return venueManagementPageChainList;
    }

    @FindBy(how = How.CSS, using = venueManagementPageChainListCSS)
    private List<WebElement> venueManagementPageChainList;


//    ==============================================================================
//    needed venue selection

    public final static String firstVenueUnderNeededChainCSS = "ul.list-group li.list-group-item:nth-child(2) ul.dropdown-menu li:nth-child(1) a";
    @FindBy(how = How.CSS, using = firstVenueUnderNeededChainCSS)
    private WebElement firstVenueUnderNeededChain;

    // =============== EDIT VENUE => INFORMATION SECTION
    public final static String editVenueFormInformationButtonCSS = "div.col-md-2.col-sm-2.sidebar  ul.nav.nav-sidebar  li.sidebar-venue-information.active>a";
    @FindBy(how = How.CSS, using = editVenueFormInformationButtonCSS)
    private WebElement editVenueFormInformationButton;

    public void informationButtonCLick() {
        editVenueFormInformationButton.click();
    }


//    alert on successfull save venuve

    public final static String alertMsgOnSaveVenueCSS = "div.alert.alert-success";


//    =============== EDIT VENUE  => THEMES section

    public final static String editVenueThemeButtonCSS = "ul.nav.nav-sidebar>li>a[href=\"/web-ui/admin/theme/assign\"]";

    @FindBy(how = How.CSS, using = editVenueThemeButtonCSS)
    private WebElement editVenueThemeButton;

    public void themesButtonCLick() {
        editVenueThemeButton.click();
    }

//    CREATE NEW THEME BUTTON


    public final static String editVenueThemesCreateButtonCSS = "form#themeAssignForm.panel div.panel-body div.row div.col-md-12 a.btn";
    @FindBy(how = How.CSS, using = editVenueThemesCreateButtonCSS)
    private WebElement editVenueThemesCreateButton;

    public void themeCreateButtonClick() {
        editVenueThemesCreateButton.click();
    }


//    THEME  LAYOUT   ==============

    //   clone from existing one  button
    public final static String cloneFromExistingOneButtonCSS = "div.col-md-10 div.control-group div.row div.col-md-4 a.btn[href=\"#cloneTheme\"]";
    @FindBy(how = How.CSS, using = cloneFromExistingOneButtonCSS)
    private WebElement cloneFromExistingOneButton;

    public void cloneTheme() {
        cloneFromExistingOneButton.click();
    }

    // clone from existing one layout:
    public final static String themeDropdownCSS = "#chosenTheme";
    //    public final static String themeDropdownCSS="html body.modal-open div.container-fluid div.row div.col-md-10 div.col-md-10 div#cloneTheme.modal div.modal-dialog div.modal-content div.modal-body p";
    @FindBy(how = How.CSS, using = themeDropdownCSS)
    private WebElement themeDropdown;

    public void rollDownThemeDropdown() {
        themeDropdown.click();
    }


//    themes LIST  . Clone layout

    public final static String themesListCSS = "#chosenTheme>option";

    public List<WebElement> getThemesList() {
        return themesList;
    }

    @FindBy(how = How.CSS, using = themesListCSS)
    private List<WebElement> themesList;


    public final static String cloneButtonCSS = "div.modal-dialog div.modal-content div.modal-footer a.btn[onclick=\"cloneExistingTheme()\"]";
    @FindBy(how = How.CSS, using = cloneButtonCSS)
    private WebElement cloneButton;

    public void cloneClick() {
        cloneButton.click();
    }


    public final static String themeTitleInputCSS = "html body div.container-fluid div.row div.col-md-10 div.col-md-10 form#themeForm.well div.row div.col-md-10 div.control-group div.row div.col-md-4 input#title.form-control";
    @FindBy(how = How.CSS, using = themeTitleInputCSS)
    private WebElement themeTitleInput;

    public void enterThemeTitle(String text) {
        themeTitleInput.sendKeys(text);
    }


    public final static String themeSubmitButtonCSS = "#theme-submit";
    @FindBy(how = How.CSS, using = themeSubmitButtonCSS)
    private WebElement themeSubmitButton;

    public void themeSubmit() {
        themeSubmitButton.click();
    }


    public final static String cloneThemeModalDialogCSS = "div#cloneTheme div.modal-dialog";


    //     ASSIGN THEME LAYOUT:
    public final static String assignThemeToVenueLayoutCSS = "html body div.container-fluid div.row div.col-md-10 div.col-md-10 form#themeAssignForm.panel div.panel-body";


    public final static String themeSelectorDropdownCSS = "#themeSelector";

    @FindBy(how = How.CSS, using = themeSelectorDropdownCSS)
    private WebElement themeSelectorDropdown;

    public void selectThemeOfExistingOnes() {
        themeSelectorDropdown.click();
    }


    public final static String existingThemeListCSS = "#themeSelector>option";

    public List<WebElement> getExistingThemeList() {
        return existingThemeList;
    }

    @FindBy(how = How.CSS, using = existingThemeListCSS)
    private List<WebElement> existingThemeList;


    public final static String existingThemesLayoutAssignButtonCSS = "form#themeAssignForm.panel div.panel-body div.row div.col-md-12 button.btn";
    @FindBy(how = How.CSS, using = existingThemesLayoutAssignButtonCSS)
    private WebElement existingThemesLayoutAssignButton;

    public void assignSelectedThemeToVenue() {
        existingThemesLayoutAssignButton.click();
    }


    public final static String successfulAssignmentAlertCSS = "form#themeAssignForm.panel div.panel-body div.row div.col-md-12 div.alert";

    public WebElement getSuccessfulAssignmentAlert() {
        return successfulAssignmentAlert;
    }

    @FindBy(how = How.CSS, using = successfulAssignmentAlertCSS)
    public WebElement successfulAssignmentAlert;


    //   ==============  DEVICES LAYOUT  ======================
    public final static String editVenueDevicesButtonCSS = "li.sidebar-venue-devices a.side-ellipsis[href=\"/web-ui/admin/venue/devices\"]";
    @FindBy(how = How.CSS, using = editVenueDevicesButtonCSS)
    private WebElement editVenueDevicesButton;

    public void devicesButtonClick() {
        editVenueDevicesButton.click();
    }


    //             ============   REGISTER NEW DEVICE   LAYOUT ========
    public final static String macAddressInputCSS = "#mac";
    @FindBy(how = How.CSS, using = macAddressInputCSS)
    private WebElement macAddressInput;

    public void fillMacOfTheDevice(String mac) {

        macAddressInput.sendKeys(mac);

    }


    public final static String deviceTypeDropdownCSS = "#new-device-type";
    @FindBy(how = How.CSS, using = deviceTypeDropdownCSS)
    private WebElement deviceTypeDropdown;

    public void rolldownDeviceTypeDropdown() {
        deviceTypeDropdown.click();
    }


    public final static String deviceTypesValuesCSS = "#new-device-type>option";

    public List<WebElement> getDeviceTypesValues() {
        return deviceTypesValues;
    }

    @FindBy(how = How.CSS, using = deviceTypesValuesCSS)
    private List<WebElement> deviceTypesValues;


    public final static String registerDeviceButtonCSS = "div.panel div.panel-body form#deviceBean div button.btn[type=\"submit\"]";
    @FindBy(how = How.CSS, using = registerDeviceButtonCSS)
    private WebElement registerDeviceButton;

    public void saveNewDevice() {
        registerDeviceButton.click();
    }


    public final static String alertMsgOnDeviceRegisterCSS = "div#messageContainer div.col-md-12 div.alert";

    public WebElement getAlertMsgOnDeviceRegister() {
        return alertMsgOnDeviceRegister;
    }

    @FindBy(how = How.CSS, using = alertMsgOnDeviceRegisterCSS)
    private WebElement alertMsgOnDeviceRegister;

    // ============= DEVICES TABLESSS =============
    public final static String venuePcDevicesListCSS = "#vpcTable tbody tr";

    public List<WebElement> getVenuePcDevicesList() {
        return venuePcDevicesList;
    }

    @FindBy(how = How.CSS, using = venuePcDevicesListCSS)
    private List<WebElement> venuePcDevicesList;


    public final static String patronTabletsDevicesListCSS = "#tabletTable tbody tr";

    public List<WebElement> getPatronTabletsDevicesList() {
        return patronTabletsDevicesList;
    }

    @FindBy(how = How.CSS, using = patronTabletsDevicesListCSS)
    private List<WebElement> patronTabletsDevicesList;


    public final static String serverHandheldsDevicesListCSS = "#hhTable tbody  tr";

    public List<WebElement> getServerHandheldsDevicesList() {
        return serverHandheldsDevicesList;
    }

    @FindBy(how = How.CSS, using = serverHandheldsDevicesListCSS)
    private List<WebElement> serverHandheldsDevicesList;


    public final static String secondScreenDevicesListCSS = "#vimTable tbody tr";

    public List<WebElement> getSecondScreenDevicesList() {
        return secondScreenDevicesList;
    }

    @FindBy(how = How.CSS, using = secondScreenDevicesListCSS)
    private List<WebElement> secondScreenDevicesList;

//  =======================  unassigning DEVICES  ===================

    public final static String venuePcCheckBoxCSS = "table#vpcTable.table tbody tr td input";
    @FindBy(how = How.CSS, using = venuePcCheckBoxCSS)
    private WebElement venuePcCheckBox;

    public void tickVenueCheckbox() {
        venuePcCheckBox.click();
    }


    public final static String unassignVenuePCButtonCSS = "div#vpcActions.btn-toolbar div.btn-group button.un-assign-btn";
    @FindBy(how = How.CSS, using = unassignVenuePCButtonCSS)
    private WebElement unassignVenuePCButton;

    public void unassignVnePC() {
        unassignVenuePCButton.click();
    }

    public final static String venuePCBeenDeassignedMsgCSS = "div#messageContainer div.col-md-12 div.alert";

    public WebElement getVenuePCBeenDeassignedMsg() {
        return venuePCBeenDeassignedMsg;
    }

    @FindBy(how = How.CSS, using = venuePCBeenDeassignedMsgCSS)
    private WebElement venuePCBeenDeassignedMsg;


    public final static String patronTabletsCheckboxCSS = "table#tabletTable.table thead tr th.manage-device-column input";
    @FindBy(how = How.CSS, using = patronTabletsCheckboxCSS)
    private WebElement patronTabletsCheckbox;

    public void tickPatronTabletsCheckbox() {
        patronTabletsCheckbox.click();
    }

    public final static String unassignPatronTabletsButtonCSS = "div#tabletActions.btn-toolbar div.btn-group button.un-assign-btn";
    @FindBy(how = How.CSS, using = unassignPatronTabletsButtonCSS)
    private WebElement unassignPatronTabletsButton;

    public void unassignPatronTablets() {
        unassignPatronTabletsButton.click();
    }


    public final static String serverHandheldsCheckboxCSS = "table#hhTable.table thead tr th.manage-device-column input";
    @FindBy(how = How.CSS, using = serverHandheldsCheckboxCSS)
    private WebElement serverHandheldsCheckbox;

    public void tickServerHandheldsCheckbox() {
        serverHandheldsCheckbox.click();
    }


    public final static String unassignServerHandheldsButtonCSS = "div#hhActions.btn-toolbar div.btn-group button.un-assign-btn";
    @FindBy(how = How.CSS, using = unassignServerHandheldsButtonCSS)
    private WebElement unassignServerHandheldsButton;

    public void unassignServerHandelds() {
        unassignServerHandheldsButton.click();
    }


//    ===================================================================================================================


    public final static String secondScreenDevicesCheckboxCSS = "table#vimTable.table thead tr th.manage-device-column input";
    @FindBy(how = How.CSS, using = secondScreenDevicesCheckboxCSS)
    private WebElement secondScreenDevicesCheckbox;

    public void tickSecondScreenDevicesCheckbox() {
        secondScreenDevicesCheckbox.click();
    }


    public final static String unassignSecondScreenButtonCSS = "div#vimActions.btn-toolbar div.btn-group button.un-assign-btn";
    @FindBy(how = How.CSS, using = unassignSecondScreenButtonCSS)
    private WebElement unassignSecondScreenButton;

    public void unassign2ndScreenDevices() {
        unassignSecondScreenButton.click();
    }


    //    ================================  tablet drdopdowns section
    public final static String firstDropdownSelectCSS = "#coreAppsTable.table tbody tr:nth-child(1) td div.col-md-3";
    public final static String secondDropdownSelectCSS = "#coreAppsTable.table tbody tr:nth-child(2) td div.col-md-3";
    public final static String thirdDropdownSelectCSS = "#coreAppsTable.table tbody tr:nth-child(3) td div.col-md-3";
    public final static String fourthDropdownSelectCSS = "#coreAppsTable.table tbody tr:nth-child(4) td div.col-md-3";
    public final static String fifthDropdownSelectCSS = "#coreAppsTable.table tbody tr:nth-child(5) td div.col-md-3";
    public final static String sixthDropdownSelectCSS = "#coreAppsTable.table tbody tr:nth-child(6) td div.col-md-3";
    public final static String seventhDropdownSelectCSS = "#coreAppsTable.table tbody tr:nth-child(7) td div.col-md-3";
    public final static String eightDropdownSelectCSS = "#coreAppsTable.table tbody tr:nth-child(8) td div.col-md-3";
    public final static String ninethDropdownSelectCSS = "#coreAppsTable.table tbody tr:nth-child(9) td div.col-md-3";
    public final static String tenthDropdownSelectCSS = "#coreAppsTable.table tbody tr:nth-child(10) td div.col-md-3";


    public final static String firstDropdownSelectXPATH = "/html/body/div[2]/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[2]/div/select";
    public final static String secondDropdownSelectXPATH = "html/body/div[2]/div/div[2]/div/div[2]/div/table/tbody/tr[2]/td[2]/div/select";
    public final static String thirdDropdownSelectXPATH = "html/body/div[2]/div/div[2]/div/div[2]/div/table/tbody/tr[3]/td[2]/div/select";
    public final static String fourthDropdownSelectXPATH = "html/body/div[2]/div/div[2]/div/div[2]/div/table/tbody/tr[4]/td[2]/div/select";
    public final static String fifthDropdownSelectXPATH = "html/body/div[2]/div/div[2]/div/div[2]/div/table/tbody/tr[5]/td[2]/div/select";
    public final static String sixthDropdownSelectXPATH = "html/body/div[2]/div/div[2]/div/div[2]/div/table/tbody/tr[6]/td[2]/div/select";
    public final static String seventhDropdownSelectXPATH = "html/body/div[2]/div/div[2]/div/div[2]/div/table/tbody/tr[7]/td[2]/div/select";
    public final static String eighthDropdownSelectXPATH = "html/body/div[2]/div/div[2]/div/div[2]/div/table/tbody/tr[8]/td[2]/div/select";
    public final static String ninethDropdownSelectXPATH = "html/body/div[2]/div/div[2]/div/div[2]/div/table/tbody/tr[9]/td[2]/div/select";
    public final static String tenthDropdownSelectXPATH = "html/body/div[2]/div/div[2]/div/div[2]/div/table/tbody/tr[10]/td[2]/div/select";


    public final static String bundlesButtonCSS = "html body div.container-fluid div.row div.col-md-2 ul.nav li.sidebar-venue-bundles a.side-ellipsis";

    public final static String tabletsCSS = "html body div.container-fluid div.row div.col-md-2 ul.nav li.sidebar-venue-bundles-tablet a.side-ellipsis";
    public final static String handheldsCSS = "html body div.container-fluid div.row div.col-md-2 ul.nav li.sidebar-venue-bundles-handheld a.side-ellipsis";
    public final static String SecondScreensCSS = "html body div.container-fluid div.row div.col-md-2 ul.nav li.sidebar-venue-bundles-second-screen a.side-ellipsis";
    public final static String VenuePCsCSS = "html body div.container-fluid div.row div.col-md-2 ul.nav li.sidebar-venue-bundles-venue-pc a.side-ellipsis";
    public final static String SaveBundleButtonCSS = "html body div.row button.btn.btn-primary.saveAPKsAssignment";
    public final static String generateDataBundleBtnCSS = "#generateDataBundleBtn";
    public final static String bundledeviceBtnCSS = "html body  td div.btn-group button.btn.btn-primary.deploy-to-selected-devices";
    public final static String unClickAllDeviceCSS = "html body thead tr th.col-md-1.sorting_disabled input";
    public final static String ClickOneDeviceCSS = "html body  td.sorting_1 input.device-mac-checkbox";
    public final static String ClickDeployCSS = "html body  a.btn.btn-primary.dropdown-toggle";
    public final static String ClickUpdateInfoCSS = "html body div.row a.btn.btn-default";
    public final static String ClickOndemandCSS = ".deployOnDemand";
    public final static String UpdatingStatusFirstDeviceCSS = "#coreAppsTable_wrapper tbody tr:nth-child(1) td:nth-child(4)";

    public final static String changeHistoryButtonCSS = "html body div.container-fluid div.row div.col-md-2 ul.nav li.sidebar-venue-history a.side-ellipsis";



    @FindBy(how = How.CSS, using = UpdatingStatusFirstDeviceCSS)
    public WebElement UpdatingStatusFirstDevice;

    @FindBy(how = How.CSS, using = changeHistoryButtonCSS)
    public WebElement changeHistoryButton;

    @FindBy(how = How.CSS, using = ClickUpdateInfoCSS)
    public WebElement ClickUpdateInfo;


    @FindBy(how = How.CSS, using = ClickOndemandCSS)
    public List <WebElement> ClickOndemandList;

    public List<WebElement> getClickOndemandList() {
        return ClickOndemandList;
    }

    @FindBy(how = How.CSS, using = ClickDeployCSS)
    public List <WebElement> ClickDeployList;

    public List<WebElement> getClickDeployList() {
        return ClickDeployList;
    }

    @FindBy(how = How.CSS, using = ClickOneDeviceCSS)
    public List<WebElement> ClickOneDeviceList;

    @FindBy(how = How.CSS, using = unClickAllDeviceCSS)
    public WebElement unClickAllDevice;

    @FindBy(how = How.CSS, using = bundledeviceBtnCSS)
    public WebElement bundledeviceBtn;

    @FindBy(how = How.CSS, using = SaveBundleButtonCSS)
    public WebElement SaveBundleButton;

    @FindBy(how = How.CSS, using = generateDataBundleBtnCSS)
    public WebElement generateDataBundleBtn;

    @FindBy(how = How.CSS, using = firstDropdownSelectCSS)
    public WebElement DropdownSelect1;

    @FindBy(how = How.CSS, using = secondDropdownSelectCSS)
    public WebElement DropdownSelect2;


    @FindBy(how = How.CSS, using = thirdDropdownSelectCSS)
    public WebElement DropdownSelect3;

    @FindBy(how = How.CSS, using = fourthDropdownSelectCSS)
    public WebElement DropdownSelect4;

    @FindBy(how = How.CSS, using = fifthDropdownSelectCSS)
    public WebElement DropdownSelect5;

    @FindBy(how = How.CSS, using = sixthDropdownSelectCSS)
    public WebElement DropdownSelect6;


    @FindBy(how = How.CSS, using = seventhDropdownSelectCSS)
    public WebElement DropdownSelect7;

    @FindBy(how = How.CSS, using = eightDropdownSelectCSS)
    public WebElement DropdownSelect8;

    @FindBy(how = How.CSS, using = ninethDropdownSelectCSS)
    public WebElement DropdownSelect9;

    @FindBy(how = How.CSS, using = tenthDropdownSelectCSS)
    public WebElement DropdownSelect10;


//   ========== DEVICES LOGS SECTION ==================

    public final static String devicesLogButtonCSS="ul.nav li.sidebar-venue-devices-log a.side-ellipsis[href=\"/web-ui/admin/deviceLog/new\"]";
    @FindBy(how = How.CSS, using = devicesLogButtonCSS)
    private WebElement devicesLogButton;

    public void navigateToDevicesLogs(){
        devicesLogButton.click();
    }

//   ===================================================

    public final static String devicesLogLayoutDevicesDropdownCSS="#mac";
    @FindBy(how = How.CSS, using = devicesLogLayoutDevicesDropdownCSS)
    private WebElement devicesLogLayoutDevicesDropdown;

    public void rollDownDevicesDropdown(){
        devicesLogLayoutDevicesDropdown.click();
    }


    public final static String devicesWithLogsListCSS="#mac option";

    public List<WebElement> getDevicesWithLogsList() {
        return devicesWithLogsList;
    }

    @FindBy(how = How.CSS, using = devicesWithLogsListCSS)
    private List< WebElement> devicesWithLogsList;


    public final static String askForLogsCSS="div.panel-body div.row div.col-md-4 a#chain-submit.btn";
    @FindBy(how = How.CSS, using = askForLogsCSS)
    private WebElement askForLogs;

    public void logRequest(){
        askForLogs.click();
    }

    public final static String actualStatusObtainedCSS="tbody.logsPendingBody>tr:nth-child(1) td.status";

    public WebElement getActualStatusObtained() {
        return actualStatusObtained;
    }

    @FindBy(how = How.CSS, using = actualStatusObtainedCSS)
    private WebElement actualStatusObtained;



//    1st approach
//   venue edit layout;  BUNDLES >devices sectnio
//    devices list CSS
    public final static String bundleSectionDevicesListCSS="tbody#events-delegator-container tr.enclosing-select-devices-btn-container td div.btn-group button.btn";

    public List<WebElement> getBundleSectionDevicesList() {
        return bundleSectionDevicesList;
    }

    public List<WebElement> getClickOneDeviceList() {
        return ClickOneDeviceList;
    }

    @FindBy(how = How.CSS, using = bundleSectionDevicesListCSS)
    public List <WebElement> bundleSectionDevicesList;


//    2nd approach; one button selection
    public final static String bundleSectionFirstDeviceButtonCSS="#dataBundlesTable tbody tr:nth-child(1) td>div.btn-group button.btn";
    @FindBy(how = How.CSS, using =bundleSectionFirstDeviceButtonCSS)
    public WebElement bundleSectionFirstDeviceButton;

    public  final static  String CurrentVerStringCSS="#dataBundlesTable tbody tr:nth-child(1) td:nth-child(2)";

    @FindBy(how = How.CSS, using = CurrentVerStringCSS)
    public WebElement WebCurrentVerString;


    public  final static  String ChangeHistoryFirstColCSS = "table.table.table-hover.table-striped thead tr:nth-child(1) th:nth-child(1)";
    public  final static  String ChangeHistorySecondColCSS = "table.table.table-hover.table-striped thead tr:nth-child(1) th:nth-child(2)";
    public  final static  String ChangeHistory3rdCol = "table.table.table-hover.table-striped thead tr:nth-child(1) th:nth-child(3)";
    public  final static  String ChangeHistory4thCol = "table.table.table-hover.table-striped thead tr:nth-child(1) th:nth-child(3)";

    public void clickOnAppropriateDevice(){
        bundleSectionFirstDeviceButton.click();
    }

}