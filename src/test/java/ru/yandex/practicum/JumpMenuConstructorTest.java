package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.data.pajeobject.ConstructorPageObject;

import static org.junit.Assert.assertTrue;

public class JumpMenuConstructorTest {
    private final WebDriver webDriver = new ChromeDriver();
    private final ConstructorPageObject constructorPageObject = new ConstructorPageObject(webDriver);

    @Before
    public void setUp() {
        constructorPageObject.openConstructorPage();
    }

    @Test
    @DisplayName("Тест перехода к разделу булки")
    public void testScrollToBunsSection() throws InterruptedException {
        constructorPageObject.scrollToFillings();
        constructorPageObject.scrollToBuns();

        assertTrue(constructorPageObject.bonsIsVisible());
    }

    @Test
    @DisplayName("Тест перехода к разделу начинки")
    public void testScrollToFillingsSection() throws InterruptedException {
        constructorPageObject.scrollToSouse();

        assertTrue(constructorPageObject.souseIsVisible());
    }

    @Test
    @DisplayName("Тест перехода к разделу соусы")
    public void testScrollToSouseSection() throws InterruptedException {
        constructorPageObject.scrollToFillings();

        assertTrue(constructorPageObject.fillingsIsVisible());
    }

    @After
    public void tearDown() {
        webDriver.close();
    }
}
