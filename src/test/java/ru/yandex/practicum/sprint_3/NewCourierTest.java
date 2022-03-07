package ru.yandex.practicum.sprint_3;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.yandex.practicum.sprint_3.courier.Courier;
import ru.yandex.practicum.sprint_3.courier.CourierRequest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewCourierTest {

    private CourierRequest courierRequest;
    private int courierId;


    @BeforeAll
    public void setUp() {
        courierRequest = new CourierRequest();
    }

    @AfterAll
    public void tearDown() {
        courierRequest.delete(courierId);
    }

    @Test //проверка возможности создать курьера и невозможности создать двух одинаковых курьеров
    public void courierCanBeCreatedWithValidLoginAndNotGreateIsTwoIdenticalCourierGreated() {
        Courier courier = Courier.greatCourier();
        String isCourierGreated = courierRequest.great(courier);

        String isTwoIdenticalCourierGreated = courierRequest.greatTwoIdentical(courier);

        assertThat(isCourierGreated, containsString("\"ok\":true"));
        assertThat(isTwoIdenticalCourierGreated, containsString("Этот логин уже используется. Попробуйте другой."));

    }

    @Test //проверка аозможности создать курьера без обязательного поля пароль
    public void courierNotBeGreatedWithoutRequiredFieldPassword() {
        Courier courier = Courier.greatCourier();
        Courier courier2 = new Courier(courier.login, "", courier.firstName);
        String greatWithoutRequiredField = courierRequest.greatWithoutRequiredField(courier2);
        System.out.println(greatWithoutRequiredField);
        assertThat(greatWithoutRequiredField, containsString("Недостаточно данных для создания учетной записи"));
    }

    @Test //проверка аозможности создать курьера без обязательного поля логин
    public void courierNotBeGreatedWithoutRequiredFieldLogin() {
        Courier courier = Courier.greatCourier();
        Courier courier2 = new Courier("", courier.password, courier.firstName);
        String greatWithoutRequiredField = courierRequest.greatWithoutRequiredField(courier2);
        System.out.println(greatWithoutRequiredField);
        assertThat(greatWithoutRequiredField, containsString("Недостаточно данных для создания учетной записи"));
    }

    @Test //проверка возможности создать курьера без обязательного поля first Name
    public void courierNotBeGreatedWithoutRequiredFieldFirstName() {
        Courier courier = Courier.greatCourier();
        Courier courier2 = new Courier(courier.login, courier.password, "");
        String greatWithoutRequiredField = courierRequest.greatWithoutRequiredField(courier2);
        System.out.println(greatWithoutRequiredField);
        assertThat(greatWithoutRequiredField, containsString("Недостаточно данных для создания учетной записи"));

    }


}
