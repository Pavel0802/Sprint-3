package ru.yandex.practicum.sprint_3.order;

import com.github.javafaker.Faker;
import java.util.Locale;

public enum Generator {
    INSTANCE;

    private final Faker faker;

    Generator() {
        faker = new Faker(new Locale("ru"));
    }

    public Faker getFaker() {
        return faker;
    }
}