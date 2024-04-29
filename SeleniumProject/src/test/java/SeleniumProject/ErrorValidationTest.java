package SeleniumProject;



import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponent.BaseTest;
import TestComponent.IRetry;


public class ErrorValidationTest extends BaseTest {
@Test(groups = {"Errorhandling"})
	public void loginerrorvalidation() throws  InterruptedException, IOException{
		// TODO Auto-generated method stub

		//String productName = "ZARA COAT 3";
		LandingPage landingPage=initialization();
		landingPage.LoginApplication("Testing.abc@gmail.com", "Test123");
	
		Assert.assertEquals("Incorrect email or password.", landingPage.geterrorLoginmsg());

	}

@Test(retryAnalyzer = IRetry.class)
public void submitOrder() throws IOException, InterruptedException{
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";
		LandingPage landingPage=initialization();
		ProductCatalouge productcatalog=landingPage.LoginApplication("Testing.abc@gmail.com", "Test@123");

		//List<WebElement> products= productcatalog.getProductList();
		productcatalog.addProductstoCart(productName);
		CartPage cartproduct=productcatalog.Clickoncart();

		Boolean match=cartproduct.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}


}
