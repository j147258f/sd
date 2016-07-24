package com.sd.entity;

/**
 * 对应数据库的参数表
 * 
 */
public class OtherEntity {
	//旷工多久算旷工半天
	String absenttime;
	//公司规定的上班时间
	String officetime;
	//公司规定的下班时间
	String offworktime;
	//公司规定的迟到多久算迟到；
	String latetime;
	//公司规定的早退多久算早退;
	String leavetime;
	public String getAbsenttime() {
		return absenttime;
	}
	public void setAbsenttime(String absenttime) {
		this.absenttime = absenttime;
	}
	public String getOfficetime() {
		return officetime;
	}
	public void setOfficetime(String officetime) {
		this.officetime = officetime;
	}
	public String getOffworktime() {
		return offworktime;
	}
	public void setOffworktime(String offworktime) {
		this.offworktime = offworktime;
	}
	public String getLatetime() {
		return latetime;
	}
	public void setLatetime(String latetime) {
		this.latetime = latetime;
	}
	public String getLeavetime() {
		return leavetime;
	}
	public void setLeavetime(String leavetime) {
		this.leavetime = leavetime;
	}
}
