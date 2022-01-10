package assignment2.utils;

import assignment2.exceptions.InputValidationException;
import assignment2.exceptions.OrderMismatchException;
import assignment2.models.User;
import java.util.ArrayList;

public class SortUsers {

  /**
   * Function which sorts and displays the users based on parameters.
   *
   * @param users ArrayList of User objects
   * @param field Field to sort the users on
   * @param order Order to sort the users on
   * @throws OrderMismatchException
   * @throws InputValidationException
   */
  public static void sortAndDisplay(ArrayList<User> users, int field, int order)
      throws OrderMismatchException, InputValidationException {
    if (order < 1 || order > 2) {
      throw new OrderMismatchException("Sorting order input invalid.");
    } else if (field < 1 || field > 4) {
      throw new InputValidationException("Field entered is false");
    }

    users.stream().sorted((a, b) -> {
      int result = 0;
      switch (field) {
        case 1:
          result =
              a.getFullName().toLowerCase().charAt(0) - b.getFullName().toLowerCase().charAt(0);
          break;
        case 2:
          result = a.getRollNo() - b.getRollNo();
          break;
        case 3:
          result = a.getAge() - b.getAge();
          break;
        case 4:
          result = a.getAddress().toLowerCase().charAt(0) - b.getAddress().toLowerCase().charAt(0);
          break;
      }

      if (order == 2) result = -result;

      return result;
    }).forEach(User::display);
  }
}
