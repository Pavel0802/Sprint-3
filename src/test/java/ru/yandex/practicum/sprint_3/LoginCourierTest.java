package ru.yandex.practicum.sprint_3;


import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ru.yandex.practicum.sprint_3.courier.Courier;
import ru.yandex.practicum.sprint_3.courier.CourierRequest;
import ru.yandex.practicum.sprint_3.courier.CourierLogin;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


//@RunWith(Parameterized.class)
public class LoginCourierTest {

    private CourierRequest courierRequest;
    private int courierId;

    @BeforeAll
    public void setUp() {
        courierRequest = new CourierRequest();
    }

    @AfterAll
    public void tearDown() {
        courierRequest.delete(courierId);
    }

    @Test
    public void courierCanGreatedAndBeLogIn() {
        Courier courier = Courier.greatCourier();
        System.out.println("Courier:" + courier.login + " " + courier.password + " " + courier.firstName);
        courierRequest = new CourierRequest();
        boolean isCourierGreated = courierRequest.great(courier);

        courierId = courierRequest.login(CourierLogin.from(courier));
        System.out.println(courierId);
        assertThat("CourierId is incorrect", courierId, is(not(0)));

    }

    @Test
    public void courierIdWithoutRequiredField(){
        Courier courier = Courier.greatCourier();
        System.out.println("Courier:" + courier.login + " " + courier.password + " " + courier.firstName);
        courierRequest = new CourierRequest();
        boolean isCourierGreated = courierRequest.great(courier);
        CourierLogin courierLogin = new CourierLogin(courier.login, "");
        String courierIdwithoutPassword = courierRequest.loginFild(courierLogin);
        System.out.println(courierIdwithoutPassword);
        assertThat(courierIdwithoutPassword, containsString("Недостаточно данных для входа"));

    }

    @Test
    public void courierIdIncorrectField(){
        Courier courier = Courier.greatCourier();
        System.out.println("Courier:" + courier.login + " " + courier.password + " " + courier.firstName);
        courierRequest = new CourierRequest();
        boolean isCourierGreated = courierRequest.great(courier);
        CourierLogin courierLogin = new CourierLogin(courier.login, courier.password +"111");
        String courierIdIncorrectPassword = courierRequest.loginIncorrectFild(courierLogin);
        System.out.println(courierIdIncorrectPassword);
        assertThat(courierIdIncorrectPassword, containsString("Учетная запись не найдена"));

    }
}
