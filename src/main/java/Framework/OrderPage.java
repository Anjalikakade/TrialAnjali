package Framework;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstarctTest;

public class OrderPage extends AbstarctTest
{
	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary ")
	WebElement orderpagetitle;

	
	public String getOrderPageTitle() 
	{
		String title = orderpagetitle.getText();
		return title;
	}
	

}
