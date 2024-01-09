package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.data.pajeobject.RegisterPageObject;
import ru.yandex.practicum.data.user.User;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.matchesRegex;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    private final WebDriver webDriver = new ChromeDriver();
    RegisterPageObject registerPageObject = new RegisterPageObject(webDriver);

    @Before
    public void setUp() {
        registerPageObject.openRegistrPage();
    }

    @Test
    @DisplayName("Попытка регистрации с паролем меньше 6 символов")
    public void testUserRegistrShortsPasswordCreateFalse() {
        registerPageObject.fillingRegistrationFormShortPassword();
        registerPageObject.loginUserInApi().then()
                .statusCode(SC_UNAUTHORIZED);
        assertTrue(registerPageObject.passwordError());
    }

    @Test
    @DisplayName("Успешная регистрация Юзера")
    public void testUserRegistrCorrectDataIsUserCreated() {
        User user = registerPageObject.fillingRegistrationFormCorrectData();
        registerPageObject.loginUserInApi(user).then()
                .statusCode(SC_OK)
                .assertThat().body("accessToken", matchesRegex("^Bearer .*"));
    }

    @After
    public void tearDown() {
        registerPageObject.deleteUserInApi();
        webDriver.close();
    }
}
