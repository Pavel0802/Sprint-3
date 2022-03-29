package ru.yandex.practicum.sprint_3;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
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
        courier = Courier.createCourier();
    }

    @After
    public void tearDown() {
        JsonElement id = courierRequest.login(CourierLogin.from(courier)).thenReturn()
                .body().as(JsonObject.class).get("id");
        if (id != null) {
            CourierRequest.delete(id.getAsString()).thenReturn();
        }
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Тест проверяет возможность создать курьера. Запрос возвращает код ответа \"201\" с телом ответа \"ok\":true")
    public void courierCanBeCreatedWithValidLogin() {
        CourierRequest.create(courier).then().assertThat()
                .body("ok", equalTo(true))
                .statusCode(201);
    }

    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    @Description("Тест проверяет появление ошибки в случае создания двух одинаковых курьеров")
    public void courierCanBeNotCreateIsTwoIdenticalCourier() {
        CourierRequest.create(courier).then().statusCode(201);
        CourierRequest.create(courier).then().assertThat()
                .body("message", equalTo("Этот логин уже используется"))
                .statusCode(409);
    }

    @Test
    @DisplayName("Создание курьера без указания поля Пароль")
    @Description("Тест проверяет появление ошибки в случае отсутствия обязательного поля Password")
    public void courierNotBeCreateWithoutRequiredFieldPassword() {

        Courier courier2 = new Courier(courier.login, "", courier.firstName);
        CourierRequest.create(courier2).then().assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .statusCode(400);
    }

    @Test
    @DisplayName("Создание курьера без указания поля Логин")
    @Description("Тест проверяет появление ошибки в случае отсутствия обязательного поля Login")
    public void courierNotBeCreateWithoutRequiredFieldLogin() {
        Courier courier2 = new Courier("", courier.password, courier.firstName);
        CourierRequest.create(courier2).then().assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .statusCode(400);
    }

    @Test
    @DisplayName("Создание курьера без указания поля Имя")
    @Description("Тест проверяет появление ошибки в случае отсутствия обязательного поля FirstName")
    public void courierNotBeCreateWithoutRequiredFieldFirstName() {
        Courier courier2 = new Courier(courier.login, courier.password, "");
        CourierRequest.create(courier2).then().assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }
}
