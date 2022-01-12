package assignment2.utils;

import assignment2.constants.Constants;
import assignment2.exceptions.InputValidationException;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.util.StringUtils;

public class ValidatorUtil {

  /**
   * Function to validate the full name
   *
   * @param str string input (name, address) of user
   * @throws InputValidationException
   */
  public static void validateString(String str) throws InputValidationException {
    if (!StringUtils.hasText(str)) {
      throw new InputValidationException(Constants.STRING_INPUT_NOT_VALID);
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
   * Function to validate if course string passed is proper
   *
   * @param courses Array of courses
   * @return true if not valid, false if valid
   */
  public static boolean validateCourses(char[] courses) {
    HashSet<Character> memo = new HashSet<Character>();
    for (char course : courses) {
      if (memo.contains(course) || Arrays.asList(Constants.COURSES_AVAILABLE).contains(course)) {
        return true;
      } else {
        memo.add(course);
      }
    }
    return false;
  }
}
