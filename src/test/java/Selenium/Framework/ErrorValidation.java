package Selenium.Framework;


import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTestComponent.BastTest;
import Framework.CartPage;

import Framework.ProductList;


public class ErrorValidation extends BastTest
{
	String Item ="ZARA COAT 3";
	@Test(groups = {"ErrorValidation"},retryAnalyzer = BaseTestComponent.Retry.class)
	public  void LoginErrorValidation() 
	{
	
		loginpage.LoginApplication("anjalikakade@gmail.com", "Anjali@123786");
		System.out.println(loginpage.getErrorMessage());
		Assert.assertEquals("Incorrect email password.", loginpage.getErrorMessage());
		// to select item
		
		
	}
	
	@Test
	public void VerifyProductAdded()
	{
		ProductList product=loginpage.LoginApplication("anjalikakade@gmail.com", "Anjali@123");
		// to select item
		product.selectItemByName(Item);
		product.AddToCart(Item);
		// click on cart section
		CartPage cart=product.ClickCartSection();
		boolean match = cart.verifyProductaddedinCartSection(Item);
		Assert.assertTrue(match);
		System.out.println("Expcted Item Added:"+Item +" "+ match);
		cart.clickCheckoutButton();
	}
	
	
	
	

}
