package org;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MtsByTests {

    private static final Logger logger = LoggerFactory.getLogger(MtsByTests.class);
    private WebDriver driver;
    private final String BASE_URL = "https://www.mts.by/";
    private org.HomePage homePage;
    private org.PaymentPage paymentPage;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        logger.info("ChromeDriver настроен.");
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(BASE_URL);
        logger.info("Открыт сайт: {}", BASE_URL);
        homePage = new org.HomePage(driver);
        paymentPage = new org.PaymentPage(driver);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер закрыт.");
        }
    }

    @Test
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    void testBlockTitle() {
        String expectedTitle = "Онлайн пополнение без комиссии";
        String actualTitle = homePage.getOnlineRechargeBlockTitle();
        assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому.");
        logger.info("Проверка заголовка блока: Ожидалось '{}', получено '{}'", expectedTitle, actualTitle);
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    void testPaymentLogos() {
        List<WebElement> logos = homePage.getPaymentSystemsContainer().findElements(By.tagName("img"));
        assertTrue(logos.size() > 0, "Логотипы платежных систем не найдены.");
        logger.info("Найдено {} логотипов платежных систем.", logos.size());

        logos.forEach(logo -> {
            assertNotNull(logo.getAttribute("src"), "У логотипа отсутствует атрибут src.");
        });
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    void testServiceDetailsLink() {
        homePage.clickServiceDetailsLink();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("oplata-online"));

        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("oplata-online"), "URL не содержит 'oplata-online'");
        logger.info("Переход по ссылке 'Подробнее о сервисе'. Текущий URL: {}", currentUrl);
    }

    @Test
    @DisplayName("Проверка надписей в незаполненных полях разных вариантов оплаты услуг")
    void testPlaceholderTexts() {
        assertEquals("Номер телефона", homePage.getUslugiSvyaziPhoneNumberPlaceholder(), "Неверный placeholder для 'Услуги связи'");
        assertEquals("Номер договора", homePage.getDomashniyInternetContractPlaceholder(), "Неверный placeholder для 'Домашний интернет'");
        assertEquals("Номер договора", homePage.getRassrochkaContractPlaceholder(), "Неверный placeholder для 'Рассрочка'");
        assertEquals("Номер лицевого счёта", homePage.getZadolzhennostAccountNumberPlaceholder(), "Неверный placeholder для 'Задолженность'");
    }

    @Test
    @DisplayName("Проверка работы кнопки 'Продолжить' и данных на странице оплаты")
    void testContinueButtonAndPaymentData() {
        homePage.selectServiceType();
        homePage.enterPhoneNumber("297777777");
        homePage.clickContinueButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(), 'Сумма пополнения')]")));
        logger.info("Страница оплаты загружена.");

        assertNotNull(paymentPage.getPaymentAmountTitle(), "Не отображается заголовок 'Сумма пополнения'");
        assertEquals("+375297777777", paymentPage.getPhoneNumberDisplay(), "Неверный номер телефона"); //Номера могут отличаться от сайта
        assertEquals("Сумма пополнения", paymentPage.getAmountFieldPlaceholder(), "Неверный placeholder для суммы пополнения");
        assertEquals("Номер карты", paymentPage.getCardNumberPlaceholder(), "Неверный placeholder для номера карты");
        assertEquals("ММ / ГГ", paymentPage.getCardExpiryPlaceholder(), "Неверный placeholder для срока действия карты");
        assertEquals("CVC / CVV", paymentPage.getCardCvvPlaceholder(), "Неверный placeholder для CVV/CVC");
        assertNotNull(paymentPage.getPaymentSystemIcons(), "Не отображаются иконки платежных систем");

        logger.info("Проверки на странице оплаты выполнены.");
    }
}
