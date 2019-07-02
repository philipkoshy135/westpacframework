package com.seleniumframework.test;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.seleniumframework.dataprovider.KiwiSaverCalculatorProvider;
import com.seleniumframework.pages.HomePage;
import com.seleniumframework.pages.KiwiSaverCalculatorsPage;
import com.seleniumframework.pages.KiwiSaverRetirementCalculatorPage;
public class MainTest extends BaseClass{

	HomePage homepage;
	KiwiSaverCalculatorsPage kiwiSaverCalculatorsPage;
	KiwiSaverRetirementCalculatorPage kiwiSaverRetirementCalculatorPage;   

	@Test
	public void validateKiwiSaverRetirementCalculator() throws NoSuchFieldException, SecurityException {
	test = extent.createTest("Validate KiwiSaver Retirement Calculator Help Icons");
	homepage= new HomePage(driver, test);
	kiwiSaverCalculatorsPage = new KiwiSaverCalculatorsPage(driver, test);
	kiwiSaverRetirementCalculatorPage = new KiwiSaverRetirementCalculatorPage(driver, test);
	test.log(Status.INFO, "Clicking Kiwi Saver Calculator Link");
	homepage.clickKiwiSaverCalculatorsLink();
	test.log(Status.INFO, "Clicking on Get Started Link under Retirement Calculator");
	kiwiSaverCalculatorsPage.clickOnClickHereToGetStartedLink();
	kiwiSaverRetirementCalculatorPage.switchToCalculatorFrame();	
	test.log(Status.INFO, "Verifying Help Icon Messages");
	kiwiSaverRetirementCalculatorPage.verifyHelpIconAndMessages();
	}

	@Test(dataProvider="kiwiSaverCalculatorProvider",dataProviderClass=KiwiSaverCalculatorProvider.class)
	public void verifyKiwiSaverRetirementCalculation(Object bean) throws Exception {
		String beanDetails = bean.getClass().getMethod("toString").invoke(bean).toString();
		test = extent.createTest("Validate KiwiSaver Retirement Calculation for "+beanDetails);
		homepage= new HomePage(driver,test);
		kiwiSaverCalculatorsPage = new KiwiSaverCalculatorsPage(driver, test);
		kiwiSaverRetirementCalculatorPage = new KiwiSaverRetirementCalculatorPage(driver, test);
		test.log(Status.INFO, "Navigating to HomePage");
		homepage.navigateToHomePage();
		test.log(Status.INFO, "Clicking Kiwi Saver Calculator Link");
		homepage.clickKiwiSaverCalculatorsLink();
		test.log(Status.INFO, "Clicking on Get Started Link under Retirement Calculator");
		kiwiSaverCalculatorsPage.clickOnClickHereToGetStartedLink();
		kiwiSaverRetirementCalculatorPage.switchToCalculatorFrame();
		test.log(Status.INFO, "Validating Retirement Calculation");
		kiwiSaverRetirementCalculatorPage.validateRetirementCalculation(bean);
	}

}
