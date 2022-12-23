package com.miika.studentmanager;


public class CourseImplementation {

  private java.sql.Timestamp starts;
  private java.sql.Timestamp ends;
  private long points;
  private long courseImplementationId;
  private long courseId;


  public java.sql.Timestamp getStarts() {
    return starts;
  }

  public void setStarts(java.sql.Timestamp starts) {
    this.starts = starts;
  }


  public java.sql.Timestamp getEnds() {
    return ends;
  }

  public void setEnds(java.sql.Timestamp ends) {
    this.ends = ends;
  }


  public long getPoints() {
    return points;
  }

  public void setPoints(long points) {
    this.points = points;
  }


  public long getCourseImplementationId() {
    return courseImplementationId;
  }

  public void setCourseImplementationId(long courseImplementationId) {
    this.courseImplementationId = courseImplementationId;
  }


  public long getCourseId() {
    return courseId;
  }

  public void setCourseId(long courseId) {
    this.courseId = courseId;
  }

}
