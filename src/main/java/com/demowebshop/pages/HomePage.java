package com.demowebshop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(className = "ico-login")
    private WebElement loginLink;

    @FindBy(className = "ico-logout")
    private WebElement logoutLink;

    @FindBy(className = "account")
    private WebElement userAccountLink;

    @FindBy(id = "small-searchterms")
    private WebElement searchBox;

    @FindBy(className = "search-box-button")
    private WebElement searchButton;
    
    @FindBy(className = "ico-cart")
    private WebElement shoppingCartLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage navigateToLogin() {
        click(loginLink);
        return new LoginPage(driver);
    }

    public SearchResultsPage searchForProduct(String productIdentifier) {
        type(searchBox, productIdentifier);
        click(searchButton);
        return new SearchResultsPage(driver);
    }
    
    public ShoppingCartPage navigateToCart() {
        click(shoppingCartLink);
        return new ShoppingCartPage(driver);
    }

    public boolean isUserAuthenticated(String username) {
        return isElementDisplayed(userAccountLink) && getText(userAccountLink).equals(username);
    }
    
    public void logout() {
        click(logoutLink);
    }
}
