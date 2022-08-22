package UtilityClasses;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesCongfig_Class
{
	Properties properties;
	
	// Iam creating constructor for Properties class because properties file should be load whenever object is created for this class.
	
	public PropertiesCongfig_Class()
	{
		File file = new File(".//Configurations//Config.properties");
		
		try 
		{
			FileInputStream fileInputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInputStream);
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// now read test data from properties file using below methods and return them when ever those methods are called.
	
	public String getUrl()
	{
		String url = properties.getProperty("url");
		return  url;
	}
	
	public String getUserName()
	{
		String userName = properties.getProperty("userName");
		return userName;
		
	}
	
	public String getPassword()
	{
		String password = properties.getProperty("password");
		return password;
	}


}
