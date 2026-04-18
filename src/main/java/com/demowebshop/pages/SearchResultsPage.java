package com.demowebshop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(css = ".product-item .product-title a")
    private List<WebElement> productTitles;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailsPage selectFirstProduct() {
        if (!productTitles.isEmpty()) {
            click(productTitles.get(0));
            return new ProductDetailsPage(driver);
        } else {
            throw new RuntimeException("Grid search resolved 0 items.");
        }
    }
}
