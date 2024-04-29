package SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	public CheckoutPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement Selectcountry;
	@FindBy(css=".action__submit")
	WebElement Submit;
	By Byresult=By.cssSelector(".ta-results");
	public void Selectcountry(String CountryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, CountryName).build().perform();
		visibilityOfElementLocated(Byresult);
		Selectcountry.click();
		
	}
	
	public ConfirmationPage SubmitOrder() {
		Submit.click();
		return new ConfirmationPage(driver);
	}
	
	
}
