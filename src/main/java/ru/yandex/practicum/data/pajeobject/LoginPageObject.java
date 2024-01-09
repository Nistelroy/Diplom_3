package ru.yandex.practicum.data.pajeobject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.data.ElementsLocators;
import ru.yandex.practicum.data.api.ApiStepsForTest;
import ru.yandex.practicum.data.user.User;
import ru.yandex.practicum.data.user.UserGenerator;

public class LoginPageObject {
    private WebDriver webDriver;
    private ElementsLocators elementsLocators;

    public LoginPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        elementsLocators = new ElementsLocators();
    }

    @Step("Открытие страницы логин")
    public void openLoginPage() {
        webDriver.get("https://stellarburgers.nomoreparties.site/login");
    }

    @Step("Создание юзера через апи и авторизация на странице логина")
    public void loginInAccautFromUI() {
        User user = UserGenerator.randomUser();
        ApiStepsForTest.createUserInApi(user);
        webDriver.findElements(elementsLocators.inputDataFieldForLogin).get(0).sendKeys(user.getEmail());
        webDriver.findElements(elementsLocators.inputDataFieldForLogin).get(1).sendKeys(user.getPassword());
        webDriver.findElement(elementsLocators.enterBottomLogPage).click();
    }
}
