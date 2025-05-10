package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahuleshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.PageObject.CartPage;
import rahulshettyacademy.PageObject.CheckoutPage;
import rahulshettyacademy.PageObject.ConfirmationPage;
import rahulshettyacademy.PageObject.LandingPage;
import rahulshettyacademy.PageObject.OrderPage;
import rahulshettyacademy.PageObject.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	//String productName = "ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
	//public void submitOrder(String email, String password, String productName) throws IOException {

		String countryName = "india";

		LandingPage landingPage = lunchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {

		ProductCatalogue productCatalogue = landingPage.loginApplication("himass687@gmail.com", "Masthan786@");

		OrderPage ordersPage = productCatalogue.goToOrderPage();
		ordersPage.verifyOrderIsDisplay("ZARA COAT 3");

	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		String filePath =System.getProperty("user.dir")
				+ "\\src\\test\\java\\rahulshettyacademy\\Data\\PurchaseOrder.json";
		List<HashMap<String, String>> data= getJsonDataToMap(filePath);
	System.out.println("Hi this is one of the get data from the jsond file");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email","himass687@gmail.com");
//		map.put("password","Masthan786@");
//		map.put("product","ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email","himass687@gmail.com");
//		map1.put("password","Masthan786@");
//		map1.put("product","ADIDAS ORIGINAL");
//return new Object[][] {{map},{map1}};
		//return new Object[][] {{"himass687@gmail.com","Masthan786@","ZARA COAT 3"},{"himass687@gmail.com","Masthan786@","ADIDAS ORIGINAL"}};
	}
	
	
}
