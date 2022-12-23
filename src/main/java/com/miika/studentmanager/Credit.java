package com.miika.studentmanager;


public class Credit {

  private long creditId;
  private String teacher;
  private long points;
  private java.sql.Date passed;
  private long courseId;
  private long studentIdFk;


  public long getCreditId() {
    return creditId;
  }

  public void setCreditId(long creditId) {
    this.creditId = creditId;
  }


  public String getTeacher() {
    return teacher;
  }

  public void setTeacher(String teacher) {
    this.teacher = teacher;
  }


  public long getPoints() {
    return points;
  }

  public void setPoints(long points) {
    this.points = points;
  }


  public java.sql.Date getPassed() {
    return passed;
  }

  public void setPassed(java.sql.Date passed) {
    this.passed = passed;
  }


  public long getCourseId() {
    return courseId;
  }

  public void setCourseId(long courseId) {
    this.courseId = courseId;
  }


  public long getStudentIdFk() {
    return studentIdFk;
  }

  public void setStudentIdFk(long studentIdFk) {
    this.studentIdFk = studentIdFk;
  }

}
