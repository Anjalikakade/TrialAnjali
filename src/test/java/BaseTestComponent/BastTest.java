package BaseTestComponent;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Framework.LandingLogin;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BastTest 
{
	public WebDriver driver;
	public LandingLogin loginpage;
	
	public WebDriver InializeDriver() throws IOException
	{
		Properties pro = new Properties();
		
		//FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"src\\main\\java\\Resources\\GlobalData.properties");
		FileInputStream fis = new FileInputStream("C:\\Users\\AnjaliKakade\\eclipse-workspace\\Framework\\src\\main\\java\\Resources\\GlobalData.properties");
		pro.load(fis);
		
		//****String browsername = pro.getProperty("browser"); // to read value from the global properties *********
		//---------to read parameter value from the command terminal ------------
		String browsername = System.getProperty("browser")!=null ? System.getProperty("browser") : pro.getProperty("browser"); // Java ternary operation 
		
		if(browsername.contains("chrome"))
		{
			ChromeOptions option = new ChromeOptions();    /// if we want to run in headless mode
			WebDriverManager.chromedriver().setup();
			if(browsername.contains("headless"))    
			{
				option.addArguments("headless");
			}
			driver = new ChromeDriver(option);
			
			driver.manage().window().setSize(new Dimension(1440, 900));
		}
		else if(browsername.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
			//code for edge browser
		}
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		return driver;
	}
	
	@BeforeMethod(alwaysRun =true)
	public LandingLogin LounchApplication() throws IOException
	{
		driver =InializeDriver();
		//System.out.println("Driver Life :" + driver);
		loginpage = new LandingLogin(driver);
		loginpage.goToLink();
		return loginpage;
		
	}
	
	@AfterMethod(alwaysRun= true)
	public void CloseBrowser()
	{
		driver.close();
	}

	//read value from JSON 
	public List<HashMap<String, String>> getJSONData(String filepath) throws IOException
	{
		//read JSON data in to string
		//C:\Users\0040LP744\Documents\Eclipse Selenium\FrameworkConcepts\src\test\java\Data\ReadJSONdata.java
		String json =FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		//convert string intoHasgmap: Jackson databind will use to convert String on to hashmap
		//Add dependance jackson databind and create object of ObjectMapper class
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(json, new TypeReference <List<HashMap<String, String>>>() {
		});
		return data;
	}
	
	//screenshot
	
	public String getScreenshot(String testcaseName,WebDriver driver ) throws IOException
	{
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File path = new File("C:\\Users\\AnjaliKakade\\eclipse-workspace\\Framework\\reports" + testcaseName + ".png");
		FileUtils.copyFile(source, path);
		return "C:\\Users\\AnjaliKakade\\eclipse-workspace\\Framework\\reports" + testcaseName + ".png";
	}
}
