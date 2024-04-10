package CucumberStepDefination;

import java.io.IOException;

import org.testng.Assert;

import BaseTestComponent.BastTest;
import Framework.CartPage;
import Framework.LandingLogin;
import Framework.OrderPage;
import Framework.ProductList;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefination extends BastTest
{
	public LandingLogin loginpage;
	public ProductList product;
	public CartPage cart;
	public OrderPage orderpage;

	@Given("I Landed on Ecommorce page")
	public void I_Landed_Ecommorce_Page() throws IOException
	{
		loginpage = LounchApplication();
		
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_with_udername_password(String username,String pass)
	{
		product=loginpage.LoginApplication(username,pass);
		
	}
	
	@When ("^I Add product (.+) to Cart List$")
	public void I_Add_Product_to_Cart_List(String productName)
	{
		
		product.selectItemByName(productName);
		product.AddToCart(productName);
		
	}
	
	@When ("^I checkout (.+) and submit order$")
	public void I_Checkout_and_Submit_Order(String pname)
	{
		cart=product.ClickCartSection();
		boolean match = cart.verifyProductaddedinCartSection(pname);
		Assert.assertTrue(match);
		System.out.println("Expcted Item Added:"+" " + pname + match);
		cart.clickCheckoutButton();
		cart.selectCountry();
		orderpage =cart.placeOrder();
		
	}
	
	@Then("{string} meassage is displayed on confirmation page")
	public void Message_Displayed_on_Confirmation_page(String string)
	{
		String msdriver = orderpage.getOrderPageTitle();
		//System.out.println(msdriver + " " +string);
		Assert.assertTrue(msdriver.equalsIgnoreCase(string));
		driver.close();
		
	}
	@Then("{string} meassage is displayed on login page")
	public void Message_displayed_on_login_page(String string)
	{
		System.out.println(loginpage.getErrorMessage());
		Assert.assertEquals(string, loginpage.getErrorMessage());
		driver.close();
	}
}
