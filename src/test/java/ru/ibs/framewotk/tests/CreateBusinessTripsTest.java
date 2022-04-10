package ru.ibs.framewotk.tests;

import org.junit.jupiter.api.Test;
import ru.ibs.framewotk.basetests.BaseTest;

public class CreateBusinessTripsTest extends BaseTest {

    @Test
    public void startTest() {

        app.getLoginPage()
                .fillLoginFields("Irina Filippova", "testing")
                .signIn()
                .checkOpenMainPage()
                .checkOpenMainPage()
                .selectMenu("Расходы")
                .selectSubMenu("Командировки")
                .checkOpenBusinessTripsPage()
                .clickCreateBusinessTrips()
                .checkOpenCreateBusinessTripsPage()
                .selectDepartment("Отдел внутренней разработки")
                .openListAndSelectOrganization("Happy Company")
                .selectCheckbox("Заказ билетов")
                .fillFieldByName("Город выбытия", "Россия, Москва")
                .fillFieldByName("Город прибытия", "Россия, Сочи")
                .fillFieldByName("Планируемая дата выезда", "17.04.2022")
                .fillFieldByName("Планируемая дата возвращения", "24.04.2022")
                .clickSaveAndClose()
                .checkErrorInPage("Список командируемых сотрудников не может быть пустым");
    }
}
