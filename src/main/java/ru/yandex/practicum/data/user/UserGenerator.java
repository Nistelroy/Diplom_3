package ru.yandex.practicum.data.user;

import com.github.javafaker.Faker;

public class UserGenerator {
    private static Faker faker = new Faker();

    public static User randomUser() {
        User user = new User();

        user.withEmail(faker.internet().emailAddress());
        user.withPassword(faker.internet().password(6, 8));
        user.withName(faker.name().firstName());

        return user;
    }
}
