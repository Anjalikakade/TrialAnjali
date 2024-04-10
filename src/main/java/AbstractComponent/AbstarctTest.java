package AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.CartPage;
import Framework.OrderPageValidation;

public class AbstarctTest 
{

	WebDriver driver;

	
	public AbstarctTest(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	@FindBy(css="[routerlink*='cart']")
	WebElement clickHomeCart;
	@FindBy(css="[routerlink*='myorders']")
	WebElement Ordersection;
	
	public void WaitTillElementDisAppear(By Findby)
	{
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOfElementLocated(Findby));
	}
	public void WaitTillElementAppear(By Findby)
	{
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Findby));
	}
	//wait till WebElement appear
	public void waitTillAppearElementbyweb(WebElement findby)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}
	public CartPage ClickCartSection()
	{
		clickHomeCart.click();
		CartPage cart = new CartPage(driver);
		return cart;
	}
	public void goToLink()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	//click on OrderSection
	public OrderPageValidation gotoOrderSection()
	{
		Ordersection.click();
		OrderPageValidation orderpage = new OrderPageValidation(driver);
		return orderpage;
	}
	
	
}
