package com.seleniumframework.bean;

public class UserBean {
	String age;
	String status;
    String PIR;
    String riskProfile;
    public UserBean(String age, String status, String PIR, String riskProfile) {
		this.age = age;
		this.status = status;
		this.PIR = PIR;
		this.riskProfile = riskProfile;
	}
    
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPIR() {
		return PIR;
	}
	public void setPIR(String pIR) {
		PIR = pIR;
	}
	public String getRiskProfile() {
		return riskProfile;
	}
	public void setRiskProfile(String riskProfile) {
		this.riskProfile = riskProfile;
	}
}
