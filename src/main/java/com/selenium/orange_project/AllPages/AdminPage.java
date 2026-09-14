package com.selenium.orange_project.AllPages;

import static org.testng.Assert.assertEquals;

import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.orange_project.BasePages.BaseActions;
import com.selenium.orange_project.Config.Readers;

public class AdminPage extends BaseActions {
	
	public Properties prop;
	

	
	public AdminPage() {
		PageFactory.initElements(getDriver(),this);
	}
	
	
	@FindBy(xpath = "//div[@class='orangehrm-header-container']/button")
	WebElement AddButton;
	
	@FindBy(xpath = "(//div[@class='oxd-select-wrapper'])[1]//i")
	WebElement UserDropDown;
	
	@FindBy(xpath = "(//div[@class='oxd-select-wrapper'])[1]//div[@class='oxd-select-text-input']")
	WebElement SelectDropDown;
	
	
	public void AddEssUser() {
		waitForVisbility(AddButton, 10);
		AddButton.click();
		waitForVisbility(UserDropDown, 10);
		prop = Readers.getPropertiesFile();
		String UserRole = prop.getProperty("userrole");
		while(!SelectDropDown.getText().equalsIgnoreCase(UserRole)) {
			UserDropDown.click();
			SelectDropDown.sendKeys(Keys.ARROW_DOWN);
		}
		
		Assert.assertEquals(SelectDropDown.getText(),
				UserRole,
				"Expected Role is not in the DropDown");
		
		SelectDropDown.sendKeys(Keys.ENTER);
		System.out.println("Selected "+SelectDropDown.getText()+" User Role");
	}
	
	
}
