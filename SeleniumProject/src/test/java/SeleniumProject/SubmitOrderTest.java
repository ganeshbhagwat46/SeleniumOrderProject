package SeleniumProject;


import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponent.BaseTest;


public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	String CountryName="India";
@Test(dataProvider = "getdata",groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException{
		// TODO Auto-generated method stub

		LandingPage landingPage=initialization();
		ProductCatalouge productcatalog=landingPage.LoginApplication(input.get("username"), input.get("password"));

		//List<WebElement> products= productcatalog.getProductList();
		productcatalog.addProductstoCart(productName);
		CartPage cartproduct=productcatalog.Clickoncart();

		Boolean match=cartproduct.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartproduct.goTOcheckout();
		
		checkoutPage.Selectcountry(CountryName);
		ConfirmationPage confirmationPage=checkoutPage.SubmitOrder();
		
		String confirmMessage = confirmationPage.getconfrimmessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

@Test(dependsOnMethods = {"submitOrder"})
public void Ordersummary() {
	ProductCatalouge productcatalog=landingPage.LoginApplication("Testing.abc@gmail.com", "Test@123");
	OrderPage orderpage=productcatalog.clickonOrder();
	Boolean match=orderpage.VerifyOrderDisplay(productName);
	Assert.assertTrue(match);
			
}

@DataProvider(name = "getdata")
public Object[][] getdata() throws IOException {
    HashMap<String, String> data1 = new HashMap<String, String>();
    data1.put("username", "Testing.abc@gmail.com");
    data1.put("password", "Test@123");

    HashMap<String, String> data2 = new HashMap<String, String>();
    data2.put("username", "anshika@gmail.com");
    data2.put("password", "Iamking@000");
    return new Object[][] { {data1}, {data2} };
    
    //json file
//	  List<HashMap<String, String>> data=getmap(System.getProperty("user.dir")+"//src//test//java//Data//PurchaseOrder.json");
//    return new Object[][]{{data.get(0)}, {data.get(1)}};
   
}


}



