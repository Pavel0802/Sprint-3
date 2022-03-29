package ru.yandex.practicum.sprint_3;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.practicum.sprint_3.order.OrderRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetOrderTest {
    private OrderRequest orderRequest;

    @Test
    @DisplayName("Вывод списка заказов")
    @Description("Тест проверяет возможность отображения доступных заказов")
    public void getOrderTest() {
        OrderRequest orderRequest = new OrderRequest();
        String getOrder = orderRequest.getOrder();
        assertThat(getOrder, containsString("orders"));
    }
}
