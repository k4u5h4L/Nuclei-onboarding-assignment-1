package assignment2;

import assignment2.exceptions.InputValidationException;
import assignment2.exceptions.OrderMismatchException;
import assignment2.exceptions.UserNotFoundException;
import assignment2.models.Course;
import assignment2.models.User;
import assignment2.services.DeleteDetails;
import assignment2.utils.SortUsers;
import assignment2.utils.Validator;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class DeleterTest {

  @Test
  @DisplayName("User is being deleted")
  public void verifyUserBeingDeleted() {
    ArrayList<User> users = new ArrayList<User>();

    User user1 = new User("kau", 21, "bengaluru", 1, "a b c d");
    User user2 = new User("sanketh", 22, "manglore", 2, "a b c f");

    users.add(user1);
    users.add(user2);

    Assertions.assertThrows(UserNotFoundException.class, () -> {
      DeleteDetails.delete(new ArrayList<User>(), 1);

      Assertions.assertEquals(DeleteDetails.delete(users, 2), user2);
    });
  }
}
