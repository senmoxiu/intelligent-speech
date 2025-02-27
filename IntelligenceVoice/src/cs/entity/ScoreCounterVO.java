package cs.entity;

import java.math.BigDecimal;

public class ScoreCounterVO {
	private Integer maxScore;
	private Integer minScore;
	private BigDecimal avgScore;
	private String classInfo;
	private String courseName;
	private Integer gradeA;
	private Integer gradeB;
	private Integer gradeC;
	private Integer gradeD;
	private Integer gradeE;
	
	public Integer getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}
	public Integer getMinScore() {
		return minScore;
	}
	public void setMinScore(Integer minScore) {
		this.minScore = minScore;
	}
	public BigDecimal getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(BigDecimal avgScore) {
		this.avgScore = avgScore;
	}
	public String getClassInfo() {
		return classInfo;
	}
	public void setClassInfo(String classInfo) {
		this.classInfo = classInfo;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getGradeA() {
		return gradeA;
	}
	public void setGradeA(Integer gradeA) {
		this.gradeA = gradeA;
	}
	public Integer getGradeB() {
		return gradeB;
	}
	public void setGradeB(Integer gradeB) {
		this.gradeB = gradeB;
	}
	public Integer getGradeC() {
		return gradeC;
	}
	public void setGradeC(Integer gradeC) {
		this.gradeC = gradeC;
	}
	public Integer getGradeD() {
		return gradeD;
	}
	public void setGradeD(Integer gradeD) {
		this.gradeD = gradeD;
	}
	public Integer getGradeE() {
		return gradeE;
	}
	public void setGradeE(Integer gradeE) {
		this.gradeE = gradeE;
	}
	
}
