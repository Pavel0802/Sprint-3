package ru.yandex.practicum.sprint_3;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.sprint_3.courier.Courier;
import ru.yandex.practicum.sprint_3.courier.CourierLogin;
import ru.yandex.practicum.sprint_3.courier.CourierRequest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class LoginCourierTest {

    private CourierRequest courierRequest;
    Courier courier;

    @Before
    public void setUp() {
        courier = Courier.greatCourier();
        courierRequest.great(courier);
    }

    @After
    public void tearDown() {
        JsonElement id = courierRequest.login(CourierLogin.from(courier)).thenReturn()
                .body().as(JsonObject.class).get("id");
        if (id != null) {
            CourierRequest.delete(id.getAsString()).thenReturn();
        }
    }

    @Test //проверка возможности авторизации курьера и вывода в случае успеха id курьера
    public void courierCanGreatedAndBeLogIn() {
        courierRequest.login(CourierLogin.from(courier)).then().assertThat()
                .body("id", notNullValue())
                .statusCode(200);
    }

    @Test //проверка вывода ошибки в случае указания не всех обязательных полей, без
    public void courierIdWithoutRequiredField() {
        CourierLogin courierLogin = new CourierLogin(courier.login, "");
        courierRequest.login(courierLogin).then().assertThat()
                .body("message", equalTo("Недостаточно данных для входа"))
                .statusCode(400);
    }

    @Test
    //проверка вывода ошибки в случае указания неверных регистрационных данных, в частности несуществующего пользователя
    public void courierIdIncorrectField() {
        CourierLogin courierLogin = new CourierLogin(courier.login + "111", courier.password);
        courierRequest.login(courierLogin).then().assertThat()
                .body("message", equalTo("Учетная запись не найдена"))
                .statusCode(404);
    }
}
