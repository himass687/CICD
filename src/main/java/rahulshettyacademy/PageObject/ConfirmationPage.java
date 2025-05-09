package rahulshettyacademy.PageObject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
	//Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	By confmessVisibility =By.cssSelector(".hero-primary");
	
	public String getConfirmMessage() {
		waitForElementToAppear(confmessVisibility);
		return confirmMessage.getText();
	}
	

}
