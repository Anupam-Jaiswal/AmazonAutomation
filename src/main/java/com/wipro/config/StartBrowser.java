package com.wipro.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class StartBrowser 
{
	public static WebDriver driver;
	
	//variable related to reporting
	
	public static ExtentReports extent;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;
	ExtentSparkReporter sparkReporter;
	
	//Initializing the report
	
	@BeforeTest
	public void generateReport()
	{
		sparkReporter = new ExtentSparkReporter("Reports/AutomationReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
	}
	
	@BeforeMethod
	public void methodName(Method method)
	{
		parentTest = extent.createTest(method.getName());
	}
	
	
	@BeforeClass
	public void invokeDriver()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@AfterClass
	public void afterClass()
	{
		driver.quit();
		extent.flush(); //To close the report, then only we can see the report.
	}

}
