package com.sd.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.util.StringUtils;

import com.sd.dao.TempDAO;
import com.sd.entity.AttendenceEntity;
import com.sd.entity.OtherEntity;

/**
 * 规则判定：一天中第一次打卡为上班，最后一次为下班 判断员工是否迟到早退
 * 
 */
public class Rule {

	// 得到第一次打卡的上班时间
	public String officetimerule(String workerID, String day) {
		TempDAO dao = new TempDAO();
		List<AttendenceEntity> aelist = dao.selectattendenceforday(workerID,
				day);
		return aelist.get(0).getTime();
	}

	// 得到最后一次打卡的下班时间
	public String offworktimerule(String workerID, String day) {
		TempDAO dao = new TempDAO();
		List<AttendenceEntity> aelist = dao.selectattendenceforday(workerID,
				day);
		if (aelist.size() == 1) {
			return "";
		}
		return aelist.get(aelist.size() - 1).getTime();
	}

	// 判断是否迟到早退旷工
	public String whetherlate(String officetime, String offworktime)
			throws ParseException {
		String result = "";
		TempDAO dao = new TempDAO();
		OtherEntity oe = dao.selectother();
		// 判旷工
		if (StringUtils.isEmpty(officetime) || StringUtils.isEmpty(offworktime)) {
			result = result + "旷工";
			return result;
		}
		// 判迟到
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		long late = sdf.parse(officetime).getTime()
				- sdf.parse(oe.getOfficetime()).getTime();
		if (late > sdf.parse(oe.getLatetime()).getTime()) {
			result = result + "迟到";
		}
		// 判早退
		long leave = sdf.parse(oe.getOffworktime()).getTime()
				- sdf.parse(offworktime).getTime();
		if (leave > sdf.parse(oe.getLatetime()).getTime()) {
			result = result + "早退";
		}
		// 判旷工半天
		if (late + leave >= sdf.parse(oe.getAbsenttime()).getTime()) {
			result = "旷工半天";
		}
		return result;
	}

	// 判断通过上下班时间得到工作时间
	public String workhour(String officetime, String offworktime) throws ParseException{
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			long time=sdf.parse(offworktime).getTime()-sdf.parse(officetime).getTime();
			long hour=time%(24*3600)/3600;
			return Long.toString(hour);
		}
}
