package com.tanjarine.automation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by ypolshchykau on 15.04.2014.
 */
public class AdminHomePage {
    // public final static String headerDownsCSS = ".caret";
    public final static String headerDownsCSS = "a.dropdown-toggle[data-toggle=\"dropdown\"]>b.caret";

    public final static String userLogoutCSS = ".glyphicon.glyphicon-off";

    public final static String venueManagementHeaderCSS = "li>a[data-role=\"service\"][href=\"/web-ui/admin/venue/list\"]";

    public final static String gameCatalogHeaderCSS = "#services [data-role=\"service\"][href=\"/web-ui/admin/game/list\"]";

    @FindBy(how = How.CSS, using = gameCatalogHeaderCSS)
    private WebElement gameCatalogHeader;

    public void gameCatalogSelectingInDropdown() {
        gameCatalogHeader.click();
    }

    public final static String globalAdminCSS = "ul.nav:nth-child(2) li:nth-child(1) b.caret";

    @FindBy(how = How.CSS, using = userLogoutCSS)
    private WebElement logoutButton;

    @FindBy(how = How.CSS, using = headerDownsCSS)
    private List<WebElement> headerDropdowns;

    public List<WebElement> getHeaderDropdowns() {
        return headerDropdowns;
    }

    public WebElement getVenueManagementItem() {
        return venueManagementItem;
    }

    @FindBy(how = How.CSS, using = venueManagementHeaderCSS)
    private WebElement venueManagementItem;

    public void selectVenueClick() {
        headerDropdowns.get(0).click();
    }

    public void globalAdminClick() {
        headerDropdowns.get(1).click();
    }

    public WebElement getGlobalAdminDropdown() {
        return headerDropdowns.get(1);
    }
    public void userSectionClick() {
        headerDropdowns.get(2).click();
    }

    public void logoutButtonClick() {
        logoutButton.click();
    }

    public void venueManagementSectionClick() {
        venueManagementItem.click();
    }

    public final static String headerWebAdminHelpCSS = "a[data-target=\"#helpModal\"] span";
    @FindBy(how = How.CSS, using = headerWebAdminHelpCSS)
    private WebElement headerWebAdminHelp;

    public void openAboutAdmin() {
        headerWebAdminHelp.click();
    }

    public final static String helpFormLayoutCSS = "html body.modal-open div#helpModal.modal div.modal-dialog";
    @FindBy(how = How.CSS, using = helpFormLayoutCSS)
    private WebElement helpFormLayout;

    public final static String adminVersionCSS = "body.modal-open div#helpModal div.modal-content div.modal-body>p";

    public WebElement getAdminVersion() {
        return adminVersion;
    }

    @FindBy(how = How.CSS, using = adminVersionCSS)
    private WebElement adminVersion;

    public final static String helpLayoutCloseButtonCSS = "div#helpModal.modal.fade.in button.btn";
    @FindBy(how = How.CSS, using = helpLayoutCloseButtonCSS)
    private WebElement helpLayoutCloseButton;

    public void closeHelp() {
        helpLayoutCloseButton.click();
    }
    /**
     * PAGE BODY LAYOUT. Application management section
     */

    public final static String applicationManagementCSS = "a[href=\"/web-ui/admin/core/apk/list\"]>h4";
    @FindBy(how = How.CSS, using = applicationManagementCSS)
    private WebElement applicationManagement;

    public void navigateToApplicationManagement(){
        applicationManagement.click();
    }


    public final static String adminHomeVelcomeCSS="div.col-md-6 div.bs-callout h4";

    public WebElement getAdminHomeVelcome() {
        return adminHomeVelcome;
    }

    @FindBy(how = How.CSS, using =adminHomeVelcomeCSS)
    private WebElement adminHomeVelcome;

}
