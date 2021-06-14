package com.wipro.webdriverCommands;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.wipro.config.StartBrowser;

public class ActionDriver 
{
	public WebDriver driver;
	
	public ActionDriver()
	{
		driver = StartBrowser.driver;
	}
	
	/**
	 * Used to navigate to application
	 * @param url -- Application url
	 */ 
	
	public void navigateToApp(String url)
	{
		try
		{
			driver.get(url);
			StartBrowser.childTest.pass("Navigated to URL : "+url);
		}
		catch (Exception e)
		{
			StartBrowser.childTest.fail("Unable to navigate to URL : "+url);
		}
	}
	
	/**
	 * Used to perform click action on button, link, radio button, check boxes
	 * @param locator -- Get it from object repository
	 * @param elementName -- Name of the element on which click action to be performed
	 */
	
	public void click(By locator, String elementName)
	{
		try
		{
			driver.findElement(locator).click();
			StartBrowser.childTest.pass("Performed click action on : "+elementName);
		}
		catch (Exception e) 
		{
			StartBrowser.childTest.fail("Unable to perform click action on : " +elementName , 
			MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
			StartBrowser.childTest.info(e);
			throw e;
		}
	}
	
	
	/**
	 * Used to perform type action on editable textbox;
	 * @param locator -- Get it from object repository
	 * @param testData -- Hardcode or get it form excel
	 * @param elementName -- Name of the element on which click action to be performed
	 */
	public void type(By locator, String testData , String elementName)
	{
		try 
		{
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(testData);
			StartBrowser.childTest.pass("Performed type action on : " + elementName + " with data : "+testData);
		}
		catch (Exception e)
		{
			StartBrowser.childTest.fail("Unable to performed type action on :" +elementName+ " with data : "+testData , 
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
			StartBrowser.childTest.info(e);
			throw e;
		}
	}
	
	public void mouseHover(By locator, String element)
	{
		try
		{
			Actions a = new Actions(driver);
			a.moveToElement(driver.findElement(locator)).build().perform();
			StartBrowser.childTest.pass("Performed mouse hover action on : " + element);
			
		} 
		catch (Exception e) 
		{
			StartBrowser.childTest.fail("Unable to performed mouse hover action on : " + element , 
			MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
			throw e;
		}
	}
	
	/**
	 * Used to extract Text
	 * @param locator -- get it from object repository
	 * @param element -- Name of the element on which text is extracted
	 */
	
	public String extractText(By locator, String element) 
	{
		String str = null;
		try
		{
			str = driver.findElement(locator).getText();
			StartBrowser.childTest.pass("Extract the message "+ str + " from : " + element);
		}
		catch (Exception e)
		{
			StartBrowser.childTest.fail("Unable to extract message from : "+ element);
			MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build();
		}
		return str;
		
	}
	/**
	 * To get all the Ids of window ie parent window and child window.
	 * @return Set <String>
	 */
	public Set <String> getWindowsId()
	{
		Set <String> s = null;
		try
		{
			 s = driver.getWindowHandles();
			 StartBrowser.childTest.pass("Successfully return the ids of all the window");
		
		}
		catch (Exception e)
		{
			StartBrowser.childTest.fail("Unable to get the windows ids");
			MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build();
		}
		return s;
	}
	/**
	 * USed to move to the differ window
	 * @param id = Id of the window
	 * @param windowName = name of the window
	 */
	
	public void moveToWindow(String id, String windowName)
	{
		try
		{
			driver.switchTo().window(id);
			StartBrowser.childTest.pass("Successfully moved to : "+ windowName + "window");
		}
		catch(Exception e)
		{
			StartBrowser.childTest.fail("Unable to move to the : "+ windowName + "window");
			MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build();
			throw e;
		}
		
	}
	
	/**
	 * USed to click on any ui using java script 
	 * @param locator -- Get it from OR
	 * @param element -- name of the UI.
	 */
	
	public void clickUsingJavaScrptit(By locator, String element)
	{
		try
		{
			WebElement ui = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", ui);
			StartBrowser.childTest.pass("performed click actionn on : "+element);
		}
		catch (Exception e)
		{
			StartBrowser.childTest.fail("Unable to performed clicked operation on : "+element);
			MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build();
			throw e;
		}
	}
		
	public String screenShot()
	{
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
}
