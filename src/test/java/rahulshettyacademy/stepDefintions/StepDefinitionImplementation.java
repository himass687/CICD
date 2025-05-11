package rahulshettyacademy.stepDefintions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import rahulshettyacademy.PageObject.CartPage;
import rahulshettyacademy.PageObject.CheckoutPage;
import rahulshettyacademy.PageObject.ConfirmationPage;
import rahulshettyacademy.PageObject.LandingPage;
import rahulshettyacademy.PageObject.ProductCatalogue;
import rahuleshettyacademy.TestComponents.BaseTest;

public class StepDefinitionImplementation extends BaseTest {

    public ProductCatalogue productCatalogue;
    public LandingPage landingPage;
    public CartPage cartPage;
    public ConfirmationPage confirmationPage;
    public CheckoutPage checkoutPage;

    @Given("I landed on E-commerce page")
    public void i_landed_on_e_commerce_page() throws IOException {
        landingPage = lunchApplication();
    }

    @Given("^Logged in with userName (.+) and password (.+)$")
    public void logged_in_with_user_name_and_password(String userName, String password) {
        productCatalogue = landingPage.loginApplication(userName, password);
    }

    @When("^I add product (.+) to cart$")
    public void i_add_product_to_cart(String productName) {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit order$")
    public void checkout_and_submit_order(String productName) {
        cartPage = productCatalogue.goToCartPage();
        boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.selectCountry("India");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on confirmationPage")
    public void message_is_displayed_on_confirmation_page(String message) {
        String confirmMessage = confirmationPage.getConfirmMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
        driver.close();
    }
    @Then("\"Incorrect email or password.?\" message is displayed")
    public void incorrect_email_or_password_message_is_displayed() {
    	confirmationPage = new ConfirmationPage(driver); // Ensure it's initialized
    	String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Incorrect email or password.?"));
		driver.close();
    }
}
