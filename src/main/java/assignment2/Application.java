package assignment2;

import assignment2.exceptions.UserNotFoundException;
import assignment2.models.UserModel;
import assignment2.services.DiskStorageService;
import assignment2.services.UserService;
import assignment2.utils.InteractiveScanningUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    ArrayList<UserModel> users;

    UserService userService = new UserService();
    DiskStorageService diskStorageService = new DiskStorageService();

    try (Scanner scan = new Scanner(System.in);
         FileOutputStream fos = new FileOutputStream("userdetails.ser");
         ObjectOutputStream oos = new ObjectOutputStream(fos);

         FileInputStream fis = new FileInputStream("userdetails.ser");
         ObjectInputStream ois = new ObjectInputStream(fis)) {

      users = diskStorageService.getFromDisk(ois);

      boolean stopSignal = true;
      while (stopSignal) {
        System.out.println("1. Add User details.\n" + "2. Display User details.\n" + "3. Delete "
            + "User details\n" + "4. Save User details.\n" + "5. Exit\n");

        System.out.print("Enter choice (number): ");
        int choice = Integer.parseInt(scan.nextLine());

        switch (choice) {
          case 1: {
            // fuction which scans and adds user details to memory
            userService.addUserDetails(users, InteractiveScanningUtil.scanMenu(scan));
            break;
          }

          case 2: {
            // function which displays all user details in memory
            userService.displayUsers(users, scan);
            break;
          }

          case 3: {
            // function which deletes based on a given roll number
            int rollNumberKey = InteractiveScanningUtil.scanRollNumberToDelete(scan);

            try {
              userService.deleteDetails(users, rollNumberKey);
            } catch (UserNotFoundException e) {
              System.out.println("The specified user is not found.");
            }
            break;
          }

          case 4: {
            // save user details to disk
            diskStorageService.saveToDisk(users, oos);
            break;
          }

          case 5: {
            // Exit the program asking if they want to save before doing so
            stopSignal = false;
            InteractiveScanningUtil.scanCloseConfirmationAlert(scan, users, oos);
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
    } catch (FileNotFoundException e) {
      logger.error("File specified is not found.", e);
    } catch (IOException e) {
      logger.error("Error in reading or writing to file.", e);
    }
  }
}
