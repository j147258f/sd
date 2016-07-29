import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sd.dao.TempDAO;
import com.sd.entity.AttendenceEntity;
import com.sd.entity.OtherEntity;

public class DaoTest {

	private SessionFactory sessionfactory;
	private Session session;
	private Transaction transacation;

	@Before
	public void init() {
		// 配置对象
		Configuration config = new Configuration().configure();
		// 服务注册对象
		ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder();
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.applySettings(config.getProperties()).buildServiceRegistry();
		// 会话工厂
		sessionfactory = config.buildSessionFactory(serviceRegistry);
		// 会话对象
		session = sessionfactory.openSession();
		// 开启事务
		transacation = session.beginTransaction();

	}

	@After
	public void destory() {
		transacation.commit();
		session.close();
		sessionfactory.close();
	}

	//主键查询及链接测试
	@Test
	public void testHirbernate() {
//		AttendenceEntity ae=new AttendenceEntity();
//		ae.setID("222");
//		ae.setWorkerID("222");
//		Date d=new Date();
//		ae.setClockTime(d);
		OtherEntity oe=(OtherEntity)session.get(OtherEntity.class, 1);
		System.out.println(oe.getOfficetime());
		System.out.println(oe.getLatetime());
	}
	
	//条件查询测试
	@Test
	public void testSelectByWhere() throws ParseException{
//		String sql="from others where latetime=?";
		String sql="from AttendenceEntity where clocktime>= ? and clocktime<= ? ";
		Query query = session.createQuery(sql);  
//		SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
//		Date d=df.parse("00:05:00");
//		query.setTime(0,d );
//		query.setString(0, "00:05:00");
		query.setString(0, "2016-07-27 00:00:00");
		query.setString(1, "2016-07-27 23:59:59");
		List<AttendenceEntity> list = query.list(); 
		System.out.println(list.get(0).getID());
		System.out.println(list.size());
		
	}
	
	//Dao层selectAttendence方法测试
		@Test
		public void testSelectAttendence(){
			TempDAO dao=new TempDAO();
			dao.start();
			List<AttendenceEntity> list=dao.selectAttendence(2, 2016, 7);
			System.out.println(list.size());
			dao.close();
		}
	
	//Dao层getAllWorker方法测试
	@Test
	public void testgetAllWorker(){
		TempDAO dao=new TempDAO();
		dao.start();
		Map<Integer,String> map=dao.getAllWorker();
		System.out.println(map);
		dao.close();
	}
	
	//Dao层getAllAttenence方法测试
		@Test
		public void testgetAllAttenence(){
			TempDAO dao=new TempDAO();
			dao.start();
			List<AttendenceEntity> ae=dao.selectAttendenceForAllByMonth(2016, 7);
			System.out.println(ae.size());
			dao.close();
		}

}

