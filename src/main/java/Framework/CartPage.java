package Framework;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import AbstractComponent.AbstarctTest;

public class CartPage extends AbstarctTest
{
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection")
	List<WebElement> CartPageList; 
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkoutbutton;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement inputCountry;
	@FindBy(xpath="(//button[contains(@class,'list-group')])[2]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement placeorder;
	
	By cartlist =By.cssSelector(".cartSection");
	By title=By.cssSelector(".cartSection h3");
	
	
	//return product list from cartpage
		public List<WebElement> getCartList()
		{
			WaitTillElementAppear(cartlist);
			return CartPageList;
		}

	//verify element added in cartPage
		public boolean verifyProductaddedinCartSection(String itemNeeded )
		{	
			Boolean match =getCartList().stream().
			anyMatch(p->p.findElement(title).getText().equalsIgnoreCase(itemNeeded));
			return match;
		}
	//click on checkout button 
		public void clickCheckoutButton() 
		{
			checkoutbutton.click();
			
		}
	//select counrty	
		public void selectCountry()
		{
			inputCountry.sendKeys("India");
			selectCountry.click();
		}
	//place order
	public OrderPage placeOrder()
	{

		placeorder.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}

}
