package com.sd.entity;

/**
 * 对应数据库的员工表
 * 
 * author zhanghao
 * 
 */
public class WorkerEntity {
	
	// 员工ID
	private Integer workerID;
	// 员工姓名
	private String workerName;

	public Integer getWorkerID() {
		return workerID;
	}

	public void setWorkerID(Integer workerID) {
		this.workerID = workerID;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public WorkerEntity() {

	}
}
