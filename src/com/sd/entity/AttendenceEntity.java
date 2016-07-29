package com.sd.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 对应数据库的签到表
 * 
 * author zhanghao
 * 
 */
public class AttendenceEntity {

	// 员工ID
	private Integer workerID;

	// 签到表主键 ID，自增
	private Integer ID;

	// 签到时间
	private Date ClockTime;
	
	//增加和员工信息表的映射关系
	private Set<WorkerEntity> workers=new HashSet<WorkerEntity>();

	/**
	 * 对日期字段拆分，拆分为yyyy-mm-dd
	 * 
	 * @return yyyy-mm-dd的日期
	 */
	public String getOnlyDay() {
		if (this.ClockTime == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		return sdf.format(this.ClockTime);
	}
	
	/**
	 * 对日期字段拆分，拆分为hh:mm:ss
	 * 
	 * @return hh:mm:ss的时间
	 */
	public String getOnlyTime() {
		if (this.ClockTime == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(this.ClockTime);
	}

	public Integer getWorkerID() {
		return workerID;
	}

	public void setWorkerID(Integer workerID) {
		this.workerID = workerID;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Date getClockTime() {
		return ClockTime;
	}

	public void setClockTime(Date clockTime) {
		ClockTime = clockTime;
	}

	public Set<WorkerEntity> getWorkers() {
		return workers;
	}

	public void setWorkers(Set<WorkerEntity> workers) {
		this.workers = workers;
	}
	
	public AttendenceEntity() {
	
	}
}
