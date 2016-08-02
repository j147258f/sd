package com.sd.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sd.content.AttendenceTable;
import com.sd.dao.TempDAO;
import com.sd.entity.AttendenceEntity;
import com.sd.entity.OtherEntity;
import com.sd.entity.WorkerEntity;
import com.sd.utils.Rule;
import com.sd.utils.Tool;

/**
 * 服务层，实现考勤表相关逻辑
 * 
 * author zhanghao
 * 
 */
public class AttendenceTableService {

	/**
	 * 根据员工ID 查询年月 得到员工考勤表
	 * 
	 * @param workID 员工ID
	 * @param month 月
	 * @param year 年
	 * @return 考勤信息集合
	 */
	public List[] buildtableformonth(Integer workerID,
			Integer month, Integer year) throws ParseException {

		// 若没指定年月，则为当前年月
		if (month == null) {
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("MM");
			month = Integer.valueOf(df.format(date));
		}
		if (year == null) {
			Date date2 = new Date();
			SimpleDateFormat df = new SimpleDateFormat("YYYY");
			year = Integer.valueOf(df.format(date2));
		}

		// 调用DAO层，从表中获取需要数据
		TempDAO dao = new TempDAO();
		dao.start();
		List<AttendenceEntity> aelist = dao.selectAttendence(workerID, year,
				month);
		WorkerEntity we = dao.selectWorkerByID(workerID);
		OtherEntity oe = dao.selectother();
		dao.close();

		// 调用rule获得工作日集合
		List<Date> weekDaysList = Rule.getWeekDays(year, month);
		List[] result=new List[2];
		List[] list=this.getTable(workerID, aelist, we.getWorkerName(), oe, weekDaysList);
		result[0]=list[0];
		result[1]=list[2];
		return result;
	}

	/**
	 * 得到选定年月中所有迟到早退旷工员工的考勤信息表
	 * 
	 * @param month 月
	 * @param year 年
	 * @return 异常考勤信息集合
	 */
	public List<AttendenceTable> buildtableforexception(Integer year,
			Integer month) throws ParseException {
		List<AttendenceTable> atlist = new ArrayList<AttendenceTable>();
		TempDAO dao = new TempDAO();
		dao.start();
		// 保存所有用户信息的Map
		Map<Integer, String> workerMap = dao.getAllWorker();
		// 指定年月中的所有用户考勤信息的集合
		List<AttendenceEntity> aelist = dao.selectAttendenceForAllByMonth(year,
				month);
		OtherEntity oe = dao.selectother();
		dao.close();
		// 调用rule获得工作日集合
		List<Date> weekDaysList = Rule.getWeekDays(year, month);
		
		List<AttendenceTable> result=new ArrayList<AttendenceTable>();
		//遍历所有员工，得到
		for (Integer key : workerMap.keySet()) {  
			result.addAll(getTable(key, aelist, workerMap.get(key), oe, weekDaysList)[1]);
		} 
		return result;
	}
	
	/**
	 * 为减少代码重复，将考勤信息逻辑单独提出来
	 * 
	 * @param workID 员工ID
	 * @param aelist 考勤信息表
	 * @param workerName 员工姓名
	 * @param oe 其他信息表
	 * @param weekDaysList 选定月工作日集合
	 * @return 考勤信息集合数组，其中下标0为所有信息表，1为异常信息表
	 */
	private List[] getTable(Integer workerID, List<AttendenceEntity> aelist,
			String workerName, OtherEntity oe, List<Date> weekDaysList)
			throws ParseException {
		int hours=0;
		List<Integer> allHour=new ArrayList<Integer>();
		List<AttendenceTable> atlist = new ArrayList<AttendenceTable>();
		List<AttendenceTable> exceptiongList = new ArrayList<AttendenceTable>();
		for (Date workday : weekDaysList) {
			AttendenceTable attendenceTable = new AttendenceTable();

			// 设置基本信息 ID和姓名
			attendenceTable.setWorkerID(workerID);
			attendenceTable.setWorkerName(workerName);
			// 调用方法得到某工作日的上下班时间
			Date[] worktime = Rule.getWorkTime(workerID,workday, weekDaysList, aelist);
			Date officetime = worktime[0];
			Date offworktime = worktime[1];
			attendenceTable.setOfficeTime(officetime);
			attendenceTable.setOffWorkTime(offworktime);

			if (officetime == null && offworktime == null) {
				attendenceTable.setAbsent("旷工");
				atlist.add(attendenceTable);
				exceptiongList.add(attendenceTable);
				continue;
			}
			// 设定是否迟到
			if (Rule.islate(officetime,
					Tool.getLateTiming(oe.getOfficetime(), oe.getLatetime()))) {
				attendenceTable.setAbsent("迟到");
			}
			// 设定是否早退
			if (Rule.isLeaveEarly(offworktime,
					Tool.getLateTiming(oe.getLatetime(), oe.getLeavetime()),workday)) {
				attendenceTable.setAbsent(attendenceTable.getAbsent() + "早退");
			}
			if (!attendenceTable.getAbsent().isEmpty()) {
					exceptiongList.add(attendenceTable);
			}else{
				attendenceTable.setWorkTime(Tool.getWorkTime(officetime, offworktime));
				hours=hours+attendenceTable.getWorkTime();
			}
			atlist.add(attendenceTable);
		}
		allHour.add(hours);
		List[] list=new List[3];
		list[0]=atlist;
		list[1]=exceptiongList;
		list[2]=allHour;
		return list;
	}
}
