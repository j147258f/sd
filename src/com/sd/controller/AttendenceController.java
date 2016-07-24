package com.sd.controller;

import java.text.ParseException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.content.AttendenceTable;
import com.sd.service.AttendenceTableService;
import com.sd.service.WorkTimeService;

/**
 * 考勤
 * 
 * author zhanghao
 * 
 */
@Controller
@RequestMapping("/attendence")
public class AttendenceController {

	/**
	 * 获取上下班时间
	 * 
	 * @param model
	 * @return json格式的数据
	 */
	@RequestMapping(value = "/workhours", method = RequestMethod.GET)
	public @ResponseBody
	Object getworkhours(Model model) {
		WorkTimeService wt = new WorkTimeService();
		String officetime = wt.getOfficeTime();
		String offworkhour = wt.getOffWorkTime();
		model.addAttribute("workhour", officetime);
		model.addAttribute("offworkhour", offworkhour);
		return model;
	}

	/**
	 * 设置上下班时间
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/workhours", method = RequestMethod.PUT)
	public String setworkhours(@RequestParam("officetime") String officetime,
			@RequestParam("offworkhour") String offworkhour) {
		WorkTimeService wt = new WorkTimeService();
		wt.setOfficeTime(officetime);
		wt.setOffWorkTime(offworkhour);
		return "success";
	}

	/**
	 * 获取迟到早退时间,缺勤半天时间
	 * 
	 * @param model
	 * @return json格式的数据
	 */
	@RequestMapping(value = "/lateleave", method = RequestMethod.GET)
	public @ResponseBody
	Object getlateleave(Model model) {
		WorkTimeService wt = new WorkTimeService();
		model.addAttribute("latetime", wt.getLateTime());
		model.addAttribute("leavetime", wt.getLeaveEarlyTime());
		model.addAttribute("absenttime", wt.getAbsentTime());
		return model;
	}

	/**
	 * 设置上下班时间
	 * 
	 * @param latetime 要设置的迟到多久算迟到
	 * @param leavetime 要设置的早退多久算早退
	 * @param absenttime 缺少多少小时算旷工半天
	 */
	@RequestMapping(value = "/lateleave", method = RequestMethod.PUT)
	public String setlateleave(@RequestParam("latetime") String latetime,
			@RequestParam("leavetime") String leavetime,
			@RequestParam("absenttime") String absenttime) {
		WorkTimeService wt = new WorkTimeService();
		wt.setLateTime(latetime);
		wt.setLeaveEarlyTime(leavetime);
		wt.setAbsentTime(absenttime);
		return "success";
	}

	/**
	 * 获取员工考勤表
	 * 
	 * @param workerID 员工id
	 * @param mounth 指定的月份
	 * @return json格式的数据
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/attendencetable", method = RequestMethod.GET)
	public @ResponseBody
	Object getattendencetable(@RequestParam("workerID") String workerID,
			@RequestParam("mounth") String mounth) throws ParseException {
		AttendenceTableService ats = new AttendenceTableService();
		return ats.buildtableformounth(workerID, mounth);
	}
	
	/**
	 * 获取全体员工异常考勤表
	 * 
	 * @param mounth 指定的月份
	 * @return json格式的数据
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/attendencealltable", method = RequestMethod.GET)
	public @ResponseBody Object getattendencealltable(@RequestParam("mounth") String mounth) throws ParseException {
		AttendenceTableService ats = new AttendenceTableService();
		return ats.buildtableforexception(mounth);
	}

}
