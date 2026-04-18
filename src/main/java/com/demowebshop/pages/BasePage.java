package com.demowebshop.pages;

import com.demowebshop.config.EnvConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Superclass abstracting Page Object behavior. Forges the PageFactory context via explicit lazy-loading hooks
 * to defend against erratic SPA rendering metrics.
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        int timeout = EnvConfig.getInstance().getDefaultTimeout();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        
        // Employs a defensive lazy instantiation schema to mitigate StaleElementReferenceException
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, timeout);
        PageFactory.initElements(factory, this);
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }
    
    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }
    
    protected void selectByVisibleText(WebElement dropdown, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
