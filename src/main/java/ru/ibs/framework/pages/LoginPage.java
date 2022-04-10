package ru.ibs.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name = "_username")
    private WebElement loginField;

    @FindBy(name = "_password")
    private WebElement passwordField;

    @FindBy(id = "_submit")
    private WebElement signInButton;

    @Step("Вводим логин и пароль")
    public LoginPage fillLoginFields(String login, String password) {
        fillInputField(loginField, login);
        fillInputField(passwordField, password);
        return this;
    }

    @Step("Нажимаем на копку войти")
    public MainPage signIn() {
        waitElementToBeClickable(signInButton).click();
        return app.getMainPage();
    }
}
