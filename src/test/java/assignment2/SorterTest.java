package assignment2;

import assignment2.exceptions.InputValidationException;
import assignment2.exceptions.OrderMismatchException;
import assignment2.models.UserModel;
import assignment2.services.UserService;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class SorterTest {

  @Test
  @DisplayName("Order and field are proper")
  public void verifyValidityOfOrderAndField() {
    ArrayList<UserModel> users = new ArrayList<UserModel>();

    try {
      users.add(new UserModel("kau", 21, "bengaluru", 1, UserService.processCourses("a b c d")));
      users.add(new UserModel("sanketh", 22, "manglore", 2, UserService.processCourses("a b c f")));

      Assertions.assertThrows(InputValidationException.class, () -> {
        UserService.sortUsers(users, 0, 1);
      });

      Assertions.assertThrows(OrderMismatchException.class, () -> {
        UserService.sortUsers(users, 5, 0);
      });
    } catch (InputValidationException e) {
      Assertions.fail("Input not valid", e);
    }
  }
}
