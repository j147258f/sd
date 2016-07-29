package com.sd.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sd.dao.TempDAO;
import com.sd.entity.AttendenceEntity;

/**
 * 规则判定：一天中第一次打卡为上班，最后一次为下班 判断员工是否迟到早退
 * 
 * @author zhanghao
 * 
 */
public class Rule {
	private static int a = 0;

	/**
	 * 判断是否迟到
	 * 
	 * @param factOfficetime 实际打卡时间
	 * @param officetime 公司规定的最晚上班时间
	 * @return true 迟到 false 没迟到
	 */
	public static boolean islate(Date factOfficetime, String officetime)
			throws ParseException {
		if (factOfficetime == null) {
			return true;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String temp = sdf.format(factOfficetime);
		Date d1 = sdf.parse(temp);
		Date d2 = sdf.parse(officetime);
		return d1.after(d2);
	}

	/**
	 * 判断是否早退
	 * 
	 * @param factOffworktime 实际打卡时间
	 * @param offworktime 公司规定的最早下班时间
	 * @return true 早退 false 没早退
	 */
	public static boolean isLeaveEarly(Date factOffworktime, String offworktime)
			throws ParseException {
		if (factOffworktime == null) {
			return true;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String temp = sdf.format(factOffworktime);
		Date d1 = sdf.parse(temp);
		Date d2 = sdf.parse(offworktime);
		return d2.before(d1);
	}

	/**
	 * 得到工作日集合
	 * 
	 * @param year 选定年
	 * @param month 选定月
	 * @return 选定年月下的工作日集合
	 */
	public static List<Date> getWeekDays(Integer year, Integer month) {
		List<Date> dates = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);
		while (cal.get(Calendar.YEAR) == year
				&& cal.get(Calendar.MONTH) < month) {
			int day = cal.get(Calendar.DAY_OF_WEEK);

			if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
				dates.add((Date) cal.getTime().clone());
			}
			cal.add(Calendar.DATE, 1);
		}
		return dates;
	}

	/**
	 * 某日上下班时间
	 * 
	 * @param workerID 员工ID
	 * @param workday 选定日
	 * @param weekdaysList 选定月中的所有工作日集合
	 * @param attendenceList 该员工选定月中的所有考情信息集合
	 * @return 选定年月下的工作日集合
	 */
	public static Date[] getWorkTime(Integer workerID, Date workday,
			List<Date> weekdaysList, List<AttendenceEntity> attendenceList) {
		String officetime = null;
		String offworktime = null;
		Date officeDate = null;
		Date offWorkTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String workDayToString = sdf.format(workday);

		for (AttendenceEntity ae : attendenceList) {
			if (workerID != ae.getWorkerID()) {
				continue;
			}
			String clocktime = ae.getOnlyTime();
			String clockday = ae.getOnlyDay();
			if (clockday.equals(workDayToString)) {
				if (officetime == null) {
					officetime = clocktime;
					officeDate = ae.getClockTime();
				} else {
					offworktime = clocktime;
					offWorkTime = ae.getClockTime();
				}
			} else {
				continue;
			}
		}
		Date[] date = new Date[2];
		date[0] = officeDate;
		date[1] = offWorkTime;

		return date;
	}
}
