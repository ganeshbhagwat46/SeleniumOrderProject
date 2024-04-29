package SeleniumProject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class ProductCatalouge extends AbstractComponent {
	WebDriver driver;
	public ProductCatalouge(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	By Productele=By.cssSelector(".mb-3");
	By addtocart=By.cssSelector(".card-body button:last-of-type");
	By waittoscreen=By.cssSelector("#toast-container");

	@FindBy(css=".ng-animating")
	WebElement invisibleElement;

	public List<WebElement> getProductList() throws InterruptedException {	
		visibilityOfElementLocated(Productele);
		return products;
	}

	public WebElement getProductsByName(String productName) throws InterruptedException {
		WebElement prod =getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
		
		
	}

	public void addProductstoCart(String productName) throws InterruptedException {
		WebElement	prod=getProductsByName(productName);
		prod.findElement(addtocart).click();
		visibilityOfElementLocated(waittoscreen);
		invisibilityOfElementLocated(invisibleElement);

	}
}


