package ru.ibs.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name = "_username")
    private WebElement loginField;

    @FindBy(name = "_password")
    private WebElement passwordField;

    @FindBy(id = "_submit")
    private WebElement signInButton;

    public LoginPage fillLoginFields(String login, String password) {
        fillInputField(loginField, login);
        fillInputField(passwordField, password);
        return this;
    }

    public MainPage signIn() {
        waitElementToBeClickable(signInButton).click();
        return app.getMainPage();
    }
}
