package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement usrEmail = driver.findElement(By.id("userEmail"));
	//WebElement paswrd = driver.findElement(By.id("userPassword"));
	//By.cssSelector(".ngx-pagination");
	
	@FindBy(css=".mb-3")
	List<WebElement> prdCtlg;
	@FindBy(css="[aria-label='Product Added To Cart']")
	WebElement cartMsg;
	
	By pagination = By.cssSelector(".ngx-pagination");
	By addPrdctCart = By.cssSelector(".card-body button:last-of-type");
	By toastMsg = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(pagination);
		return prdCtlg;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prdct = getProductList().stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prdct;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addPrdctCart).click();
		waitForElementToAppear(toastMsg);
		waitForElementToDisappear(cartMsg);
	}

}
