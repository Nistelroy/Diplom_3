package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.data.pajeobject.ConstructorPageObject;
import ru.yandex.practicum.data.pajeobject.LoginPageObject;
import ru.yandex.practicum.data.pajeobject.ProfilePageObject;
import ru.yandex.practicum.data.pajeobject.RegisterPageObject;

import static org.junit.Assert.assertEquals;

public class TransitionTest {
    private final WebDriver webDriver = new ChromeDriver();
    private final RegisterPageObject registerPageObject = new RegisterPageObject(webDriver);
    private final ConstructorPageObject constructorPageObject = new ConstructorPageObject(webDriver);
    private final LoginPageObject loginPageObject = new LoginPageObject(webDriver);
    private final ProfilePageObject profilePageObject = new ProfilePageObject(webDriver);

    @Test
    @DisplayName("Переход авторизованного пользователя в личный кабинет")
    public void transitAftorizedUserToPersonalAreaOpenProfilPage() throws InterruptedException {
        constructorPageObject.openConstructorPage();
        constructorPageObject.clickToBottomPersonalArea();
        loginPageObject.loginInAccautFromUI();
        constructorPageObject.clickToBottomPersonalArea();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/account/profile";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Попытка перехода неавторизованного пользователя в личный кабинет с перенаправлением на страницу логин")
    public void transitNoAftorizedUserToPersonalAreaOpenLoginPage() throws InterruptedException {
        constructorPageObject.openConstructorPage();
        constructorPageObject.clickToBottomPersonalArea();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/login";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Переход авторизованного юзера по логотипу сайта из ЛК в конструктор")
    public void transitAftorizedUserInPersonalAreaToLogotipOpenConstrPage() throws InterruptedException {
        loginPageObject.openLoginPage();
        loginPageObject.loginInAccautFromUI();
        constructorPageObject.clickToBottomPersonalArea();
        profilePageObject.clickToLogotip();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Переход авторизованного юзера по кнопке конструктор из ЛК в конструктор")
    public void transitAftorizedUserInPersonalAreaToConstructorOpenConstrPage() throws InterruptedException {
        loginPageObject.openLoginPage();
        loginPageObject.loginInAccautFromUI();
        constructorPageObject.clickToBottomPersonalArea();
        profilePageObject.clickToConstructor();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Выход авторизованного пользователя из личного кабинета")
    public void exitAftorizedUserInPersonalAreaOpenLoginPage() throws InterruptedException {
        loginPageObject.openLoginPage();
        loginPageObject.loginInAccautFromUI();
        constructorPageObject.clickToBottomPersonalArea();
        profilePageObject.clickToExit();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/login";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @After
    public void tearDown() {
        registerPageObject.deleteUserInApi();
        webDriver.close();
    }
}
