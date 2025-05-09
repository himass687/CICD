package rahulshettyacademy.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// List<WebElement> cartProducts
	// =driver.findElements(By.cssSelector(".cartSection h3"));

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	public Boolean verifyProductDisplay(String productName) {

		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;

	}

	// driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	public CheckoutPage goToCheckoutPage() {
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

}
