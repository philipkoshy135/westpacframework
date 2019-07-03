package com.seleniumframework.seleniumactions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;


public class SeleniumActions{


	/**
	 * This method is used to hover over passed web element using Actions class
	 * @author  philip_koshy@infosys.com
	 * @return void
	 */
	public static void hover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	/**
	 * This method accepts a string values which is an xpath and replaces a specific text with passed substitution value,
	 * then finds the element and returns the WebElement
	 * @author  philip_koshy@infosys.com
	 */
	public static WebElement getDynamicWebElement(WebDriver driver, String xpathValue, String substitutionValue) {
		try {
			return driver.findElement(By.xpath(xpathValue.replace("%s", substitutionValue)));
		}
		catch (Exception e) {
			return null;
		}
	}


	/**
	 * This method accepts a string values which is an xpath and replaces a specific text with passed substitution value,
	 * then finds the elements and returns the WebElement List
	 * @author  philip_koshy@infosys.com
	 */
	public static List<WebElement> getDynamicWebElementList(WebDriver driver, String xpathValue, String substitutionValue) {
		return driver.findElements(By.xpath(xpathValue.replace("%s", substitutionValue)));

	}


	/**
	 * This  method returns a wait initialized with fluent wait with passed timeout and polling interval value
	 * @author  philip_koshy@infosys.com
	 */
	public static Wait<WebDriver> fluentWait(WebDriver driver, long timeOut, long pollingInterval) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
				.withTimeout(Duration.ofSeconds(timeOut)) 			
				.pollingEvery(Duration.ofSeconds(pollingInterval)) 			
				.ignoring(NoSuchElementException.class);
		return wait;
	}

	/**
	 * This  method wait for invisibility of an element without causing an exception, this is used for 
	 * element that may not always be present in the page
	 * @author  philip_koshy@infosys.com
	 */
	public static void waitForNotPresentWithoutException(WebDriver driver, WebElement element) {
		try {
			if(element.isDisplayed()) {
				try {
					fluentWait(driver, 3, 1).until(ExpectedConditions.invisibilityOf(element));
				}
				catch (Exception e) {
					Reporter.log("Loading Widget visibility turned false before fluentWait");
				}

			}

		} catch (Exception e) {
			Reporter.log("Loading Widget was not displayed");
		}
	}

	/**
	 *This method is used as while using sendekeys for input elements inside an iframe, certain times 
	 * it does not perform as expected, causing some leak
	 * @author  philip_koshy@infosys.com
	 */
	public static void type(WebElement element, String text) {
		element.click();
		element.clear();
		element.sendKeys(text);
	}

	/**
	 *This method checks whether if an element is displayed or not And overrides the issue of if element is null
	 * @author  philip_koshy@infosys.com
	 */
	public static boolean isDisplayed(WebElement element) {
		if(element!=null && element.isDisplayed()) {
			return true;	
		}
		else
		{
			return false;
		}
	}
}
