package ru.ibs.framework.steps;

import io.cucumber.java.bg.И;
import io.cucumber.java.ru.Тогда;
import ru.ibs.framework.managers.PagesManager;

public class MainPageSteps {

    PagesManager pagesManager = PagesManager.getManagerPages();

    @Тогда("^открылась 'Главная страница'$")
    public void checkOpenMainPage() {
        pagesManager.getMainPage().checkOpenMainPage();
    }

    @И("^выбираем меню \"(.*)\"$")
    public void selectMenu(String menuName) {
        pagesManager.getMainPage().selectMenu(menuName);
    }

    @И("^выбираем подменю \"(.*)\"$")
    public void selectSubMenu(String menuName) {
        pagesManager.getMainPage().selectSubMenu(menuName);
    }
}
