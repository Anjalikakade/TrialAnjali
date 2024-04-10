package Selenium.Framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;



public class ExtendReport 

{
	ExtentReports extent;
	@BeforeTest
	public void config()
	{
		//create object of extendReports and extendsparklereporter class
		String path = "C:\\Users\\AnjaliKakade\\eclipse-workspace\\Framework\\reports\\\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Selenium Automation");
		reporter.config().setReportName("Demo");
		
		 extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Anjali");
	}
	@Test
	public void Demo()
	{
		ExtentTest test =extent.createTest("Initial Demo");
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		driver.close();
		extent.flush();


	}
}
