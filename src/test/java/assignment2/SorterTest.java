package assignment2;

import assignment2.exceptions.InputValidationException;
import assignment2.exceptions.OrderMismatchException;
import assignment2.models.Course;
import assignment2.models.User;
import assignment2.utils.SortUsers;
import assignment2.utils.Validator;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class SorterTest {

  @Test
  @DisplayName("Order and field are proper")
  public void verifyValidityOfOrderAndField() {
    ArrayList<User> users = new ArrayList<User>();

    users.add(new User("kau", 21, "bengaluru", 1, "a b c d"));
    users.add(new User("sanketh", 22, "manglore", 2, "a b c f"));

    Assertions.assertThrows(InputValidationException.class, () -> {
      SortUsers.sortAndDisplay(users, 0, 1);
    });

    Assertions.assertThrows(OrderMismatchException.class, () -> {
      SortUsers.sortAndDisplay(users, 5, 0);
    });
  }
}
