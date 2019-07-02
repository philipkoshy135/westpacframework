package com.seleniumframework.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumframework.bean.EmployedBean;
import com.seleniumframework.bean.TwinBean;
import com.seleniumframework.commonutils.HelpIconIdentifier;
import com.seleniumframework.commonutils.HelpIconMessages;
import com.seleniumframework.seleniumactions.SeleniumActions;

public class KiwiSaverRetirementCalculatorPage {

	@FindBy(xpath = "//div[@id='calculator-embed']/iframe")
	WebElement retirementCalculatorFrame;
	@FindBy(xpath = "//div[@help-id='CurrentAge']//input")
	WebElement currentAgeInputBox;
	@FindBy(xpath = "//div[@help-id='EmploymentStatus']//div[@ng-bind-html='selectedContent']/span")
	WebElement employmentStatusSelectedContent;
	@FindBy(xpath = "//div[@help-id='PIRRate']//div[@ng-bind-html='selectedContent']/span")
	WebElement pirRateSelectedContent;
	@FindBy(xpath = "//div[@help-id='VoluntaryContributions']//div[@ng-bind-html='selectedContent']/span")
	WebElement voluntaryContributionFrequencySelectedContent;
	@FindBy(xpath = "//div[@help-id='KiwiSaverBalance']//input")
	WebElement currentKiwiSaverBalanceInputBox;
	@FindBy(xpath = "//div[@help-id='VoluntaryContributions']//input")
	WebElement voluntaryContributionsInputBox;
	@FindBy(xpath = "//div[@id='widget-loading-mask']")
	WebElement loadingMask;
	@FindBy(xpath = "//div[@help-id='AnnualIncome']//input")
	WebElement salaryInputBox;
	@FindBy(xpath = "//div[@help-id='SavingsGoal']//input")
	WebElement savingsGoalInputBox;
	@FindBy(xpath = "//span[@ng-show='ctrl.data.formCompleted']")
	WebElement submitFormButton;
	@FindBy(xpath = "//div[@class='results-heading']//span[contains(@class,'result-value')]")
	WebElement resultValue;

	private String helpIcons = "//div[@help-id='%s']/button/i";
	private String helpMessageXpath = "//div[@help-id='%s']//div[@class='message-row ng-scope']//div[not(contains(@class,'ng-hide'))]/p";
	private String riskProfilehelpMessageXpath = "//div[@help-id='RiskProfile']//div[@class='message-row ng-scope']//div[not(contains(@class,'ng-hide'))]/ul/li";
	private String employeeStatusList = "//div[@help-id='EmploymentStatus']//li//span[contains(text(),'%s')]";
	private String voluntaryContributionList = "//div[@help-id='VoluntaryContributions']//li//span[contains(text(),'%s')]";
	private String pirRateList = "//div[@help-id='PIRRate']//li//span[contains(text(),'%s')]";
	private String kiwiSaverMemberContributionRadioButton = "//div[@help-id='KiwiSaverMemberContribution']//input[@value='%s']";
	private String riskProfileRadioButton = "//div[@help-id='RiskProfile']//input[@value='%s']";

	private WebDriver driver;
	private ExtentTest logger;
	private Wait<WebDriver> wait;

	public KiwiSaverRetirementCalculatorPage(WebDriver driver, ExtentTest logger)
	{
		this.driver=driver;
		this.logger = logger;
		wait = SeleniumActions.fluentWait(driver, 6, 1);
		PageFactory.initElements(driver, this);
	}

