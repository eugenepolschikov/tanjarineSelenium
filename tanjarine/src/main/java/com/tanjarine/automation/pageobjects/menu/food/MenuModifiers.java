package com.tanjarine.automation.pageobjects.menu.food;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by ypolshchykau on 28.11.2014.
 */
public class MenuModifiers extends MenuCommonPage {


    public final static String modifierButtonCSS = "form#menuItemBean div.row div.col-md-4 div#modifiers.form-group div.row div.col-md-12 button.btn";
    @FindBy(how = How.CSS, using = modifierButtonCSS)
    private WebElement modifierButton;


    public void addModifier() {
        modifierButton.click();
    }


}
