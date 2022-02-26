package ru.yandex.practicum.sprint_3;


import freemarker.template.utility.Constants;
import io.qameta.allure.Description;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.sprint_3.order.Order;

import java.util.List;

@RunWith(Parameterized.class)
public class OrderTest {

    private final List<String> colors;
    private String responseBody;


    @Before
    public void setup() {
        RestAssured.baseURI = Constants.baseURI;
    }

    @After
    public void destroy() {
        if (responseBody != null) {
            //api отмены заказа не работает, но во всяком случае написал код отмены тестовых действий
            OrderRequestHelper.cancelOrder(responseBody).thenReturn();
        }
    }

    @Parameterized.Parameters
    public static Object[][] getColor() {
        return new Object[][]{
                {List.of()},
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("GREY", "BLACK")}
        };
    }

    @Test
    @DisplayName("Проверка создания заказа с разными цветами")
    @Description("Проверяет создание заказа с разными вариантами выбора цвета: " +
            "с серым, с черным, серым и черным одновременно или без выбора цвета")
    public void orderCreationTest() {
        Order order = OrderGenerator.generate(colors);

    }
}