package ru.yandex.practicum.sprint_3;

import org.junit.Test;
import ru.yandex.practicum.sprint_3.order.OrderRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetOrderTest {
    private OrderRequest orderRequest;

    @Test
    public void getOrderTest() {
        OrderRequest orderRequest = new OrderRequest();
        String getOrder = orderRequest.getOrder();
        assertThat(getOrder, containsString("orders"));
    }
}
