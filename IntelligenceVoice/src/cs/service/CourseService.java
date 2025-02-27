package cs.service;

import java.util.List;

import cs.entity.CourseBean;

public class CourseService {
	private CourseService() {
	}

	public static CourseService service = new CourseService();

	public Integer addCourse(String courseNo, String courseName) {
		String sql = "insert into course (`course_no`, `course_name`) value (?, ?)";
		Object[] params = new Object[] { courseNo, courseName };
		// 增删改都调用这个方法
		Integer rtn = DbService.dbUtil.genericDML(sql, params);
		return rtn;
	}

	public Integer deleteCourse(String courseNo) {
		String sql = "delete from course where course_no = ?";
		Object[] params = new Object[] { courseNo };
		Integer rtn = DbService.dbUtil.genericDML(sql, params);
		return rtn;
	}
	
	public Integer deleteCourse1(String courseNo) {
		String sql = "delete from score where course_no = ?";
		Object[] params = new Object[] { courseNo };
		Integer rtn = DbService.dbUtil.genericDML(sql, params);
		return rtn;
	}

	public Integer updateCourseByCourseNo(String courseName, String courseNo) {
		String sql = "update course set course_name = ? where course_no = ? ";
		Object[] params = new Object[] {courseName, courseNo};
		Integer rtn = DbService.dbUtil.genericDML(sql, params);
		return rtn;
	}

	public List<CourseBean> queryAll() {
		String sql = "select * from course";
		List<CourseBean> list = DbService.dbUtil.genericQuery(sql, null, CourseBean.class);
		return list;
	}
	
	public List<CourseBean> queryAllCourse() {
		String sql = "select course_name from course";
		List<CourseBean> list = DbService.dbUtil.genericQuery(sql, null, CourseBean.class);
		return list;
	}
	
	//转化成二维数组
	public Object[][] toArray(List list){
		if (list != null && list.size() > 0) {
			Object[][] arr = new Object[list.size()][2];
			for (int i = 0; i < list.size(); i++) {
				CourseBean courseBean = ((java.util.List<CourseBean>) list).get(i);
				arr[i] = new Object[] {courseBean.getCourseNo(), courseBean.getCourseName()};
			}
			return arr;
		}else {
			return new Object[0][0];
		}
	}

	public List<CourseBean> queryCourseByCourseName(String courseName ) {
		 String sql = "select course_no,course_name from course where course_name like concat('%', ?, '%') ";
		 Object[] params = new Object [] {courseName};
		 List<CourseBean> list = DbService.dbUtil.genericQuery(sql, params, CourseBean.class);
		 if(list != null) {
			 for (CourseBean courseBean :list) {
				 System.out.println(courseBean.getCourseNo() + "," + courseBean.getCourseName());
			 }
		 }
		 return list;
	}

	public List<CourseBean> queryCourseByCourseNo(String courseNo ) {
		 String sql = "select * from course where course_no = ?";
		 Object[] params = new Object [] {courseNo};
		 List<CourseBean> list = DbService.dbUtil.genericQuery(sql, params, CourseBean.class);
		 if(list != null) {
			 for (CourseBean courseBean :list) {
				 System.out.println(courseBean.getCourseNo() + "," + courseBean.getCourseName());
			 }
		 }
		 return list;
	}
	// 修改、根据课程名查询课程、查询所有课程 DbService.dbUtil.genericQuery

	public static void main(String[] args) {
//		System.out.println("====" + CourseService.service.addCourse("001", "Java面向对象")); 
//		System.out.println("====" + CourseService.service.deleteCourse("001"));
//		System.out.println("====" + CourseService.service.updateCourseByCourseNo("数学建模", "001"));
//		System.out.println(CourseService.service.queryAll());
		System.out.println(CourseService.service.queryCourseByCourseName("Java"));
		
//		String sql = "select course_name from course";
//		List<CourseBean> list = DbService.dbUtil.genericQuery(sql, null, CourseBean.class);
//		System.out.println(list.toString());
//		list.toString();
	}

}
