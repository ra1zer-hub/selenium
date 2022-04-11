package ru.ibs.framework.steps;

import io.cucumber.java.ru.И;
import ru.ibs.framework.managers.PagesManager;

public class LoginPageSteps {

    PagesManager pagesManager = PagesManager.getManagerPages();

    @И("^вводим логин \"(.*)\" и пароль \"(.*)\"$")
    public void fillLoginFields(String login, String password) {
        pagesManager.getLoginPage().fillLoginFields(login, password);
    }

    @И("^нажимаем на кнопку войти$")
    public void signIn() {
        pagesManager.getLoginPage().signIn();
    }
}
