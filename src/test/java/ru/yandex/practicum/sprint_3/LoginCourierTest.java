package ru.yandex.practicum.sprint_3;


import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ru.yandex.practicum.sprint_3.courier.Courier;
import ru.yandex.practicum.sprint_3.courier.CourierLogin;
import ru.yandex.practicum.sprint_3.courier.CourierRequest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginCourierTest {

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

    @Test //проверка возможности авторизации курьера и вывода в случае успеха id курьера
    public void courierCanGreatedAndBeLogIn() {
        Courier courier = Courier.greatCourier();
        courierRequest = new CourierRequest();
        courierRequest.great(courier);
        courierId = courierRequest.login(CourierLogin.from(courier));
        System.out.println(courierId);
        assertThat("CourierId is incorrect", courierId, is(not(0)));

    }

    @Test //проверка вывода ошибки в случае указания не всех обязательных полей
    public void courierIdWithoutRequiredField() {
        Courier courier = Courier.greatCourier();
        courierRequest = new CourierRequest();
        courierRequest.great(courier);
        CourierLogin courierLogin = new CourierLogin(courier.login, "");
        String courierIdwithoutPassword = courierRequest.loginFild(courierLogin);
        assertThat(courierIdwithoutPassword, containsString("Недостаточно данных для входа"));

    }

    @Test //проверка вывода ошибки в случае указания неверных регистрационных данных
    public void courierIdIncorrectField() {
        Courier courier = Courier.greatCourier();
        courierRequest = new CourierRequest();
        courierRequest.great(courier);
        CourierLogin courierLogin = new CourierLogin(courier.login + "111", courier.password);
        String courierIdIncorrectLogin = courierRequest.loginIncorrectFild(courierLogin);
        assertThat(courierIdIncorrectLogin, containsString("Учетная запись не найдена"));

    }
}
