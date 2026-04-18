package com.demowebshop.tests;

import com.aventstack.extentreports.Status;
import com.demowebshop.config.EnvConfig;
import com.demowebshop.pages.*;
import com.demowebshop.reporting.ExtentManager;
import com.demowebshop.utils.JSONReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class E2EPurchaseFlowTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(E2EPurchaseFlowTest.class);

    private void trackStep(String stepDescription) {
        logger.info(stepDescription);
        ExtentManager.getTest().log(Status.PASS, stepDescription);
    }

    @DataProvider(name = "purchaseMatrix")
    public Object[][] purchaseMatrix() {
        List<Map<String, String>> parsedNodes = JSONReader.getTestData("testdata.json");
        Object[][] testDataBindings = new Object[parsedNodes.size()][3];
        
        for (int i = 0; i < parsedNodes.size(); i++) {
            testDataBindings[i][0] = EnvConfig.getInstance().getUsername();
            testDataBindings[i][1] = EnvConfig.getInstance().getPassword();
            testDataBindings[i][2] = parsedNodes.get(i).get("product");
        }
        
        return testDataBindings;
    }

    @Test(description = "Validates the primary E2E purchase pipeline.", dataProvider = "purchaseMatrix", retryAnalyzer = RetryAnalyzer.class)
    public void executeEndToEndPurchase(String username, String password, String targetProduct) {
        // Parametrized variables injected via DataProvider
        // 1. Launch browser & navigate to homepage
        HomePage homePage = new HomePage(driver);
        trackStep("Step 1: Launched browser and navigated to Demo Webshop homepage.");

        // 2. Login with credentials
        LoginPage loginPage = homePage.navigateToLogin();
        homePage = loginPage.login(username, password);
        Assert.assertTrue(homePage.isUserAuthenticated(username), "Target identity provisioning verification failed!");
        trackStep("Step 2: Logged in successfully with Email: " + username + " and Password: " + password);

        // 3. Search for a product
        SearchResultsPage searchResults = homePage.searchForProduct(targetProduct);
        trackStep("Step 3: Searched for product target vector: '" + targetProduct + "'");

        // 4. Add product to the cart
        ProductDetailsPage productDetails = searchResults.selectFirstProduct();
        String selectedProductName = productDetails.getProductName();
        productDetails.addToCart();
        Assert.assertTrue(productDetails.isNotificationDisplayed(), "State mutation (Cart notification) was not detected.");
        trackStep("Step 4: Clicked product details and added item to cart.");

        // 5. Navigate to the cart and verify listed
        ShoppingCartPage cartPage = homePage.navigateToCart();
        Assert.assertTrue(cartPage.isProductInCart(selectedProductName), "Cart integrity anomaly: Item missing from Cartesian grid.");
        trackStep("Step 5: Navigated to Cart and formally asserted '" + selectedProductName + "' is actively listed.");

        // 6. Proceed to checkout
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        trackStep("Step 6: Initiated Checkout orchestration cycle.");

        // 7. & 8. & 9. Fill billing, shipping, select methods, confirm
        checkoutPage.processCheckoutFlow();
        trackStep("Step 7: Executed Dummy Billing and Shipping mapping parameters.");
        trackStep("Step 8: Authorized Shipping Methodology and standard Payment bindings.");
        trackStep("Step 9: Confirmed and finalized the authoritative order block.");

        // 10. Verify order success message
        Assert.assertTrue(checkoutPage.isOrderConfirmationDisplayed(), "Final termination state (Order Success) failed to resolve.");
        trackStep("Step 10: Assured the explicit display of ultimate success notification: 'Your order has been successfully processed!'");
        
        // 11. Logout
        try {
            homePage.logout();
            trackStep("Step 11: Invoked session termination via Logout.");
        } catch (Exception e) {
            ExtentManager.getTest().log(Status.WARNING, "Logout loop intercepted, likely state decay: " + e.getMessage());
        }
    }
}
