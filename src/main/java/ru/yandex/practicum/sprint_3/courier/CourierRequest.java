package ru.yandex.practicum.sprint_3.courier;


import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierRequest extends BaseData {

    public static final String PATH = BASE_URL + "courier/";


    public static Response great(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .post(PATH);
    }


    public static Response login(CourierLogin courierLogin) {
        return given()
                .spec(getBaseSpec())
                .body(courierLogin)
                .post(PATH + "login/");
    }

    public static Response delete(String courierId) {
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(PATH + courierId);
    }

}