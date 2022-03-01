package ru.yandex.practicum.sprint_3.order;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderRequest extends BaseData {
    /*public final String PATH = BASE_URL + "orders/";

    public  String greatOrder(Order order) {
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

    }*/

    public static Response createOrder(String json) {
        return given()
                .config(BaseData.config())
                .header("Content-type", "application/json")
                .body(json)
                .post("/api/v1/orders");
    }

    public static Response cancelOrder(String json) {
        return given()
                .config(BaseData.config())
                .body(json)
                .put("/api/v1/orders/cancel");
    }

}