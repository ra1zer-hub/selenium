package ru.ibs.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusinessTripsPage extends BasePage {

    @FindBy(xpath = "//ul[@class='breadcrumb']")
    private WebElement title;

    @FindBy(xpath = "//*[text()='Создать командировку']")
    private WebElement createBusinessTripsButton;

    @Step("Проверяем, что открылась страница 'Командировки'")
    public BusinessTripsPage checkOpenBusinessTripsPage() {
        waitElementToBeVisible(title);
        assertEquals("Расходы/ Командировки", title.getText(),
                "Страница не открыта или была открыта не правильная страница");
        return this;
    }

    @Step("Нажимаем на копку 'Создать командировку'")
    public CreateBusinessTripsPage clickCreateBusinessTrips() {
        waitElementToBeClickable(createBusinessTripsButton).click();
        return app.getCreateBusinessTripsPage();
    }
}
