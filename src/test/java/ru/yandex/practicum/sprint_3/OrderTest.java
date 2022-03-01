package ru.yandex.practicum.sprint_3;


import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.sprint_3.order.BaseData;
import ru.yandex.practicum.sprint_3.order.Order;
import ru.yandex.practicum.sprint_3.order.OrderGenerator;
import ru.yandex.practicum.sprint_3.order.OrderRequest;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(Parameterized.class)
public class OrderTest {

    /*private OrderRequest orderRequest;
    private OrderGenerator orderGenerator;
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String color;

    public OrderTest (String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String color){
        this.firstName = firstName;
        this.lastName =lastName;
        this.address =address;
        this.metroStation =metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate =deliveryDate;
        this.comment =comment;
        this.color =color;
    }

    @BeforeAll
    public void setUp(){
        orderRequest = new OrderRequest();
        //Order order = new Order();
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"qrfewg", "rgdg", "fdhd fdhdf", "4", "+79991235566", 2, "2022-03-03", "ffffff", "GREY"},
                {"qrfewg", "rgdg", "fdhd fdhdf", "4", "+79991235566", 2, "2022-03-03", "ffffff", "BLACK"},
                {"qrfewg", "rgdg", "fdhd fdhdf", "4", "+79991235566", 2, "2022-03-03", "ffffff", ""},
                {"qrfewg", "rgdg", "fdhd fdhdf", "4", "+79991235566", 2, "2022-03-03", "ffffff", "GREY, BLACK"}
        };
    }

    //@AfterAll
    //public void tearDown (){
    //    courierRequest.delete(courierId);
    //}

    @Test //
    public void orderCanBeCreatedWithValidFild() {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        String isOrderGreated = orderRequest.greatOrder(order);
        System.out.println("Order be Greated: " + isOrderGreated);

    }*/

    private final List<String> colors;
    private String responseBody;

    public  OrderTest(List<String> colors) {
        this.colors = colors;
    }

    @Before

    public void setup() {
        RestAssured.baseURI = BaseData.baseURI;
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
        Response response = OrderRequest.createOrder(order.toString());
        response.then().assertThat().body("track", notNullValue()).statusCode(201);
        this.responseBody = response.body().asString();
    }
}