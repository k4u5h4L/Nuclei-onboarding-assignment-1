package assignment2;

import assignment2.exceptions.InputValidationException;
import assignment2.models.Course;
import assignment2.utils.Validator;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class ValidatorTest {

  @Test
  @DisplayName("Name is not null")
  public void verifyValidityOfName() {
    Assertions.assertThrows(InputValidationException.class, () -> {
      Validator.validateFullName("");
    });
  }

  @Test
  @DisplayName("Age is not less than 0 or greater than 150")
  public void verifyValidityOfAge() {
    Assertions.assertThrows(InputValidationException.class, () -> {
      Validator.validateAge(0);
    });

    Assertions.assertThrows(InputValidationException.class, () -> {
      Validator.validateAge(151);
    });
  }

  @Test
  @DisplayName("Address is not null")
  public void verifyValidityOfAddress() {
    Assertions.assertThrows(InputValidationException.class, () -> {
      Validator.validateAddress("");
    });
  }

  @Test
  @DisplayName("Roll number is not already present")
  public void verifyValidityOfRollNo() {
    HashSet<Integer> hs = new HashSet<Integer>();

    hs.add(1);
    hs.add(2);

    Assertions.assertThrows(InputValidationException.class, () -> {
      Validator.validateRollNo(1, hs);
    });
  }

  @Test
  @DisplayName("Courses are returned right without exceptions")
  public void verifyValueOfCourses() {
    Course[] courses =
        new Course[]{new Course('A'), new Course('B'), new Course('C'), new Course('D')};

    Assertions.assertThrows(InputValidationException.class, () -> {
      Validator.processCourses("a b c");

      Assertions.assertEquals(Validator.processCourses("a b c d"), courses);
    });
  }

  @Test
  @DisplayName("Courses valid (4 courses out of A, B, C, D)")
  public void verifyValidityOfCourses() {
    Course[] courses =
        new Course[]{new Course('A'), new Course('B'), new Course('C'), new Course('D')};

    Assertions.assertFalse(Validator.validateCourses(courses));
  }
}
