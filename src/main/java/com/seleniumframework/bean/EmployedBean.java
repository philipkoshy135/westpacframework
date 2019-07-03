package com.seleniumframework.bean;

public class EmployedBean extends UserBean {
	String salary;
	String memberContribution;
    
    public EmployedBean(String age, String status, String salary, String memberContribution, String PIR, String riskProfile, String projection) {
    	super(age, status, PIR, riskProfile, projection);
		this.salary = salary;
		this.memberContribution = memberContribution;
	}

	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getMemberContribution() {
		return memberContribution;
	}
	public void setMemberContribution(String memberContribution) {
		this.memberContribution = memberContribution;
	}
	
	@Override
	public String toString() {
		return getStatus()+" user with age "+getAge();
	}

}
