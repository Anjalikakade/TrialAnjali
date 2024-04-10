package Framework;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstarctTest;

public class OrderPageValidation extends AbstarctTest
{
	WebDriver driver;
	
	public OrderPageValidation(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr  td:nth-child(3)")
	List<WebElement> OrderPageList; 
	
	//return product list from cartpage
	public List<WebElement> getOrderHistory()
	{
		//waitTillAppearElement(cartlist);
		return OrderPageList;
	}

//verify element added in cartPage
	public boolean verifyOrderHistory(String itemNeeded )
	{	
		Boolean match =getOrderHistory().stream().
		anyMatch(p->p.getText().equalsIgnoreCase(itemNeeded));
		return match;
	}

}
