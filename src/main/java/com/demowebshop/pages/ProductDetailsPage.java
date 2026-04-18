package com.demowebshop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage {

    @FindBy(className = "add-to-cart-button")
    private WebElement addToCartButton;

    @FindBy(id = "bar-notification")
    private WebElement barNotification;

    @FindBy(css = ".product-name h1")
    private WebElement productName;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return getText(productName);
    }

    public void addToCart() {
        // Simple iteration ignoring complex configurable parameters for standard items
        click(addToCartButton);
    }

    public boolean isNotificationDisplayed() {
        return isElementDisplayed(barNotification);
    }
}
