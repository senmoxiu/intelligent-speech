package cs.service;

import java.util.List;

import cs.entity.ScoreCounterVO;
import cs.entity.ScoreVO;

public class ScoreService {
	private ScoreService() {
	}
	
	public static ScoreService service = new ScoreService();
	
	public Integer addScore(String stuNo, String courseNo, Integer score) {
		String sql = "insert into score (`student_no`, `course_no`, `mark`) value (?, ?, ?)";
		Object[] params = new Object[] { stuNo, courseNo, score };
		// 增删改都调用这个方法
		Integer rtn = DbService.dbUtil.genericDML(sql, params);
		return rtn;
	}
	
	public Integer addCourse(String stuNo, String courseNo) {
		String sql = "insert into score (`student_no`, `course_no`) value (?, ?)";
		Object[] params = new Object[] { stuNo, courseNo};
		// 增删改都调用这个方法
		Integer rtn = DbService.dbUtil.genericDML(sql, params);
		return rtn;
	}

	public Integer deleteScore(String stuNo, String courseNo) {
		String sql = "delete from score where course_no = ? and student_no = ?";
		Object[] params = new Object[] { courseNo , stuNo};
		Integer rtn = DbService.dbUtil.genericDML(sql, params);
		return rtn;
	}

	public Integer updateScore(String stuNo, String courseNo, Integer score) {
		String sql = "update score set mark = ? where course_no = ? and student_no = ?";
		Object[] params = new Object[] {score, courseNo, stuNo};
		Integer rtn = DbService.dbUtil.genericDML(sql, params);
		return rtn;
	}
	
	public List<ScoreVO> queryAll(){
		String sql = "select sco.student_no, stu.student_name, sco.course_no, c.course_name, sco.mark, "
				+ "stu.class_info from score sco left join student stu on sco.student_no=stu.student_no "
				+ "left join course c on sco.course_no=c.course_no";
		List<ScoreVO> list = DbService.dbUtil.genericQuery(sql, null, ScoreVO.class);
		for(ScoreVO scoreVO: list) {
			System.out.println(scoreVO.getStudentNo() + ", " + scoreVO.getStudentName() + ", " + 
					scoreVO.getCourseName() + ", " + scoreVO.getMark());
		}
		return list;
	}
	
	public Object[][] toArrayqueryAll(List<ScoreVO> list){
		if (list != null && list.size() > 0) {
			Object[][] arr = new Object[list.size()][4];
			for (int i = 0; i < list.size(); i++) {
				ScoreVO scoreVO = list.get(i);
				arr[i] = new Object[] {scoreVO.getStudentNo() + ", " + scoreVO.getStudentName() + ", " + 
						scoreVO.getCourseName() + ", " + scoreVO.getMark()};
			}
			return arr;
		}else {
			return new Object[0][0];
		}
	}
	
	
	public List<ScoreVO> queryInformationStuNO(String stuNo){
		String sql = " select student_no,student_name,class_info\r\n" + 
				"     from student\r\n" + 
				"     where student_no= ? ";
		Object[] params = new Object[] { stuNo };
		List<ScoreVO> list = DbService.dbUtil.genericQuery(sql, params, ScoreVO.class);
		for(ScoreVO scoreVO: list) {
			System.out.println(scoreVO.getStudentNo() + ", " + scoreVO.getStudentName() + ", " 
						+ scoreVO.getClassInfo());
		}
		return list;
	}
	
	public Object[][] toArrayInformationStuNO(List<ScoreVO> list){
		if (list != null && list.size() > 0) {
			Object[][] arr = new Object[list.size()][3];
			for (int i = 0; i < list.size(); i++) {
				ScoreVO scoreVO = list.get(i);
				arr[i] = new Object[] {scoreVO.getStudentNo() + ", " + scoreVO.getStudentName() + ", " 
						+ scoreVO.getClassInfo()};
			}
			return arr;
		}else {
			return new Object[0][0];
		}
	}
	
	public Integer queryInformation(String stuNo, String courseNo){
		String sql = " select sco.mark\r\n" + 
				"  from score sco left join student stu on sco.student_no=stu.student_no \r\n" + 
				" left join course c on sco.course_no=c.course_no\r\n" + 
				" where stu.student_no = ? and c.course_no = ?";
		Object[] params = new Object[] {stuNo, courseNo};
		List<ScoreVO> list = DbService.dbUtil.genericQuery(sql, params, ScoreVO.class);
		
		for(ScoreVO scoreVO: list) {
			System.out.println(scoreVO.getMark());
			return scoreVO.getMark();
		}
		return null;
	}
	
