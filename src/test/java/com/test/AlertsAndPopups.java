package com.test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class AlertsAndPopups {
	public static WebDriver driver;

	@Test
	public void alertsPopup() throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers\\chromedriver.exe");

		DesiredCapabilities caps = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		caps.setCapability(ChromeOptions.CAPABILITY, options);
		
		
		driver = new ChromeDriver(options);
		//driver.navigate().to("https://demo.guru99.com/test/delete_customer.php"); 
		driver.navigate().to("https://demo.guru99.com/popup.php");
		driver.manage().window().maximize();
		
		Thread.sleep(5000);
		//alertsHandling();
		popUpWindowHandling();
		driver.close();
				
	}
	
	public static void alertsHandling() {
		try {
			
			//Alert class.
			
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
	
	public static void popUpWindowHandling() {
		
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
			
				driver.findElement(By.name("emailid")).sendKeys("rameshbabu.challa@gmail.com");
				driver.findElement(By.name("btnLogin")).click();
				
				driver.close();
			} else {
				System.out.println("inside else block");
			}
		}
		
		driver.switchTo().window(parentWindow);
		driver.close();
		
		
		//Step1. get current window and store in parentWindow variable
		//Step2. get All windowHandles and store in iterator
		//step3 : loop the iterator (2 windows)
		//window 1: parent Window, widow 2: child window.
		//step 4: loop the iterator. first value: parentwindow
		//step 5: if(parentWindow.step1 parentWindow), if both are same then you dont need to switch.
		//else you need to switch
	
		
	}
}
