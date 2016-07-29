package com.sd.content;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 考勤类
 * 
 * author zhanghao
 * 
 */
public class AttendenceTable {
	// 员工Id
	private Integer workerID;
	// 员工姓名
	private String workerName;
	// 上班时间
	private Date officeTime;
	// 下班时间
	private Date offWorkTime;
	// 工作时长
	private String workTime;
	// 是否迟到早退
	private String absent;

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public Date getOfficeTime() {
		return officeTime;
	}

	public void setOfficeTime(Date officeTime) {
		this.officeTime = officeTime;
	}

	public Date getOffWorkTime() {
		return offWorkTime;
	}

	public void setOffWorkTime(Date offWorkTime) {
		this.offWorkTime = offWorkTime;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getAbsent() {
		return absent;
	}

	public void setAbsent(String absent) {
		this.absent = absent;
	}

	public Integer getWorkerID() {
		return workerID;
	}

	public void setWorkerID(Integer workerID) {
		this.workerID = workerID;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result="员工ID:"+this.workerID+" 姓名:"+this.workerName+" 上班时间:"+this.officeTime+
				" 下班时间:"+this.offWorkTime+" 是否迟到早退:"+this.absent;
		return result;
	}
	
}
