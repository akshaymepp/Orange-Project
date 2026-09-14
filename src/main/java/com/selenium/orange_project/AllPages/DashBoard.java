package com.selenium.orange_project.AllPages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.selenium.orange_project.BasePages.BaseActions;
import com.selenium.orange_project.Config.Readers;

public class DashBoard extends BaseActions{
	

	
	public DashBoard() {
		PageFactory.initElements(getDriver(),this);
	}
	
	@FindBy(xpath = "//ul[@class='oxd-main-menu']/child::li[2]")
	WebElement addEmployeepagebutton;
	
	
	@FindBy(xpath = "//ul[@class='oxd-main-menu']/child::li[1]")
	WebElement adminpagebutton;
	
	
	public LoginPage validateUrl() {
		
		LoginPage page = null;
		
        System.out.println("Test Case : Verify Login to Dashboard");
        System.out.println("---------------------------------------------------------");
		
		Properties dashboard = Readers.getPropertiesFile();
		String Expected_dashBoardUrl = dashboard.getProperty("dashboardUrl");
		String Actual_dashBoardUrl = getDriver().getCurrentUrl();
		SoftAssert softassert =  new SoftAssert();
		try {
			softassert.assertEquals(Expected_dashBoardUrl,Actual_dashBoardUrl);
			logStep("Verification Passed:Navigated correctly to dashBoard","PASS");
			restart();
			try {
				page = invoke_Browser();
				softassert.assertNotEquals(Actual_dashBoardUrl, Expected_dashBoardUrl);
				logStep("Verification Passed:Navigated correctly to loginPage","PASS");
				return page;
			} catch (Exception e) {
				logStep("Verification Passed:Navigated Not correctly to loginPage","FAIL");
			}

			
		} catch (Exception e) {
			logStep("Verification failed : Dashboard is not correctly navigated","FAIL");
			e.printStackTrace();
			
		}
		return page;
		
	}
	
	public AddEmployee NavigateToAddEmployee() {
		waitForVisbility(addEmployeepagebutton, 10);	
		addEmployeepagebutton.click();
		
		
		return new AddEmployee();
	}
	
	
	public AdminPage NavigateToAdminTab() {
		waitForVisbility(adminpagebutton, 10);
		adminpagebutton.click();
		
		return new AdminPage();
		
	}



	
	
}