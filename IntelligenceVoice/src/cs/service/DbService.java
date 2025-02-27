package cs.service;

import java.util.List;
import cs.db.DbUtil;

public class DbService {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3307/gao?useUnicode=true&characterEncoding=utf8&useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	
	public static DbUtil dbUtil;
	static {
		dbUtil = new DbUtil(DB_URL, USER, PASSWORD);
	}
	public static DbUtil getDbUtil() {
		return dbUtil;
	}
	
	public static void main(String[] args) {
		System.out.println(DbService.dbUtil);
//		List<Student> list = DbService.dbUtil.genericQuery("select * from student", null, Student.class);
//		if (list != null) {
//			for(Student student: list) {
//				System.out.println(student.getId() + ", " + student.getName());
//			}
//		}

	}

}
