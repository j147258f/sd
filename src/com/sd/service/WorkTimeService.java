package com.sd.service;

import java.text.SimpleDateFormat;

import com.sd.dao.TempDAO;
import com.sd.entity.OtherEntity;

/**
 * 服务层，实现具体设定上下班时间等逻辑
 * 
 * author zhanghao
 * 
 */
public class WorkTimeService {

	/**
	 * 得到公司设定的上班时间
	 * 
	 * @return 上班时间
	 */
	public String getOfficeTime() {
		TempDAO dao=new TempDAO();
		dao.start();
		OtherEntity oe=dao.selectother();
		dao.close();
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		return sdf.format(oe.getOfficetime());
	}

	/**
	 * 得到公司设定的下班时间
	 * 
	 * @return 下班时间
	 */
	public String getOffWorkTime() {
		TempDAO dao=new TempDAO();
		dao.start();
		OtherEntity oe=dao.selectother();
		dao.close();
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		return sdf.format(oe.getOffworktime());
	}

	/**
	 * 设定上班时间
	 * 
	 * @param officetime 上班时间
	 */
	public void setOfficeTime(String officetime) {
	}

	/**
	 * 设定下班时间
	 * 
	 * @param officetime 下班时间
	 */
	public void setOffWorkTime(String offworktime) {
	}

	/**
	 * 得到公司规定的迟到时间
	 * 
	 * @return 迟到多久算迟到
	 */
	public String getLateTime() {
		TempDAO dao=new TempDAO();
		dao.start();
		OtherEntity oe=dao.selectother();
		dao.close();
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		return sdf.format(oe.getLatetime());
	}

	/**
	 * 得到公司规定的早退时间
	 * 
	 * @return 早退多久算早退
	 */
	public String getLeaveEarlyTime() {
		TempDAO dao=new TempDAO();
		dao.start();
		OtherEntity oe=dao.selectother();
		dao.close();
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		return sdf.format(oe.getLeavetime());
	}

	/**
	 * 得到公司规定的旷工时间
	 * 
	 * @return 旷工多久算旷工
	 */
	public String getAbsentTime() {
		String absenttime = "01:00:00";
		return absenttime;
	}

	/**
	 * 设定旷工时间
	 * 
	 * @param officetime 旷工时间
	 */
	public void setAbsentTime(String absenttime) {
	}

	/**
	 * 设定迟到时间
	 * 
	 * @param officetime 迟到时间
	 */
	public void setLateTime(String latetime) {
	}

	/**
	 * 设定早退时间
	 * 
	 * @param officetime 早退时间
	 */
	public void setLeaveEarlyTime(String leavetime) {
	}

}
