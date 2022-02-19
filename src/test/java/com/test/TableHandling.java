package com.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

public class TableHandling {
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
		driver.navigate().to("https://www.w3schools.com/html/html_tables.asp");
		driver.manage().window().maximize();
		
		
		tableHandling("Mexico");
		
	}
	
	//Step 1. Find Number of rows using findElements method
	//Step 2: iterate each row using for loop
	//Step 3: find the actual column value that you are looking for using getText method
	//Step 4: Compare the Expected and actual values using if loop
	//Step 5: if the condition met, get the required column values using td
	//Step 6: Break
	
	
	
public static void tableHandling(String countryName) {
	
 List<WebElement> noOfRows = driver.findElements(By.xpath("//table[@id='customers']//tr"));	
	
	for(int i=2;i<=noOfRows.size();i++) {
		
		String country = driver.findElement(By.xpath("//table[@id='customers']//tr["+ i +"]//td[3]")).getText();
		System.out.println("country:"+country);
		if(country.equals(countryName)) {
			String companyName = driver.findElement(By.xpath("//table[@id='customers']//tr["+i+"]//td[1]")).getText(); 
			String contactName = driver.findElement(By.xpath("//table[@id='customers']//tr["+i+"]//td[2]")).getText();
			System.out.println("CompanyName:"+companyName +" and contact name:"+ contactName);
			break;
		}
		
	}
	
}
	

}
