package TestClasses;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import UtilityClasses.PropertiesCongfig_Class;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeBase 
{

	public static ExtentReports reports;
	public static ExtentTest extentTest;
	public static String screenshotsSubFolderName;

	public static  WebDriver driver;

	public static Logger logger;

	PropertiesCongfig_Class config = new PropertiesCongfig_Class();
//
	public String url = config.getUrl();
	
	  public String userName = config.getUserName(); 
	  public String password = config.getPassword();
	  
	  

	@Parameters("browser")
	@BeforeTest
	public void setUp(@Optional("chrome")String browser, ITestContext context)
	{
		logger = Logger.getLogger("Orange_Hrm");
		PropertyConfigurator.configure("log4j.properties");

		switch(browser.toLowerCase())
		{
		case "chrome":

			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver","C:\\Users\\khaisar jaha\\eclipse-workspace\\HybridFrameWork1\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			break;

		default:
			System.out.println("Browser did'nt initilized properly ");

		}

		//ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//reports//"+context.getName());

//		extentTest = reports.createTest(context.getName());
//
//		Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
//		String browserName = cap.getBrowserName();
//		String browserVersion = cap.getVersion();
//		String authorName = context.getCurrentXmlTest().getParameter("author");
//
//		extentTest.assignDevice(browserName);
//		extentTest.assignDevice(browserVersion);
//		extentTest.assignAuthor(authorName);

	}

	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}


	@BeforeSuite
	public void initializationOfExtentreports()
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//reports//AllTests.html");
		sparkReporter.config().setReportName("Orang_Login_All");

		ExtentSparkReporter failSparkReporter = new ExtentSparkReporter(".//reports//FailTests.html");
		failSparkReporter.filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
		failSparkReporter.config().setReportName("Fail_Tests_Orange");
		reports = new ExtentReports();
		reports.attachReporter(sparkReporter,failSparkReporter);

		reports.setSystemInfo("Operating System", System.getProperty("os.name"));
		reports.setSystemInfo("program version", System.getProperty("java.version"));
	}

	@AfterSuite
	public void reportsGeneration()
	{
		reports.flush();
	}

	

	@AfterMethod
	public void CheckStatus(ITestContext context, Method m, ITestResult result)
	{
		
		if(result.getStatus() == ITestResult.FAILURE)
		{
			extentTest = reports.createTest(context.getName());
			String ScreenShotPath = null;
			ScreenShotPath = captureScreenshot(result.getTestContext().getName()+ "_" +result.getMethod().getMethodName()+".jpg");
			extentTest.addScreenCaptureFromPath(ScreenShotPath);
			extentTest.fail(result.getThrowable());
			extentTest.assignCategory(m.getAnnotation(Test.class).groups());
			
			Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
			String browserName = cap.getBrowserName();
			String browserVersion = cap.getVersion();
			String authorName = context.getCurrentXmlTest().getParameter("author");

			extentTest.assignDevice(browserName);
			extentTest.assignDevice(browserVersion);
			extentTest.assignAuthor(authorName);
		}
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			extentTest = reports.createTest(context.getName());
			extentTest.pass(m.getName()+" is passed ");
			extentTest.assignCategory(m.getAnnotation(Test.class).groups());
			
			Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
			String browserName = cap.getBrowserName();
			String browserVersion = cap.getVersion();
			String authorName = context.getCurrentXmlTest().getParameter("author");

			extentTest.assignDevice(browserName);
			extentTest.assignDevice(browserVersion);
			extentTest.assignAuthor(authorName);
		}

		

	}


	


	public String captureScreenshot(String fileName) {
		if(screenshotsSubFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
			screenshotsSubFolderName = myDateObj.format(myFormatObj);
		}

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/"+ screenshotsSubFolderName+"/"+fileName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");
		return destFile.getAbsolutePath();
	}


}
