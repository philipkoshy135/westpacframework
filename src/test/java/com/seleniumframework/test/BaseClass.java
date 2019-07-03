package com.seleniumframework.test;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass 
{
	protected static WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	protected ExtentReports extent;     
	protected ExtentTest test;
	InputStream inputStream;
	Properties prop ;

	/**
	 * This method runs before running any of the suite or test method
	 * This method invokes the method to create the webdriver,
	 * builds the constructs required for Extent report
	 *
	 * @author  philip_koshy@infosys.com
	 */
	@BeforeSuite
	public void setupApplication() throws IOException
	{
		htmlReporter = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		prop = new Properties();
		prop.load(BaseClass.class.getClassLoader().getResourceAsStream("config/config.properties"));
		createWebDriver();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
	}
	
	/**
	 * Based on the browser name given in config.properties file, this method creates the corresponding 
	 * webdriver. Currently only implemented for chrome. More browsers can be implemented easily
	 *
	 * @author  philip_koshy@infosys.com
	 */
	public void createWebDriver() {
		String browser = prop.getProperty("browser");
		switch(browser) {
		case "chrome" :
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox" :
			//Similarly Other Browsers can be implemented
			break;
		}
	}

	/**
	 * This method is invoked after each test method and using TestNG listners based on whether
	 * the test is failure,success or skip, corresponding entry is made into report
	 * @author  philip_koshy@infosys.com
	 */
	@AfterMethod
	public void getResult(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
		}
		else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}

	/**
	 * This method is invoked after all the test/suite is executed and this tear down the driver and 
	 * creates the final emailable extent report
	 * @author  philip_koshy@infosys.com
	 */
	@AfterSuite
	public void closeApplication()
	{
		driver.quit();
		extent.flush();
	}



}