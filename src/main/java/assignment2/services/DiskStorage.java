package assignment2.services;

import assignment2.Application;
import assignment2.models.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiskStorage {

  final static Logger logger = LoggerFactory.getLogger(Application.class);

  /**
   * Function to save the users to disk
   *
   * @param users ArrayList of User objects
   */
  public static void saveToDisk(ArrayList<User> users) {
    try {
      FileOutputStream fos = new FileOutputStream("userdetails.ser");
      ObjectOutputStream oos = new ObjectOutputStream(fos);

      oos.writeObject(users);

      oos.close();
      fos.close();

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
  public static ArrayList<User> getFromDisk() {
    ArrayList<User> users;
    try {
      FileInputStream fis = new FileInputStream("userdetails.ser");
      ObjectInputStream ois = new ObjectInputStream(fis);

      //noinspection unchecked
      users = (ArrayList<User>) ois.readObject();

      ois.close();
      fis.close();

      for (User u : users) {
        System.out.println(u);
      }

      return users;
    } catch (FileNotFoundException e) {
      logger.error("File specified is not found.");
      users = new ArrayList<User>();
    } catch (IOException e) {
      logger.error("Error in reading or writing to file.");
      users = new ArrayList<User>();
    } catch (ClassNotFoundException e) {
      logger.error("Class not found in file.");
      users = new ArrayList<User>();
    }

    return users;
  }
}
