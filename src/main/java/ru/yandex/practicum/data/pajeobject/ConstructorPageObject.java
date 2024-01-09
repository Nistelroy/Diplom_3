package ru.yandex.practicum.data.pajeobject;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.data.ElementsLocators;

import java.util.concurrent.TimeUnit;

public class ConstructorPageObject {
    private WebDriver webDriver;
    private ElementsLocators elementsLocators;
    private WebDriverWait wait;

    public ConstructorPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        elementsLocators = new ElementsLocators();
        wait = new WebDriverWait(webDriver, 3);
    }
    @Step("Открытие страницы конструктора")
    public void openConstructorPage() {
        webDriver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step
    public void clickToBottomEnterAccount(){
        webDriver.findElement(elementsLocators.buttonOfEnterInAccount).click();
    }

    @Step
    public void clickToBottomPersonalArea() throws InterruptedException {
        webDriver.findElement(elementsLocators.buttonOfEnterInPersonalArea).click();
        Thread.sleep(1500);
    }

    @Step
    public void scrollToBuns() throws InterruptedException {
        webDriver.findElement(elementsLocators.buttonOfBuns).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementsLocators.bunsHeader));
        Thread.sleep(1000);
    }

    @Step
    public void scrollToSouse() throws InterruptedException {
        webDriver.findElement(elementsLocators.buttonOfSauces).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementsLocators.saucesHeader));
        Thread.sleep(1000);
    }

    @Step
    public void scrollToFillings() throws InterruptedException {
        webDriver.findElement(elementsLocators.buttonOfFillings).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementsLocators.fillingsHeader));
        Thread.sleep(1000);
    }

    public boolean bounsIsVisible() {
       return webDriver.findElement(elementsLocators.bunsHeader).isDisplayed();
    }

    public boolean souseIsVisible() {
        return webDriver.findElement(elementsLocators.saucesHeader).isDisplayed();
    }

    public boolean filluingsIsVisible() {
        return webDriver.findElement(elementsLocators.fillingsHeader).isDisplayed();
    }
}
