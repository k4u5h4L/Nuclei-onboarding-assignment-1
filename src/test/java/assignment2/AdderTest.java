package assignment2;

import assignment2.exceptions.InputValidationException;
import assignment2.models.User;
import assignment2.services.UserService;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class AdderTest {

  @Test
  @DisplayName("User is being added")
  public void verifyUsersAreBeingAdded() {
    ArrayList<User> users = new ArrayList<User>();

    try {
      User user = new User("kau", 21, "bengaluru", 1, UserService.processCourses("a b c d"));

      users.add(user);

      Assertions.assertEquals(UserService.addUserDetails(new ArrayList<User>(), user), users);
    } catch (InputValidationException e) {
      Assertions.fail("Input not valid", e);
    }
  }
}
