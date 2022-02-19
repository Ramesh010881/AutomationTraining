package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class CheckBoxes {
	public static WebDriver driver;

	@Test
	public void checkBoxes() throws InterruptedException {
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
		//Thread.sleep(5000);
		
		driver.findElement(By.linkText("Checkboxes")).click();
		
		Thread.sleep(5000);
		if(driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]")).isSelected()) {
			System.out.println("check box 1 is already selected");
		} else {
			driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]")).click();
		}
		Thread.sleep(5000);
		
		if(driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]")).isSelected()) {
			driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]")).click();
			
		} else {
			System.out.println("check box 2 is not selected");
		}
		
		driver.navigate().back();
		driver.close();
		
	}
	
	

}
