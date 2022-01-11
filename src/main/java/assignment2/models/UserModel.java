package assignment2.models;

import assignment2.constants.Constants;
import java.io.Serializable;
import java.util.HashSet;

public class UserModel implements Comparable<UserModel>, Serializable {

  protected String fullName, address;
  protected int rollNo, age;
  protected char[] courses;

  static HashSet<Integer> rollNoTracker = new HashSet<Integer>();

  public UserModel(String name, int age, String address, int rollNo, char[] courses) {
    this.fullName = name;
    this.address = address;
    this.rollNo = rollNo;
    this.age = age;
    this.courses = courses;
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

  public char[] getCourses() {
    return courses;
  }

  public void setCourses(final char[] courses) {
    this.courses = courses;
  }

  public static HashSet<Integer> getRollNoTracker() {
    return rollNoTracker;
  }

  public static void setRollNoTracker(final HashSet<Integer> rollNoTracker) {
    UserModel.rollNoTracker = rollNoTracker;
  }

  @Override
  public String toString() {
    return "User [name=" + fullName + ", age=" + age + ", address=" + address + ", roll "
        + "number=" + rollNo + "]";
  }

  @Override
  public int compareTo(UserModel u) {
    if (fullName.toLowerCase().charAt(0) == u.fullName.toLowerCase().charAt(0))
      return rollNo - u.rollNo;
    return fullName.toLowerCase().charAt(0) - u.fullName.toLowerCase().charAt(0);
  }


  public void display() {
    String[] coursesToPrint = new String[courses.length];

    for (int i = 0; i < courses.length; i++) {
      coursesToPrint[i] = String.valueOf(courses[i]);
    }

    System.out.println(fullName + Constants.TAB_SEPARATOR + rollNo + Constants.TAB_SEPARATOR + age
        + Constants.TAB_SEPARATOR + address + Constants.TAB_SEPARATOR + String.join(", ",
        coursesToPrint));
  }
}