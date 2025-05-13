package org;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    @FindBy(xpath = "//h2[contains(text(), 'Онлайн пополнение без комиссии')]")
    private WebElement onlineRechargeBlockTitle;

    @FindBy(css = ".payment-systems")
    private WebElement paymentSystemsContainer;

    @FindBy(linkText = "Подробнее о сервисе")
    private WebElement serviceDetailsLink;

    @FindBy(xpath = "//select[@name='paymentService']")
    private WebElement serviceTypeDropdown;

    @FindBy(xpath = "//option[text()='Услуги связи']")
    private WebElement serviceOption;

    @FindBy(xpath = "//input[@name='msisdn']")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//button[text()='Продолжить']")
    private WebElement continueButton;

    //Локаторы для проверки надписей в незаполненных полях для разных вариантов оплаты
    private final By uslugiSvyaziPhoneNumberPlaceholder = By.xpath("//input[@name='msisdn' and @placeholder='Номер телефона']");
    private final By domashniyInternetContractPlaceholder = By.xpath("//input[@name='accountNumber' and @placeholder='Номер договора']");
    private final By rassrochkaContractPlaceholder = By.xpath("//input[@name='contractNumber' and @placeholder='Номер договора']");
    private final By zadolzhennostAccountNumberPlaceholder = By.xpath("//input[@name='accountNumber' and @placeholder='Номер лицевого счёта']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getOnlineRechargeBlockTitle() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(onlineRechargeBlockTitle));
        return onlineRechargeBlockTitle.getText();
    }

    public WebElement getPaymentSystemsContainer() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(paymentSystemsContainer));
        return paymentSystemsContainer;
    }

    public void clickServiceDetailsLink() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(serviceDetailsLink));
        serviceDetailsLink.click();
    }

    public void selectServiceType() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(serviceTypeDropdown));
        serviceTypeDropdown.click();

        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(serviceOption));
        serviceOption.click();
    }

    public void enterPhoneNumber(String phoneNumber) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(phoneNumberField));
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void clickContinueButton() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }

    public String getUslugiSvyaziPhoneNumberPlaceholder() {
        return driver.findElement(uslugiSvyaziPhoneNumberPlaceholder).getAttribute("placeholder");
    }

    public String getDomashniyInternetContractPlaceholder() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(domashniyInternetContractPlaceholder));
        return driver.findElement(domashniyInternetContractPlaceholder).getAttribute("placeholder");
    }

    public String getRassrochkaContractPlaceholder() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(rassrochkaContractPlaceholder));
        return driver.findElement(rassrochkaContractPlaceholder).getAttribute("placeholder");
    }

    public String getZadolzhennostAccountNumberPlaceholder() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(zadolzhennostAccountNumberPlaceholder));
        return driver.findElement(zadolzhennostAccountNumberPlaceholder).getAttribute("placeholder");
    }
}
