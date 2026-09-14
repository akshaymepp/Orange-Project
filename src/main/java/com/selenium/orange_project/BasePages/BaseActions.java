package com.selenium.orange_project.BasePages;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.selenium.orange_project.AllPages.LoginPage;
import com.selenium.orange_project.Config.Readers;

import io.github.bonigarcia.wdm.WebDriverManager;

public  class BaseActions { 
	
	//protected WebDriver driver;
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public static Properties prop;
	public static boolean validateurl = false;
	
	

	public LoginPage invoke_Browser()  {
		
	prop = Readers.getPropertiesFile();
	
	String BrowserName = prop.getProperty("browser");
	
	
	if(BrowserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			driver.set(new ChromeDriver());

		
	}
	
	else if(BrowserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
			

	}
	
	else if(BrowserName.equalsIgnoreCase("Firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver.set(new FirefoxDriver());
		
	}
	else {
		throw new RuntimeException("Browser is not supported"+BrowserName);
	}
	
	getDriver().manage().window().maximize();
	getDriver().navigate().to(
			validateurl
			? prop.getProperty("dashboardUrl")
			: prop.getProperty("url")
			);
	
	return new LoginPage();
	}
	
	public void restart() {
		validateurl = true;
		if(driver!=null) {
			getDriver().quit();
			driver.remove();
		}

	}

	public void Refreshpage() {
		getDriver().navigate().refresh();
		
	}
	
	public void logStep(String step,String status) {
		System.out.println(step + " : " + status);
	}
	
	public void waitForVisbility(WebElement element,int timeinseconds) {
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(timeinseconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public int RandomId() {
		
		Random random = new Random();
		int randomdigit = 1000 + random.nextInt(9000);
		
		return randomdigit;
	}
	
	public boolean Displayed(WebElement element) {
		
		try {
			element.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public Map<String,String> ExcelReader() throws IOException{
		
		Map<String,String> ExceData = Readers.readExcel(1);
		
		return ExceData;
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	
	
	
	
}