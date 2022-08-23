package ExtentReports.ExtentReports;

import java.io.File;
import java.sql.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {


		public static ExtentReports getInstance()
		{
			java.util.Date date = new java.util.Date();
			String Extentname = date.toString().replace(" ", "_").replace(":", "_");
			Extentname = Extentname+".html";
			
			// this above three statements suggest create date instance and convert it into string and append ".html" tag to date.
			
			String filePath = ".//reports//"+Extentname; // this statement is all about in project which folder you want to store 
			// .....generated reports.
			ExtentReports repo = new ExtentReports(filePath, true, DisplayOrder.NEWEST_FIRST); // create an object of extent 
			 
			File configXml = new File(".//ReportsConfig.xml");
			
			repo.loadConfig(configXml);
			
			return repo; 
			
			  
		}
		
	

}
