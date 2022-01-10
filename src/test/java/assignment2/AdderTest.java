package assignment2;

import assignment2.exceptions.InputValidationException;
import assignment2.exceptions.OrderMismatchException;
import assignment2.models.Course;
import assignment2.models.User;
import assignment2.services.AddDetails;
import assignment2.utils.SortUsers;
import assignment2.utils.Validator;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class AdderTest {

  @Test
  @DisplayName("User is being added")
  public void verifyUsersAreBeingAdded() {
    ArrayList<User> users = new ArrayList<User>();

    User user = new User("kau", 21, "bengaluru", 1, "a b c d");

    users.add(user);

    Assertions.assertEquals(AddDetails.addUserDetails(new ArrayList<User>(), user), users);
  }
}
