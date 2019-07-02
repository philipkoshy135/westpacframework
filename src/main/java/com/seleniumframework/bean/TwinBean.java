package com.seleniumframework.bean;


public class TwinBean extends UserBean {
	String voluntaryContribution;
	String savingGoalRequirement;
	String currentKiwiSaverBalance;
	String contributionFrequency;


	public TwinBean(String age, String status,String PIR,String riskProfile, String voluntaryContribution,String contributionFrequency, String savingGoalRequirement, String currentKiwiSaverBalance) {
    	super(age, status, PIR, riskProfile);
		this.voluntaryContribution = voluntaryContribution;
		this.savingGoalRequirement = savingGoalRequirement;
		this.currentKiwiSaverBalance = currentKiwiSaverBalance;
		this.contributionFrequency = contributionFrequency;
	}

	public String getVoluntaryContribution() {
		return voluntaryContribution;
	}

	public void setVoluntaryContribution(String voluntaryContribution) {
		this.voluntaryContribution = voluntaryContribution;
	}

	public String getSavingGoalRequirement() {
		return savingGoalRequirement;
	}

	public void setSavingGoalRequirement(String savingGoalRequirement) {
		this.savingGoalRequirement = savingGoalRequirement;
	}

	public String getCurrentKiwiSaverBalance() {
		return currentKiwiSaverBalance;
	}

	public void setCurrentKiwiSaverBalance(String currentKiwiSaverBalance) {
		this.currentKiwiSaverBalance = currentKiwiSaverBalance;
	}
    
    public String getContributionFrequency() {
		return contributionFrequency;
	}

	public void setContributionFrequency(String contributionFrequency) {
		this.contributionFrequency = contributionFrequency;
	}
	
	@Override
	public String toString() {
		return getStatus()+" user with age "+getAge();
	}

}
