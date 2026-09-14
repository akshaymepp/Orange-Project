package com.selenium.orange_project.AllPages;

import java.io.IOException;
import java.io.Reader;
import java.time.Duration;
import java.util.*;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.orange_project.BasePages.BaseActions;
import com.selenium.orange_project.Config.Readers;

public class LoginPage extends BaseActions{

	
	public LoginPage() {
		PageFactory.initElements(getDriver(),this);
	}
	
    @FindBy(xpath = "//input[@name='username']")
    WebElement username;
    
    @FindBy(xpath = "//input[@name='password']")
    WebElement password;
    
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submit;
    
    @FindBy(xpath="//div[@class='oxd-alert oxd-alert--error']//p")
    WebElement invalidMsg;
    
    public DashBoard Login() throws IOException {
    	
        System.out.println("Test Case : Verify Login With Correct Credentials");
        System.out.println("---------------------------------------------------------");
    	
    	Map<String,String> ExcelData = ExcelReader();
    	waitForVisbility(username,10);
    	try {
        	username.sendKeys(ExcelData.get("Username"));
        	password.sendKeys(ExcelData.get("Password"));
        	submit.click();
        	logStep("Submission button clicked" ,"PASS");
		} catch (Exception e) {
			e.printStackTrace();
        	logStep("Verification Failed" ,"Failed");
		}

    	
    	
    	
    	return new DashBoard();
    }
    
    public void Invalid_Login_Check() throws IOException {
    	
        System.out.println("Test Case : Verify Login With InCorrect Credentials");
        System.out.println("---------------------------------------------------------");
    	 	
    	Map<String,String> ExcelData = ExcelReader();
    	waitForVisbility(username,10);
    	username.sendKeys(ExcelData.get("Username"));
    	password.sendKeys(ExcelData.get("InvalidPassword"));
    	submit.click();
    	try {
			waitForVisbility(invalidMsg,10);
			String invalid_message = invalidMsg.getText();
			logStep("Verification Passed : Invalid Message Showned : "+invalid_message,"PASS");
			Refreshpage();
			try {
				waitForVisbility(invalidMsg,10);
				logStep("Verification Failed : The Invalid Message is displayed","FAIL");
			}
			catch (Exception e) {
				logStep("Verification Passed : The Invalid Message is not displayed","PASS");
				System.out.println("");
			}
			
		} catch (Exception e) {
			logStep("Verification Failed : Invalid Message is not showned","FAIL");
			System.out.println("");
			e.printStackTrace();
			
		}
    	
    	
    	
    	
    }
	
}