package assignment2.models;

import java.io.Serializable;

public class Course implements Serializable {

  protected char course;

  public Course(final char courses) {
    this.course = courses;
  }

  public char getCourse() {
    return course;
  }

  public void setCourse(final char courses) {
    this.course = courses;
  }
}
