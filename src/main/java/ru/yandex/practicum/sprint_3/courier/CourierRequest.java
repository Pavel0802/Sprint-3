package ru.yandex.practicum.sprint_3.courier;


import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierRequest extends BaseData {

    public static final String COURIER_PATH = BASE_URL + "courier/";

    @Step("Создание курьера {courier}")
    public static Response create(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .post(COURIER_PATH);
    }

    @Step("Регистрация курьера {courierLogin}")
    public static Response login(CourierLogin courierLogin) {
        return given()
                .spec(getBaseSpec())
                .body(courierLogin)
                .post(COURIER_PATH + "login/");
    }

    @Step("Удаление курьера {courierId}")
    public static Response delete(String courierId) {
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_PATH + courierId);
    }

}