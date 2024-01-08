package ru.yandex.practicum.data.pajeobject;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.data.DataForTest;
import ru.yandex.practicum.data.ElementsLocators;
import ru.yandex.practicum.data.User;

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
    public void fillingRegistrationFormCorrectData() {
        user = new User().withName(faker.name().firstName())
                .withEmail(faker.internet().emailAddress())
                .withPassword("123456");

        webDriver.findElements(elementsLocators.inputDataFieldForRegistr).get(0).sendKeys(user.getName());
        webDriver.findElements(elementsLocators.inputDataFieldForRegistr).get(1).sendKeys(user.getEmail());
        webDriver.findElements(elementsLocators.inputDataFieldForRegistr).get(2).sendKeys(user.getPassword());
        webDriver.findElement(elementsLocators.buttonForRegistr).click();
    }

    @Step("Заполенение формы с паролем меньше 6 символов")
    public void fillingRegistrationFormShortPassword() {
        user = new User().withName(faker.name().firstName())
                .withEmail(faker.internet().emailAddress())
                .withPassword("12345");
        webDriver.findElements(elementsLocators.inputDataFieldForRegistr).get(0).sendKeys(user.getName());
        webDriver.findElements(elementsLocators.inputDataFieldForRegistr).get(1).sendKeys(user.getEmail());
        webDriver.findElements(elementsLocators.inputDataFieldForRegistr).get(2).sendKeys(user.getPassword());
        webDriver.findElement(elementsLocators.buttonForRegistr).click();
    }

    @Step("Удаление юзера через апи")
    public void deleteUserInApi() {
        DataForTest.deleteUser();
    }

    @Step("Логин юзером через апи")
    public Response loginUserInApi() {
        return DataForTest.loginUser(user);
    }

    @Step("Проверка видимости ошибки ввода пароля")
    public boolean passwordError() {
        return webDriver.findElement(elementsLocators.passwordErrorMassage).isDisplayed();
    }
}

