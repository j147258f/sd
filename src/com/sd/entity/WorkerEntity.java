package com.sd.entity;

/**
 * 对应数据库的员工表
 * 
 * @param model
 * @return json格式的数据
 */
public class WorkerEntity {
	private String workerID;
	private String workerName;
	public String getWorkerID() {
		return workerID;
	}
	public void setWorkerID(String workerID) {
		this.workerID = workerID;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
}
