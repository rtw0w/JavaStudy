package org;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {
    private final WebDriver driver;
    private final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    @FindBy(xpath = "//h3[contains(text(), 'Сумма пополнения')]")
    private WebElement paymentAmountTitle;

    @FindBy(xpath = "//button[contains(text(), 'Оплатить')]") // Может меняться текст кнопки
    private WebElement payButton;
    private final By phoneNumberDisplay = By.xpath("//div[contains(text(), '+375297777777')]");
    private final By amountFieldPlaceholder = By.xpath("//input[@name='amount' and @placeholder='Сумма пополнения']");
    private final By cardNumberPlaceholder = By.xpath("//input[@name='pan' and @placeholder='Номер карты']");
    private final By cardExpiryPlaceholder = By.xpath("//input[@name='expiry' and @placeholder='ММ / ГГ']");
    private final By cardCvvPlaceholder = By.xpath("//input[@name='cvv' and @placeholder='CVC / CVV']");
    private final By paymentSystemIcons = By.cssSelector(".payment-card-icons");

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPaymentAmountTitle() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(paymentAmountTitle));
        return paymentAmountTitle.getText();
    }

    public String getPhoneNumberDisplay() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(phoneNumberDisplay));
        return driver.findElement(phoneNumberDisplay).getText();
    }

    public String getAmountFieldPlaceholder() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(amountFieldPlaceholder));
        return driver.findElement(amountFieldPlaceholder).getAttribute("placeholder");
    }

    public String getCardNumberPlaceholder() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(cardNumberPlaceholder));
        return driver.findElement(cardNumberPlaceholder).getAttribute("placeholder");
    }

    public String getCardExpiryPlaceholder() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(cardExpiryPlaceholder));
        return driver.findElement(cardExpiryPlaceholder).getAttribute("placeholder");
    }

    public String getCardCvvPlaceholder() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(cardCvvPlaceholder));
        return driver.findElement(cardCvvPlaceholder).getAttribute("placeholder");
    }

    public WebElement getPaymentSystemIcons() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(paymentSystemIcons));
        return driver.findElement(paymentSystemIcons);
    }
}
