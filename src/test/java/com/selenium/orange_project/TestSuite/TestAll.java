package com.selenium.orange_project.TestSuite;

import java.io.IOException;
import java.lang.reflect.*;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.orange_project.AllPages.AddEmployee;
import com.selenium.orange_project.AllPages.AdminPage;
import com.selenium.orange_project.AllPages.DashBoard;
import com.selenium.orange_project.AllPages.LoginPage;
import com.selenium.orange_project.BasePages.BaseActions;
import com.selenium.orange_project.Utils.RetryAnalyzer;
import com.selenium.orange_project.Utils.SkipLogin;

public class TestAll extends BaseActions {
	
	
	LoginPage loginpage;
	DashBoard dashboard;
	AddEmployee addemployee;
	AdminPage adminpage;
	
	@DataProvider(name ="loginData")
	public Object[][] getData(){
	return new Object[][] {
		
		{"user1","akshay"},
		{"user2","athul"}
	};
	}
	
	@BeforeMethod
	public void invokeSetup(Method method) throws IOException {
	loginpage = invoke_Browser();
	if(method.isAnnotationPresent(SkipLogin.class)) {
		return ;
	}
	dashboard = loginpage.Login();
	}
	
	@Test(priority = 1,retryAnalyzer = RetryAnalyzer.class,groups = {"smoke","regression"},dataProvider = "loginData")
	public void Login_With_Session(String username,String password) throws IOException {
		loginpage = dashboard.validateUrl();	
		System.out.println(username + " " + password);
	}
	

	@Test(priority = 2, groups = {"smoke","regression"})
	@SkipLogin
	public void Invalid_login() throws IOException {
		loginpage.Invalid_Login_Check();
	}
	
	@Test(priority = 3, groups = {"smoke","admin"})
	public void Add_Employee_with_unique_id_Validation() throws IOException {
	
		addemployee = dashboard.NavigateToAddEmployee();
		addemployee.ValidateTheEmployee();
		
		
		
	}
	@Test(priority = 4)
	public void Search_Employee() throws IOException {
		addemployee = dashboard.NavigateToAddEmployee();
		addemployee.Search_With_Filter();
	}
	
	@Test(priority = 5)
	public void Employee_Count() {
		addemployee = dashboard.NavigateToAddEmployee();
		addemployee.EmployeeeCount();
	}
	
	@Test(priority = 6)
	public void Role_Based_Login() {
		adminpage = dashboard.NavigateToAdminTab();
		adminpage.AddEssUser();
	}
	
	
	@Test(priority = 6)
	public void Role_Based_Login() {
		adminpage = dashboard.NavigateToAdminTab();
		adminpage.AddEssUser();
	}
	
	@Test(priority = 6)
	public void Role_Based_Login() {
		adminpage = dashboard.NavigateToAdminTab();
		adminpage.AddEssUser();
	}
	@Test(priority = 6)
	public void Role_Based_Login() {
		adminpage = dashboard.NavigateToAdminTab();
		adminpage.AddEssUser();
	}
	@Test(priority = 6)
	public void Role_Based_Login() {
		adminpage = dashboard.NavigateToAdminTab();
		adminpage.AddEssUser();
	}
	@Test(priority = 6)
	public void Role_Based_Login() {
		adminpage = dashboard.NavigateToAdminTab();
		adminpage.AddEssUser();
	}
	@Test(priority = 6)
	public void Role_Based_Login() {
		adminpage = dashboard.NavigateToAdminTab();
		adminpage.AddEssUser();
	}
	
	
	@Test(priority = 6)
	public void Role_Based_Login() {
		adminpage = dashboard.NavigateToAdminTab();
		adminpage.AddEssUser();
	}
	
	@Test(priority = 6)
	public void Role_Based_Login() {
		adminpage = dashboard.NavigateToAdminTab();
		adminpage.AddEssUser();
	}
	
	@Test(priority = 6)
	public void Role_Based_Login() {
		adminpage = dashboard.NavigateToAdminTab();
		adminpage.AddEssUser();
	}
	
	
	
	@AfterMethod
	public void TearDown() {
		getDriver().quit();
		driver.remove();
		
	}
	

}

