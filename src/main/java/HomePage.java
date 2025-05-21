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

public class HomePage {

    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);
    private final WebDriver driver;
    private final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    @FindBy(xpath = "//h2[contains(text(), 'Онлайн пополнение без комиссии')]")
    private WebElement onlineRechargeBlockTitle;

    @FindBy(css = ".payment-systems")
    private WebElement paymentSystemsContainer;

    @FindBy(linkText = "Подробнее о сервисе")
    private WebElement serviceDetailsLink;

    @FindBy(id = "onlPay")
    private WebElement serviceTypeDropdown;

    @FindBy(xpath = "//option[text()='Услуги связи']")
    private WebElement serviceOption;

    @FindBy(name = "msisdn")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//button[text()='Продолжить']")
    private WebElement continueButton;

    //Локаторы для проверки надписей в незаполненных полях для разных вариантов оплаты
    private final By uslugiSvyaziPhoneNumberPlaceholder = By.name("msisdn");
    private final By domashniyInternetContractPlaceholder = By.cssSelector("input[name='accountNumber'][placeholder='Номер договора']");
    private final By rassrochkaContractPlaceholder = By.cssSelector("input[name='contractNumber'][placeholder='Номер договора']");
    private final By zadolzhennostAccountNumberPlaceholder = By.cssSelector("input[name='accountNumber'][placeholder='Номер лицевого счёта']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.debug("Инициализирован HomePage");
    }

    public String getOnlineRechargeBlockTitle() {
        WebElement element = new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(onlineRechargeBlockTitle));
        logger.debug("Заголовок блока: {}", element.getText());
        return element.getText();
    }

    public WebElement getPaymentSystemsContainer() {
        WebElement element = new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".payment-systems")));
        return element;
    }

    public void clickServiceDetailsLink() {
        WebElement element = new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(serviceDetailsLink));
        serviceDetailsLink.click();
        logger.debug("Клик по ссылке 'Подробнее о сервисе'");
    }

    public void selectServiceType() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(serviceTypeDropdown)).click();

        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(serviceOption)).click();
        logger.debug("Выбран тип сервиса: Услуги связи");
    }

    public void enterPhoneNumber(String phoneNumber) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(phoneNumberField)).sendKeys(phoneNumber);
        logger.debug("Введен номер телефона: {}", phoneNumber);
    }

    public void clickContinueButton() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        logger.debug("Нажата кнопка 'Продолжить'");
    }

    public String getUslugiSvyaziPhoneNumberPlaceholder() {
        WebElement element = new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(uslugiSvyaziPhoneNumberPlaceholder));
        String attribute = element.getAttribute("placeholder");
        logger.debug("Placeholder 'Номер телефона': {}", attribute);
        return attribute;
    }

    public String getDomashniyInternetContractPlaceholder() {
        WebElement element = new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(domashniyInternetContractPlaceholder));
        String attribute = element.getAttribute("placeholder");
        logger.debug("Placeholder 'Номер договора' (Домашний интернет): {}", attribute);
        return attribute;
    }

    public String getRassrochkaContractPlaceholder() {
        WebElement element = new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(rassrochkaContractPlaceholder));
        String attribute = element.getAttribute("placeholder");
        logger.debug("Placeholder 'Номер договора' (Рассрочка): {}", attribute);
        return attribute;
    }

    public String getZadolzhennostAccountNumberPlaceholder() {
        WebElement element = new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(zadolzhennostAccountNumberPlaceholder));
        String attribute = element.getAttribute("placeholder");
        logger.debug("Placeholder 'Номер лицевого счёта' (Задолженность): {}", attribute);
        return attribute;
    }

}