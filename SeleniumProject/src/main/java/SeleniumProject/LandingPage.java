package SeleniumProject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	public LandingPage(WebDriver driver){
		super(driver);
		this .driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userName;
	@FindBy(id="userPassword")
	WebElement userPassword;
	@FindBy(id="login")
	WebElement Signin;
	@FindBy(css="[class*='flyInOut']")
	WebElement erroMessage;



public ProductCatalouge LoginApplication(String Username, String Password) {
	userName.sendKeys(Username);
	userPassword.sendKeys(Password);
	Signin.click();
	ProductCatalouge productcatalog=new ProductCatalouge(driver);
	return productcatalog;
}

public String geterrorLoginmsg() {
	visibilityOfElementLocatedElement(erroMessage);
	return erroMessage.getText();
}

public void goTO() {
	driver.get("https://rahulshettyacademy.com/client");
}
}
