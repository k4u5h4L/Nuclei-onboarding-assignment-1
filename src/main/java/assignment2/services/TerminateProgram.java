package assignment2.services;

import assignment2.models.User;
import assignment2.utils.InteractiveScanning;
import java.util.ArrayList;
import java.util.Scanner;

public class TerminateProgram {

  /**
   * Function to ask the user before terminating the program
   *
   * @param users ArrayList of users to save to disk
   * @param scan  Scanner object to scan from cmd line
   */
  public static void terminate(ArrayList<User> users, Scanner scan) {
    InteractiveScanning.closeConfirmationAlertScanner(scan, users);
  }
}
