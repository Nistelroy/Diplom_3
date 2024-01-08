package ru.yandex.practicum.data;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DataForTest {
    private static final String URI_FOR_LOGIN_USER = "https://stellarburgers.nomoreparties.site/api/auth/login";
    private static final String URI_FOR_UPDATE_OR_DELETE_USER = "https://stellarburgers.nomoreparties.site/api/auth/user";
    public static String accessToken;

    @Step("Логин юзером")
    public static Response loginUser(User user) {
        Response response = given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(URI_FOR_LOGIN_USER);
        accessToken = response.then().extract().path("accessToken");
        return response;
    }

    @Step("Удаление юзера")
    public static void deleteUser() {
        if (accessToken != null) {
            given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", accessToken)
                    .when()
                    .delete(URI_FOR_UPDATE_OR_DELETE_USER);
        }
    }
}
