package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	public WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement usrEmail = driver.findElement(By.id("userEmail"));
	//WebElement paswrd = driver.findElement(By.id("userPassword"));
	
	@FindBy(id="userEmail")
	WebElement usrEmail;
	@FindBy(id="userPassword")
	WebElement paswrd;
	@FindBy(id="login")
	WebElement submitBtn;
	@FindBy(css="[class*='ng-trigger-flyInOut']")
	WebElement errorMsg;
	By errMsg = By.cssSelector("[class*='ng-trigger-flyInOut']");
	
	
	public ProductCatalogue loginApplication(String email, String pswd) {
		
		usrEmail.sendKeys(email);
		paswrd.sendKeys(pswd);
		submitBtn.click();
		ProductCatalogue prdCtlg = new ProductCatalogue(driver);
		return prdCtlg;
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		//waitForElementToAppear(errMsg);
		waitForWebElementToAppear(errorMsg);
		//return driver.findElement(errMsg).getText();
		return errorMsg.getText();
	}

}
