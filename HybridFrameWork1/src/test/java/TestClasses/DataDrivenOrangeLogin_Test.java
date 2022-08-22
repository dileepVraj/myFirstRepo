package TestClasses;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.Orange_Hrm_Page_Object;
import UtilityClasses.ExcelUtilities;

public class DataDrivenOrangeLogin_Test extends OrangeBase{
	
	@Test(dataProvider = "loginData")
	public void loginTest(String userName, String password)
	{
		driver.get(url);
		
		Orange_Hrm_Page_Object loginPage = new Orange_Hrm_Page_Object(driver);
		
		loginPage.enterUserName(userName);
		loginPage.enterPassword(password);
		loginPage.clickLlogin();
		
		if(IsWelcomeEnabled()==true)
		{
			Assert.assertTrue(true);
			loginPage.clickWelcome();
			loginPage.clickLogout();
			
		}
		else if(IsWelcomeEnabled()== false)
		{
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}
		
	}
	
	public boolean IsWelcomeEnabled()
	{
		Orange_Hrm_Page_Object loginPage = new Orange_Hrm_Page_Object(driver);
		try
		{
			WebElement welcomeButton = loginPage.welcome();
			welcomeButton.isDisplayed();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	

	
	@DataProvider(name = "loginData")
	public Object[][] getData() throws IOException
	{
		String path = "C:\\Users\\khaisar jaha\\eclipse-workspace\\HybridFrameWork1\\src\\test\\java\\Test_Data\\Orange_Login_Data.xlsx";
		
		ExcelUtilities utils = new ExcelUtilities();
		
		int numberOfRows = utils.getNumberOfRows(path, 0);
		int cellCount = utils.getNumberOfCells(path,0, 1);
		
		Object loginData[][] = new Object[numberOfRows][cellCount];
		
		for(int i = 1; i<=numberOfRows; i++)
		{
			for(int j = 0; j<cellCount; j++)
			{
				loginData[i-1][j] = utils.getCellData(path, 0, i, j);
			}
		}
		
		return loginData;
		
	}
	
	
}
