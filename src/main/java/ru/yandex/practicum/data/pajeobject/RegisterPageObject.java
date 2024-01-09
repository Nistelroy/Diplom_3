package ru.yandex.practicum.data.pajeobject;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.data.ElementsLocators;
import ru.yandex.practicum.data.api.ApiStepsForTest;
import ru.yandex.practicum.data.user.User;
import ru.yandex.practicum.data.user.UserGenerator;

public class RegisterPageObject {
    private WebDriver webDriver;
    private ElementsLocators elementsLocators;
    private Faker faker;
    private User user;

    public RegisterPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        elementsLocators = new ElementsLocators();
        faker = new Faker();
    }

    @Step("Открытие страницы регистрации")
    public void openRegistrPage() {
        webDriver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Step("Заполнение формы корректными данными")
    public User fillingRegistrationFormCorrectData() {
        user = UserGenerator.randomUser().withPassword("123456");

        webDriver.findElements(elementsLocators.inputDataFieldForRegistr).get(0).sendKeys(user.getName());
        webDriver.findElements(elementsLocators.inputDataFieldForRegistr).get(1).sendKeys(user.getEmail());
        webDriver.findElements(elementsLocators.inputDataFieldForRegistr).get(2).sendKeys(user.getPassword());
        webDriver.findElement(elementsLocators.buttonForRegistr).click();
        return user;
    }

    @Step("Заполенение формы с паролем меньше 6 символов")
    public void fillingRegistrationFormShortPassword() {
        user = UserGenerator.randomUser().withPassword("12345");

        webDriver.findElements(elementsLocators.inputDataFieldForRegistr).get(0).sendKeys(user.getName());
        webDriver.findElements(elementsLocators.inputDataFieldForRegistr).get(1).sendKeys(user.getEmail());
        webDriver.findElements(elementsLocators.inputDataFieldForRegistr).get(2).sendKeys(user.getPassword());
        webDriver.findElement(elementsLocators.buttonForRegistr).click();
    }

    @Step("Удаление юзера через апи")
    public void deleteUserInApi() {
        ApiStepsForTest.deleteUser();
    }

    @Step("Логин юзером через апи")
    public Response loginUserInApi(User user) {
        Response response = ApiStepsForTest.loginUser(user);
        System.out.println(response.asString());
        return response;
    }

    @Step("Логин юзером через апи")
    public Response loginUserInApi() {
        Response response = ApiStepsForTest.loginUser(user);
        System.out.println(response.asString());
        return response;
    }

    @Step("Проверка видимости ошибки ввода пароля")
    public boolean passwordError() {
        return webDriver.findElement(elementsLocators.passwordErrorMassage).isDisplayed();
    }

    @Step("Клик по кнопке входа на странице регистрации")
    public void clickToEnterBottom() {
        webDriver.findElement(elementsLocators.buttonOfEnterRegPage).click();
    }
}

