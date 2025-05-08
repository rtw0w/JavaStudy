import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MtsByTest {
    private static WebDriver driver;
    private final String BASE_URL = "https://www.mts.by/";
    private HomePage homePage;
    private PaymentPage paymentPage;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterAll
    void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    void testBlockTitle() {
        assertEquals("Онлайн пополнение без комиссии", homePage.getOnlineRechargeBlockTitle(), "Название блока не соответствует ожидаемому.");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    void testPaymentLogos() {
        List<WebElement> logos = homePage.getPaymentSystemsContainer().findElements(org.openqa.selenium.By.tagName("img"));
        assertTrue(logos.size() > 0, "Логотипы платежных систем не найдены.");
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    void testServiceDetailsLink() {
        homePage.clickServiceDetailsLink();
        assertTrue(driver.getCurrentUrl().contains("oplata-online"), "URL не содержит 'oplata-online'");
    }

    @Test
    @DisplayName("Проверка надписей в незаполненных полях разных вариантов оплаты услуг")
    void testPlaceholderTexts() {
        assertEquals("Номер телефона", homePage.getUslugiSvyaziPhoneNumberPlaceholder(), "Неверный placeholder для 'Услуги связи'");
        assertEquals("Номер телефона", homePage.getDomashniyInternetContractPlaceholder(), "Неверный placeholder для 'Домашний интернет'");
        assertEquals("Номер договора", homePage.getRassrochkaContractPlaceholder(), "Неверный placeholder для 'Рассрочка'");
        assertEquals("Номер лицевого счёта", homePage.getZadolzhennostAccountNumberPlaceholder(), "Неверный placeholder для 'Задолженность'");
    }

    @Test
    @DisplayName("Проверка работы кнопки 'Продолжить' и данных на странице оплаты")
    void testContinueButtonAndPaymentData() {
        homePage.selectServiceType();
        homePage.enterPhoneNumber("297777777");
        homePage.clickContinueButton();

        assertNotNull(paymentPage.getPaymentAmountTitle(), "Не отображается заголовок 'Сумма пополнения'");
        assertEquals("+375297777777", paymentPage.getPhoneNumberDisplay(), "Неверный номер телефона"); //Номера могут отличаться от сайта
        assertEquals("Сумма пополнения", paymentPage.getAmountFieldPlaceholder(), "Неверный placeholder для суммы пополнения");
        assertEquals("Номер карты", paymentPage.getCardNumberPlaceholder(), "Неверный placeholder для номера карты");
        assertEquals("ММ / ГГ", paymentPage.getCardExpiryPlaceholder(), "Неверный placeholder для срока действия карты");
        assertEquals("CVC / CVV", paymentPage.getCardCvvPlaceholder(), "Неверный placeholder для CVV/CVC");
        assertNotNull(paymentPage.getPaymentSystemIcons(), "Не отображаются иконки платежных систем");

    }
}
