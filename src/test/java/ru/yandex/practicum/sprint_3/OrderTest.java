package ru.yandex.practicum.sprint_3;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.sprint_3.order.Order;
import ru.yandex.practicum.sprint_3.order.OrderGenerator;
import ru.yandex.practicum.sprint_3.order.OrderRequest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(Parameterized.class)
public class OrderTest {

    private String color;


    public OrderTest(String color) {
        this.color = color;
    }

    /*@After
    public void destroy() {
        if (responseBody != null) {
            //api отмены заказа не работает, но во всяком случае написал код отмены тестовых действий
            OrderRequest.cancelOrder(responseBody).thenReturn();
        }*/

    @Parameterized.Parameters
    public static Object[][] getColor() {
        return new Object[][]{
                {"GREY"},
                {"BLACK"},
                {" "},
                {"GREY, BLACK"}
        };
    }


    @Test
    public void orderCanBeCreatedWithValidFild() {
        Order order = OrderGenerator.generate(color);
        OrderRequest orderRequest = new OrderRequest();
        System.out.println(order);
        String isOrderGreated = orderRequest.greatOrder(order);
        System.out.println("Order be Greated: " + isOrderGreated);
        assertThat(isOrderGreated, containsString("track"));

    }



}