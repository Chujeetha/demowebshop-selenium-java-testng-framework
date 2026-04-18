package com.demowebshop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    @FindBy(className = "product-name")
    private List<WebElement> cartItems;

    @FindBy(id = "termsofservice")
    private WebElement termsCheckbox;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductInCart(String productName) {
        return cartItems.stream()
                .anyMatch(item -> getText(item).equalsIgnoreCase(productName));
    }

    public CheckoutPage proceedToCheckout() {
        click(termsCheckbox);
        click(checkoutButton);
        return new CheckoutPage(driver);
    }
}
