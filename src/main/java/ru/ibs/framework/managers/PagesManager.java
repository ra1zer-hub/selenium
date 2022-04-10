package ru.ibs.framework.managers;


import ru.ibs.framework.pages.BusinessTripsPage;
import ru.ibs.framework.pages.CreateBusinessTripsPage;
import ru.ibs.framework.pages.LoginPage;
import ru.ibs.framework.pages.MainPage;

public class PagesManager {

    private static PagesManager pagesManager;
    private LoginPage loginPage;
    private MainPage mainPage;
    private BusinessTripsPage businessTripsPage;
    private CreateBusinessTripsPage createBusinessTripsPage;

    private PagesManager() {
    }

    public static PagesManager getManagerPages() {
        if (pagesManager == null) {
            pagesManager = new PagesManager();
        }
        return pagesManager;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public MainPage getMainPage() {
        if (mainPage == null) {
            mainPage = new MainPage();
        }
        return mainPage;
    }

    public BusinessTripsPage getBusinessTripsPage() {
        if (businessTripsPage == null) {
            businessTripsPage = new BusinessTripsPage();
        }
        return businessTripsPage;
    }

    public CreateBusinessTripsPage getCreateBusinessTripsPage() {
        if (createBusinessTripsPage == null) {
            createBusinessTripsPage = new CreateBusinessTripsPage();
        }
        return createBusinessTripsPage;
    }

}
