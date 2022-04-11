package ru.ibs.framework.steps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import ru.ibs.framework.managers.PagesManager;

public class BusinessTripsPageSteps {

    PagesManager pagesManager = PagesManager.getManagerPages();

    @Тогда("^открылась страница 'Командировки'$")
    public void checkOpenBusinessTripsPage() {
        pagesManager.getBusinessTripsPage().checkOpenBusinessTripsPage();
    }

    @И("^нажимаем на копку \"(.*)\"$")
    public void clickCreateBusinessTrips(String buttonName) {
        pagesManager.getBusinessTripsPage().clickCreateBusinessTrips(buttonName);
    }
}
