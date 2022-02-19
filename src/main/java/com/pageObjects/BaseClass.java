package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	public static WebDriver driver;
	public static String befreotestRunTimeStamp;
	public static String aftertestRunTimeStamp;
	
	@BeforeSuite
	public void launchBorwser() {
	
		java.util.Date today = new java.util.Date();
		befreotestRunTimeStamp = new java.sql.Timestamp(today.getTime()).toString();
		System.out.println("current time:"+befreotestRunTimeStamp);
		//7 am
		
	}
	
	@AfterSuite
	public void afterSuite() {
		java.util.Date today = new java.util.Date();
		aftertestRunTimeStamp = new java.sql.Timestamp(today.getTime()).toString();
		System.out.println("current time:"+aftertestRunTimeStamp);
		
	
//7.30 am
	}
	
	@AfterClass
	public void beforeClass() {
		driver.quit();
	}
	
	
}

//30 minutes