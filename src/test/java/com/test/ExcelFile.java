package com.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelFile {
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
		// driver.get("http://the-internet.herokuapp.com/");
		driver.navigate().to("https://www.browserstack.com/");
		driver.manage().window().maximize();
		readExcelFile("sheet1", "C:\\workspace\\AutomationPoC\\TestData\\LmaTestData.xlsx");

		Thread.sleep(5000);
		handleMouseKeyBoardActions();
		Thread.sleep(5000);

	}

	public void handleMouseKeyBoardActions() {
		try {
			Actions act = new Actions(driver);
			readExcelFile("sheet2", "C:\\workspace\\AutomationPoC\\TestData\\LmaTestData.xlsx");

			act.moveToElement(driver.findElement(By.id("developers-menu-toggle"))).build().perform();
			driver.findElement(By.linkText("Documentation")).click();

			act.contextClick(driver.findElement(By.id("developers-menu-toggle")));
			act.doubleClick(driver.findElement(By.id("developers-menu-toggle")));

			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
		
	
	@Test
	public void testLogin() {
		Map<String, String> data = readExcelFile("sheet2", "C:\\workspace\\AutomationPoC\\TestData\\LmaTestData.xlsx");
		
		for (Map.Entry<String,String> entry : data.entrySet())  
		{  
			login(entry.getKey(), entry.getValue());
		}  
		
	}
	
	//Apache POI libraries
	
		//.xls - HSSFWorkBook
		//.xlsx - XSSFWorkBook
		
		//No Of rows you have in excel sheet - get Last Row num - get First Row Num: 32 - 0 = 32 rows
		// iterate each row
		//iterate each cell for each row
		//getCellValue
	
	
	public static Map<String, String> readExcelFile(String sheetName, String fileName) {

		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			Map<String, String> data = new HashMap<String, String>();
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			Sheet sh = wb.getSheet(sheetName);
			int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();
			Row header = sh.getRow(0);
			for (int i = 1; i < rowCount; i++) {
				Row row = sh.getRow(i);
				int columnCount = row.getLastCellNum() - row.getFirstCellNum();
				
				for(int j=0;j<columnCount;j++) {
					System.out.println("cell values:"+row.getCell(j));
					data.put(header.getCell(j).toString(), row.getCell(j).toString());
				}
				
			}
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void login(String userName, String password) {
		try {
			
			driver.findElement(By.id("userNmae")).sendKeys(userName);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("login buttin")).click();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
