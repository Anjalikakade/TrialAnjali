package Selenium.Framework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Framework.LandingLogin;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest 
{
	public static void main(String[] args) 
	{
		String Item ="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.findElement(By.id("userEmail")).sendKeys("anjalikakade@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Anjali@123");
		driver.findElement(By.xpath("//input[@type='submit' ][@id='login'] ")).click();
	
		
		// to select item
		List<WebElement> productList =driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement product =productList.stream().
		filter(p->p.findElement(By.cssSelector(".card-body h5")).getText().equalsIgnoreCase(Item)).findFirst().orElse(null);
		// click on add to cart button
		 driver.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		 //explicit wait for to load msg 
		 WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".toast-message")));
		
		// click on cart section
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//check item present in cart section
		List<WebElement> cartList=driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartList.stream().anyMatch(p->p.getText().equalsIgnoreCase(Item));
		Assert.assertTrue(match);
		System.out.println(match);
		//click on checkout option
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//scroll  page
		js.executeScript("window.scrollBy(0,500)","");
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click(); ////button[text()='Checkout']
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		List<WebElement> country=driver.findElements(By.cssSelector(".ta-item span"));
		country.stream().filter(p-> p.getText().equalsIgnoreCase("India"));
		driver.findElement(By.xpath("(//button[contains(@class,'list-group')])[2]")).click();
		//js.executeScript("window.scrollBy(0,500)","");
		driver.findElement(By.cssSelector(".action__submit")).click();
		String msdriver=driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(msdriver);
		Assert.assertTrue(msdriver.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	

}
