package com.tanjarine.automation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class GameManagementPage {
    //    =============== WEBADMIN 3.5 locator ================
    public final static String gameManagementButtonLeftColumnCSS = "li.nav-header a[href=\"#\"]";
//    ============ WEBADMIN 4.0  locator ====================
//    public final static String gameManagementButtonLeftColumnCSS="div.sidebar a[href=\"/web-ui/admin/game/list\"]";

    public WebElement getGameManagementButtonLeftColumn() {
        return gameManagementButtonLeftColumn;
    }

    @FindBy(how = How.CSS, using = gameManagementButtonLeftColumnCSS)
    private WebElement gameManagementButtonLeftColumn;

    public final static String addGameButtonCSS = "div.col-md-2 a[href=\"/web-ui/admin/game/add-game\"]";

    @FindBy(how = How.CSS, using = addGameButtonCSS)
    private WebElement addGameButton;

    public void addGame() {
        addGameButton.click();
    }


    //    ======================== game collection list ===============
    public final static String overallGameCollectionListCSS = "#collectionsTable td[data-sort=\"title\"]";
    @FindBy(how = How.CSS, using = overallGameCollectionListCSS)
    private List<WebElement> overallGameCollectionList;


    public List<WebElement> getGameCollectionList() {
        return overallGameCollectionList;
    }


    //    ============= [Edit] button(-s) list ===================
    public final static String editGameCollectionButtonListCSS = "td>a[href*=\"game-collection-edit\"]";
    @FindBy(how = How.CSS, using = editGameCollectionButtonListCSS)
    private List<WebElement> editGameCollectionButtonList;


    public List<WebElement> getEditGameCollectionButtonList() {
        return editGameCollectionButtonList;
    }


    //    =============== hidden Delete ID for game collection selected ===========
    public final static String gameCollectionDeleteHiddenLocatorCSS = "#delete-game-collection-btn";

    //    ============ WEBADMIN 3.5 locator  =======================
    public final static String gameCollectionButtonCSS = "div.col-md-2.col-sm-2.sidebar a[href=\"/web-ui/admin//game/game-collections-list\"]";

    //    ============ WEBADMIN 4.0 locator  =======================
//    public final static String gameCollectionButtonCSS="ul.nav.nav-list a[href=\"/web-ui/admin/game/game-collections-list\"]";
    @FindBy(how = How.CSS, using = gameCollectionButtonCSS)
    private WebElement gameCollectionsButton;

    public void gameCollectionsNavigate() {
        gameCollectionsButton.click();
    }

    public final static String selectAPKButtonCSS = "div.input-append  span.btn";

    public WebElement getSelectAPKButton() {
        return selectAPKButton;
    }

    @FindBy(how = How.CSS, using = selectAPKButtonCSS)
    private WebElement selectAPKButton;

    public void selectApkGameClick() {
        selectAPKButton.click();
    }

    public final static String selectFileInputCSS = "div.input-append>div.form-control";
    @FindBy(how = How.CSS, using = selectFileInputCSS)
    private WebElement selectFileInput;

    public void selectFileInputClick() {
        selectFileInput.click();
    }

    // =========================  add game: Base tab input fields   layout:  =================================
    public final static String addGameLayoutHeaderCSS = "#base  h2";
    @FindBy(how = How.CSS, using = addGameLayoutHeaderCSS)
    private WebElement addGameLayoutHeader;

    public final static String addGameLayoutTitleInputCSS = "#title";
    @FindBy(how = How.CSS, using = addGameLayoutTitleInputCSS)
    private WebElement addGameLayoutTitleInput;

    public void addGameEnterTitle(String title) {
        addGameLayoutTitleInput.sendKeys(title);
    }

    public final static String addGameLayoutDescriptionCSS = "#base #description";
    @FindBy(how = How.CSS, using = addGameLayoutDescriptionCSS)
    private WebElement addGameLayoutDescription;

    public void addGameEnterDescription(String toEnter) {
        addGameLayoutDescription.sendKeys(toEnter);
    }

    // genres section!
    // 1st genre item!\
    public final static String addGameLayoutLastGenreCSS = ".container-fluid.input-xxlarge div.span4 input#genre7";
    // genre list
    public final static String addGameLayoutGenreListCSS = ".container-fluid.input-xxlarge div.span4 input";

    public List<WebElement> getAddGameLayoutGenreList() {
        return addGameLayoutGenreList;
    }

    @FindBy(how = How.CSS, using = addGameLayoutGenreListCSS)
    private List<WebElement> addGameLayoutGenreList;

    public final static String addGameLayoutTeenAudienceRadiobuttonSelectCSS = "#audience3";
    @FindBy(how = How.CSS, using = addGameLayoutTeenAudienceRadiobuttonSelectCSS)
    private WebElement addGameLayoutTeenAudienceRadiobuttonSelect;

    public void selectAudience() {
        addGameLayoutTeenAudienceRadiobuttonSelect.click();
    }

    // modes selection
    public final static String addGameLayoutModeSingleCSS = "div.span4 input#mode1";
    @FindBy(how = How.CSS, using = addGameLayoutModeSingleCSS)
    private WebElement addGameLayoutModeSingle;

    public void clickSingleMode() {
        addGameLayoutModeSingle.click();
    }

    public final static String addGameLayoutModeMultiplayerCSS = "div.span4 input#mode1";
    @FindBy(how = How.CSS, using = addGameLayoutModeMultiplayerCSS)
    private WebElement addGameLayoutModeMultiplayer;

    public void clickMultiplayerMode() {
        addGameLayoutModeSingle.click();
    }

    public final static String addGameLayoutTagsInputCSS = "#tags li input";
    @FindBy(how = How.CSS, using = addGameLayoutTagsInputCSS)
    private WebElement addGameLayoutTagsInput;

    public void inputTags(String tag) {
        addGameLayoutTagsInput.sendKeys(tag);

    }

    //================ primary media layout  ========
    public static final String addGamePrimaryMediaSectionCSS = "a[href=\"#primaryMedia\"]";

    @FindBy(how = How.CSS, using = addGamePrimaryMediaSectionCSS)
    private WebElement addGamePrimaryMediaSection;

    public void addGameNavigateToPrimaryMediaSection() {
        addGamePrimaryMediaSection.click();
    }

    public static final String primaryMediaTitleCSS = "#primaryMedia div.col-md-8 h3";

    public WebElement getPrimaryMediaTitle() {
        return primaryMediaTitle;
    }

    @FindBy(how = How.CSS, using = primaryMediaTitleCSS)
    private WebElement primaryMediaTitle;

    public static final String addGamePrimaryTabSelectLogoCSS = "html body div.container-fluid div.row div.col-md-10 form#gameBean div.tab-content div#primaryMedia.tab-pane div.col-md-8 div.row:nth-child(2)  div.col-md-12 div.fileupload div span.btn";
    @FindBy(how = How.CSS, using = addGamePrimaryTabSelectLogoCSS)
    private WebElement addGamePrimaryTabSelectLogo;

    public void clickSelectImageLogo() {
        addGamePrimaryTabSelectLogo.click();
    }

    public static final String addGamePrimaryTabSelectPrimScreenCSS = "html body div.container-fluid div.row div.col-md-10 form#gameBean div.tab-content div#primaryMedia.tab-pane div.col-md-8 div.row:nth-child(3)  div.col-md-12 div.fileupload div span.btn";
    @FindBy(how = How.CSS, using = addGamePrimaryTabSelectPrimScreenCSS)
    private WebElement addGamePrimaryTabSelectPrimScreen;

    public void clickSelectPrimaryScreen() {
        addGamePrimaryTabSelectPrimScreen.click();
    }

    //  involving selection all [Select File] buttons
    /*public static final String SelectFileButtonListCSS="html body div.container-fluid div.row div.col-md-10 form#gameBean div.tab-content div#primaryMedia.tab-pane div.row div.col-md-8 div.row div.col-md-12 div.fileupload div span.btn";
    @FindBy(how = How.CSS, using = SelectFileButtonListCSS)
    private List<WebElement> SelectFileButtonList;
    public void clickFirst(){
        SelectFileButtonList.get(0).click();
    }
    */
    // ========= add to collections section  ==============
    public static final String addGameAddToCollectionsSectionCSS = "a[href=\"#addToCollections\"]";

    @FindBy(how = How.CSS, using = addGameAddToCollectionsSectionCSS)
    private WebElement addGameAddToCollectionsSection;

    public void addToCollectionsNavigate() {
        addGameAddToCollectionsSection.click();
    }

    // select all button
    // public static final String addGameAddToCollectionsSelectAllCSS="div.btn-group  button.btn:nth-child(1)";
    public static final String addGameAddToCollectionsSelectAllCSS = "html body div.container-fluid div.row div.col-md-10 form#gameBean div.tab-content div#addToCollections.tab-pane div.row div.col-md-8 div.btn-group button.btn:nth-child(1)";
    @FindBy(how = How.CSS, using = addGameAddToCollectionsSelectAllCSS)
    private WebElement addGameAddToCollectionsSelectAll;

    public void addToCollectionsSelectAllClick() {
        addGameAddToCollectionsSelectAll.click();
    }

    // ==========================
    // add Game submit button -
    public final static String submitButtonCSS = "button[type=\"submit\"]";

    @FindBy(how = How.CSS, using = submitButtonCSS)
    private WebElement submitButton;

    public void submitGame() {
        submitButton.click();
    }

    //    tab to which game has added
    public final static String activeTabCSS = "div.col-md-10  #group-choise-navbar li.active";
    @FindBy(how = How.CSS, using = activeTabCSS)
    private WebElement activeTab;

    //============== GAME collections layout
    public final static String createNewGameCollectionCSS = "div.col-md-8  a.btn[href=\"/web-ui/admin/game/game-collection-create\"]";
    @FindBy(how = How.CSS, using = createNewGameCollectionCSS)
    private WebElement createNewGameCollection;

    public void createNewGameCollection() {
        createNewGameCollection.click();
    }

    public final static String newCollectionNameCSS = "#gameCollectionBean #title";
    @FindBy(how = How.CSS, using = newCollectionNameCSS)
    private WebElement newCollectionName;

    public void enterCollectionName(String toInput) {
        newCollectionName.sendKeys(toInput);
    }

    public final static String newCollectionDescriptionCSS = "#gameCollectionBean #description";
    @FindBy(how = How.CSS, using = newCollectionDescriptionCSS)
    private WebElement newCollectionDescription;

    public void enterCollectionDescription(String toEnter) {
        newCollectionDescription.sendKeys(toEnter);
    }

    public final static String addGamesButtonCSS = "#gameCollectionBean div.col-md-12 button.btn";

    @FindBy(how = How.CSS, using = addGamesButtonCSS)
    private WebElement addGamesButton;

    public void addGames() {
        addGamesButton.click();
    }

    // choose games  form (layout)
    public final static String chooseGamesHeaderLabelCSS = "body.modal-open   form#gameCollectionBean div#gamesListModal.modal div.modal-dialog div.modal-content   div.modal-header h4.modal-title";
    @FindBy(how = How.CSS, using = chooseGamesHeaderLabelCSS)
    private WebElement chooseGamesHeaderLabel;

    public final static String gamesListToChooseCSS = "form#gameCollectionBean div#gamesListModal div.modal-content div#accordion div[id*=\"gamesAccordion\"] h4.panel-title a";

    public List<WebElement> getGamesListToChoose() {
        return gamesListToChoose;
    }

    @FindBy(how = How.CSS, using = gamesListToChooseCSS)
    private List<WebElement> gamesListToChoose;

    // this CSS is a partial CSS ; index  should be added
    public final static String gameCategoryListCSS_partial = "ul#gamesList-";

    public final static String addSelectedButtonCSS = "html body.modal-open div.container-fluid div.row div.col-md-10 div.row div.col-md-8 form#gameCollectionBean div.row div#gamesListModal.modal div.modal-dialog div.modal-content div.modal-body div.modal-footer button.btn";
    @FindBy(how = How.CSS, using = addSelectedButtonCSS)
    private WebElement addSelectedButton;

    public void clickAddSelected() {
        addSelectedButton.click();
    }

    public final static String saveCollectionButtonCSS = "html body div.container-fluid div.row div.col-md-10 div.row div.col-md-8 form#gameCollectionBean div.btn-group button.btn[type=\"submit\"]";
    @FindBy(how = How.CSS, using = saveCollectionButtonCSS)
    private WebElement saveCollectionButton;

    public void saveCollection() {
        saveCollectionButton.click();
    }

    public final static String collectionsTableCSS = "table#collectionsTable";
    @FindBy(how = How.CSS, using = collectionsTableCSS)
    private WebElement collectionsTable;
}
