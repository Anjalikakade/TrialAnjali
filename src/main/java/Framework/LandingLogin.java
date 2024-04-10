package Framework;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstarctTest;

public class LandingLogin extends AbstarctTest
{
	WebDriver driver;
	public LandingLogin(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement useremail;
	
	@FindBy(id="userPassword")
	WebElement userpass;
	
	@FindBy(xpath="//input[@type='submit' ][@id='login'] ")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;
	
	public ProductList LoginApplication(String username,String Password)
	{
		useremail.sendKeys(username);
		userpass.sendKeys(Password);
		submit.click();
		ProductList product = new ProductList(driver);
		return product;
	}
	
	public String getErrorMessage()
	{
		waitTillAppearElementbyweb(errormessage);
		String error = errormessage.getText();
		return error;
	}

}
