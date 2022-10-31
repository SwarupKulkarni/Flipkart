package com.flipkart.testcases;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.flipkart.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyLoginPage {
	
	WebDriver driver;
	
	@BeforeTest
	public void WebdriverSetup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
	}
	@Test
	public void test1() 
	{
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		LoginPage login = new LoginPage(driver);
		
		
		try {
			login.login();
			login.SearchBox();
			login.select();	
			login.scroll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void test2()
	{
		LoginPage login = new LoginPage(driver);
		
		try {
			login.price();
			login.expensive();
			login.review();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	@AfterTest
	public void close() {
		//driver.quit(); 
	}

}
