package assignment2.services;

import assignment2.exceptions.UserNotFoundException;
import assignment2.models.User;
import java.util.ArrayList;

public class DeleteDetails {

  /**
   * Function to delete User from users ArrayList based on roll number
   *
   * @param users         User object ArrayList
   * @param rollNumberKey Unique roll number of the user
   * @return The deleted user
   * @throws UserNotFoundException
   */
  public static User delete(ArrayList<User> users, int rollNumberKey) throws UserNotFoundException {
    int index = -1;
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getRollNo() == rollNumberKey) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      throw new UserNotFoundException("User with given roll number does not exist.");
    }

    User newUser = users.remove(index);
    System.out.println("User " + newUser.getFullName() + " has been deleted successfully.\n");

    return newUser;
  }
}
