package assignment2;

import assignment2.exceptions.InputValidationException;
import assignment2.models.UserModel;
import assignment2.services.UserService;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class AdderTest {

  UserService userService;

  @BeforeEach
  public void runBeforeEach() {
    userService = new UserService();
  }

  @Test
  @DisplayName("User is being added")
  public void verifyUsersAreBeingAdded() {
    ArrayList<UserModel> users = new ArrayList<UserModel>();

    try {
      UserModel user =
          new UserModel("kau", 21, "bengaluru", 1, userService.processCourses("a b c d"));

      users.add(user);

      Assertions.assertEquals(userService.addUserDetails(new ArrayList<UserModel>(), user), users);
    } catch (InputValidationException e) {
      Assertions.fail("Input not valid", e);
    }
  }
}
