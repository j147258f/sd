package com.sd.dao;

import java.util.ArrayList;
import java.util.List;

import com.sd.entity.AttendenceEntity;
import com.sd.entity.OtherEntity;
import com.sd.entity.WorkerEntity;



public class TempDAO {
	//根据员工id和月份查询签到表
	public List<AttendenceEntity> selectattendencebyIDmounth(String workID,String mounth){
		List<AttendenceEntity> aelist=new ArrayList();
		return aelist;
	}
	
	//根据月份查询签到表
		public List<AttendenceEntity> selectattendencebymounth(String mounth){
			List<AttendenceEntity> aelist=new ArrayList();
			return aelist;
		}
	
	//根据员工id查询员工表
	public WorkerEntity selectworker(String workID){
		WorkerEntity we =new WorkerEntity();
		
		return we;
	}
	//查询参数表
	public OtherEntity selectother(){
		OtherEntity oe =new OtherEntity();
		
		return oe;
	}
	
	//根据员工id和日期查询签到表,得到的结果集用日期排序，从小到大
		public List<AttendenceEntity> selectattendenceforday(String workID,String day){
			List<AttendenceEntity> aelist=new ArrayList();
			return aelist;
		}
}
