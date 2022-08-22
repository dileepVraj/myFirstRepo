package Listeners;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.compress.harmony.pack200.NewAttribute;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import net.bytebuddy.implementation.bind.MethodDelegationBinder.AmbiguityResolver.Resolution;

public class ReportingListener extends TestListenerAdapter
{
	

	public ExtentReports reports;
	public ExtentTest extentTest;
	OrangeBaseListener base = new OrangeBaseListener();
	
	public String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
	public void onStart(ITestContext context)
	{
		
		
		reports = new ExtentReports();
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//"+timeStamp+".html");
		
		reports.attachReporter(sparkReporter);
		
		reports.setSystemInfo("OS", System.getProperty("os.name"));
		reports.setSystemInfo("java version", System.getProperty("java.version"));
		
		sparkReporter.config().setReportName("Orange_Login");
		
		
		
		//Capabilities cap = ((RemoteWebDriver)base.driver).getCapabilities();
		extentTest = reports.createTest(context.getName());
		String author = context.getCurrentXmlTest().getParameter("author");
		//String browserName = cap.getBrowserName();
		//String browserVersion = cap.getVersion();
		
		extentTest.assignAuthor(author);
		//extentTest.assignDevice(browserName);
		//extentTest.assignDevice(browserVersion);
		
		
		
		
	}
	
	
	public void onTestSuccess(ITestResult result)
	{
		
		extentTest.pass(MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
		//extentTest.log(Status.PASS, result.getName()+"is passed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		
		extentTest.log(Status.FAIL,MarkupHelper.createLabel(result.getName(),ExtentColor.RED));
		
		
		
//		String ScPath = base.CaptureScreenShot(result.getName()+timeStamp+".png");
//		
//		File file = new File(ScPath);
//		
//		if(file.exists())
//		{
//			try
//			{
//				extentTest.fail("ScreenShot is below"+ extentTest.addScreenCaptureFromPath(ScPath));
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//			System.out.println("Succesfully p5rinted screenshot");
		//}
	}
	
	public void onFinish(ITestContext context)
	{
	
		reports.flush();
	}
	
	
}
