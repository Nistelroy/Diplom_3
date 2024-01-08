package ru.yandex.practicum.data;

import org.openqa.selenium.By;

public class ElementsLocators {
    //registr page
    public By inputDataFieldForRegistr = By.cssSelector("input.text.input__textfield.text_type_main-default");
    public By buttonForRegistr = By.xpath("//button[contains(text(), 'Зарегистрироваться')]");
    public By passwordErrorMassage = By.xpath("//p[contains(text(), 'Некорректный пароль')]");

}
