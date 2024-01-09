package ru.yandex.practicum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.data.pajeobject.ConstructorPageObject;
import ru.yandex.practicum.data.pajeobject.RegisterPageObject;

import static org.junit.Assert.assertTrue;

public class JumpMenuConstructorTest {
    private final WebDriver webDriver = new ChromeDriver();
    private final ConstructorPageObject constructorPageObject = new ConstructorPageObject(webDriver);


    @Before
    public void setUp() {
        constructorPageObject.openConstructorPage();
    }

    @Test
    public void testScrollToBunsSection() throws InterruptedException {
        constructorPageObject.scrollToFillings();
        constructorPageObject.scrollToBuns();

        assertTrue(constructorPageObject.bounsIsVisible());
    }

    @Test
    public void testScrollToFillingsSection() throws InterruptedException {
        constructorPageObject.scrollToSouse();

        assertTrue(constructorPageObject.souseIsVisible());
    }

    @Test
    public void testScrollToSouseSection() throws InterruptedException {
        constructorPageObject.scrollToFillings();

        assertTrue(constructorPageObject.filluingsIsVisible());
    }

    @After
    public void tearDown() {
        webDriver.close();
    }
}
