package com.aroc.system;

import java.util.Date;

public class SMSInfo {
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public Date getCurrentDate() {
		return CurrentDate;
	}
	public void setCurrentDate(Date currentDate) {
		CurrentDate = currentDate;
	}
	public int getInfoType() {
		return InfoType;
	}
	public void setInfoType(int infoType) {
		InfoType = infoType;
	}
	public String getBody() {
		return Body;
	}
	public void setBody(String body) {
		Body = body;
	}
	/**
	 * 来源/目标号码
	 */
	private String Address;
	/**
	 * 当前日期
	 */
	private Date CurrentDate;
	/**
	 * 1为收信2为发信
	 */
	private int InfoType;
	/**
	 * 短信主体内容
	 */
	private String Body;	
}
