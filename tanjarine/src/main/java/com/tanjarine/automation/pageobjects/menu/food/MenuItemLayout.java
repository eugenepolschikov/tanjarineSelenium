package com.tanjarine.automation.pageobjects.menu.food;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.Random;

/**
 * Created by ypolshchykau on 04.12.2014.
 */
public class MenuItemLayout {


    public final static String addNewItemFormCSS = "form#menuItemBean";

//  new item form ,  some inputs description

    public final static String newItemFormNewItemNameCSS = "form#menuItemBean div.row div.col-md-4 div.form-group input#name";
    @FindBy(how = How.CSS, using = newItemFormNewItemNameCSS)
    private WebElement newItemFormNewItemName;

    public void fillNewItemName(String toInput) {
        newItemFormNewItemName.sendKeys(toInput);
    }

    public final static String itemTypeCSS = "#moduleType";
    @FindBy(how = How.CSS, using = itemTypeCSS)
    private WebElement itemTypeDropdown;

    public void itemTypeDropdownRolldown() {
        itemTypeDropdown.click();
    }


    public final static String itemType1x1CSS = "#moduleType>option:nth-child(2)";
    public final static String itemType2x1CSS = "#moduleType>option:nth-child(3)";
    public final static String itemType2x2CSS = "#moduleType>option:nth-child(4)";

    @FindBy(how = How.CSS, using = itemType1x1CSS)
    private WebElement itemType1x1;

    @FindBy(how = How.CSS, using = itemType2x1CSS)
    private WebElement itemType2x1;

    @FindBy(how = How.CSS, using = itemType2x2CSS)
    private WebElement itemType2x2;

    public void item1x1Click() {

        itemType1x1.click();
    }

    public void item2x1Click() {

        itemType2x1.click();
    }

    public void item2x2Click() {

        itemType2x2.click();
    }


    public final static String newemenuItemModuleDescriptionCSS = "textarea#moduleDescription";
    @FindBy(how = How.CSS, using = newemenuItemModuleDescriptionCSS)
    private WebElement newemenuItemModuleDescription;

    public void fillModuleDescription(String toInput) {
        newemenuItemModuleDescription.sendKeys(toInput);
    }


    public final static String newmenuItemDetailDescriptionCSS = "textarea#detailDescription";

    @FindBy(how = How.CSS, using = newmenuItemDetailDescriptionCSS)
    private WebElement newmenuItemDetailDescription;

    public void fillDetailDecription(String toInput) {
        newmenuItemDetailDescription.sendKeys(toInput);
    }


    public final static String imagesLayoutCSS = "form#menuItemBean div.row div.col-md-4:nth-child(2) div.form-group:nth-child(1)>a>label";
    @FindBy(how = How.CSS, using = imagesLayoutCSS)
    private WebElement imagesLayout;

    public void makeImagesSectionAppear() {
        imagesLayout.click();
    }


    public final static String ingredientsAreaCSS="a[href=\"#ingredients\"]";
    @FindBy(how = How.CSS, using = ingredientsAreaCSS)
    private WebElement ingredientsArea;

    public void setFocusToIngredientsArea(){
        ingredientsArea.click();
    }

    public final static String ingredientsInputCSS="textArea#ingredients";

    @FindBy(how = How.CSS, using = ingredientsInputCSS)
    private WebElement ingredientsInput;

    public void fillIngredients(String toFill){
        ingredientsInput.clear();
        ingredientsInput.sendKeys(toFill);
    }


//    =========== NUTRITION INFO  area =================
    public final static String nutritionAreaCSS="a[href=\"#nutrTable\"]";
    @FindBy(how = How.CSS, using = nutritionAreaCSS)
    private WebElement nutritionArea;

    public void setFocusToNutritionArea(){
        nutritionArea.click();
    }

    public final static String caloriesInputCSS="input[id=\"nutritionTable.calories\"]";

    public final static String caloriesFromFatCSS="input[id=\"nutritionTable.caloriesFromFat\" ]";


    @FindBy(how = How.CSS, using = caloriesInputCSS)
    private WebElement caloriesInput;

    public void fillInCalories(String toInput){
        caloriesInput.clear();
        caloriesInput.sendKeys(toInput);
    }


    @FindBy(how = How.CSS, using = caloriesFromFatCSS)
    private WebElement caloriesFromFat;

    public void fillInCaloriesFromFat(String toInput){
        caloriesFromFat.clear();
        caloriesFromFat.sendKeys(toInput);
    }
//    totalFat grams =[id="nutritionTable.totalFatAmount"]


//    ==================  DESCRIPTORS area ======================
    public final static String descriptorsAreaCSS="a[href=\"#descriptors\"]";
    @FindBy(how = How.CSS, using = descriptorsAreaCSS)
    private WebElement descriptorsArea;

    public void setFocusToDesctiptorsArea(){
        descriptorsArea.click();
    }


    public final static String descriptorsListCSS="div.col-md-6>[id*=\"descriptorIds\"]";
    @FindBy(how = How.CSS, using = descriptorsListCSS)
    private List<WebElement> descriptorsList;

    public void selectOneRandomDescriptor(){
        int size=descriptorsList.size();
        Random rand = new Random();
        int indexFirst=rand.nextInt(size) ;
//        int indexSecond=rand.nextInt(size) ;
        descriptorsList.get(indexFirst).click();

//        descriptorsList.get(indexSecond).click();

    }


}
