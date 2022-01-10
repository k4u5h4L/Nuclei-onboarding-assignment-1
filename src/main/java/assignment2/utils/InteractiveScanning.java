package assignment2.utils;

import assignment2.Application;
import assignment2.models.User;
import assignment2.services.DiskStorage;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InteractiveScanning {

  final static Logger logger = LoggerFactory.getLogger(InteractiveScanning.class);

  /**
   * Function which scans the different attributes of the User.
   *
   * @param scan Scanner object to scan from cmd line
   * @return The populated User object
   */
  public static User menuScanner(Scanner scan) {
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

    return new User(userFullName, userAge, userAddress, userRollNo, userCourses);
  }

  /**
   * Function to ask the user to sort based on field
   *
   * @param scan Scanner object to scan from cmd line
   * @return User input
   */
  public static int fieldScanner(Scanner scan) {
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
  public static int orderScanner(Scanner scan) {
    System.out.print("1. Ascending\n2. Descending\n\nEnter your choice: ");
    return Integer.parseInt(scan.nextLine());
  }

  /**
   * Function to ask the user their roll number
   *
   * @param scan Scanner object to scan from cmd line
   * @return User input
   */
  public static int rollNumberToDeleteScanner(Scanner scan) {
    System.out.print("Enter the roll number of the student you want to remove: ");
    return Integer.parseInt(scan.nextLine());
  }

  /**
   * Function to confirm if the user wants to save before exit
   *
   * @param scan  Scanner object to scan from cmd line
   * @param users ArrayList of User objects to be saved to disk
   */
  public static void closeConfirmationAlertScanner(Scanner scan, ArrayList<User> users) {
    System.out.print("Do you want to save before exiting? (y/n): ");
    char choice = scan.nextLine().charAt(0);

    if (choice == 'y' || choice == 'Y') {
      DiskStorage.saveToDisk(users);
    }
  }
}
