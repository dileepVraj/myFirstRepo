package Listeners;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import PageObjects.Orange_Hrm_Page_Object;

public class Orange_Login_Test extends OrangeBaseListener{

	
	
	@Test(testName = "Orange_Login",groups =  {"smoke"})
	//@Test
	public void Login()
	{
		Orange_Hrm_Page_Object object = new Orange_Hrm_Page_Object(driver);
		
		driver.get(url);
		
		logger.info("redirected to orange Login Screen");
		
		object.enterUserName(userName);
		logger.info("User name is Entered");
		object.enterPassword(password);
		logger.info("password is entered");
		object.clickLlogin();
		logger.info("login button is clicked");
		
		WebElement welcome = driver.findElement(By.id("welcome"));
		
		
		if(welcome.isEnabled())
		{
			logger.info("Login successfull");
			assertTrue(true);
			
		}
		else if(!welcome.isEnabled())
		{
			logger.info("login failed");
			assertTrue(false);
			
		}
	}
	
}
