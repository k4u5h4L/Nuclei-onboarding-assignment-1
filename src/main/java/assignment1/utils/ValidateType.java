package assignment1.utils;

import assignment1.exceptions.InvalidArguementsException;

public class ValidateType {

  /*
   * Function to check if 'type' has only one of the 3 values or not
   */
  public static void validateTypeInput(String type) throws InvalidArguementsException {
    if ((!(type.equalsIgnoreCase("raw") || type.equalsIgnoreCase("manufactured")
        || type.equalsIgnoreCase("imported")))) {
      throw new InvalidArguementsException(
          "-type should have only 3 types of values: raw, manufactured and imported");
    }
  }
}
