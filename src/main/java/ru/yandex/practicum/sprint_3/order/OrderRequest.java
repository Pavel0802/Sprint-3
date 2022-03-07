package ru.yandex.practicum.sprint_3.order;

import io.restassured.response.Response;
import ru.yandex.practicum.sprint_3.courier.BaseData;

import static io.restassured.RestAssured.given;

public class OrderRequest extends BaseData {
    public final String PATH = BASE_URL + "orders/";

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
    }

    public String getOrder() {
        return given()
                .spec(getBaseSpec())
                //.body(order)
                .when()
                .get(PATH)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body().asString();
    }

}