import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MtsByTests {
    private static WebDriver driver;
    private final String BASE_URL = "https://www.mts.by/";

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterAll
    static void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    void testBlockTitle() {
        driver.get(BASE_URL);
        WebElement titleElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'Онлайн пополнение без комиссии')]")));
        assertNotNull(titleElement, "Блок 'Онлайн пополнение без комиссии' не найден.");
        assertEquals("Онлайн пополнение без комиссии", titleElement.getText(), "Название блока не соответствует ожидаемому.");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    void testPaymentLogos() {
        driver.get(BASE_URL);

        WebElement logosContainer = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".payment-systems")));

        List<WebElement> logos = logosContainer.findElements(By.tagName("img"));

        assertTrue(logos.size() > 0, "Логотипы платежных систем не найдены.");
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    void testServiceDetailsLink() {
        driver.get(BASE_URL);
        WebElement link = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Подробнее о сервисе")));
        link.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("oplata-online"));
    }

    @Test
    @DisplayName("Проверка работы кнопки 'Продолжить'")
    void testContinueButton() {
        driver.get(BASE_URL);

        WebElement serviceTypeDropdown = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='paymentService']")));
        serviceTypeDropdown.click();

        WebElement serviceOption = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//option[text()='Услуги связи']")));
        serviceOption.click();

        WebElement phoneNumberField = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='msisdn']")));
        phoneNumberField.sendKeys("297777777");

        WebElement continueButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Продолжить']")));
        continueButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(), 'Сумма пополнения')]")));
    }
}