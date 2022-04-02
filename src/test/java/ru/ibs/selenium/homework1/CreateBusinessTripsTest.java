package ru.ibs.selenium.homework1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateBusinessTripsTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String baseUrl = "http://training.appline.ru/user/login";
        driver.get(baseUrl);
    }

    @Test
    public void scenario() {

//        Ввод логина, пароля, вход в систему
        fillInputField(driver.findElement(By.name("_username")), "Irina Filippova");
        fillInputField(driver.findElement(By.name("_password")), "testing");
        WebElement signInButton = driver.findElement(By.id("_submit"));
        elementToBeClickable(signInButton).click();

//        Проверка входа в систему
        String pageTitleXPath = "//h1[@class='oro-subtitle']";
        elementToBeVisible(By.xpath(pageTitleXPath));
        WebElement pageTitle = driver.findElement(By.xpath(pageTitleXPath));
        assertEquals("Панель быстрого запуска", pageTitle.getText(),
                "Заголовок отсутствует/не соответствует требуемому");

//        Выбрать пункт меню - "Расходы"
        String expensesButtonXPath = "//ul[contains(@class,'main-menu')]/li/a/span[contains(text(), 'Расходы')]";
        WebElement expensesButton = driver.findElement(By.xpath(expensesButtonXPath));
        elementToBeClickable(expensesButton).click();

//        Выбрать пункт подменю - "Командировки"
        String businessTripsButtonXPath = "//span[text()='Командировки']";
        WebElement businessTripsButton = driver.findElement(By.xpath(businessTripsButtonXPath));
        elementToBeClickable(businessTripsButton).click();

//        Проверка открытия страницы "Командировки"
        String pageTitleBusinessTripsXPath = "//ul[@class='breadcrumb']";
        elementToBeVisible(By.xpath(pageTitleBusinessTripsXPath));
        WebElement pageTitleBusinessTrips = driver.findElement(By.xpath(pageTitleBusinessTripsXPath));
        assertEquals("Расходы/ Командировки",
                pageTitleBusinessTrips.getText(), "Открыта не правильная страница");

//        Нажать на "Создать командировку"
        String createBusinessTripsButtonXPath = "//*[text()='Создать командировку']";
        WebElement createBusinessTripsButton = driver.findElement(By.xpath(createBusinessTripsButtonXPath));
        elementToBeClickable(createBusinessTripsButton).click();

//        Проверка открытия страницы "Создать командировку"
        String pageTitleCreateBusinessTripsXPath = "//h1[@class='user-name']";
        elementToBeVisible(By.xpath(pageTitleCreateBusinessTripsXPath));
        WebElement pageTitleCreateBusinessTrips = driver.findElement(By.xpath(pageTitleCreateBusinessTripsXPath));
        assertEquals("Создать командировку",
                pageTitleCreateBusinessTrips.getText(), "Открыта не правильная страница");

//        Подразделение - выбрать Отдел внутренней разработки
        String fieldSubdivisionXPath = "//select[@name='crm_business_trip[businessUnit]']";
        WebElement fieldSubdivision = driver.findElement(By.xpath(fieldSubdivisionXPath));
        fillSelect(fieldSubdivision, "Отдел внутренней разработки");

//        Принимающая организация - нажать "Открыть список" и в поле "Укажите организацию" выбрать любое значение
        WebElement openListButton = driver.findElement(By.id("company-selector-show"));
        elementToBeClickable(openListButton).click();

        String organizationXPath = "//div[contains(@class,'select2-container select2')]";
        WebElement organizationButton = driver.findElement(By.xpath(organizationXPath));
        elementToBeClickable(organizationButton).click();

        String searchOrganizationXPath = "//div[@class='select2-search']/input";
        WebElement searchOrganizationInput = driver.findElement(By.xpath(searchOrganizationXPath));
        fillSearchField(searchOrganizationInput, "Happy Company");

//        В задачах поставить чекбокс на "Заказ билетов"
        String orderingTicketsXPath = "//label[text()='%s']/preceding-sibling::input";
        String checkboxName = "Заказ билетов";
        selectCheckbox(driver.findElement(By.xpath(String.format(orderingTicketsXPath, checkboxName))), checkboxName);

//        Указать города выбытия и прибытия
        String departureCityXPath = "//input[contains(@name, '[departureCity]')]";
        String arrivalCityXPath = "//input[contains(@name, '[arrivalCity]')]";
        fillInputField(driver.findElement(By.xpath(departureCityXPath)), "Россия, Москва");
        fillInputField(driver.findElement(By.xpath(arrivalCityXPath)), "Россия, МСочи");

//        Указать даты выезда и возвращения
        String plannedDepartureDateXPath = "//*[text()='Планируемая дата выезда']/../following-sibling::div/input";
        String plannedReturnDateXPath = "//*[text()='Планируемая дата возвращения']/../following-sibling::div/input";
        fillDateField(driver.findElement(By.xpath(plannedDepartureDateXPath)), "09.04.2022");
        fillDateField(driver.findElement(By.xpath(plannedReturnDateXPath)), "23.04.2022");

//        Нажать "Сохранить и закрыть"
        String saveAndCloseXpath = "//button[contains(text(),'Сохранить и закрыть')]";
        WebElement saveAndCloseButton = driver.findElement(By.xpath(saveAndCloseXpath));
        elementToBeClickable(saveAndCloseButton).click();

//        Проверить, что на странице появилось сообщение: "Список командируемых сотрудников не может быть пустым"
        String errorXpath = "//*[text()='%s']";
        String errorMessage = "Список командируемых сотрудников не может быть пустым";
        assertTrue(elementIsDisplayed(By.xpath(String.format(errorXpath, errorMessage))),
                "Сообщение: \"" + errorMessage + "\" не найдено на странице");
    }

    @AfterEach
    public void after(){
        driver.quit();
    }

    private void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private WebElement elementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void elementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private boolean elementIsDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void fillInputField(WebElement element, String value) {
        scrollToElementJs(element);
        elementToBeClickable(element).click();
        element.clear();
        element.sendKeys(value);
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        assertTrue(checkFlag, "Поле было заполнено некорректно");
    }

    private void fillSelect(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByVisibleText(value);
        String selectedOption = select.getFirstSelectedOption().getAttribute("text");
        assertEquals(value, selectedOption, "В выпадающем списке было выбрано некорректное значение");
    }

    private void selectCheckbox(WebElement checkbox, String checkboxName) {
        scrollToElementJs(checkbox);
        elementToBeClickable(checkbox);
        if (!checkbox.isSelected()) {
            elementToBeClickable(checkbox).click();
        }
        assertTrue(checkbox.isSelected(), "Чекбокс '" + checkboxName + "' не выбран");
    }

    private void fillSearchField(WebElement field, String value) {
        elementToBeClickable(field).click();
        field.sendKeys(value);
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(field, "value", value));
        assertTrue(checkFlag, "Поле поиска было заполнено некорректно");
        sleep(3);
        field.sendKeys(Keys.ENTER);
    }

    private void fillDateField(WebElement element, String value) {
        scrollToElementJs(element);
        elementToBeClickable(element).click();
        element.clear();
        element.sendKeys(value);
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        assertTrue(checkFlag, "Поле было заполнено некорректно");
        element.sendKeys(Keys.ESCAPE);
    }

    private void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
