package assignment2.utils;

import assignment2.exceptions.InputValidationException;
import assignment2.models.UserModel;
import assignment2.services.DiskStorageService;
import assignment2.services.UserService;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InteractiveScanningUtil {

  final static Logger logger = LoggerFactory.getLogger(InteractiveScanningUtil.class);

  /**
   * Function which scans the different attributes of the User.
   *
   * @param scan Scanner object to scan from cmd line
   * @return The populated User object
   */
  public static UserModel scanMenu(Scanner scan) {
    System.out.print("Enter full name: ");
    String userFullName = scan.nextLine();
    System.out.print("Enter age: ");
    int userAge = scan.nextInt();
    scan.nextLine();
    System.out.print("Enter address: ");
    String userAddress = scan.nextLine();
    System.out.print("Enter roll number: ");
    int userRollNo = scan.nextInt();
    scan.nextLine();
    System.out.print("Enter 4 courses (A to F) separated by a space. e.g \"A B C D\": ");
    String userCourses = scan.nextLine().toUpperCase();

    char[] courses = {};

    try {
      ValidatorUtil.validateString(userFullName);
      ValidatorUtil.validateAge(userAge);
      ValidatorUtil.validateString(userAddress);
      ValidatorUtil.validateRollNo(userRollNo, UserModel.getRollNoTracker());
      courses = UserService.processCourses(userCourses);
    } catch (InputValidationException e) {
      logger.error(e.getMessage(), e);
    }

    return new UserModel(userFullName, userAge, userAddress, userRollNo, courses);
  }

  /**
   * Function to ask the user to sort based on field
   *
   * @param scan Scanner object to scan from cmd line
   * @return User input
   */
  public static int scanField(Scanner scan) {
    System.out.println("Based on what should the results be sorted?");
    System.out.print("1. Full name\n2. Roll number\n3. Age\n4. Address\nEnter your choice: ");
    return Integer.parseInt(scan.nextLine());
  }

  /**
   * Function to ask the user to sort based on order
   *
   * @param scan Scanner object to scan from cmd line
   * @return User input
   */
  public static int scanOrder(Scanner scan) {
    System.out.print("1. Ascending\n2. Descending\n\nEnter your choice: ");
    return Integer.parseInt(scan.nextLine());
  }

  /**
   * Function to ask the user their roll number
   *
   * @param scan Scanner object to scan from cmd line
   * @return User input
   */
  public static int scanRollNumberToDelete(Scanner scan) {
    System.out.print("Enter the roll number of the student you want to remove: ");
    return Integer.parseInt(scan.nextLine());
  }

  /**
   * Function to confirm if the user wants to save before exit
   *
   * @param scan  Scanner object to scan from cmd line
   * @param users ArrayList of User objects to be saved to disk
   */
  public static void scanCloseConfirmationAlert(Scanner scan, ArrayList<UserModel> users,
                                                ObjectOutputStream oos) {
    System.out.print("Do you want to save before exiting? (y/n): ");
    char choice = scan.nextLine().charAt(0);

    if (choice == 'y' || choice == 'Y') {
      DiskStorageService.saveToDisk(users, oos);
    }
  }
}
