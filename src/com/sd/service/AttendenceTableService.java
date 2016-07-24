package com.sd.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import com.sd.content.AttendenceTable;
import com.sd.dao.TempDAO;
import com.sd.entity.AttendenceEntity;
import com.sd.entity.OtherEntity;
import com.sd.entity.WorkerEntity;

public class AttendenceTableService {

	// 一个月的考勤表
	public List<AttendenceTable> buildtableformounth(String workerID,
			String mounth) throws ParseException {
		// 若没指定月份，则为当前月份
		if (mounth == null || mounth.isEmpty()) {
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("MM");
			mounth = df.format(date);
		}
		// 调用DAO层，从表中获取需要数据
		TempDAO dao = new TempDAO();
		List<AttendenceEntity> aelist = dao.selectattendencebyIDmounth(
				workerID, mounth);
		WorkerEntity we = dao.selectworker(workerID);
		// 根据数据组装考勤表
		List<AttendenceTable> atlist = new ArrayList();
		// 这里应该根据当前月的判定，时间不够先写为每个月都是三十天
		for (int i = 0; i < 30; i++) {
			AttendenceTable attendenceTable = new AttendenceTable();
			attendenceTable.setWorkerID(workerID);
			attendenceTable.setMounth(mounth);
			attendenceTable.setWorkerName(we.getWorkerName());
			// attendenceTable.setOfficetime(aelist.get(i).getTime());
			// attendenceTable.setOffworktime(aelist.get(i).getTime());

			Rule rule = new Rule();
			// 调用rule获得上下班时间，判断是否迟到 早退

			attendenceTable.setOfficetime(rule.officetimerule(workerID, aelist
					.get(i).getDay()));
			attendenceTable.setOffworktime(rule.offworktimerule(workerID,
					aelist.get(i).getDay()));
			attendenceTable.setAbsent(rule.whetherlate(
					attendenceTable.getOfficetime(),
					attendenceTable.getOffworktime()));

			// 在未旷工的情况下得到工作时间
			if (attendenceTable.getAbsent().equals("旷工")) {
				attendenceTable.setWorktime(rule.workhour(
						attendenceTable.getOfficetime(),
						attendenceTable.getOffworktime()));
			} else {
				attendenceTable.setWorktime("旷工");
			}
			atlist.add(attendenceTable);
		}
		return atlist;
	}

	// 异常考勤表,只返回有迟到早退旷工情况的员工当天的表单
	public List<AttendenceTable> buildtableforexception(String mounth)
			throws ParseException {
		List<AttendenceTable> atlist = new ArrayList();
		TempDAO dao = new TempDAO();
		List<AttendenceEntity> mounthtablelist = dao
				.selectattendencebymounth(mounth);
		for (AttendenceEntity ae : mounthtablelist) {
			AttendenceTable attendenceTable = new AttendenceTable();
			Rule rule = new Rule();
			// 调用rule获得上下班时间，判断是否迟到 早退
			attendenceTable.setOfficetime(rule.officetimerule(ae.getWorkerID(),
					ae.getDay()));
			attendenceTable.setOffworktime(rule.offworktimerule(
					ae.getWorkerID(), ae.getDay()));
			attendenceTable.setAbsent(rule.whetherlate(
					attendenceTable.getOfficetime(),
					attendenceTable.getOffworktime()));
			// 在未旷工的情况下得到工作时间
			if (!attendenceTable.getAbsent().equals("旷工")) {
				attendenceTable.setWorktime(rule.workhour(
						attendenceTable.getOfficetime(),
						attendenceTable.getOffworktime()));
			} else {
				attendenceTable.setWorktime("旷工");
			}
			// 判断旷工属性是否为空，若为空 则说明没有迟到早退旷工现象
			if (StringUtils.isEmpty(attendenceTable.getAbsent())) {

			} else {
				attendenceTable.setWorkerName(dao
						.selectworker(ae.getWorkerID()).getWorkerName());
				atlist.add(attendenceTable);
			}
		}
		return atlist;
	}
}
