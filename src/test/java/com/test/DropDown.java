package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class DropDown {
	public static WebDriver driver;

	@Test
	public void dropdown() throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers\\chromedriver.exe");

		DesiredCapabilities caps = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		caps.setCapability(ChromeOptions.CAPABILITY, options);
		
		
		driver = new ChromeDriver(options);// open chrome driver
		driver.navigate().to("http://the-internet.herokuapp.com/");
		driver.manage().window().maximize();
		
		
		  driver.findElement(By.id("user-name")).sendKeys("standard_user");
		  driver.findElement(By.id("password")).sendKeys("secret_sauce");
		  driver.findElement(By.id("login-button")).click();
		  
		  verifyNavigation("PRODUCTS");
		  
		  
		  driver.findElement(By.id("shopping_cart_container")).click();
		  verifyNavigation("YOUR CART");
		  
		  driver.findElement(By.id("continue-shopping")).click();
		  
		  verifyNavigation("PRODUCTS");
		  
		  
		  Select filter = new Select(driver.findElement(By.className("product_sort_container")));
		  filter.selectByValue("za");
		  filter.selectByVisibleText("za");
		  filter.selectByIndex(1);
		  
		  filter.deselectByVisibleText("za");
		  filter.deselectByValue("za");
		  filter.deselectByIndex(2);
		  
		  
		  driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
		 
		  driver.close(); // current browser.
		  driver.quit(); // all the browsers which are opened by the driver and also the instance
		
	}
	
	public static void verifyNavigation(String expectedTitle) {
		
		String actualTitle = driver.findElement(By.className("title")).getText();
		System.out.println("Header:"+actualTitle);
		
		if(expectedTitle.equals(actualTitle)) {
			System.out.println("Successfully navigated to products page from cart");
		} else {
			System.out.println("UnSuccessful navigating to products page from cart");
		}
	}

}
