package com.sd.entity;

import java.util.Date;

/**
 * 对应数据库的参数表
 * 
 * author zhanghao
 * 
 */
public class OtherEntity {

	// ID
	private Integer ID;
	// 旷工多久算旷工半天
	private String absenttime;
	// 公司规定的上班时间
	private Date officetime;
	// 公司规定的下班时间
	private Date offworktime;
	// 公司规定的迟到多久算迟到；
	private Date latetime;
	// 公司规定的早退多久算早退;
	private Date leavetime;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getAbsenttime() {
		return absenttime;
	}

	public void setAbsenttime(String absenttime) {
		this.absenttime = absenttime;
	}

	public Date getOfficetime() {
		return officetime;
	}

	public void setOfficetime(Date officetime) {
		this.officetime = officetime;
	}

	public Date getOffworktime() {
		return offworktime;
	}

	public void setOffworktime(Date offworktime) {
		this.offworktime = offworktime;
	}

	public Date getLatetime() {
		return latetime;
	}

	public void setLatetime(Date latetime) {
		this.latetime = latetime;
	}

	public Date getLeavetime() {
		return leavetime;
	}

	public void setLeavetime(Date leavetime) {
		this.leavetime = leavetime;
	}
	
	public OtherEntity() {
		
	}
}
