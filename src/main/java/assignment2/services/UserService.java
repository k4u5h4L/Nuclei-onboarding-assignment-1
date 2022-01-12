package assignment2.services;

import assignment2.constants.Constants;
import assignment2.exceptions.InputValidationException;
import assignment2.exceptions.OrderMismatchException;
import assignment2.exceptions.UserNotFoundException;
import assignment2.models.UserModel;
import assignment2.utils.InteractiveScanningUtil;
import assignment2.utils.ValidatorUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

  final static Logger logger = LoggerFactory.getLogger(UserService.class);

  /**
   * Function to add user object into users ArraList
   *
   * @param users ArrayList of User objects
   * @param user  User object
   * @return updated users ArrayList
   */
  public ArrayList<UserModel> addUserDetails(ArrayList<UserModel> users, UserModel user) {
    users.add(user);

    return users;
  }

  /**
   * Function to delete User from users ArrayList based on roll number
   *
   * @param users         User object ArrayList
   * @param rollNumberKey Unique roll number of the user
   * @return The deleted user
   * @throws UserNotFoundException
   */
  public void deleteDetails(ArrayList<UserModel> users, int rollNumberKey)
      throws UserNotFoundException {

    boolean isRemoved = users.removeIf(user -> (user.getRollNo() == rollNumberKey));

    if (!isRemoved) {
      throw new UserNotFoundException("User with given roll number does not exist.");
    }
  }

  /**
   * Simply prints a line divider
   */
  private void printLine() {
    System.out.println("-----------------------------------------------------------------------");
  }

  /**
   * Simply prints all the attributes of the user
   */
  private void printHeaders() {
    printLine();
    System.out.println(
        "Name" + Constants.TAB_SEPARATOR + "Roll number" + Constants.TAB_SEPARATOR + "Age"
            + Constants.TAB_SEPARATOR + "Address" + Constants.TAB_SEPARATOR + "Courses");
    printLine();
  }

  /**
   * Displays the Users in the order chosen.
   *
   * @param users ArrayList of User objects
   * @param scan  Scanner object to scan from cmd line
   */
  public void displayUsers(ArrayList<UserModel> users, Scanner scan) {
    int field = InteractiveScanningUtil.scanField(scan);
    int order = InteractiveScanningUtil.scanOrder(scan);

    printHeaders();

    try {
      sortUsers(users, field, order);
    } catch (OrderMismatchException e) {
      logger.error("Order given was invalid (not 1 or 2).", e);
    } catch (InputValidationException e) {
      logger.error("Field given was out of bounds.", e);
    }

    for (UserModel user : users) {
      user.display();
    }

    System.out.println("\n");

  }

  /**
   * Function which sorts and displays the users based on parameters.
   *
   * @param users ArrayList of User objects
   * @param field Field to sort the users on
   * @param order Order to sort the users on
   * @throws OrderMismatchException
   * @throws InputValidationException
   */
  public void sortUsers(ArrayList<UserModel> users, int field, int order)
      throws OrderMismatchException, InputValidationException {
    if (order < 1 || order > 2) {
      throw new OrderMismatchException("Sorting order input invalid.");
    } else if (field < 1 || field > 4) {
      throw new InputValidationException("Field entered is false");
    }

    switch (field) {
      case 1: {
        users.sort(Comparator.comparing(o -> o.getFullName().toLowerCase().charAt(0)));
        break;
      }

      case 2: {
        users.sort(Comparator.comparing(o -> o.getRollNo()));
        break;
      }

      case 3: {
        users.sort(Comparator.comparing(o -> o.getAge()));
        break;
      }

      case 4: {
        users.sort(Comparator.comparing(o -> o.getAddress().toLowerCase().charAt(0)));
        break;
      }
    }

    if (order == 2) {
      Collections.reverse(users);
    }
  }

  /**
   * Function to process/parse the courses string
   *
   * @param coursesString String of courses. e.g. "A B C D"
   * @return Array of (parsed) courses
   * @throws InputValidationException
   */
  public char[] processCourses(String coursesString) throws InputValidationException {
    String[] courses = coursesString.split(Constants.REGEX_TO_SPLIT_COURSES);

    if (courses.length != 4) {
      throw new InputValidationException(Constants.COURSES_INPUT_NOT_VALID);
    }

    char[] result = new char[4];
    for (int i = 0; i < courses.length; i++) {
      result[i] = courses[i].charAt(0);
    }

    if (ValidatorUtil.validateCourses(result)) {
      throw new InputValidationException(Constants.COURSES_INPUT_NOT_VALID);
    }

    return result;
  }
}
