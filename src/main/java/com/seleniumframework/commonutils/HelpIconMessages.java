package com.seleniumframework.commonutils;

public enum HelpIconMessages {
	CurrentAge("This calculator has an age limit of 84 years old."),
	EmploymentStatus("If you are earning a salary or wage, select ‘Employed’. Your employer contributions will be automatically calculated at a rate of 3% of your before-tax salary or wages. You can also select ‘Self-employed’ or ‘Not employed’ and then enter below (in the Voluntary contributions field), the amount and frequency of any contributions that you wish to make."), 
	AnnualIncome("Only include your total annual income that is paid to you by your employer(s). Other income sources such as rental income or dividends should not be included."), 
	KiwiSaverMemberContribution("You can choose to contribute a regular amount equal to 3%, 4%, 6%, 8% or 10% of your before-tax salary or wage. If you do not select a rate, your rate will be 3%."),
	PIRRate("This is your prescribed investor rate (PIR) for tax purposes. If you don't know what your PIR is, click on the ‘Find My Rate’ button."),
	KiwiSaverBalance("If you do not have a KiwiSaver account, then leave this field blank."),
	VoluntaryContributions("If you are 'Self-Employed' or 'Not employed', you can make direct contributions to your KiwiSaver account. If you are 'Employed', you can make voluntary contributions in addition to your regular employee contributions."),
	RiskProfile("The risk profile affects your potential investment returns:"),
	RiskProfile1("Low risk investments usually contain mostly income assets. Generally, investments of this nature can be expected to deliver modest but more consistent returns. They are less likely to go up and down, but will usually provide lower returns over the long term."),
	RiskProfile2("Medium risk investments are spread more evenly between income assets and growth assets. Generally, these types of investments can be expected to provide moderate levels of returns with moderate levels of investment risk. Returns will vary and may be low or negative in some years."), 
	RiskProfile3("High risk investments usually contain mostly growth assets. Investments with more exposure to growth assets have the potential for higher long-term returns, but they are more likely to go up and down in the short term and will experience periods of negative returns."), 
	SavingsGoal("Enter the amount you would like to have saved when you reach your intended retirement age. If you aren’t sure what this amount is, you can leave it blank or use the Sorted Retirement Planner");
	private final String message;
	
	private HelpIconMessages(String message) {
		this.message = message;
	}
	
	public String getNode() {
		return message;
	}
}
