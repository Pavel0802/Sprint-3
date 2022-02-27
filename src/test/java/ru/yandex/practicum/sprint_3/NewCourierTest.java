package ru.yandex.practicum.sprint_3;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.yandex.practicum.sprint_3.courier.Courier;
import ru.yandex.practicum.sprint_3.courier.CourierRequest;
import ru.yandex.practicum.sprint_3.courier.CourierLogin;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewCourierTest {

    private CourierRequest courierRequest;
    private int courierId;

    @BeforeAll
    public void setUp(){
        courierRequest = new CourierRequest();
    }

    @AfterAll
    public void tearDown (){
        courierRequest.delete(courierId);
    }

    @Test //проверка возможности создать курьера и невозможности создать двух одинаковых курьеров
    public void courierCanBeCreatedWithValidLoginAndNotGreateIsTwoIdenticalCourierGreated() {
        Courier courier = Courier.greatCourier();
        System.out.println("Courier:" + courier.login + " " + courier.password +" " + courier.firstName);
        boolean isCourierGreated = courierRequest.great(courier);
        System.out.println("Courier be Greated: " + isCourierGreated);

        String isTwoIdenticalCourierGreated = courierRequest.greatTwoIdentical(courier);
        System.out.println(isTwoIdenticalCourierGreated);

        courierId = courierRequest.login(CourierLogin.from(courier));
        System.out.println("courier Id: " + courierId);

        assertTrue(isCourierGreated, "Courier is not created");
        assertThat(isTwoIdenticalCourierGreated, containsString("Этот логин уже используется. Попробуйте другой."));
        assertThat("CourierId is incorrect", courierId, is(not(0)));
    }

    @Test //проверка аозможности создать курьера без обязательного поля пароль
    public void courierNotBeGreatedWithoutRequiredFieldPassword (){
        Courier courier = new Courier("asdfghjkl ", "", "123456");
        String greatWithoutRequiredField = courierRequest.greatWithoutRequiredField(courier);
        System.out.println(greatWithoutRequiredField);
        assertThat(greatWithoutRequiredField, containsString("Недостаточно данных для создания учетной записи"));

    }
    @Test //проверка возможности создать курьера без обязательного поля first Name
    public void courierNotBeGreatedWithoutRequiredFieldFirstName (){
        Courier courier = new Courier("fhdsjhjj", "weret", "");
        String greatWithoutRequiredField = courierRequest.greatWithoutRequiredField(courier);
        System.out.println(greatWithoutRequiredField);
        assertThat(greatWithoutRequiredField, containsString("Недостаточно данных для создания учетной записи"));

    }

    @Test
    public void courierCanBeLogIn() {
        Courier courier = Courier.greatCourier();
        System.out.println("Courier:" + courier.login + " " + courier.password + " " + courier.firstName);
        courierRequest = new CourierRequest();
        boolean isCourierGreated = courierRequest.great(courier);

        courierId = courierRequest.login(CourierLogin.from(courier));
        System.out.println(courierId);

        assertTrue(isCourierGreated);
        assertThat("CourierId is incorrect", courierId, is(not(0)));
    }


}
