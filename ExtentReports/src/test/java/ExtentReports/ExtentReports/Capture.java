package ExtentReports.ExtentReports;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.github.dockerjava.api.model.Driver;

public class Capture {
	
	public static String screenShot (WebDriver driver) throws IOException
	
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File("src/../screenShots/"+System.currentTimeMillis()+".png");
		
		String filePath = destFile.getAbsolutePath();
		
		FileUtils.copyFile(scrFile, destFile);
		
		return filePath;
		
		
	}

}
