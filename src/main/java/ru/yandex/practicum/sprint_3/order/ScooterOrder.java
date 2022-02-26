package ru.yandex.practicum.sprint_3.order;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ScooterOrder {
    public final String BASE_URL = "https://qa-scooter.praktikum-services.ru/api/v1/";

    protected RequestSpecification getBaseSpec(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }
}
