package ru.yandex.practicum.sprint_3.order;

import static io.restassured.RestAssured.given;

public class OrderGreate  extends ScooterOrder {
    public final String PATH = BASE_URL + "orders/";

    public String greatOrder(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(PATH)
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .body().asString();

    }
}