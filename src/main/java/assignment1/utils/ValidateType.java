package assignment1.utils;

import assignment1.exceptions.InvalidArguementsException;
import assignment1.models.enums.ItemType;

public class ValidateType {

  /**
   * Function to check if the type of the item is valid or not (raw, manufactured, imported)
   *
   * @param type Type of the item
   * @throws InvalidArguementsException
   */
  public static void validateTypeInput(String type) throws InvalidArguementsException {
    try {
      ItemType itemType = ItemType.valueOf(type.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new InvalidArguementsException(
          "-type should have only 3 types of values: raw, manufactured and imported");
    }
  }
}
