/**
 * 
 */
package com.flipkart.pages;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

/**
 * @author swlaxmik
 *
 */
public class LoginPage {

	WebDriver driver;
	int max;
	String b ;
	By num = By.xpath("//input[@class='_2IX_2- VJZDxU']");
	By pass = By.xpath("//input[@class='_2IX_2- _3mctLh VJZDxU']");
	By btn = By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']");
	By search = By.xpath("//input[@name='q']");
	By option = By.xpath("*//div[contains(@class,'lrtEPN')]");
	By prices = By.xpath("//div[@class='_30jeq3']");
	By nxt = By.xpath("//span[contains(text(),'Next')]");
	By rev = By.xpath("*//div[@class='_6K-7Co']");
	By rating = By.xpath("//div[@class='_3LWZlK _138NNC']");
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void login()
	{
		//login the flipkart application
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(num).sendKeys("7020640697");
		driver.findElement(pass).sendKeys("pass@123");
		driver.findElement(btn).click();
		
	}
	
	public void SearchBox() throws Exception
	{
		//Search box 
		Thread.sleep(4000);
		driver.findElement(search).click();
		
	}
	
	public void select() throws Exception
	{
		//From default dropdown selecting a desired option such as tshirts
		String suggest = "t shirts";
		//String title;
		Thread.sleep(2000);
		List<WebElement> opt = driver.findElements(option);
		
		for(WebElement e : opt)
		{
			String current = e.getText();
			if (current.contains(suggest))
			{
				e.click();       //click 
				Thread.sleep(1000);
				break;
			}
			
		}
		title = driver.getTitle().toString(); //getting the title of new page
		System.out.println(title);
		String actual = "T Shirts- Buy Products Online at Best Price in India - All Categories | Flipkart.com";
		//Assert.assertEquals(title, actual);
		
	}
	
	public void scroll() 
	{
		//scrolling for the new page..
		
		try {
			Thread.sleep(4000);		
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scroll(0,3900)");
			driver.findElement(nxt).click();			
		} 
		catch (InterruptedException e) {
	
			e.printStackTrace();
		}
	}
	
	public void price() throws Exception
	{
		//fetching the prices of all displayed products..
		
		Thread.sleep(4000);
		List<WebElement> ele=  driver.findElements(prices);
		System.out.println("List of Prices is : " +ele.size());
		String[] str = new String[ele.size()];
		int i = 0;	
		for(WebElement webele : ele)
		{
	        str[i] = webele.getText();
	        i++;	
		}
		System.out.println("String Array : "+Arrays.toString(str));
		String str1 = String.join(" ", str).replaceAll("\\₹", "").replaceAll(",","");; 
		String[] num =str1.split(" ");
		System.out.println("num[] : "+ Arrays.toString(num));
		
		int[] arr = new int [num.length];
		
		for(int q=0;q<num.length;q++)
		{
			arr[q]=Integer.valueOf(num[q]);
		}
		
		//finding the  most expensive product from the list. 
		max =arr[0];
		for(int x =1; x<arr.length;x++)
		{
			if(arr[x]>max)
			{
				max = arr[x];
			}
		}
		
		//if number is greater than 1000
		NumberFormat myFormat = NumberFormat.getInstance();
		  if (max>=1000)  {
		  double d = max; 
		  b =myFormat.format(d); //1,000
		  System.out.println(b);
		  } 
		  else
		  {
			 b=Integer.toString(max); 
		  }
		  System.out.println("Most expensive product has price : "+ b);
	}
	
	//By maximum = By.xpath("//div[contains(text(),'"+b+"')]/../..");
		
	public void expensive() throws Exception
	{
		//clicking on the most expensive product by using xpath
		Thread.sleep(2000);
		WebElement ok= driver.findElement(By.xpath("//div[contains(text(),'"+b+"')]/../.."));
		ok.click();
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", ok); 
			 
	}
	
	public void review() throws InterruptedException
	{
		//changing the window handle so that we can fetch rating as well as Reviews
		
		Thread.sleep(2000);
		String parent=driver.getWindowHandle();
		Set<String> all =  driver.getWindowHandles();
		System.out.println("Windows "+all.size());
		
		Iterator <String> ite = all.iterator();
		
		while(ite.hasNext())
		{
			String child = ite.next();
			
			if(!parent.equals(child))
			{
				driver.switchTo().window(child);
			}
		}
		
		WebElement wb =driver.findElement(rating);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", wb);
		wb.getText().toString();
		System.out.println("Ratings for this products are : "+wb.getText().toString());
		
		WebElement wb1 = driver.findElement(rev);
		System.out.println("Review is : "+wb1.getText().toString());
		System.out.println("completed");
		
	}
}
