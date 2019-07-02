package com.seleniumframework.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumframework.seleniumactions.SeleniumActions;

public class KiwiSaverCalculatorsPage {

	@FindBy(xpath = "//span[contains(text(),'Westpac KiwiSaver Scheme Retirement Calculator')]//parent::h3//following-sibling::p//a[contains(.,'Click here to get started')]")
	WebElement clickHereToGetStartedLink;

	private WebDriver driver;
	private ExtentTest logger;
	private Wait<WebDriver> wait;
	
	public KiwiSaverCalculatorsPage(WebDriver driver,ExtentTest logger)
	{
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
		wait = SeleniumActions.fluentWait(this.driver, 6, 1);
	}

	/**
	 * The method clicks on Click Here To Get Started Link
	 * Under Westpac KiwiSaver Scheme Retirement Calculator
	 *
	 * @author  philip_koshy@infosys.com
	 */
	public void clickOnClickHereToGetStartedLink() {
		wait.until(ExpectedConditions.elementToBeClickable(clickHereToGetStartedLink));
		clickHereToGetStartedLink.click();
		logger.log(Status.PASS, "Clicked on Get Started Link");

	}

}
