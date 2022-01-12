package assignment2;

import assignment2.exceptions.InputValidationException;
import assignment2.services.UserService;
import assignment2.utils.ValidatorUtil;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class ValidatorTest {

  UserService userService;

  @BeforeEach
  public void runBeforeEach() {
    userService = new UserService();
  }

  @Test
  @DisplayName("Name or address is not null")
  public void verifyValidityOfNameAndAddress() {
    Assertions.assertThrows(InputValidationException.class, () -> {
      ValidatorUtil.validateString("");
    });
  }

  @Test
  @DisplayName("Age is not less than 0 or greater than 150")
  public void verifyValidityOfAge() {
    Assertions.assertThrows(InputValidationException.class, () -> {
      ValidatorUtil.validateAge(0);
    });

    Assertions.assertThrows(InputValidationException.class, () -> {
      ValidatorUtil.validateAge(151);
    });
  }

  @Test
  @DisplayName("Roll number is not already present")
  public void verifyValidityOfRollNo() {
    HashSet<Integer> hs = new HashSet<Integer>();

    hs.add(1);
    hs.add(2);

    Assertions.assertThrows(InputValidationException.class, () -> {
      ValidatorUtil.validateRollNo(1, hs);
    });
  }

  @Test
  @DisplayName("Courses are returned right without exceptions")
  public void verifyValueOfCourses() {
    char[] courses = new char[]{'A', 'B', 'C', 'D'};

    Assertions.assertThrows(InputValidationException.class, () -> {
      userService.processCourses("a b c");

      Assertions.assertEquals(userService.processCourses("a b c d"), courses);
    });
  }

  @Test
  @DisplayName("Courses valid (4 courses out of A, B, C, D)")
  public void verifyValidityOfCourses() {
    char[] courses = new char[]{'A', 'B', 'C', 'D'};

    Assertions.assertFalse(ValidatorUtil.validateCourses(courses));
  }
}
