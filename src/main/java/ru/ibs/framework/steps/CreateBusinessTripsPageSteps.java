package ru.ibs.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import ru.ibs.framework.managers.PagesManager;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;

public class CreateBusinessTripsPageSteps {

    PagesManager pagesManager = PagesManager.getManagerPages();

    @Тогда("^открылась страница 'Создание командировки'$")
    public void checkOpenCreateBusinessTripsPage() {
        pagesManager.getCreateBusinessTripsPage().checkOpenCreateBusinessTripsPage();
    }

    @И("^у поля \"(.*)\" выбираем значение \"(.*)\"$")
    public void selectDepartment(String fieldName, String value) {
        pagesManager.getCreateBusinessTripsPage().selectDepartment(fieldName, value);
    }

    @И("^открыть список организаций и выбрать \"(.*)\"$")
    public void openListAndSelectOrganization(String value) {
        pagesManager.getCreateBusinessTripsPage().openListAndSelectOrganization(value);
    }

    @И("^выбрать чекбокс \"(.*)\"$")
    public void selectCheckbox(String value) {
        pagesManager.getCreateBusinessTripsPage().selectCheckbox(value);
    }

    @И("^заполняем поля:$")
    public void fillFieldByName(DataTable dataTable) {
        Map<String,String> fields = dataTable.asMap(String.class, String.class);
        fields.forEach((nameField, value) -> pagesManager.getCreateBusinessTripsPage().fillFieldByName(nameField,value));

    }

    @И("^нажимаем на копку 'Сохранить и закрыть'$")
    public void clickSaveAndClose() {
        pagesManager.getCreateBusinessTripsPage().clickSaveAndClose();
    }

    @И("^проверяем на странице наличие ошибки с текстом \"(.*)\"$")
    public void checkErrorInPage(String errorText) {
        pagesManager.getCreateBusinessTripsPage().checkErrorInPage(errorText);
    }
}
