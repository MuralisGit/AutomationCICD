package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrder extends BaseTest {
	
	//public static void main(String[] args) throws InterruptedException
	String productName="ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	//public void submitOrder(String email, String paswd, String prdName) throws InterruptedException, IOException 
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException{
		
//		WebDriverManager.chromedriver().setup();
//		ChromeOptions option = new ChromeOptions();
//		option.addArguments("-remote-allow-origins=*");
//		option.addArguments("--disable-notifications");
//		option.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
//		WebDriver driver = new ChromeDriver(option);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();
		
//		driver.get("https://rahulshettyacademy.com/client");
		//LandingPage landingpage = new LandingPage(driver);
		//landingpage.goTo();
		//LandingPage landingpage = launchApplication();
		
//		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
//		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
//		driver.findElement(By.id("login")).click();
		ProductCatalogue prdCtlg = landingpage.loginApplication(input.get("email"), input.get("paswd"));
		
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(".ngx-pagination"))));
//		List<WebElement> products = driver.findElements(By.cssSelector(".card"));
		//ProductCatalogue prdCtlg = new ProductCatalogue(driver);
		//List<WebElement> products = prdCtlg.getProductList();
		
//		WebElement prod = products.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
//		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//		WebElement prod = prdCtlg.getProductByName(productName);
		prdCtlg.addProductToCart(input.get("prdName"));
		CartPage cartpg = prdCtlg.goToCart();
		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("[aria-label='Product Added To Cart']"))));
//		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
//		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
//		Boolean match = cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		//CartPage cartpg = new CartPage(driver);
		boolean match = cartpg.verifyProductDisplay(input.get("prdName"));
		Assert.assertTrue(match);

//		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		CheckoutPage chkoutpage = cartpg.goToCheckout();
		
//		Actions action = new Actions(driver);
//		WebElement country = driver.findElement(By.cssSelector("[placeholder='Select Country']"));
//		action.sendKeys(country,"india").build().perform();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group.ng-star-inserted")));
//		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
//		driver.findElement(By.cssSelector(".action__submit")).click();		
		//CheckoutPage chkoutpage = new CheckoutPage(driver);
		chkoutpage.selectCountry("india");
		ConfirmationPage confirmpage = chkoutpage.submitOrder();
		
//		Actions action = new Actions(driver);
//		WebElement country = driver.findElement(By.cssSelector("[placeholder='Select Country']"));
//		action.sendKeys(country,"india").build().perform();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group.ng-star-inserted")));
//		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
//		driver.findElement(By.cssSelector(".action__submit")).click();	
//		String ordrMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		//ConfirmationPage confirmpage = new ConfirmationPage(driver);
		String ordrMsg = confirmpage.getConfirmationMessage();
		Assert.assertTrue(ordrMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue prdCtlg = landingpage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage orderspage = prdCtlg.gotoOrderPage();
		Assert.assertTrue(orderspage.verifyOrderHistory(productName));
	}
	
	@DataProvider
	//public Object[][] getData() 
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map = new HashMap<String,String>();
//		map.put("email", "anshika@gmail.com");
//		map.put("paswd", "Iamking@000");
//		map.put("prdName", "ZARA COAT 3");
//		HashMap<String, String> map1 = new HashMap<String,String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("paswd", "Iamking@000");
//		map1.put("prdName", "ADIDAS ORIGINAL");
//		return new Object[][] {{map},{map1}};
		//return new Object[][] {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
		List<HashMap<String,String>> data = getJsonDatatoMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
}
