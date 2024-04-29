package AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumProject.CartPage;
import SeleniumProject.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement Cartbutton;
	
	@FindBy(css="[routerlink*='order']")
	WebElement Orderbutton;

	public void visibilityOfElementLocated(By FindBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}

	public void visibilityOfElementLocatedElement(WebElement FindByEle) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(FindByEle));
	}

	public void invisibilityOfElementLocated(WebElement FindByEle) throws InterruptedException {
		//wait.until(ExpectedConditions.invisibilityOf(FindByEle));
		Thread.sleep(3000);
	}

	public CartPage Clickoncart()
	{
		Cartbutton.click();
		return new CartPage(driver);
	}
	public OrderPage clickonOrder()
	{
		Orderbutton.click();
		return new OrderPage(driver);
	}
}
