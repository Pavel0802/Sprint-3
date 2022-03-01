package ru.yandex.practicum.sprint_3.order;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.params.CoreConnectionPNames;

public class BaseData {
    /*public final String BASE_URL = "https://qa-scooter.praktikum-services.ru/api/v1/";

    protected RequestSpecification getBaseSpec(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }*/
    public static final String baseURI = "http://qa-scooter.praktikum-services.ru/";

    @SuppressWarnings("deprecation")
    public static RestAssuredConfig config(){
        return RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 2000));
    }
}
