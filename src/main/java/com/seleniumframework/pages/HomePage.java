package com.seleniumframework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.AcceptedW3CCapabilityKeys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumframework.seleniumactions.SeleniumActions;

public class HomePage {
	@FindBy(id = "ubermenu-section-link-kiwisaver-ps")
	WebElement kiwiSaverListOnHeaderUberMenu;
	@FindBy(id = "ubermenu-item-cta-kiwisaver-calculators-ps")
	WebElement kiwiSaverCalculatorLink;

	private ExtentTest logger;
	private WebDriver driver;

	public HomePage(WebDriver driver,ExtentTest logger)
	{
		this.driver=driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}

	/**
	 * The method clicks on Kiwi Calculators link 
	 * from home page header KiwiSaver uber menu List 
	 *
	 * @author  philip_koshy@infosys.com
	 */
	public void clickKiwiSaverCalculatorsLink() {
		Wait<WebDriver> wait = SeleniumActions.fluentWait(driver, 6, 1);
		logger.log(Status.INFO, "Hovering on KiwiSaver List Link on Header Uber Menu");
		wait.until(ExpectedConditions.visibilityOf(kiwiSaverListOnHeaderUberMenu));
		SeleniumActions.hover(driver, kiwiSaverListOnHeaderUberMenu);
		wait.until(ExpectedConditions.elementToBeClickable(kiwiSaverCalculatorLink));
		kiwiSaverCalculatorLink.click();
		logger.log(Status.PASS, "Clicked on KiwiSaverCalculatorLink");
	}

	/**
	 * This method navigates to Westpac homepage
	 *
	 * @author  philip_koshy@infosys.com
	 */

	public void navigateToHomePage() {
		driver.navigate().to("https://www.westpac.co.nz/");
		logger.log(Status.PASS, "Navigated to HomePage");
	}
}
