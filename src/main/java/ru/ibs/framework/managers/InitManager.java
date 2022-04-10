package ru.ibs.framework.managers;

import java.time.Duration;

import static ru.ibs.framework.utils.PropConst.*;

public class InitManager {

    public static final PropManager prop = PropManager.getPropManager();
    private static final DriverManager driverManager = DriverManager.getDriverManager();


    public static void initFramework() {
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty(IMPLICITLY_WAIT))));
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(prop.getProperty(PAGE_LOAD_TIMEOUT))));
        driverManager.getDriver().get(prop.getProperty(APP_URL));
    }

    public static void quitFramework() {
        driverManager.quitDriver();
    }
}
