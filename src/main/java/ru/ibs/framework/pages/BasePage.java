package ru.ibs.framework.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.PagesManager;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class BasePage {

    protected final DriverManager driverManager = DriverManager.getDriverManager();
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();
    protected PagesManager app = PagesManager.getManagerPages();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(15), Duration.ofSeconds(1));
    protected Select select;

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement scrollToElementJs(WebElement element) {
        checkLoadedPage();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    protected WebElement waitElementToBeClickable(WebElement element) {
        checkLoadedPage();
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitElementToBeVisible(WebElement element) {
        checkLoadedPage();
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected boolean waitElementIsDisplayed(WebElement element) {
        checkLoadedPage();
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void fillInputField(WebElement element, String value) {
        checkLoadedPage();
        scrollToElementJs(element);
        waitElementToBeClickable(element).click();
        element.clear();
        element.sendKeys(value);
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        assertTrue(checkFlag, "Поле было заполнено некорректно");
    }

    protected void fillSelect(WebElement element, String value) {
        checkLoadedPage();
        select = new Select(element);
        select.selectByVisibleText(value);
        String selectedOption = select.getFirstSelectedOption().getAttribute("text");
        assertEquals(value, selectedOption, "В выпадающем списке не было найдено значение " + value);
    }

    protected void selectCheckbox(WebElement checkbox, String checkboxName) {
        checkLoadedPage();
        WebElement checkboxButton = checkbox.findElement(By.xpath("./input"));
        if (!checkbox.isSelected()) {
            waitElementToBeClickable(checkboxButton).click();
        }
        assertTrue(checkboxButton.isSelected(), "Чекбокс '" + checkboxName + "' не выбран");
    }

    protected void fillSearchField(WebElement field, String value) {
        checkLoadedPage();
        waitElementToBeClickable(field).click();
        field.sendKeys(value);
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(field, "value", value));
        assertTrue(checkFlag, "Поле поиска было заполнено некорректно");
        sleep(3);
        field.sendKeys(Keys.ENTER);
    }

    protected void fillDateField(WebElement element, String value) {
        checkLoadedPage();
        scrollToElementJs(element);
        waitElementToBeClickable(element).click();
        element.clear();
        element.sendKeys(value);
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        assertTrue(checkFlag, "Поле было заполнено некорректно");
        element.sendKeys(Keys.ESCAPE);
    }

    private void checkLoadedPage() {
        driverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        String loading2 = "//div[@class='loader-mask']";
        int i = 0;
        while (i < 180) {
            try {
                if (driverManager.getDriver().findElement(By.xpath(loading2)).isDisplayed()) {
                    sleep(1);
                } else return;
            } catch (NoSuchElementException ex) {
                return;
            } finally {
                driverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
            }
            i++;
        }
        fail("Истекло время ожидания загрузки данных");
    }

    private void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
