package assignment2.models;

import assignment2.constants.Constants;
import assignment2.exceptions.InputValidationException;
import assignment2.utils.Validator;
import java.io.Serializable;
import java.util.HashSet;

public class User implements Comparable<User>, Serializable {

  protected String fullName, address;
  protected int rollNo, age;
  protected Course[] courses;

  static HashSet<Integer> rollNoTracker = new HashSet<Integer>();

  public User(String name, int age, String address, int rollNo, String courses) {
    try {
      Validator.validateFullName(name);
      Validator.validateAge(age);
      Validator.validateAddress(address);
      Validator.validateRollNo(rollNo, rollNoTracker);
      this.courses = Validator.processCourses(courses);
    } catch (InputValidationException e) {
      e.printStackTrace();
    }
    this.fullName = name;
    this.address = address;
    this.rollNo = rollNo;
    this.age = age;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(final String fullName) {
    this.fullName = fullName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public int getRollNo() {
    return rollNo;
  }

  public void setRollNo(final int rollNo) {
    this.rollNo = rollNo;
  }

  public int getAge() {
    return age;
  }

  public void setAge(final int age) {
    this.age = age;
  }

  public Course[] getCourses() {
    return courses;
  }

  public void setCourses(final Course[] courses) {
    this.courses = courses;
  }

  public static HashSet<Integer> getRollNoTracker() {
    return rollNoTracker;
  }

  public static void setRollNoTracker(final HashSet<Integer> rollNoTracker) {
    User.rollNoTracker = rollNoTracker;
  }

  @Override
  public String toString() {
    return "User [name=" + fullName + ", age=" + age + ", address=" + address + ", roll "
        + "number=" + rollNo + "]";
  }

  @Override
  public int compareTo(User u) {
    if (fullName.toLowerCase().charAt(0) == u.fullName.toLowerCase().charAt(0))
      return rollNo - u.rollNo;
    return fullName.toLowerCase().charAt(0) - u.fullName.toLowerCase().charAt(0);
  }


  public void display() {
    String[] coursesToPrint = new String[courses.length];

    for (int i = 0; i < courses.length; i++) {
      coursesToPrint[i] = String.valueOf(courses[i].getCourse());
    }

    System.out.println(fullName + Constants.TAB_SEPARATOR + rollNo + Constants.TAB_SEPARATOR + age
        + Constants.TAB_SEPARATOR + address + Constants.TAB_SEPARATOR + String.join(", ",
        coursesToPrint));
  }
}