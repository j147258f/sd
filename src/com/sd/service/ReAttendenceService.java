package com.sd.service;

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
	 */
	public void reAttendence(Integer workID){
		TempDAO dao=new TempDAO();
		dao.start();
		dao.reAttendence(workID);
		dao.close();
	}
}
