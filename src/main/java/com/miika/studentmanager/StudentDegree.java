package com.miika.studentmanager;


public class StudentDegree {

  private long studentDegreeId;
  private long degree;
  private java.sql.Date started;
  private java.sql.Date completed;
  private long studentId;


  public long getStudentDegreeId() {
    return studentDegreeId;
  }

  public void setStudentDegreeId(long studentDegreeId) {
    this.studentDegreeId = studentDegreeId;
  }


  public long getDegree() {
    return degree;
  }

  public void setDegree(long degree) {
    this.degree = degree;
  }


  public java.sql.Date getStarted() {
    return started;
  }

  public void setStarted(java.sql.Date started) {
    this.started = started;
  }


  public java.sql.Date getCompleted() {
    return completed;
  }

  public void setCompleted(java.sql.Date completed) {
    this.completed = completed;
  }


  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }

}
