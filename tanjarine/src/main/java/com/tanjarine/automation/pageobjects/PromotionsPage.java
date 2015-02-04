package com.tanjarine.automation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by ypolshchykau on 25.06.2014.
 */
public class PromotionsPage {
    // promotions List
    public static final String promotionsAllTabCSS = "#typeChooser li:first-child";
    public WebElement getPromotionsAllTab() {
        return promotionsAllTab;
    }

    @FindBy(how = How.CSS, using = promotionsAllTabCSS)
    private WebElement promotionsAllTab;

    // dashboard promotion
    public static final String addDashboardPromoButtonCSS = "a#addDashboard";
    @FindBy(how = How.CSS, using = addDashboardPromoButtonCSS)
    private WebElement addDashboardPromoButton;

    public void addDashboardPromotion() {
        addDashboardPromoButton.click();
    }

    // campaign promotion
    public static final String addCampaignPromotionButtonCSS = "a#addCampaign";
    @FindBy(how = How.CSS, using = addCampaignPromotionButtonCSS)
    private WebElement addCampaignPromotionButton;

    public void addCampaignPromotion() {
        addCampaignPromotionButton.click();
    }
    // ============= COMMON INPUTS / BUTTONS for ALL promotions
    public static final String newPromotionTitleInputCSS = "#title";
    @FindBy(how = How.CSS, using = newPromotionTitleInputCSS)
    private WebElement newPromotionTitleInput;

    public void fillPromotionTitle(String toEnter) {
        newPromotionTitleInput.clear();
        newPromotionTitleInput.sendKeys(toEnter);
    }

//    public static final String newPromotionStartDateButtonCSS = "#startDate  i.icon-calendar";
    public static final String newPromotionStartDateButtonCSS="div#startDate-container.input-append.date div.input-group span.add-on.input-group-addon i.icon-calendar.glyphicon.glyphicon-calendar";

    @FindBy(how = How.CSS, using = newPromotionStartDateButtonCSS)
    private WebElement newPromotionStartDateButton;

    public void startDateButtonClick() {
        newPromotionStartDateButton.click();
    }

//    public static final String newPromotionEndDateButtonCSS = "#endDate  i.icon-calendar";
    public static final String newPromotionEndDateButtonCSS="div#endDate-container.input-append.date div.input-group span.add-on.input-group-addon i.icon-calendar.glyphicon.glyphicon-calendar";

    @FindBy(how = How.CSS, using = newPromotionEndDateButtonCSS )
    private WebElement newPromotionEndDateButton;

    public void endDateButtonClick() {
        newPromotionEndDateButton.click();
    }

    public static final String newPromotionSaveButtonCSS = "#chain-submit";

    @FindBy(how = How.CSS, using = newPromotionSaveButtonCSS)
    private WebElement newPromotionSaveButton;

