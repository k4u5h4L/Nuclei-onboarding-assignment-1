package assignment2;

import assignment2.exceptions.UserNotFoundException;
import assignment2.models.User;
import assignment2.services.AddDetails;
import assignment2.services.DeleteDetails;
import assignment2.services.DiskStorage;
import assignment2.services.DisplayDetails;
import assignment2.services.TerminateProgram;
import assignment2.utils.InteractiveScanning;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

  final static Logger logger = LoggerFactory.getLogger(Application.class);

  /**
   * Main application starting point
   *
   * @param args array of command line arguements
   */
  public static void init(String[] args) {
    ArrayList<User> users;

    users = DiskStorage.getFromDisk();

    try (Scanner scan = new Scanner(System.in)) {
      boolean stopSignal = true;
      while (stopSignal) {
        System.out.println("1. Add User details.\n" + "2. Display User details.\n" + "3. Delete "
            + "User details\n" + "4. Save User details.\n" + "5. Exit\n");

        System.out.print("Enter choice (number): ");
        int choice = Integer.parseInt(scan.nextLine());

        switch (choice) {
          case 1: {
            // fuction which scans and adds user details to memory
            AddDetails.handleAddUser(users, scan);
            break;
          }

          case 2: {
            // function which displays all user details in memory
            DisplayDetails.display(users, scan);
            break;
          }

          case 3: {
            // function which deletes based on a given roll number
            int rollNumberKey = InteractiveScanning.rollNumberToDeleteScanner(scan);

            try {
              DeleteDetails.delete(users, rollNumberKey);
            } catch (UserNotFoundException e) {
              System.out.println("The specified user is not found.");
            }
            break;
          }

          case 4: {
            // save user details to disk
            DiskStorage.saveToDisk(users);
            break;
          }

          case 5: {
            // Exit the program asking if they want to save before doing so
            stopSignal = false;
            TerminateProgram.terminate(users, scan);
            break;
          }

          default: {
            System.out.println("Enter the available options only.");
            break;
          }
        }
      }
    } catch (InputMismatchException e) {
      logger.error("Input is not matching its type.", e);
    }
  }
}