	/**
	 * The method clicks on Click Here To Get Started Link
	 * Under Westpac KiwiSaver Scheme Retirement Calculator
	 *
	 * @author  philip_koshy@infosys.com
	 */
	public void switchToCalculatorFrame() throws NoSuchFieldException, SecurityException {
		wait = SeleniumActions.fluentWait(driver, 10, 3);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(retirementCalculatorFrame));	
		logger.log(Status.PASS, "Switched to Calculator Frame");	
	}

	/**
	 * The method verifies whether all the help icons are displayed
	 * and clicks on the icons and verifies the messages displayed 
	 * This a generic method that has the capability to click and verify all the help icons based on values in Constant Enum class: @see {@link HelpIconIdentifier} class
	 * @author  philip_koshy@infosys.com
	 */
	public void verifyHelpIconAndMessages() {
		SeleniumActions.waitForWithoutException(driver, loadingMask);
		SoftAssert softAssert = new SoftAssert();
		for (HelpIconIdentifier id : HelpIconIdentifier.values()) {
			WebElement helpIcon = SeleniumActions.getDynamicWebElement(driver, helpIcons, id.toString());
			logger.log(Status.INFO, "Verifying "+id.toString()+" icon displayed");	
			softAssert.assertTrue(helpIcon.isDisplayed(), "Verifying "+id.toString()+" icon displayed");
			wait.until(ExpectedConditions.elementToBeClickable(helpIcon));
			helpIcon.click();
			WebElement helpMessage = SeleniumActions.getDynamicWebElement(driver, helpMessageXpath, id.toString());
			logger.log(Status.INFO, "Verifying message for "+id.toString()+" icon");	
			softAssert.assertEquals(helpMessage.getText(), HelpIconMessages.valueOf(id.toString()).getNode(), "Verifying message for "+id.toString()+" icon");
			if(id.toString().equalsIgnoreCase("RiskProfile")) {
				int riskProfileListCounter =1;
				List<WebElement> riskProfileHelpMessageList = SeleniumActions.getDynamicWebElementList(driver, riskProfilehelpMessageXpath, id.toString());
				for(WebElement element : riskProfileHelpMessageList) {
					assertEquals(element.getText(), HelpIconMessages.valueOf(id.toString()+riskProfileListCounter).getNode());
					softAssert.assertEquals(element.getText(), HelpIconMessages.valueOf(id.toString()+riskProfileListCounter).getNode(), "Verifying message for "+id.toString()+" icon");
					riskProfileListCounter++;
				}
			}
		}
		logger.log(Status.INFO, "Asserting all verification of Icon and Messages");	
		softAssert.assertAll();
		logger.log(Status.PASS, "Icons and Messages Verified Successfully");
	}

	public void validateRetirementCalculation(Object bean) {
		enterCalculatorFields(bean);
		clickSubmitFormButton();
		verifyCalculationResult();
		
	}
	/**
	 * This method invokes corresponding method to
	 * fill/select values in Kiwi Saver Retirement Calculator based on the user type
	 * @author  philip_koshy@infosys.com
	 */
	public void enterCalculatorFields(Object bean) {
		SeleniumActions.waitForWithoutException(driver, loadingMask);
		wait.until(ExpectedConditions.elementToBeClickable(currentAgeInputBox));
		currentAgeInputBox.click();
		currentAgeInputBox.clear();
		if(bean instanceof EmployedBean) {
			EmployedBean employedBean = (EmployedBean) bean;
			enterCalculatorFieldsForEmployed(employedBean);
		}
		else {
			TwinBean twinBean = (TwinBean) bean;
			enterCalculatorFieldsForSelfAndNotEmployed(twinBean);	
		}	
		
	}

	/**
	 * This method fills/selects values for fields in Kiwi Saver Retirement Calculator for Employed User
	 * @author  philip_koshy@infosys.com
	 * @return void
	 */

	public void enterCalculatorFieldsForEmployed(EmployedBean employedBean) {
		logger.log(Status.INFO, "Entering values for Employment Status: "+employedBean.getStatus());
		enterCommonCalculatorFields(employedBean.getAge(), employedBean.getStatus(), employedBean.getPIR(),  employedBean.getRiskProfile().toLowerCase());	
		salaryInputBox.sendKeys(employedBean.getSalary());
		logger.log(Status.PASS, "Entered Salary as : "+ employedBean.getSalary());
		SeleniumActions.getDynamicWebElement(driver, kiwiSaverMemberContributionRadioButton, employedBean.getMemberContribution()).click();
		logger.log(Status.PASS, "Selected Member Contribution as : "+ employedBean.getMemberContribution());
	}

	/**
	 * This method fills/selects values for fields in Kiwi Saver Retirement Calculator for Not/Self Employed User
	 * @author  philip_koshy@infosys.com
	 * @return void
	 */

	public void enterCalculatorFieldsForSelfAndNotEmployed(TwinBean twinBean) {
		logger.log(Status.INFO, "Entering values for Employment Status: "+twinBean.getStatus());
		enterCommonCalculatorFields(twinBean.getAge(), twinBean.getStatus(), twinBean.getPIR(),  twinBean.getRiskProfile().toLowerCase());		
		currentKiwiSaverBalanceInputBox.sendKeys(twinBean.getCurrentKiwiSaverBalance());
		logger.log(Status.PASS, "Entered Kiwi Saver Balance as : "+ twinBean.getCurrentKiwiSaverBalance());
		savingsGoalInputBox.sendKeys(twinBean.getSavingGoalRequirement());
		logger.log(Status.PASS, "Entered Saving Goal requirement as : "+ twinBean.getSavingGoalRequirement());
		voluntaryContributionsInputBox.sendKeys(twinBean.getVoluntaryContribution());
		logger.log(Status.PASS, "Entered Voluntary Contribution as : "+ twinBean.getVoluntaryContribution());
		voluntaryContributionFrequencySelectedContent.click();
		SeleniumActions.getDynamicWebElement(driver, voluntaryContributionList, twinBean.getContributionFrequency()).click();
		logger.log(Status.PASS, "Selected Voluntary Contribution frequency as : "+ twinBean.getContributionFrequency());
	}

	/**
	 * This method fills/selects values for common fields in Kiwi Saver Retirement Calculator for all user Types
	 * @author  philip_koshy@infosys.com
	 * @return void
	 */


	public void enterCommonCalculatorFields(String age, String employmentStatus, String pirRate, String riskProfile) {
		currentAgeInputBox.sendKeys(age);
		logger.log(Status.PASS, "Entered Age as : "+ age);
		employmentStatusSelectedContent.click();
		SeleniumActions.getDynamicWebElement(driver, employeeStatusList, employmentStatus).click();
		logger.log(Status.PASS, "Selected Employment Status as : "+ employmentStatus);
		pirRateSelectedContent.click();
		SeleniumActions.getDynamicWebElement(driver, pirRateList,pirRate).click();
		logger.log(Status.PASS, "Selected PIR rate as : "+ pirRate);
		SeleniumActions.getDynamicWebElement(driver, riskProfileRadioButton,riskProfile).click();
		logger.log(Status.PASS, "Selected Risk Profile as : "+ riskProfile);
	}
	
	public void clickSubmitFormButton() {
		wait.until(ExpectedConditions.elementToBeClickable(submitFormButton)).click();
		logger.log(Status.PASS, "Clicked Submit Form Button");
	}
	
	public void verifyCalculationResult() {
		assertTrue(wait.until(ExpectedConditions.visibilityOf(resultValue)).isDisplayed(),"Verifiying Calculation Result Displayed");
		logger.log(Status.PASS, "Result was calculated as "+resultValue.getText());
	}
}
