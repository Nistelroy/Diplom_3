package ru.yandex.practicum.data.pajeobject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.data.ElementsLocators;

public class ResetPasswordPageObject {
    private WebDriver webDriver;
    private ElementsLocators elementsLocators;

    public ResetPasswordPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        elementsLocators = new ElementsLocators();
    }

    @Step("Открытие страницы восстанавления пароля")
    public void openResetPasswordPage() {
        webDriver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    @Step("Клик по кнопке входа на странице восстанавления пароля")
    public void clickToEnter() {
        webDriver.findElement(elementsLocators.enterBottomForgotPage).click();
    }
}
