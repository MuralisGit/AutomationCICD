package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement usrEmail = driver.findElement(By.id("userEmail"));
	//WebElement paswrd = driver.findElement(By.id("userPassword"));
	//By.cssSelector(".ngx-pagination");
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement chkout;
	
	public boolean verifyProductDisplay(String productName) {
		boolean match = cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		chkout.click();
		CheckoutPage chkoutpage = new CheckoutPage(driver);
		return chkoutpage;
	}

}
