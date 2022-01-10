package assignment2.services;

import assignment2.models.User;
import assignment2.utils.InteractiveScanning;
import java.util.ArrayList;
import java.util.Scanner;


public class AddDetails {

  /**
   * Function to add the user to the users ArrayList after scanning from cmd line
   *
   * @param users ArrayList of User objects
   * @param scan  Scanner object to scan from cmd line
   */
  public static void handleAddUser(ArrayList<User> users, Scanner scan) {
    User user = InteractiveScanning.menuScanner(scan);
    addUserDetails(users, user);
  }

  /**
   * Function to add user object into users ArraList
   *
   * @param users ArrayList of User objects
   * @param user  User object
   * @return updated users ArrayList
   */
  public static ArrayList<User> addUserDetails(ArrayList<User> users, User user) {
    users.add(user);

    return users;
  }
}
