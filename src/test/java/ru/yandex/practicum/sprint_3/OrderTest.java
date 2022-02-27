package ru.yandex.practicum.sprint_3;


import freemarker.template.utility.Constants;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.sprint_3.order.BaseData;
import ru.yandex.practicum.sprint_3.order.Order;
import ru.yandex.practicum.sprint_3.order.OrderGenerator;
import ru.yandex.practicum.sprint_3.order.OrderRequest;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(Parameterized.class)
public class OrderTest {

    /*private OrderRequest orderRequest;
    private OrderGenerator order;

    @BeforeAll
    public void setUp(){
        orderRequest = new OrderRequest();
        //order = new Order();
    }

    //@AfterAll
    //public void tearDown (){
    //    courierRequest.delete(courierId);
    //}

    @Test //
    public void orderCanBeCreatedWithValidFild() {
        Order order = new Order("Qfhgf", "Egjghfj", "fdh dfhs", "4", "+79991111111", 2, "2020-01-02", "ewttewtew", "GREY");
        String isOrderGreated = orderRequest.greatOrder(order);
        System.out.println("Order be Greated: " + isOrderGreated);


        //courierId = courierRequest.login(CourierLogin.from(courier));
        //System.out.println("courier Id: " + courierId);

        //assertTrue(isCourierGreated, "Courier is not created");
        assertThat(isOrderGreated, containsString("track"));
        //assertThat("CourierId is incorrect", courierId, is(not(0)));
    }*/

    private final List<String> colors;
    private String responseBody;

    public  OrderTest(List<String> colors) {
        this.colors = colors;
    }

    @Before
    public void setup() {
        RestAssured.baseURI = BaseData.BASE_URL;
    }

    @After
    public void destroy() {
        if (responseBody != null) {
            //api отмены заказа не работает, но во всяком случае написал код отмены тестовых действий
            OrderRequest.cancelOrder(responseBody).thenReturn();
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
        Response response = OrderRequest.greatOrder(order.toString());
        response.then().assertThat().body("track", notNullValue()).statusCode(201);
        this.responseBody = response.body().asString();
    }
}