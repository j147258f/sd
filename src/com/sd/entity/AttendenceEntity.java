package com.sd.entity;

/**
 * 对应数据库的签到表
 * 
 * @param model
 * @return json格式的数据
 */
public class AttendenceEntity {
	//ID
	private String workerID;
	//年
	private String year;
	//月
	private String mounth;
	//日
	private String day;
	//时间
	private String time;
	public String getWorkerID() {
		return workerID;
	}
	public void setWorkerID(String workerID) {
		this.workerID = workerID;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMounth() {
		return mounth;
	}
	public void setMounth(String mounth) {
		this.mounth = mounth;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
