package com.tanjarine.automation.pageobjects.menu.food;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by ypolshchykau on 28.11.2014.
 */
public class MenuCategory extends MenuCommonPage {
    //    add category button
    public final static String addCategoryCSS = "#firstColumn div.col-md-12 button.btn.btn-success.btn-xs.add-cat-btn";


    @FindBy(how = How.CSS, using = addCategoryCSS)
    private WebElement addCategory;

    public void addCategory() {
        addCategory.click();
    }


    public final static String categoryNameTextAreaCSS = "textArea.categoryInput[id=\"categoryItems0.name\"]";

    @FindBy(how = How.CSS, using = categoryNameTextAreaCSS)
    private WebElement categoryNameTextArea;


    public void fillCategoryName(String toFill) {

        categoryNameTextArea.clear();
        categoryNameTextArea.sendKeys(toFill);
    }


    public final static String categoryDescriptionTextAreaCSS = "textArea.categoryInput[id=\"categoryItems0.description\"]";

    @FindBy(how = How.CSS, using = categoryDescriptionTextAreaCSS)
    private WebElement categoryDescriptionTextArea;


    public void fillCategoryDescription(String toFill) {
        categoryDescriptionTextArea.clear();
        categoryDescriptionTextArea.sendKeys(toFill);

    }

    public final static String saveCategoryButtonCSS = "div#categoryContainer div.categoryActionButtons   [type=\"submit\"]";

    @FindBy(how = How.CSS, using = saveCategoryButtonCSS)
    private WebElement saveCategoryButton;


    public void submitNewCategory() {
        saveCategoryButton.click();
    }


//    public final static String saveNewSubcategoryButtonCSS="#subcategoriesContainer  div.categoryActionButtons   [type=\"submit\"]";
    public final static String  saveNewSubcategoryButtonCSS="div#subcategoriesContainer div.categoryActionButtons button.btn.btn-success[type=\"submit\"]";
    @FindBy(how = How.CSS, using = saveNewSubcategoryButtonCSS)
    private WebElement saveNewSubcategoryButton;

    public void submitNewSubcategory(){

        saveNewSubcategoryButton.click();
    }



    public final static String allSubcategoriesNameListCSS="#subcategoriesContainer div.form-group textarea[id*=\".name\"]";

    public List<WebElement> getAllSubcategoriesNameList() {
        return allSubcategoriesNameList;
    }

    @FindBy(how = How.CSS, using = allSubcategoriesNameListCSS)
    private List<WebElement> allSubcategoriesNameList;


    public final static String allSubcategoriesDescriptionListCSS="#subcategoriesContainer div.form-group textarea[id*=\".description\"]";

    public List<WebElement> getAllSubcategoriesDescriptionList() {
        return allSubcategoriesDescriptionList;
    }

    @FindBy(how = How.CSS, using = allSubcategoriesDescriptionListCSS)
    private List<WebElement> allSubcategoriesDescriptionList;



}
