package com.tanjarine.automation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Hello world!
 * !!! please note: all items withing selected page are expected to be located with CSS selectors!!!
 */
public class LoginPage {
    // email input locator
    public final static String emailInputCSS = "#email";
    // passwrod input locator
    public final static String passInputCSS = "#password";
    // login button locator
    public final static String signInButtonCSS = "button[type=\"submit\"]";

    public final static String validationCSS = ".text-danger";

    public final static String rememberMeCSS = "#rememberMe";

    public final static String validationMessageForEmptyInputs = "Invalid login or password" ;

    public WebElement getLoginpageEmailInput() {
        return loginpageEmailInput;
    }

    public WebElement getLoginpagePasswordInput() {
        return loginpagePasswordInput;
    }

    public void enterLogin(String login) {
        loginpageEmailInput.sendKeys(login);
    }

    public void enterPassword(String pass) {
        loginpagePasswordInput.sendKeys(pass);
    }

    public void loginFormSubmit() {
        loginpageSignInButton.click();
    }

    public WebElement getLoginpageSignInButton() {
        return loginpageSignInButton;
    }

    @FindBy(how = How.CSS, using = emailInputCSS)
    private WebElement loginpageEmailInput;

    @FindBy(how = How.CSS, using = passInputCSS)
    private WebElement loginpagePasswordInput;

    @FindBy(how = How.CSS, using = signInButtonCSS)
    private WebElement loginpageSignInButton;

    public WebElement getValidation() {
        return validation;
    }

    @FindBy(how = How.CSS, using = validationCSS)
    private WebElement validation;

}