	public Integer queryInformation1(String stuNo, String courseNo){
		String sql = " select sco.mark\r\n" + 
				"  from score sco left join student stu on sco.student_no=stu.student_no \r\n" + 
				" left join course c on sco.course_no=c.course_no\r\n" + 
				" where stu.student_no = ? and c.course_no = ?";
		Object[] params = new Object[] {stuNo, courseNo};
//		List<ScoreVO> list = DbService.dbUtil.genericQuery(sql, params, ScoreVO.class);
//		
//		for(ScoreVO scoreVO: list) {
//			System.out.println(scoreVO.getMark());
//			return scoreVO.getMark();
//		}
		Integer rtn = DbService.dbUtil.genericDML(sql, params);
		return rtn;
	}
	
	public List<ScoreVO> querystudentCourseBystuNo(String studentNo) {
		  String sql = "select c.course_no,c.course_name "
		    + " from score sco left join student stu on sco.student_no=stu.student_no "
		    + " left join course c on sco.course_no=c.course_no " + " where stu.student_no = ? ";
		  Object[] params = new Object[] { studentNo };
		  List<ScoreVO> list = DbService.dbUtil.genericQuery(sql, params, ScoreVO.class);
		  return list;
		 }
	
	public List<ScoreVO> querystudentNotCourseBystuNo(String studentNo) {
		  String sql = " select course_no,course_name from course \r\n" + 
		  		"  where course_no not in (select course_no from score \r\n" + 
		  		"  where student_no= ?)";
		  Object[] params = new Object[] { studentNo };
		  List<ScoreVO> list = DbService.dbUtil.genericQuery(sql, params, ScoreVO.class);
		  return list;
		 }
	
	public ScoreCounterVO count(String classInfo, String courseNo) {
		String sql = "select max(sco.mark) as max_score, min(sco.mark) as min_score, "
				+ "ROUND(avg(sco.mark), 1) as avg_score from score sco left join student stu "
				+ "on sco.student_no=stu.student_no left join course c on sco.course_no=c.course_no" + 
				" where stu.class_info=? and sco.course_no=?";
		Object[] params = new Object[] {classInfo, courseNo};
		List<ScoreCounterVO> list = DbService.dbUtil.genericQuery(sql, params, ScoreCounterVO.class);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}
	
	public List<ScoreCounterVO> countScore1(String courseNo) {
		String sql = "select class_info, c.course_name, max(sco.mark) as max_score, min(sco.mark) as min_score, "
				+ "ROUND(avg(sco.mark), 1) as avg_score from score sco left join student stu "
				+ "on sco.student_no=stu.student_no left join course c on sco.course_no=c.course_no" + 
				" where sco.course_no=? group by class_info";
		Object[] params = new Object[] {courseNo};
		List<ScoreCounterVO> list = DbService.dbUtil.genericQuery(sql, params, ScoreCounterVO.class);
		return list;
	}
	
	public List<ScoreCounterVO> countScore2(String classInfo) {
		String sql = "select class_info, c.course_name, max(sco.mark) as max_score, min(sco.mark) as min_score, "
				+ "ROUND(avg(sco.mark), 1) as avg_score from score sco left join student stu "
				+ "on sco.student_no=stu.student_no left join course c on sco.course_no=c.course_no" + 
				" where stu.class_info= ? group by c.course_no";
		Object[] params = new Object[] {classInfo};
		List<ScoreCounterVO> list = DbService.dbUtil.genericQuery(sql, params, ScoreCounterVO.class);
		for(ScoreCounterVO scoreVO: list) {
			System.out.println(scoreVO.getCourseName());
		}
		return list;
	}
	
	public List<ScoreCounterVO> countScore3() {
		String sql = "select class_info, c.course_name, max(sco.mark) as max_score, min(sco.mark) as min_score, "
				+ "ROUND(avg(sco.mark), 1) as avg_score from score sco left join student stu "
				+ "on sco.student_no=stu.student_no left join course c on sco.course_no=c.course_no";
		
		List<ScoreCounterVO> list = DbService.dbUtil.genericQuery(sql, null, ScoreCounterVO.class);
		for(ScoreCounterVO scoreVO: list) {
			System.out.println(scoreVO.getCourseName());
		}
		return list;
	}
	
