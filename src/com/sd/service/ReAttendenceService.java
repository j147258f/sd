package com.sd.service;

import java.util.Date;

import com.sd.dao.TempDAO;

/**
 * 服务层，实现补签
 * 
 * author zhanghao
 * 
 */
public class ReAttendenceService {
	/**
	 * 根据员工ID 补签
	 * 
	 * @param workID 员工ID
	 * @param date 补签日期
	 */
	public void reAttendence(Integer workID,Date date){
		TempDAO dao=new TempDAO();
		dao.start();
		dao.reAttendence(workID,date);
		dao.close();
	}
}
