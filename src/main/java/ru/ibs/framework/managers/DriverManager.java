package ru.ibs.framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.fail;
import static ru.ibs.framework.utils.PropConst.*;

public class DriverManager {

    private WebDriver driver;
    private static DriverManager INSTANCE = null;
    private final PropManager prop = PropManager.getPropManager();

    private DriverManager() {
    }

    public static DriverManager getDriverManager() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private void initDriver() {
        if (prop.getProperty(TYPE_BROWSER).equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", prop.getProperty(PATH_GEKO_DRIVER));
            driver = new FirefoxDriver();
        } else if (prop.getProperty(TYPE_BROWSER).equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", prop.getProperty(PATH_CHROME_DRIVER));
            driver = new ChromeDriver();
        } else {
            fail("Типа браузера '" + prop.getProperty(TYPE_BROWSER) + "' не существует во фреймворке");
        }
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
