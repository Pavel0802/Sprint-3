package ru.yandex.practicum.sprint_3.order;

import io.qameta.allure.Step;
import ru.yandex.practicum.sprint_3.courier.BaseData;

import static io.restassured.RestAssured.given;

public class OrderRequest extends BaseData {
    public final String ORDER_PATH = BASE_URL + "orders/";

    @Step("Создание заказа {order}")
    public String createOrder(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .body().asString();
    }

    @Step("Вывод списка заказов")
    public String getOrder() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body().asString();
    }

}