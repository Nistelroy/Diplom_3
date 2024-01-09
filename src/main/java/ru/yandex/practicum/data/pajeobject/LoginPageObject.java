package ru.yandex.practicum.data.pajeobject;

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

    public void loginInAccautrFromUI(){
        User user = UserGenerator.randomUser();
        ApiStepsForTest.createUserInApi(user);
        webDriver.findElements(elementsLocators.inputDataFieldForLogin).get(0).sendKeys(user.getEmail());
        webDriver.findElements(elementsLocators.inputDataFieldForLogin).get(1).sendKeys(user.getPassword());
        webDriver.findElement(elementsLocators.enterBottomLogPage).click();
    }

    public void openLoginPage() {
        webDriver.get("https://stellarburgers.nomoreparties.site/login");
    }
}
