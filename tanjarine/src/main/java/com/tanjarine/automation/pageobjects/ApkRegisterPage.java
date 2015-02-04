package com.tanjarine.automation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by ypolshchykau on 08.07.2014.
 */
public class ApkRegisterPage {

    public final static String apkInputNameCSS = "form#apkBean  #name";

    @FindBy(how = How.CSS, using = apkInputNameCSS)
    private WebElement apkInputName;

    public void rollDownApkList() {
        apkInputName.click();
    }


    public final static String versionInputNameCSS = "#version";

    @FindBy(how = How.CSS, using = versionInputNameCSS)
    private WebElement versionInputName;

    public void rollDownVersionList() {
        versionInputName.click();
    }

    public final static String firstApkInDropDownCSS = "#ui-id-1.ui-autocomplete  li:first-child>a";
    @FindBy(how = How.CSS, using = firstApkInDropDownCSS)
    private WebElement firstApkInDropDown;

    public final static String apksDropdownCSS = "#ui-id-1.ui-autocomplete  li>a";

    public List<WebElement> getApksList() {
        return apksDropdown;
    }

    @FindBy(how = How.CSS, using = apksDropdownCSS)
    private List<WebElement> apksDropdown;

    public final static String  firstVersionInDropdownCSS = "ul#ui-id-2.ui-autocomplete  li:first-child>a";
    @FindBy(how = How.CSS, using = firstVersionInDropdownCSS)
    private WebElement firstVersionInDropdown;

    public final static String versionsDropdownCSS = "ul#ui-id-2.ui-autocomplete  li>a";

    public List<WebElement> getVersionsList() {
        return versionsDropdown;
    }

    @FindBy(how = How.CSS, using = versionsDropdownCSS)
    private List<WebElement> versionsDropdown;

    public final static String registerNewApkFormCSS = "html body div.container-fluid div.row div.col-md-10 div.row div.col-md-12 div.panel div.panel-body";
    @FindBy(how = How.CSS, using = registerNewApkFormCSS)
    private WebElement registerNewApkForm;

    public void newApksFormClick() {
        registerNewApkForm.click();
    }

    public final static String registerButtonCSS = "html body div.container-fluid div.row div.col-md-10 div.row div.col-md-12 div.panel div.panel-body div.row form#apkBean div.col-md-12 div.pull-right button.btn";
    @FindBy(how = How.CSS, using = registerButtonCSS)
    private WebElement registerButton;

    public void registerActionPerform() {
        registerButton.click();
    }
}
