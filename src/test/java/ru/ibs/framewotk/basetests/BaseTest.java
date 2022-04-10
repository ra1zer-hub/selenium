package ru.ibs.framewotk.basetests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.PagesManager;
import ru.ibs.framework.managers.PropManager;

import static ru.ibs.framework.managers.InitManager.initFramework;
import static ru.ibs.framework.managers.InitManager.quitFramework;
import static ru.ibs.framework.utils.PropConst.APP_URL;

public class BaseTest {

    protected PagesManager app = PagesManager.getManagerPages();
    private final DriverManager driverManager = DriverManager.getDriverManager();


    @BeforeAll
    static void beforeAll() {
        initFramework();
    }

    @BeforeEach
    void beforeEach() {
        driverManager.getDriver().get(PropManager.getPropManager().getProperty(APP_URL));
    }

    @AfterAll
    static void afterAll() {
        quitFramework();
    }
}
