package ru.yandex.practicum.sprint_3;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.sprint_3.order.Order;
import ru.yandex.practicum.sprint_3.order.OrderGenerator;
import ru.yandex.practicum.sprint_3.order.OrderRequest;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(Parameterized.class)
public class OrderTest {

    private final List<String> color;

    public OrderTest(List<String> color) {
        this.color = color;
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
    public void orderCanBeCreatedWithValidFild() {
        Order order = OrderGenerator.generate(color);
        OrderRequest orderRequest = new OrderRequest();
        System.out.println(order);
        String isOrderGreated = orderRequest.greatOrder(order);
        System.out.println("Order be Greated: " + isOrderGreated);
        assertThat(isOrderGreated, containsString("track"));

    }

}