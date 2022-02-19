package com.test;

import java.util.List;

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

public class ActionClass {
	public static WebDriver driver;

	@Test
	public void actionClass() throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers\\chromedriver.exe");

		DesiredCapabilities caps = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		caps.setCapability(ChromeOptions.CAPABILITY, options);
		
		
		driver = new ChromeDriver(options);
		//driver.get("http://the-internet.herokuapp.com/");
		driver.navigate().to("https://www.browserstack.com/");
		driver.manage().window().maximize();
		
		Thread.sleep(5000);
		handleMouseKeyBoardActions();
		Thread.sleep(5000);
		
		driver.close();
	}
	
	public void handleMouseKeyBoardActions() {
		try {
			Actions act = new Actions(driver);
			
			act.moveToElement(driver.findElement(By.id("developers-menu-toggle"))).build().perform();
			driver.findElement(By.linkText("Documentation")).click();
			
			act.contextClick(driver.findElement(By.id("developers-menu-toggle")));
			act.doubleClick(driver.findElement(By.id("developers-menu-toggle")));
			
				Robot r = new Robot();
		      r.keyPress(KeyEvent.VK_ENTER);
		      r.keyRelease(KeyEvent.VK_ENTER);
		      
		      r.keyPress(KeyEvent.VK_TAB);
		      r.keyRelease(KeyEvent.VK_TAB);
							
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
