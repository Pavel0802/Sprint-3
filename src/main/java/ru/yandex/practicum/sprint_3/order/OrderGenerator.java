package ru.yandex.practicum.sprint_3.order;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class OrderGenerator {


    public static Order generate (String color){
        String firstName = RandomStringUtils.randomAlphabetic(7);
        String lastName = RandomStringUtils.randomAlphabetic(7);
        String address = RandomStringUtils.randomAlphabetic(7) + " " + RandomStringUtils.randomAlphabetic(7);
        String metroStation = RandomStringUtils.randomNumeric(2);
        String phone = "+7 9" + RandomStringUtils.randomNumeric(9);
        int rentTime = RandomUtils.nextInt(1,20);
        String deliveryDate = RandomStringUtils.randomNumeric(4) + "-" + RandomStringUtils.randomNumeric(1) + "-" + RandomStringUtils.randomNumeric(1);
        String comment = RandomStringUtils.randomAlphabetic(10);

        return new Order(firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment,
                color) ;
    }


}
