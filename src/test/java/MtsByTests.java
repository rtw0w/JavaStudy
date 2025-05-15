package org;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllureJunit5.class)
@Epic("UI Tests")
@Feature("Online Recharge Block")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MtsByTests {

    private static final Logger logger = LoggerFactory.getLogger(MtsByTests.class);
    private WebDriver driver;
    private final String BASE_URL = "https://www.mts.by/";
    private org.HomePage homePage;
    private org.PaymentPage paymentPage;
    private Object SeverityLevel;

    @BeforeAll
    @Step("Настройка ChromeDriver")
    void setupClass() {
        WebDriverManager.chromedriver().setup();
        logger.info("ChromeDriver настроен.");
    }

    @BeforeEach
    @Step("Настройка и открытие браузера")
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
    @Step("Закрытие браузера и создание скриншота")
    void teardown(TestInfo testInfo) {
        if (driver != null) {
            captureScreenshot(testInfo.getDisplayName());
            driver.quit();
            logger.info("Драйвер закрыт.");
        }
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] captureScreenshot(String testName) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            logger.error("Не удалось сделать скриншот: {}", e.getMessage());
            return null;
        }
    }

    @Test
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    @Description("Проверяет, что название блока 'Онлайн пополнение без комиссии' соответствует ожидаемому значению.")
    @Step("Проверка названия блока")
    void testBlockTitle() {
        String expectedTitle = "Онлайн пополнение без комиссии";
        String actualTitle = homePage.getOnlineRechargeBlockTitle();
        assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому.");
        logger.info("Проверка заголовка блока: Ожидалось '{}', получено '{}'", expectedTitle, actualTitle);
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    @Description("Проверяет, что на странице присутствуют логотипы платежных систем.")
    @Step("Проверка логотипов платежных систем")
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
    @Description("Проверяет, что ссылка 'Подробнее о сервисе' ведет на правильную страницу.")
    @Step("Клик по ссылке 'Подробнее о сервисе' и проверка URL")
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
    @Description("Проверяет placeholder тексты в полях ввода для разных типов оплаты.")
    @Step("Проверка placeholder текстов")
    void testPlaceholderTexts() {
        assertEquals("Номер телефона", homePage.getUslugiSvyaziPhoneNumberPlaceholder(), "Неверный placeholder для 'Услуги связи'");
        assertEquals("Номер договора", homePage.getDomashniyInternetContractPlaceholder(), "Неверный placeholder для 'Домашний интернет'");
        assertEquals("Номер договора", homePage.getRassrochkaContractPlaceholder(), "Неверный placeholder для 'Рассрочка'");
        assertEquals("Номер лицевого счёта", homePage.getZadolzhennostAccountNumberPlaceholder(), "Неверный placeholder для 'Задолженность'");
    }

    @Test
    @DisplayName("Проверка работы кнопки 'Продолжить' и данных на странице оплаты")
    @Description("Проверяет переход на страницу оплаты и отображение основных элементов.")
    @Step("Заполнение формы и проверка перехода на страницу оплаты")
    void testContinueButtonAndPaymentData() {
        homePage.selectServiceType();
        homePage.enterPhoneNumber("297777777");
        homePage.clickContinueButton();

        // **Ожидаем загрузку страницы оплаты**
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(), 'Сумма пополнения')]")));
        logger.info("Страница оплаты загружена.");

        // Проверки на странице оплаты
        assertNotNull(paymentPage.getPaymentAmountTitle(), "Не отображается заголовок 'Сумма пополнения'");
        assertEquals("+375297777777", paymentPage.getPhoneNumberDisplay(), "Неверный номер телефона");
        assertEquals("Сумма пополнения", paymentPage.getAmountFieldPlaceholder(), "Неверный placeholder для суммы пополнения");
        assertEquals("Номер карты", paymentPage.getCardNumberPlaceholder(), "Неверный placeholder для номера карты");
        assertEquals("ММ / ГГ", paymentPage.getCardExpiryPlaceholder(), "Неверный placeholder для срока действия карты");
        assertEquals("CVC / CVV", paymentPage.getCardCvvPlaceholder(), "Неверный placeholder для CVV/CVC");
        assertNotNull(paymentPage.getPaymentSystemIcons(), "Не отображаются иконки платежных систем");

        logger.info("Проверки на странице оплаты выполнены.");
    }
}