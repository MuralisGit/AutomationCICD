package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selCountry;
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By countryOptions = By.cssSelector(".ta-results.list-group.ng-star-inserted");
	
	public void selectCountry(String cntryName) {
		Actions action = new Actions(driver);
		action.sendKeys(country,cntryName).build().perform();
		waitForElementToAppear(countryOptions);
		selCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		ConfirmationPage confirmpage = new ConfirmationPage(driver);
		return confirmpage;
	}
	
	
}
