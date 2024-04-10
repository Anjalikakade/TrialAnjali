package Selenium.Framework;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseTestComponent.BastTest;
import Framework.CartPage;
import Framework.OrderPage;
import Framework.OrderPageValidation;
import Framework.ProductList;


public class SubmitOrder extends BastTest
{
	String Item ="ZARA COAT 3";
	@Test(dataProvider = "getData",groups = {"SmokeTest"})
	public  void SubmitProductOrder(HashMap<String,String> input) 
	{
		
		//ProductList product=loginpage.LoginApplication("anjalikakade@gmail.com", "Anjali@123");
		ProductList product=loginpage.LoginApplication(input.get("Username"), input.get("password"));
		// to select item
		product.selectItemByName(input.get("Item"));
		product.AddToCart(input.get("Item"));
		// click on cart section
		CartPage cart=product.ClickCartSection();
		boolean match = cart.verifyProductaddedinCartSection(input.get("Item"));
		Assert.assertTrue(match);
		System.out.println("Expcted Item Added:"+" " + input.get("Item") + match);
		cart.clickCheckoutButton();
		cart.selectCountry();
		OrderPage orderpage =cart.placeOrder();
		String msdriver = orderpage.getOrderPageTitle();
		Assert.assertTrue(msdriver.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods = {"SubmitProductOrder"})
	public void VerifyOrderDependancemethod()
	{
		ProductList productpage =loginpage.LoginApplication("anjalikakade@gmail.com", "Anjali@123");
		OrderPageValidation orderpage=productpage.gotoOrderSection();
		boolean match = orderpage.verifyOrderHistory(Item);
		Assert.assertTrue(match);
		System.out.println("Item Verified in Order Page...");
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		String path ="C:\\Users\\AnjaliKakade\\eclipse-workspace\\Framework\\src\\main\\java\\Resources\\InputData.json";
		List<HashMap<String, String>> data=getJSONData(path);
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	//**************first way to read data*******************
	
	/*@DataProvider
	public Object[][] getData()
	{
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("Username", "anjalikakade@gmail.com");
		map.put("password", "Anjali@123");
		map.put("Item", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("Username", "kakade@gmail.com");
		map1.put("password", "Anjali@123");
		map1.put("Item", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map1}};
	}*/
	

}
