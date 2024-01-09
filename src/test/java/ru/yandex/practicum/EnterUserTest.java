package ru.yandex.practicum;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.data.pajeobject.ConstructorPageObject;
import ru.yandex.practicum.data.pajeobject.LoginPageObject;
import ru.yandex.practicum.data.pajeobject.RegisterPageObject;
import ru.yandex.practicum.data.pajeobject.ResetPasswordPageObject;

import static org.junit.Assert.assertEquals;

public class EnterUserTest {
    private final WebDriver webDriver = new ChromeDriver();
    private final RegisterPageObject registerPageObject = new RegisterPageObject(webDriver);
    private final ConstructorPageObject constructorPageObject = new ConstructorPageObject(webDriver);
    private final LoginPageObject loginPageObject = new LoginPageObject(webDriver);
    private final ResetPasswordPageObject resetPasswordPageObject = new ResetPasswordPageObject(webDriver);

    @Test
    public void loginInMainPageBottomEnterAccountTestAccessed() throws InterruptedException {
        constructorPageObject.openConstructorPage();
        constructorPageObject.clickToBottomEnterAccount();
        loginPageObject.loginInAccautrFromUI();
        constructorPageObject.clickToBottomPersonalArea();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/account/profile";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void loginInMainPageBottomPersonalAreaTestAccessed() throws InterruptedException {
        constructorPageObject.openConstructorPage();
        constructorPageObject.clickToBottomPersonalArea();
        loginPageObject.loginInAccautrFromUI();
        constructorPageObject.clickToBottomPersonalArea();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/account/profile";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void loginInRegistrPageBottomEnterAccountTestAccessed() throws InterruptedException {
        registerPageObject.openRegistrPage();
        registerPageObject.clickToEnterBottom();
        loginPageObject.loginInAccautrFromUI();
        constructorPageObject.clickToBottomPersonalArea();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/account/profile";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void loginInResetPassPageBottomEnterAccountTestAccessed() throws InterruptedException {
        resetPasswordPageObject.openResetPasswordPage();
        resetPasswordPageObject.clickToEnter();
        loginPageObject.loginInAccautrFromUI();
        constructorPageObject.clickToBottomPersonalArea();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/account/profile";
        String actualUrl = webDriver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @After
    public void tearDown() {
        registerPageObject.deleteUserInApi();
        webDriver.close();
    }
}
