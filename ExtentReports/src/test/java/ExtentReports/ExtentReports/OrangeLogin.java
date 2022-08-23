package ExtentReports.ExtentReports;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeLogin {
WebDriver driver;
ExtentReports returnRepo;
ExtentTest test;
	
	@BeforeClass
	public void setup()
	{
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void loginverification() throws IOException
	{
		
		
		 returnRepo = ExtentManager.getInstance();
		 test = returnRepo.startTest("Orange Hrm login");
		 test.log(LogStatus.PASS, test.addScreenCapture(Capture.screenShot(driver))+ "Test passed");
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		test.log(LogStatus.INFO, "redirected to orange Hrm login page");
		
		String loginPage = driver.getTitle();
		
//		@FindBy(id = "txtUsername") WebElement userName;
//		@FindBy(id = "txtPassword") WebElement password;
		
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		
		test.log(LogStatus.INFO, "Entered credentials to login fields");
		
		driver.findElement(By.id("btnLogin")).click();
		
		String homePageTitle = driver.getTitle();
		
		
		
		if(!loginPage.equals(homePageTitle))
		{
//			Assert.assertTrue(true);
//			System.out.println("login successfull");
			
			test.log(LogStatus.PASS,"login successfull");
		}
		else
		{
		
//			System.out.println("login unsuccessfull ");
//			Assert.assertFalse(false);
			test.log(LogStatus.FAIL,"login UnSuccessfull");
			
		}
		
	}
	
	
	@AfterMethod
	public void testClouser()
	{
		
		returnRepo.endTest(test);
		returnRepo.flush();
		
	}
	
	
	@AfterClass 
	public void terminate()
	{
		driver.close();
	}
	
}
