package rahulshettyacademy.stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImp extends BaseTest {
	
	public LandingPage landingpage;
	public ProductCatalogue productcatalogue;
	public CartPage cartpage;
	public CheckoutPage checkoutpage;
	public ConfirmationPage confirmationpage;
	
	@Given("I landed on ecommerce website")
	public void I_landed_on_ecommerce_website() throws IOException {
		System.out.println("Start Browser");
		landingpage = launchApplication();
	}

	
	@Given("^(.+) and (.+) to login ecommerce website")
	public void Logged_in_with_username_and_password(String username, String password) {
		productcatalogue = landingpage.loginApplication(username, password);
	}
	
	@When("^login using (.+) and (.+) ecommerce website")
	public void login_using_ecommerce_website(String username, String password) {
		productcatalogue = landingpage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to cart")
	public void I_add_product_to_the_cart(String productname) throws InterruptedException {
		List<WebElement> product = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productname);
	}
	
	@And("^checkout (.+) and submit order")
	public void  checkout_and_submit_order(String productname) {
		cartpage = productcatalogue.goToCart();
		Boolean match = cartpage.verifyProductDisplay(productname);
		Assert.assertTrue(match);
		checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		confirmationpage = checkoutpage.submitOrder();
	}
	
	@Then("verify {string} message is displayed on Confirmation page")
	public void message_is_displayed_on_Confirmation_page(String string) {
		String confirmMessge = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessge.equalsIgnoreCase(string));
		tearDown();
	}
	
	@Then ("{string} message is displayed")
	public void message_is_displayed(String string) {
		String errMsg = landingpage.getErrorMessage();
		Assert.assertTrue(errMsg.equalsIgnoreCase(string));
		tearDown();
	}

}
