package ru.yandex.practicum.sprint_3.courier;


import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class CourierRequest extends BaseData {

    public final String PATH = BASE_URL + "courier/";

    //@Step("Greate courier {courier}")
    public String great(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(PATH)
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .body().asString();

    }

    //@Step("Greate two identical courier {courier}")
    public String greatTwoIdentical(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(PATH)
                .then()
                .assertThat()
                .statusCode(409)
                .extract()
                .body().asString();
    }

    //@Step("Greate courier without required field {courier}")
    public String greatWithoutRequiredField(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(PATH)
                .then()
                .assertThat()
                .statusCode(400)
                .extract()
                .body().asString();
    }

    //@Step("Login as {courierLogin}")
    public int login(CourierLogin courierLogin) {
        return given()
                .spec(getBaseSpec())
                .body(courierLogin)
                .when()
                .post(PATH + "login/")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
    }

    //@Step("Login without required field {courierLogin}")
    public String loginFild(CourierLogin courierLogin) {
        return given()
                .spec(getBaseSpec())
                .body(courierLogin)
                .when()
                .post(PATH + "login/")
                .then()
                .assertThat()
                .statusCode(400)
                .extract()
                .body().asString();
    }

    //@Step("Login incorrect field {courierLogin}")
    public String loginIncorrectFild(CourierLogin courierLogin) {
        return given()
                .spec(getBaseSpec())
                .body(courierLogin)
                .when()
                .post(PATH + "login/")
                .then()
                .assertThat()
                .statusCode(404)
                .extract()
                .body().asString();
    }

    //@Step("Delete courier {courierId}")
    public boolean delete(int courierId) {
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(PATH + courierId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("ok");
    }

}