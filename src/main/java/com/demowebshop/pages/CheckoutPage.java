package com.demowebshop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(css = "#billing-buttons-container .new-address-next-step-button")
    private WebElement billingContinueBtn;

    @FindBy(css = "#shipping-buttons-container .new-address-next-step-button")
    private WebElement shippingContinueBtn;

    @FindBy(css = ".shipping-method-next-step-button")
    private WebElement shippingMethodContinueBtn;

    @FindBy(css = ".payment-method-next-step-button")
    private WebElement paymentMethodContinueBtn;

    @FindBy(css = ".payment-info-next-step-button")
    private WebElement paymentInfoContinueBtn;

    @FindBy(css = ".confirm-order-next-step-button")
    private WebElement confirmOrderBtn;

    @FindBy(xpath = "//strong[contains(text(), 'Your order has been successfully processed!')]")
    private WebElement orderCompletedTitle;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void processCheckoutFlow() {
        // Sequentially collapse accordion menus for pre-saved dummy info
        click(billingContinueBtn);
        click(shippingContinueBtn);
        click(shippingMethodContinueBtn);
        click(paymentMethodContinueBtn);
        click(paymentInfoContinueBtn);
        click(confirmOrderBtn);
    }

    public boolean isOrderConfirmationDisplayed() {
        return isElementDisplayed(orderCompletedTitle) && 
               getText(orderCompletedTitle).contains("Your order has been successfully processed!");
    }
}
