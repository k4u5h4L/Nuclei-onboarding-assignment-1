package assignment2;

import assignment2.exceptions.InputValidationException;
import assignment2.exceptions.OrderMismatchException;
import assignment2.models.UserModel;
import assignment2.services.UserService;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class SorterTest {

  UserService userService;

  @BeforeEach
  public void runBeforeEach() {
    userService = new UserService();
  }

  @Test
  @DisplayName("Order and field are proper")
  public void verifyValidityOfOrderAndField() {
    ArrayList<UserModel> users = new ArrayList<UserModel>();

    try {
      users.add(new UserModel("kau", 21, "bengaluru", 1, userService.processCourses("a b c d")));
      users.add(new UserModel("sanketh", 22, "manglore", 2, userService.processCourses("a b c f")));

      Assertions.assertThrows(InputValidationException.class, () -> {
        userService.sortUsers(users, 0, 1);
      });

      Assertions.assertThrows(OrderMismatchException.class, () -> {
        userService.sortUsers(users, 5, 0);
      });
    } catch (InputValidationException e) {
      Assertions.fail("Input not valid", e);
    }
  }
}
