package com.sd.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 工具类，主要完成Date类型的相加
 * 
 * @author zhanghao
 * 
 */
public class Tool {

	/**
	 * 通过公司规定的上班时间和迟到多久算迟到，得到具体迟到时刻
	 * 
	 * @param date1 公司规定的上班时间
	 * @param date2 迟到多久算迟到
	 * @return 具体迟到时刻
	 */
	public static String getLateTiming(Date date1, Date date2)
			throws ParseException {
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
		Calendar ca = Calendar.getInstance();
		ca.setTime(date1);
		String tempTime = sdfTime.format(date2);
		long time = sdfTime.parse(tempTime).getTime()
				- sdfTime.parse("00:00:00").getTime();
		int minute = (int) time / 1000 / 60;
		int hour = (int) time / 1000 / 3600;
		int second = (int) time / 1000;
		ca.add(Calendar.MINUTE, minute);
		ca.add(Calendar.HOUR, hour);
		ca.add(Calendar.SECOND, second);
		return sdfTime.format(ca.getTime());
	}
}
