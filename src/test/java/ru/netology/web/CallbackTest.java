package ru.netology.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {

    private WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "./driver/win/chromedriver.exe");

    }


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldSendForm() throws InterruptedException {
        driver.get("http://localhost:9999");
        Thread.sleep(500);
        WebElement form = driver.findElement(By.cssSelector("[action='/']"));
        form.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иван-Иван Петр");
        Thread.sleep(500);
        form.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79110000000");
        Thread.sleep(500);
        form.findElement(By.cssSelector(".checkbox__box")).click();
        Thread.sleep(500);
        form.findElement(By.cssSelector(".button__content")).click();
        Thread.sleep(500);
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        Thread.sleep(500);
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());

    }
}