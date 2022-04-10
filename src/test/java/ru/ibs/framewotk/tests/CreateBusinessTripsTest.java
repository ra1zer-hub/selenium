package ru.ibs.framewotk.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.ibs.framework.utils.MyAllureListener;
import ru.ibs.framewotk.basetests.BaseTest;

@ExtendWith(MyAllureListener.class)
public class CreateBusinessTripsTest extends BaseTest {

    @Test
    @DisplayName("Учебный тест")
    public void startTest() {
        app.getLoginPage()
                .fillLoginFields("Irina Filippova", "testing")
                .signIn()
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
