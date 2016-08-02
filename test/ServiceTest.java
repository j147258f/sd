import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import com.sd.content.AttendenceTable;
import com.sd.service.AttendenceTableService;
import com.sd.service.ReAttendenceService;

public class ServiceTest {

	// buildtableformonth方法测试
	@Test
	public void testBuildTable() throws ParseException {
		AttendenceTableService a = new AttendenceTableService();
		List<Integer> atlist = a.buildtableformonth(1, 7, 2016)[1];
		for (Integer aa : atlist) {
			System.out.println(aa);
		}
	}
	
	//buildtableforexception异常表测试
	@Test
	public void testbuildtableforexception() throws ParseException{
		AttendenceTableService a = new AttendenceTableService();
		List<AttendenceTable> atlist = a.buildtableforexception(2016,7);
		for (AttendenceTable aa : atlist) {
			System.out.println(aa);
		}
	}
	
	//ReAttendenceService方法测试
	@Test
	public void testReAttendenceService(){
		ReAttendenceService ras = new ReAttendenceService();
		ras.reAttendence(1);
		
	}
}
