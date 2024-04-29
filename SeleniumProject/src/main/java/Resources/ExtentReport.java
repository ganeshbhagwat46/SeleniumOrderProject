package Resources;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	public static ExtentReports generatereport() {
	String path=System.getProperty("user.dir")+"//reports//Extent.html";
 ExtentSparkReporter spark=new ExtentSparkReporter(path);
 spark.config().setReportName("Orderwebreport");
 spark.config().setDocumentTitle("Seleniumrreport");
 
 ExtentReports ext=new ExtentReports();
 ext.attachReporter(spark);
 
 ext.setSystemInfo("Tester","Ganesh");

 
 return ext;
}
}