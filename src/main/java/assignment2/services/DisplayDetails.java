package assignment2.services;

import assignment2.constants.Constants;
import assignment2.exceptions.InputValidationException;
import assignment2.exceptions.OrderMismatchException;
import assignment2.models.User;
import assignment2.utils.InteractiveScanning;
import assignment2.utils.SortUsers;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisplayDetails {

  final static Logger logger = LoggerFactory.getLogger(DisplayDetails.class);

  /**
   * Simply prints a line divider
   */
  private static void printLine() {
    System.out.println("-----------------------------------------------------------------------");
  }

  /**
   * Simply prints all the attributes of the user
   */
  private static void printHeaders() {
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
  public static void display(ArrayList<User> users, Scanner scan) {
    int field = InteractiveScanning.fieldScanner(scan);
    int order = InteractiveScanning.orderScanner(scan);

    printHeaders();

    try {
      SortUsers.sortAndDisplay(users, field, order);
    } catch (OrderMismatchException e) {
      logger.error("Order given was invalid (not 1 or 2).", e);
    } catch (InputValidationException e) {
      logger.error("Field given was out of bounds.", e);
    }

    System.out.println("\n");

  }
}
