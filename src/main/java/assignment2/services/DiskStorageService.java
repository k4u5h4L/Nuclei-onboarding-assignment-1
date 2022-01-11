package assignment2.services;

import assignment2.Application;
import assignment2.models.UserModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiskStorageService {

  final static Logger logger = LoggerFactory.getLogger(Application.class);

  /**
   * Function to save the users to disk
   *
   * @param users ArrayList of User objects
   */
  public static void saveToDisk(ArrayList<UserModel> users, ObjectOutputStream oos) {
    try {
      oos.writeObject(users);

      System.out.println("Successfully saved to disk.");
    } catch (FileNotFoundException e) {
      logger.error("File specified is not found.", e);
    } catch (IOException e) {
      logger.error("Error in reading or writing to file.", e);
    }
  }

  /**
   * Get the ArrayList of User objects from disk
   *
   * @return ArrayList of User objects
   */
  public static ArrayList<UserModel> getFromDisk(ObjectInputStream ois) {
    ArrayList<UserModel> users;
    try {
      users = (ArrayList<UserModel>) ois.readObject();

      return users;
    } catch (FileNotFoundException e) {
      logger.error("File specified is not found.");
      users = new ArrayList<UserModel>();
    } catch (IOException e) {
      logger.error("Error in reading or writing to file.");
      users = new ArrayList<UserModel>();
    } catch (ClassNotFoundException e) {
      logger.error("Class not found in file.");
      users = new ArrayList<UserModel>();
    }

    return users;
  }
}
