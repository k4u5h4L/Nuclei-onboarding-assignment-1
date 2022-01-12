package assignment2;

import assignment2.exceptions.InputValidationException;
import assignment2.exceptions.UserNotFoundException;
import assignment2.models.UserModel;
import assignment2.services.UserService;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class DeleterTest {

  UserService userService;

  @BeforeEach
  public void runBeforeEach() {
    userService = new UserService();
  }

  @Test
  @DisplayName("User is being deleted")
  public void verifyUserBeingDeleted() {
    ArrayList<UserModel> users = new ArrayList<UserModel>();

    try {
      UserModel user1 =
          new UserModel("kau", 21, "bengaluru", 1, userService.processCourses("a b c d"));
      UserModel user2 =
          new UserModel("sanketh", 22, "manglore", 2, userService.processCourses("a b c f"));

      users.add(user1);
      users.add(user2);

      Assertions.assertThrows(UserNotFoundException.class, () -> {
        userService.deleteDetails(new ArrayList<UserModel>(), 1);
      });

    } catch (InputValidationException e) {
      Assertions.fail("Input not valid", e);
    }

    try {
      userService.deleteDetails(users, 2);
    } catch (UserNotFoundException e) {
      Assertions.fail("User not found", e);
    }
  }
}
