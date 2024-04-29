package TestComponent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumProject.LandingPage;
import TestComponent.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//Resources//Globalparameter.properties");
		prop.load(fis);
		String browserName=prop.getProperty("browser");
		if (browserName == null) {
            throw new IllegalArgumentException("Browser name is not provided in Globalparameter.properties");
        }

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name provided in Globalparameter.properties");
        }
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	public List<HashMap<String, String>> getmap(String filepath) throws IOException {
		String jsonContent = 	FileUtils.readFileToString(new File(filepath), 
				StandardCharsets.UTF_8);
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
    });
	  return data;
}
	
	public String takeScrenshot(String testcaseName,WebDriver driver) throws IOException {
		File Sorece=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File Dest= new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(Sorece, Dest);
		return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage initialization() throws IOException {
		driver =initializeDriver();
		 landingPage = new LandingPage(driver);
		 landingPage.goTO();
		return landingPage;
	}
	
	@AfterMethod
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
}