package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Orange_Hrm_Page_Object 
{

	WebDriver Ldriver;
	
	public Orange_Hrm_Page_Object(WebDriver rdriver)
	{
		Ldriver = rdriver;
		PageFactory.initElements( rdriver,this);
	}
	
	@FindBy (id = "txtUsername") WebElement userNameBox;
	@FindBy (id = "txtPassword") WebElement passwordBox;
	@FindBy (id = "btnLogin") WebElement loginButton;
	@FindBy (id = "welcome") WebElement welcomElement;
	@FindBy (linkText = "Logout") WebElement logOut;
	
	
	
	public void enterUserName(String userName)
	{
		userNameBox.sendKeys(userName);
	}
	
	public void enterPassword(String password)
	{
		passwordBox.sendKeys(password);
	}
	
	public void clickLlogin()
	{
		loginButton.click();
	}
	
	public void clickLogout()
	{
		logOut.click();
	}
	
	
	public WebElement welcome()
	{
		return welcomElement;
	}
	
	public void clickWelcome()
	{
		welcomElement.click();
	}

	
	
}
