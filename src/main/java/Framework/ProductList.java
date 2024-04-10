package Framework;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstarctTest;

public class ProductList extends AbstarctTest
{
	WebDriver driver;
	
	public ProductList(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".col-lg-4")
	List<WebElement> productList;
	
	
	By AddToCartButton  = By.cssSelector(".card-body button:last-of-type");
	By toast=By.id("toast-container");
	By toastMessage=By.id("toast-container");
	public List<WebElement> getProductList()
	{
		WaitTillElementAppear(By.cssSelector(".col-lg-4"));
		return productList;
	}
	
	public WebElement selectItemByName(String Item)
	{
		WebElement product =productList.stream().
		filter(p->p.findElement(By.cssSelector(".card-body h5")).getText().equalsIgnoreCase(Item)).findFirst().orElse(null);
		return product;
	}
	
	public void AddToCart(String Item)
	{
		WebElement product =selectItemByName(Item);
		product.findElement(AddToCartButton).click();
		WaitTillElementAppear(toast);
		WaitTillElementDisAppear(toastMessage);
	}
	

}
