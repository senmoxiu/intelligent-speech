package cs.service;

import java.util.List;

import cs.entity.CourseBean;
import cs.entity.StudentBean;

public class StudentService {
	private StudentService() {
	}

	public static StudentService service = new StudentService();

	public Integer addStudent(String stuNo, String stuName,String stuClass) {
		String sql = "insert into student (`student_no`, `student_name`,`class_info`) value (?, ?, ?)";
		Object[] params = new Object[] { stuNo, stuName, stuClass };
		// 增删改都调用这个方法
		Integer rtn = DbService.dbUtil.genericDML(sql, params);
		return rtn;
	}
	public Integer addStudent1(String stuNo) {
		String sql = "insert into user (`user_name`) value ( ?)";
		Object[] params = new Object[] { stuNo };
		// 增删改都调用这个方法
		Integer rtn = DbService.dbUtil.genericDML(sql, params);
		return rtn;
	}

	public Integer deleteStudent(String stuNo) {
		String sql = "delete from student where student_no = ?";
		Object[] params = new Object[] { stuNo };
		Integer rtn = DbService.dbUtil.genericDML(sql, params);
		return rtn;
	}

	public Integer updateStudentByStudentNo(String stuName, String stuNo,String stuClass) {
		String sql = "update student set student_name = ?, class_info = ? where student_no = ? ";
		Object[] params = new Object[] {stuName, stuClass, stuNo};
		Integer rtn = DbService.dbUtil.genericDML(sql, params);
		return rtn;
	}

	public List<StudentBean> queryAll() {
		String sql = "select * from student";
		List<StudentBean> list = DbService.dbUtil.genericQuery(sql, null, StudentBean.class);
		return list;
	}
	
	public Object[][] toArray(List<StudentBean> list){
		if (list != null && list.size() > 0) {
			Object[][] arr = new Object[list.size()][3];
			for (int i = 0; i < list.size(); i++) {
				StudentBean studentBean = list.get(i);
				arr[i] = new Object[] {studentBean.getStudentNo(), studentBean.getStudentName(),studentBean.getClassInfo()};
			}
			return arr;
		}else {
			return new Object[0][0];
		}
	}

	public List<StudentBean> queryStudentByStudentName(String stuName ) {
		 String sql = "select * from student where student_name like concat('%', ?, '%')";
		 Object[] params = new Object [] {stuName};
		 List<StudentBean> list = DbService.dbUtil.genericQuery(sql, params, StudentBean.class);
		 if(list != null) {
			 for (StudentBean studentBean :list) {
				 System.out.println(studentBean.getStudentNo() + "," + studentBean.getStudentName());
			 }
		 }
		 return list;
	}
	
	public List<StudentBean> queryStudentByStudentNo(String stuNo ) {
		 String sql = "select * from student where student_no = ?";
		 Object[] params = new Object [] {stuNo};
		 List<StudentBean> list = DbService.dbUtil.genericQuery(sql, params, StudentBean.class);
		 if(list != null) {
			 for (StudentBean studentBean :list) {
				 System.out.println(studentBean.getStudentNo() + "," + studentBean.getStudentName());
			 }
		 }
		 return list;
	}

	public List<StudentBean> queryStudentByStudentClass(String stuClass ) {
		 String sql = "select * from student where class_info = ?";
		 Object[] params = new Object [] {stuClass};
		 List<StudentBean> list = DbService.dbUtil.genericQuery(sql, params, StudentBean.class);
		 if(list != null) {
			 for (StudentBean studentBean :list) {
				 System.out.println(studentBean.getStudentNo() + "," + studentBean.getStudentName());
			 }
		 }
		 return list;
	}
	// 修改、根据课程名查询课程、查询所有课程 DbService.dbUtil.genericQuery

	public static void main(String[] args) {
//		System.out.println("====" + StudentService.service.addStudent("001", "李规划","21软工3班")); 
//		System.out.println("====" + StudentService.service.deleteStudent("001"));
//		System.out.println("====" + StudentService.service.updateStudentByStudentNo("数据", "001", "21软工8班"));
		System.out.println(StudentService.service.queryAll());
//		System.out.println(StudentService.service.queryStudentByStudentName("数据"));
	}
}
