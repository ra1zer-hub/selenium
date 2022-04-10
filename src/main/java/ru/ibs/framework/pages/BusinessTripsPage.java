package ru.ibs.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusinessTripsPage extends BasePage {

    @FindBy(xpath = "//ul[@class='breadcrumb']")
    private WebElement title;

    @FindBy(xpath = "//*[text()='Создать командировку']")
    private WebElement createBusinessTripsButton;

    public BusinessTripsPage checkOpenBusinessTripsPage() {
        waitElementToBeVisible(title);
        assertEquals("Расходы/ Командировки", title.getText(),
                "Страница не открыта или была открыта не правильная страница");
        return this;
    }

    public CreateBusinessTripsPage clickCreateBusinessTrips() {
        waitElementToBeClickable(createBusinessTripsButton).click();
        return app.getCreateBusinessTripsPage();
    }
}
