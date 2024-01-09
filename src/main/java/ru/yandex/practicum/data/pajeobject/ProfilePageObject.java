package ru.yandex.practicum.data.pajeobject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.data.ElementsLocators;

public class ProfilePageObject {
    private WebDriver webDriver;
    private ElementsLocators elementsLocators;

    public ProfilePageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        elementsLocators = new ElementsLocators();
    }

    @Step("Клик по логотипу")
    public void clickToLogotip() {
        webDriver.findElement(elementsLocators.logotipBottomLogPage).click();
    }

    @Step("Клик по кнопке конструктор")
    public void clickToConstructor() {
        webDriver.findElement(elementsLocators.constructorBottomLogPage).click();
    }

    @Step("Клик по кнопке выход из профиля")
    public void clickToExit() throws InterruptedException {
        webDriver.findElement(elementsLocators.exitBottomProfilPage).click();
        Thread.sleep(1500);
    }
}
