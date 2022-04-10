package ru.ibs.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MainPage extends BasePage {

    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement title;

    @FindBy(xpath = "//ul[contains(@class, 'main-menu')]/li")
    private List<WebElement> listMenu;

    @FindBy(xpath = "//ul[contains(@class, 'menu_level_1')]/li[@data-route or @class='dropdown']")
    private List<WebElement> listSubMenu;

    @Step("Проверяем, что открылась главная страница")
    public MainPage checkOpenMainPage() {
        waitElementToBeVisible(title);
        assertEquals("Панель быстрого запуска", title.getText(),
                "Заголовок отсутствует/не соответствует требуемому");
        return this;
    }

    @Step("Выбираем меню '{menuName}'")
    public MainPage selectMenu(String menuName) {
        for (WebElement menuItem : listMenu) {
            if (menuItem.getText().trim().equalsIgnoreCase(menuName)) {
                waitElementToBeClickable(menuItem).click();
                return this;
            }
        }
        fail("Меню '" + menuName + "' не найдено");
        return this;
    }

    @Step("Выбираем подменю '{menuName}'")
    public BusinessTripsPage selectSubMenu(String menuName) {
        for (WebElement menuItem : listSubMenu) {
            if (menuItem.getText().trim().equalsIgnoreCase(menuName)) {
                waitElementToBeClickable(menuItem).click();
                return app.getBusinessTripsPage();
            }
        }
        fail("Меню '" + menuName + "' не найдено");
        return app.getBusinessTripsPage();
    }
}