	public List<ScoreCounterVO> countGrade(String courseNo) {
		String sql = "select \r\n" + 
				"sum(case when sco.mark>=90 then 1 else 0 end) as grade_a,\r\n" + 
				"sum(case when sco.mark between 80 and 89 then 1 else 0 end) as grade_b,\r\n" + 
				"sum(case when sco.mark between 70 and 79 then 1 else 0 end) as grade_c,\r\n" + 
				"sum(case when sco.mark between 60 and 69 then 1 else 0 end) as grade_d,\r\n" + 
				"sum(case when sco.mark<=59 then 1 else 0 end) as grade_e\r\n" + 
				"from score sco left join student stu on sco.student_no=stu.student_no left join course c on sco.course_no=c.course_no\r\n" + 
				"where sco.course_no= ? group by stu.class_info";
		Object[] params = new Object[] {courseNo};
		List<ScoreCounterVO> list = DbService.dbUtil.genericQuery(sql, params, ScoreCounterVO.class);
		return list;
	}
	
	public List<ScoreCounterVO> countGrade2(String classInfo) {
		String sql = "select \r\n" + 
				"sum(case when sco.mark>=90 then 1 else 0 end) as grade_a,\r\n" + 
				"sum(case when sco.mark between 80 and 89 then 1 else 0 end) as grade_b,\r\n" + 
				"sum(case when sco.mark between 70 and 79 then 1 else 0 end) as grade_c,\r\n" + 
				"sum(case when sco.mark between 60 and 69 then 1 else 0 end) as grade_d,\r\n" + 
				"sum(case when sco.mark<=59 then 1 else 0 end) as grade_e\r\n" + 
				"from score sco left join student stu on sco.student_no=stu.student_no left join course c on sco.course_no=c.course_no\r\n" + 
				"where stu.class_info= ? group by sco.course_no";
		Object[] params = new Object[] {classInfo};
		List<ScoreCounterVO> list = DbService.dbUtil.genericQuery(sql, params, ScoreCounterVO.class);
		return list;
	}
	
	public Object[][] toArrayVO(List<ScoreCounterVO> list){
		if (list != null && list.size() > 0) {
			Object[][] arr = new Object[list.size()][5];
			for (int i = 0; i < list.size(); i++) {
				ScoreCounterVO scoreCounterVO = list.get(i);
				arr[i] = new Object[] {scoreCounterVO.getClassInfo(), scoreCounterVO.getCourseName(), 
						scoreCounterVO.getMaxScore(),scoreCounterVO.getMinScore(),
						scoreCounterVO.getAvgScore()};
			}
			return arr;
		}else {
			return new Object[0][0];
		}
	}
	
	public Object[][] toArrayCourse(List<ScoreVO> list){
		if (list != null && list.size() > 0) {
			Object[][] arr = new Object[list.size()][2];
			for (int i = 0; i < list.size(); i++) {
				ScoreVO scoreVO = list.get(i);
				arr[i] = new Object[] {scoreVO.getCourseNo(), scoreVO.getCourseName()};
			}
			return arr;
		}else {
			return new Object[0][0];
		}
	}
	
	public Object[][] toArray(List<ScoreVO> list){
		if (list != null && list.size() > 0) {
			Object[][] arr = new Object[list.size()][6];
			for (int i = 0; i < list.size(); i++) {
				ScoreVO scoreVO = list.get(i);
				arr[i] = new Object[] {scoreVO.getStudentNo(), scoreVO.getStudentName(), scoreVO.getCourseNo(),
						scoreVO.getCourseName(), scoreVO.getMark(), scoreVO.getClassInfo()};
			}
			return arr;
		}else {
			return new Object[0][0];
		}
	}

	public static void main(String[] args) {
//		ScoreService.service.queryAll();
//		ScoreCounterVO scoreCounterVO = ScoreService.service.count("21软工8班", "003");
//		System.out.println(scoreCounterVO.getMaxScore() + ", " +
//				scoreCounterVO.getMinScore() + ", " + scoreCounterVO.getAvgScore());
//		ScoreService.service.addScore("219950320", "101", 88);
//		System.out.println(ScoreService.service.querystudentNotCourseBystuNo("219970906"));
//		System.out.println(ScoreService.service.queryInformation("219970906", "104"));
//		ScoreService.service.countScore2("21软工9班");
		System.out.println(ScoreService.service.queryInformation("219970906", "104"));
	}


}