    public void submitNewPromotion() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        newPromotionSaveButton.click();
    }

    public static final String newPromotionCancelButtonCSS = "div.form-actions a[ href=\"/web-ui/admin/promotion/commonList\"]";

    //    public final static String newPromotionSelectImageButtonCSS ="span.btn .fileupload-new";
    // WORKING!!!!
    //    public final static String  newPromotionSelectImageButtonCSS="html body div.container-fluid div.row div.col-md-10 form#promotionForm.panel div.panel-body div.row div.col-md-12 div.fileupload div span.btn";
    public final static String  newPromotionSelectImageButtonCSS = "html body div.container-fluid div.row div.col-md-10 form#promotionForm.panel div.panel-body div.row div.fileupload div span.btn";
    @FindBy(how = How.CSS, using = newPromotionSelectImageButtonCSS)
    private WebElement newPromotionSelectImageButton;

    public void selectImageButtonClick() {
        newPromotionSelectImageButton.click();
    }

    public static final String newPromotionChooseItemsButtonCSS = "button[data-target=\"#itemsModal\"]";
    @FindBy(how = How.CSS, using = newPromotionChooseItemsButtonCSS)
    private WebElement newPromotionChooseItemsButton;

    public void chooseItemsClick() {
        newPromotionChooseItemsButton.click();
    }
    //    ================ END OF COMMON INPUTS/BUTTONS for   promotions  ===========================
    //    ==============CALENDAR manipuilation
    public final static String calendarPrevButtonCSS = "div[style*=\"display: block\"] div.datepicker-days table.table-condensed>thead>tr>th.prev";
    @FindBy(how = How.CSS, using = calendarPrevButtonCSS)
    private WebElement calendarPrevButton;

    public void startDatePrevClick() {
        calendarPrevButton.click();
    }

    public final static String calendarNextButtonCSS = "div[style*=\"display: block\"] div.datepicker-days table.table-condensed>thead>tr>th.next";
    @FindBy(how = How.CSS, using = calendarNextButtonCSS)
    private WebElement calendarNextButton;
    public void endDateNextClick() {
        calendarNextButton.click();
    }

    public final static String calendarDaysListCSS = "html body div.bootstrap-datetimepicker-widget[style*=\"display: block\"] div.datepicker div.datepicker-days table.table-condensed tbody tr td.day";

    public List<WebElement> getCalendarDaysList() {
        return calendarDaysList;
    }

    @FindBy(how = How.CSS, using = calendarDaysListCSS)
    private List<WebElement> calendarDaysList;

    //  =============  marker based promo section -     =======================
    public static final String addMarkerBasedPromotionButtonCSS = "a#addMarker";

    @FindBy(how = How.CSS, using = addMarkerBasedPromotionButtonCSS)
    private WebElement addMarkerBasedPromotionButton;

    public void addMarkerBasedPromotion() {
        addMarkerBasedPromotionButton.click();
    }

    public static final String markerBasedPromotionGamesButtonToBeSelectedCSS = "button[data-bucket=\"GAME\"]";

    public static final String markerBasedPromotionMenuItemsButtonToBeSelectedCSS = "button[data-bucket=\"EMENU\"]";

    @FindBy(how = How.CSS, using = markerBasedPromotionMenuItemsButtonToBeSelectedCSS)
    private WebElement markerBasedPromotionMenuItemsButtonToBeSelected;

    public void markerBasedPromoMenuItemsSelect() {
        markerBasedPromotionMenuItemsButtonToBeSelected.click();
    }

    public static final String markerBasedPromotionMarkerTypeDropdownCSS = "#markerList";

    @FindBy(how = How.CSS, using = markerBasedPromotionMarkerTypeDropdownCSS)
    private WebElement markerBasedPromotionMarkerTypeDropdown;
    public void markerTypeDropdownClick() {
        markerBasedPromotionMarkerTypeDropdown.click();
    }
    // ====== ============= OPTIONS  ================
    public static final String markerPOPULARCSS = "#markerList>[value=\"POPULAR\"]";
    @FindBy(how = How.CSS, using = markerPOPULARCSS)
    private WebElement markerPOPULAR;

    public void popularClick() {
        markerPOPULAR.click();
    }

    public static final String markerNEWCSS = "#markerList>[value=\"NEW\"]";

    @FindBy(how = How.CSS, using = markerNEWCSS)
    private WebElement markerNEW;

    public void newClick() {
        markerNEW.click();
    }

    public static final String markerSPECIALCSS = "#markerList>[value=\"SPECIAL\"]";

    @FindBy(how = How.CSS, using = markerSPECIALCSS)
    private WebElement markerSPECIAL;

    public void specialClick() {
        markerNEW.click();
    }
    //     ============== CHOOOSE items FORM
    public static final String  firstItemAtChooseItemsFormCSS = "ul#availItems>li:first-child";
    @FindBy(how = How.CSS, using =  firstItemAtChooseItemsFormCSS)
    private WebElement  firstItemAtChooseItemsForm;

    public static final String availableItemListCSS = "ul#availItems>li[id*=\"availItem\"]";

    public List<WebElement> getAvailableItemList() {
        return availableItemList;
    }

    @FindBy(how = How.CSS, using =  availableItemListCSS)
    private List<WebElement>  availableItemList;

    public static final String chooseItemsFormApplyButtonCSS = "#itemsModal div.modal-footer button.btn[data-dismiss=\"modal\"]";
    @FindBy(how = How.CSS, using =  chooseItemsFormApplyButtonCSS)
    private WebElement  chooseItemsFormApplyButton;

    public void applySelectedItems() {
        chooseItemsFormApplyButton.click();
    }

    //    ================= END of marker based promotion ===================
    //    campaign promotion!!!!
    public final static String campaignPromotionChooseItemsButtonCSS = "button[data-target=\"#itemsModal\"]";
    //    ============== end of campaign promotion
    //    dashboard promotion !!!! ==================
    public final static String dashboardPromotionImageButtonCSS = "button.media-image[type=\"button\"]";
    public final static String dashboardPromotionVideoButtonCSS = "button.media-video[type=\"button\"]";
    public final static String dashboardPromotionVenueButtonCSS = "div.btn-group>#VENUE";
    public final static String dashboardPromotionGamesButtonCSS = "div.btn-group>#GAME";
    public final static String dashboardPromotionMusicButtonCSS = "div.btn-group>#MUSIC";
    public final static String dashboardPromotionLoaltyButtonCSS="div.btn-group>button#LOYALTY.btn";
    public final static String dashboardPromoZoneOneButtonCSS="div#displayZonesContainer.row div.btn-group label.btn.btn-default:nth-child(1)";
    public final static String dashboardPromoZoneTwoButtonCSS="div#displayZonesContainer.row div.btn-group label.btn.btn-default:nth-child(2)";
    public final static String dashboardPromoZoneThreeButtonCSS="div#displayZonesContainer.row div.btn-group label.btn.btn-default:nth-child(3)";


    //    ============== end of dashboard promotion!
}
