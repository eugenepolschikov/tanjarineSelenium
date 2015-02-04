package com.tanjarine.automation.pageobjects.menu.food;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by ypolshchykau on 16.09.2014.
 */
public class MenuCommonPage {

   public final static String editVenueLayouteMenuButtonCSS="a.side-ellipsis[href=\"/web-ui/admin-ui/emenu/list\"]";
   @FindBy(how = How.CSS, using = editVenueLayouteMenuButtonCSS)
   private WebElement editVenueLayouteMenuButton;

    public void navigateToeMenuSection(){
        editVenueLayouteMenuButton.click();
    }


    public final static String menuLayoutAdminHomeButtonCSS="div#sideBarContainer.col-md-2 ul.nav li a[href=\"/web-ui/admin/home\"]>span";
    @FindBy(how = How.CSS, using = menuLayoutAdminHomeButtonCSS)
    private WebElement menuLayoutAdminHomeButton;

    public void navigateBackToAdminHome(){
        menuLayoutAdminHomeButton.click();

    }


    public final static String emenuCategoryListCSS="#table-categories tbody  tr td.ellipsis>a";

    public List<WebElement> getEmenuCategoryList() {
        return emenuCategoryList;
    }

    @FindBy(how = How.CSS, using = emenuCategoryListCSS)
    private List<WebElement> emenuCategoryList;



    public final static String tableMenuItemsCSS="#table-category-menuItems";



    public final static String subcategoryButtonCSS="div#secondColumn.col-md-4.items-column div.row div.col-md-12 button.btn.btn-success.btn-xs.add-subCategory span";
    @FindBy(how = How.CSS, using = subcategoryButtonCSS)
    private WebElement subcategoryButton;

    public void addSubcateogry(){
        subcategoryButton.click();
    }


//    items within category selected
    public final static String addNewItemUnderSelectedCategoryCSS="button[data-target=\"#addMenuItemModal\"]";
    @FindBy(how = How.CSS, using = addNewItemUnderSelectedCategoryCSS)
    private WebElement addNewItemUnderSelectedCategory;

    public void addNewItem(){
        addNewItemUnderSelectedCategory.click();
    }



//    search string input
    public final static String searchInputCSS="#searchStringInput";
    @FindBy(how = How.CSS, using= searchInputCSS)
    private  WebElement searchInput;

    public void setFocusOnSearchInput(){
        searchInput.clear();
    }



//    category list
    public final static String categoryListCSS="#table-categories";
    @FindBy(how = How.CSS, using= categoryListCSS)
    private  WebElement categoryList;


//    menu footer
    public final static String  eMenuFooterCSS="#emenuFooter";
    @FindBy(how = How.CSS, using= eMenuFooterCSS)
    private  WebElement eMenuFooter;



//    category deactivation button list
//    public final static String categoryActivateDeactivateButtonListCSS ="table#table-categories.table.table-categories tbody.ui-sortable  button.btn.btn-xs.btn-green.category-switch span";
    public final static String categoryActivateDeactivateButtonListCSS ="table#table-categories.table.table-categories tbody.ui-sortable  button.btn.btn-xs.category-switch span";


    public List<WebElement> getCategoryActivateDeactivateButtonList() {
        return categoryDeactivationButtonList;
    }

    @FindBy(how = How.CSS, using= categoryActivateDeactivateButtonListCSS)
    private  List <WebElement> categoryDeactivationButtonList;


    public final static String confirmCategoryDeactivationButtonCSS="div#confirmationDialog.modal.fade.disabled-scroll.in  div.col-md-12 button.btn.btn-success";
    @FindBy(how = How.CSS, using= confirmCategoryDeactivationButtonCSS)
    private  WebElement confirmCategoryDeactivationButton;

    public void confirmDeactivation(){
        confirmCategoryDeactivationButton.click();
    }



    public final static String footerButtonCSS="div#emenuFooter button.btn.btn-success.btn-xs.edit-footer.form-group span";
    @FindBy(how = How.CSS, using= footerButtonCSS)
    private  WebElement footerButton;

    public void addFooter(){
        footerButton.click();
    }

    public final static String footerTextAreaCSS="div#emenuFooter div.row.form-group div.col-md-11.ellipsis textarea.form-control.menu-footer";

    public WebElement getFooterTextArea() {
        return footerTextArea;
    }

    @FindBy(how = How.CSS, using= footerTextAreaCSS)
    private  WebElement footerTextArea;

    public void fillFooter(String txt){
        footerTextArea.sendKeys("\n");
        footerTextArea.sendKeys(txt);

    }

    public final static String submitNewFooterButtonCSS="div#emenuFooter div#footer-actions button.btn.btn-success";
    @FindBy(how = How.CSS, using= submitNewFooterButtonCSS)
    private  WebElement submitNewFooterButton;

    public void submitFooter(){
        submitNewFooterButton.click();
    }



}
