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
	 * ��Դ/Ŀ�����
	 */
	private String Address;
	/**
	 * ��ǰ����
	 */
	private Date CurrentDate;
	/**
	 * 1Ϊ����2Ϊ����
	 */
	private int InfoType;
	/**
	 * ������������
	 */
	private String Body;	
}
