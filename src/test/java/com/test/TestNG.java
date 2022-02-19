package com.test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class TestNG {
	public static WebDriver driver;
	public static String testRunTimeStamp;
	
	@BeforeMethod
	public void launchBrowser() {
		
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers\\chromedriver.exe");

		DesiredCapabilities caps = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		caps.setCapability(ChromeOptions.CAPABILITY, options);
		
		driver = new ChromeDriver(options);
		driver.navigate().to("https://demo.guru99.com/test/delete_customer.php");
		
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
	
	
	
	
	@Test(priority=1)
	public void alertsHandling() {
		try {
			
			driver.manage().window().maximize();
			
			driver.findElement(By.name("cusid")).sendKeys("53920");
			driver.findElement(By.name("submit")).click();
			
			Alert alert = driver.switchTo().alert();
			String alertText = driver.switchTo().alert().getText();
			System.out.println("alertText:"+alertText);
			Thread.sleep(3000);
			alert.dismiss();
			//alert.accept();
			Thread.sleep(3000);
				
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=0)
	public void popUpWindowHandling() throws IOException {
		driver.navigate().to("https://demo.guru99.com/popup.php");
		driver.manage().window().maximize();
		
		takeScreenshot(System.getProperty("user.dir") + "\\Results\\Screenshots_"+testRunTimeStamp+"\\ParentWindow.png");
		
		String parentWindow = driver.getWindowHandle();
		System.out.println("parent window name:"+parentWindow);
		driver.findElement(By.linkText("Click Here")).click();
		
		Set<String> allWindows = driver.getWindowHandles();
		
		Iterator<String> i1 = allWindows.iterator();
		
		while(i1.hasNext()) {
			String childWindow = i1.next();
			System.out.println("child window name:"+childWindow);
			if(!parentWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
				
				takeScreenshot(System.getProperty("user.dir") + "\\Results\\Screenshots_"+testRunTimeStamp+"\\ChildWindow1.png");
			
				driver.findElement(By.name("emailid")).sendKeys("rameshbabu.challa@gmail.com");
				driver.findElement(By.name("btnLogin")).click();
				
				takeScreenshot(System.getProperty("user.dir") + "\\Results\\Screenshots_"+testRunTimeStamp+"\\FilledDetails.png");
				
				driver.close();
			} else {
				System.out.println("inside else block");
			}
		}
		
		driver.switchTo().window(parentWindow);
		takeScreenshot(System.getProperty("user.dir") + "\\Results\\Screenshots_"+testRunTimeStamp+"\\ParentWindow1.png");
		driver.close();
		
		
		//Step1. get current window and store in parentWindow variable
		//Step2. get All windowHandles and store in iterator
		//step3 : loop the iterator (2 windows)
		//window 1: parent Window, widow 2: child window.
		//step 4: loop the iterator. first value: parentwindow
		//step 5: if(parentWindow.step1 parentWindow), if both are same then you dont need to switch.
		//else you need to switch
	
		
	}

	public static void takeScreenshot(String fileName) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);		
		FileUtils.copyFile(scrFile, new File(fileName));
	}
	
	
	
	
	
}
