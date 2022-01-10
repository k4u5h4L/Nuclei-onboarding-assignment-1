package assignment2.utils;

import assignment2.constants.Constants;
import assignment2.exceptions.InputValidationException;
import assignment2.models.Course;
import java.util.Arrays;
import java.util.HashSet;

public class Validator {

  /**
   * Function to validate the full name
   *
   * @param name Full name of user
   * @throws InputValidationException
   */
  public static void validateFullName(String name) throws InputValidationException {
    if (name == null || name.equals("")) {
      throw new InputValidationException("Full name" + Constants.STRING_INPUT_NOT_VALID);
    }
  }

  /**
   * Function to validate the age
   *
   * @param age Age of user
   * @throws InputValidationException
   */
  public static void validateAge(int age) throws InputValidationException {
    if (age < 1 || age > 150) {
      throw new InputValidationException("Age" + Constants.INT_INPUT_NOT_VALID);
    }
  }

  /**
   * Function to validate the address
   *
   * @param address Address of user
   * @throws InputValidationException
   */
  public static void validateAddress(String address) throws InputValidationException {
    if (address == null || address.equals("")) {
      throw new InputValidationException("Address" + Constants.STRING_INPUT_NOT_VALID);
    }
  }

  /**
   * Function to validate the roll number
   *
   * @param rollNo Roll number of user
   * @throws InputValidationException
   */
  public static void validateRollNo(int rollNo, HashSet<Integer> rollNoTracker)
      throws InputValidationException {
    if (rollNo < 1) {
      throw new InputValidationException("Roll number cannot be less than 1.");
    } else if (rollNoTracker.contains(rollNo)) {
      throw new InputValidationException("Roll number " + rollNo + " is already entered.");
    }

    rollNoTracker.add(rollNo);
  }

  /**
   * Function to process/parse the courses string
   *
   * @param coursesString String of courses. e.g. "A B C D"
   * @return Array of (parsed) Course objects
   * @throws InputValidationException
   */
  public static Course[] processCourses(String coursesString) throws InputValidationException {
    String[] courses = coursesString.split("[,\\s]+");

    if (courses.length != 4) {
      throw new InputValidationException(Constants.COURSES_INPUT_NOT_VALID);
    }

    Course[] result = new Course[4];
    for (int i = 0; i < courses.length; i++) {
      result[i] = new Course(courses[i].charAt(0));
    }

    if (validateCourses(result)) {
      throw new InputValidationException(Constants.COURSES_INPUT_NOT_VALID);
    }

    return result;
  }

  /**
   * Function to validate if course string passed is proper
   *
   * @param courses Array of Course objects
   * @return true if not valid, false if valid
   */
  public static boolean validateCourses(Course[] courses) {
    HashSet<Character> memo = new HashSet<Character>();
    for (Course course : courses) {
      if (memo.contains(course.getCourse()) || Arrays.asList(Constants.COURSES_AVAILABLE)
          .contains(course.getCourse())) {
        return true;
      } else {
        memo.add(course.getCourse());
      }
    }
    return false;
  }
}
