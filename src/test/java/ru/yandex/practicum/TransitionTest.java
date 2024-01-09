package ru.yandex.practicum;

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
    public void transitAftorizedUserToPersonalAreaOpenProfilPage() throws InterruptedException {
        constructorPageObject.openConstructorPage();
        constructorPageObject.clickToBottomPersonalArea();
        loginPageObject.loginInAccautrFromUI();
        constructorPageObject.clickToBottomPersonalArea();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/account/profile";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void transitNoAftorizedUserToPersonalAreaOpenLoginPage() throws InterruptedException {
        constructorPageObject.openConstructorPage();
        constructorPageObject.clickToBottomPersonalArea();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/login";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void transitAftorizedUserInPersonalAreaToLogotipOpenConstrPage() throws InterruptedException {
        loginPageObject.openLoginPage();
        loginPageObject.loginInAccautrFromUI();
        constructorPageObject.clickToBottomPersonalArea();
        profilePageObject.clickToLogotip();


        String expectedUrl = "https://stellarburgers.nomoreparties.site/";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void transitAftorizedUserInPersonalAreaToConstructorOpenConstrPage() throws InterruptedException {
        loginPageObject.openLoginPage();
        loginPageObject.loginInAccautrFromUI();
        constructorPageObject.clickToBottomPersonalArea();
        profilePageObject.clickToConstructor();


        String expectedUrl = "https://stellarburgers.nomoreparties.site/";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void exitAftorizedUserInPersonalAreaOpenLoginPage() throws InterruptedException {
        loginPageObject.openLoginPage();
        loginPageObject.loginInAccautrFromUI();
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
