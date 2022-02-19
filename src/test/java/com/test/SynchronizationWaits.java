package com.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SynchronizationWaits {
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers\\chromedriver.exe");

		DesiredCapabilities caps = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		caps.setCapability(ChromeOptions.CAPABILITY, options);
		
		
		driver = new ChromeDriver(options);
		//driver.get("http://the-internet.herokuapp.com/");
		driver.navigate().to("http://the-internet.herokuapp.com/");
		driver.manage().window().maximize();
		
				
		waits();
	}
	
	public static void waits() {
		
		//Implicit wait	- will wait for each web element inside the DOM (Data Object Model)
		//Explicit wait - will wait for specific web element
		//Fluent wait - 
		
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Checkboxes")));
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"islrg\"]/div[1]/div[2]/a[1]/div[1]")));
		
		wait.until(ExpectedConditions.alertIsPresent());
		
		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Checkboxes")).click();
		
		
		if(driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]")).isSelected()) {
			System.out.println("check box 1 is already selected");
		} else {
			driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]")).click();
		}
		
		if(driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]")).isSelected()) {
			driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]")).click();
			
		} else {
			System.out.println("check box 2 is not selected");
		}
		
		
	}
	

}
