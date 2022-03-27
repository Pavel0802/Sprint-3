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


public class NewCourierTest {

    private CourierRequest courierRequest;
    private Courier courier;
    private CourierLogin courierLogin;

    @Before
    public void setUp() {
        courier = Courier.greatCourier();
    }

    @After
    public void tearDown() {
        JsonElement id = courierRequest.login(CourierLogin.from(courier)).thenReturn()
                .body().as(JsonObject.class).get("id");
        if (id != null) {
            CourierRequest.delete(id.getAsString()).thenReturn();
        }
    }


    @Test //проверка возможности создать курьера, запрос возвращает код ответа "201" с телом ответа "ok":true
    public void courierCanBeCreatedWithValidLogin() {
        CourierRequest.great(courier).then().assertThat()
                .body("ok", equalTo(true))
                .statusCode(201);
    }

    @Test//проверка невозможности создать двух одинаковых курьеров
    public void courierCanBeNotGreatIsTwoIdenticalCourier() {
        CourierRequest.great(courier).then().statusCode(201);
        CourierRequest.great(courier).then().assertThat()
                .body("message", equalTo("Этот логин уже используется"))
                .statusCode(409);
    }

    @Test //проверка возможности создать курьера без обязательного поля пароль
    public void courierNotBeGreatWithoutRequiredFieldPassword() {

        Courier courier2 = new Courier(courier.login, "", courier.firstName);
        CourierRequest.great(courier2).then().assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .statusCode(400);
    }

    @Test //проверка возможности создать курьера без обязательного поля логин
    public void courierNotBeGreatWithoutRequiredFieldLogin() {
        Courier courier2 = new Courier("", courier.password, courier.firstName);
        CourierRequest.great(courier2).then().assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .statusCode(400);
    }


    @Test //проверка возможности создать курьера без обязательного поля first Name
    public void courierNotBeGreatWithoutRequiredFieldFirstName() {
        Courier courier2 = new Courier(courier.login, courier.password, "");
        CourierRequest.great(courier2).then().assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }
}
