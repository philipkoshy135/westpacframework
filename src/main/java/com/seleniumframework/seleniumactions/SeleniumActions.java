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


	public static void hover(WebDriver driver, WebElement element) {
	    Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	public static WebElement getDynamicWebElement(WebDriver driver, String xpathValue, String substitutionValue) {
		return driver.findElement(By.xpath(xpathValue.replace("%s", substitutionValue)));

	}


	public static List<WebElement> getDynamicWebElementList(WebDriver driver, String xpathValue, String substitutionValue) {
		return driver.findElements(By.xpath(xpathValue.replace("%s", substitutionValue)));

	}

	public static Wait<WebDriver> fluentWait(WebDriver driver, long timeOut, long pollingInterval) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
				.withTimeout(Duration.ofSeconds(timeOut)) 			
				.pollingEvery(Duration.ofSeconds(pollingInterval)) 			
				.ignoring(NoSuchElementException.class);
		return wait;
	}
	
    public static void waitForWithoutException(WebDriver driver, WebElement element) {
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
}
