package ru.ibs.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateBusinessTripsPage extends BasePage {

    @FindBy(xpath = "//h1[@class='user-name']")
    private WebElement title;

    @FindBy(xpath = "//select[@name='crm_business_trip[businessUnit]']")
    private WebElement fieldDepartment;

    @FindBy(id = "company-selector-show")
    private WebElement openListButton;

    @FindBy(xpath = "//div[contains(@class,'select2-container select2')]")
    private WebElement organizationButton;

    @FindBy(xpath = "//div[@class='select2-search']/input")
    private WebElement searchOrganizationInput;

    @FindBy(xpath = "//div[@class='oro-clearfix']")
    private List<WebElement> listCheckbox;

    @FindBy(xpath = "//input[contains(@name, '[departureCity]')]")
    private WebElement departureCity;

    @FindBy(xpath = "//input[contains(@name, '[arrivalCity]')]")
    private WebElement arrivalCity;

    @FindBy(xpath = "//*[text()='Планируемая дата выезда']/../following-sibling::div/input")
    private WebElement plannedDepartureDate;

    @FindBy(xpath = "//*[text()='Планируемая дата возвращения']/../following-sibling::div/input")
    private WebElement plannedReturnDate;

    @FindBy(xpath = "//button[contains(text(),'Сохранить и закрыть')]")
    private WebElement saveAndCloseButton;

    @FindBy(xpath = "//span[@class='validation-failed']")
    private List<WebElement> listErrors;

    @Step("Проверяем, что открылась страница создания командировки")
    public CreateBusinessTripsPage checkOpenCreateBusinessTripsPage() {
        waitElementToBeVisible(title);
        assertEquals("Создать командировку", title.getText(),
                "Страница не открыта или была открыта не правильная страница");
        return this;
    }

    @Step("У подразделения выбираем '{value}'")
    public CreateBusinessTripsPage selectDepartment(String value) {
        fillSelect(fieldDepartment, value);
        return this;
    }

    @Step("Открываем список организаций и выбираем '{value}'")
    public CreateBusinessTripsPage openListAndSelectOrganization(String value) {
        waitElementToBeClickable(openListButton).click();
        waitElementToBeClickable(organizationButton).click();
        fillSearchField(searchOrganizationInput, value);
        return this;
    }

    @Step("Выбираем чекбокс '{value}'")
    public CreateBusinessTripsPage selectCheckbox(String value) {
        for (WebElement checkbox : listCheckbox) {
            if (checkbox.getText().trim().equalsIgnoreCase(value)) {
                selectCheckbox(checkbox, value);
                return this;
            }
        }
        fail("Чекбокс '" + value + "' не найден");
        return this;
    }

    @Step("Поле {nameField} заполняем значением '{value}'")
    public CreateBusinessTripsPage fillFieldByName(String nameField, String value) {
        switch (nameField) {
            case "Город выбытия":
                fillInputField(departureCity, value);
                break;
            case "Город прибытия":
                fillInputField(arrivalCity, value);
                break;
            case "Планируемая дата выезда":
                fillDateField(plannedDepartureDate, value);
                break;
            case "Планируемая дата возвращения":
                fillDateField(plannedReturnDate, value);
                break;
            default:
                fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Создать командировку'");
        }
        return this;
    }

    @Step("Нажимаем на копку 'Сохранить и закрыть'")
    public CreateBusinessTripsPage clickSaveAndClose() {
        waitElementToBeClickable(saveAndCloseButton).click();
        return this;
    }

    @Step("Проверяем на странице наличие ошибки с текстом '{errorText}'")
    public CreateBusinessTripsPage checkErrorInPage(String errorText) {
        for (WebElement error : listErrors) {
            if (error.getText().trim().equalsIgnoreCase(errorText)) {
                scrollToElementJs(error);
                assertTrue(waitElementIsDisplayed(error),
                        "Сообщение: \"" + errorText + "\" не показывается на странице");
                return this;
            }
        }
        fail("Сообщение: \"" + errorText + "\" не найдено на странице");
        return this;
    }
}
