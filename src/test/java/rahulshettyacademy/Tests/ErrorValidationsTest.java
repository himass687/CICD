package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahuleshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.PageObject.CartPage;
import rahulshettyacademy.PageObject.CheckoutPage;
import rahulshettyacademy.PageObject.ConfirmationPage;
import rahulshettyacademy.PageObject.LandingPage;
import rahulshettyacademy.PageObject.ProductCatalogue;
import rahuleshettyacademy.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups= {"ErrorVaidation"},retryAnalyzer =Retry.class)
	public void loginErrorValidation() throws IOException {

		LandingPage landingPage = lunchApplication();
		System.out.println("this method is belogs to error validation method");
		landingPage.loginApplication("himass68@gmail.com", "@Masthan786@");
		Assert.assertEquals("Incorrect email or password.?", landingPage.getErrorMessage());	

	}
	
	@Test
	public void productErrorValidation() throws IOException {

		String productName = "ZARA COAT 3";
		LandingPage landingPage = lunchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication("himass687@gmail.com", "Masthan786@");
		System.out.println("I am going to to hhandle dirrrent test cases");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
		

	}



}
