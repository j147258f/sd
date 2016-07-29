package com.sd.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.sd.entity.AttendenceEntity;
import com.sd.entity.OtherEntity;
import com.sd.entity.WorkerEntity;

/**
 * 用于和数据库交互
 * 
 * author zhanghao
 * 
 */
public class TempDAO {

	private SessionFactory sessionfactory;
	private Session session;
	private Transaction transacation;

	/**
	 * 封装链接数据库的相关配置
	 * 
	 * 
	 */
	public void start() {
		// 配置对象
		Configuration config = new Configuration().configure();
		// 服务注册对象
		ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder();
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.applySettings(
				config.getProperties()).buildServiceRegistry();
		// 会话工厂
		sessionfactory = config.buildSessionFactory(serviceRegistry);
		// 会话对象
		session = sessionfactory.openSession();
		// 开启事务
		transacation = session.beginTransaction();
	}

	/**
	 * 释放链接
	 * 
	 * 
	 */
	public void close() {
		transacation.commit();
		session.close();
		sessionfactory.close();
	}

	/**
	 * 根据员工ID查询员工信息表
	 * 
	 * @param workID 员工ID
	 * @return 与员工信息表相对应的Entity
	 */
	public WorkerEntity selectWorkerByID(Integer workID) {
		WorkerEntity we = (WorkerEntity) session
				.get(WorkerEntity.class, workID);
		return we;
	}
	
	/**
	 * 获取所有员工信息
	 * 
	 * @return 以员工Id为Key的Map
	 */
	public Map<Integer,String> getAllWorker() {
		Query query = session.createQuery("from WorkerEntity");
		Iterator iterator= query.iterate();
		Map<Integer,String> workMap=new HashMap<Integer,String>();
		while(iterator.hasNext()){
			WorkerEntity weTemp=(WorkerEntity)iterator.next();
			workMap.put(weTemp.getWorkerID(), weTemp.getWorkerName());
		}
		return workMap;
	}

	/**
	 * 查询其他表，获取上下班时间的相关设置
	 * 
	 */
	public OtherEntity selectother() {
		OtherEntity oe = (OtherEntity) session.get(OtherEntity.class, 1);
		return oe;
	}

	/**
	 * 根据员工ID和年月查询打卡信息表
	 * 
	 * @param workID 员工ID
	 * @param year 年
	 * @param month 月
	 * @return 打卡信息集合
	 */
	public List<AttendenceEntity> selectAttendence(Integer workID, Integer year,
			Integer month) {
		String hql = "from AttendenceEntity where clocktime>= ? and clocktime<= ? and workerID= ?";
		Query query = session.createQuery(hql);
		String begintime = year + "-" + month + "-01" + " 00:00:00";
		String endtime = year + "-" + (month + 1) + "-01" + " 00:00:00";
		query.setString(0, begintime);
		query.setString(1, endtime);
		query.setInteger(2,workID);
		List<AttendenceEntity> aelist = query.list();
		return aelist;
	}
	
	/**
	 * 根据年月查询打卡信息表
	 * 
	 * @param workID 员工ID
	 * @param year 年
	 * @param month 月
	 * @return 打卡信息集合
	 */
	public List<AttendenceEntity> selectAttendenceForAllByMonth( Integer year,
			Integer month) {
		String hql = "from AttendenceEntity where clocktime>= ? and clocktime<= ?";
		Query query = session.createQuery(hql);
		String begintime = year + "-" + month + "-01" + " 00:00:00";
		String endtime = year + "-" + (month + 1) + "-01" + " 00:00:00";
		query.setString(0, begintime);
		query.setString(1, endtime);
		List<AttendenceEntity> aelist = query.list();
		return aelist;
	}
}
