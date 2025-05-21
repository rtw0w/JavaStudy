package org;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class PaymentPage {

    private static final Logger logger = LoggerFactory.getLogger(PaymentPage.class);
    private final WebDriver driver;
    private final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    @FindBy(xpath = "//h3[contains(text(), 'Сумма пополнения')]")
    private WebElement paymentAmountTitle;

    @FindBy(xpath = "//button[contains(text(), 'Оплатить')]") // Может меняться текст кнопки
    private WebElement payButton;

    // Локаторы для проверки данных
    private final By phoneNumberDisplay = By.xpath("//div[contains(text(), '+375297777777')]");
    private final By amountFieldPlaceholder = By.cssSelector("input[name='amount'][placeholder='Сумма пополнения']");
    private final By cardNumberPlaceholder = By.cssSelector("input[name='pan'][placeholder='Номер карты']");
    private final By cardExpiryPlaceholder = By.cssSelector("input[name='expiry'][placeholder='ММ / ГГ']");
    private final By cardCvvPlaceholder = By.cssSelector("input[name='cvv'][placeholder='CVC / CVV']");
    private final By paymentSystemIcons = By.cssSelector(".payment-card-icons");

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.debug("Инициализирован PaymentPage");
    }

    public String getPaymentAmountTitle() {
        WebElement element = new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(), 'Сумма пополнения')]")));
        logger.debug("Заголовок 'Сумма пополнения': {}", element.getText());
        return element.getText();
    }

    public String getPhoneNumberDisplay() {
        WebElement element = new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(phoneNumberDisplay));
        String text = element.getText();
        logger.debug("Номер телефона на странице оплаты: {}", text);
        return text;
    }

    public String getAmountFieldPlaceholder() {
        WebElement element = new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(amountFieldPlaceholder));
        String attribute = element.getAttribute("placeholder");
        logger.debug("Placeholder поля 'Сумма пополнения': {}", attribute);
        return attribute;
    }

    public String getCardNumberPlaceholder() {
        WebElement element = new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(cardNumberPlaceholder));
        String attribute = element.getAttribute("placeholder");
        logger.debug("Placeholder поля 'Номер карты': {}", attribute);
        return attribute;
    }

    public String getCardExpiryPlaceholder() {
        WebElement element = new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(cardExpiryPlaceholder));
        String attribute = element.getAttribute("placeholder");
        logger.debug("Placeholder поля 'ММ / ГГ': {}", attribute);
        return attribute;
    }

    public String getCardCvvPlaceholder() {
        WebElement element = new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(cardCvvPlaceholder));
        String attribute = element.getAttribute("placeholder");
        logger.debug("Placeholder поля 'CVC / CVV': {}", attribute);
        return attribute;
    }

    public WebElement getPaymentSystemIcons() {
        WebElement element = new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(paymentSystemIcons));
        logger.debug("Отображаются иконки платежных систем");
        return element;
    }
}
