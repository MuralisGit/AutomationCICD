package rahulshettyacademy.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidation extends BaseTest {
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() {
		landingpage.loginApplication("anshika@gmail.com", "Iamking");
		Assert.assertEquals(landingpage.getErrorMessage(),"Incorrect email or password.");
	}
	
	@Test
	public void productErrorValidation() throws InterruptedException {
		String productName="ZARA COAT 3";
		ProductCatalogue prdCtlg = landingpage.loginApplication("anshika@gmail.com", "Iamking@000");
		prdCtlg.addProductToCart(productName);
		CartPage cartpg = prdCtlg.goToCart();
		boolean match = cartpg.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
