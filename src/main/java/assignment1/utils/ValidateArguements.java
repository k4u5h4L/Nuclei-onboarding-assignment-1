package assignment1.utils;

import assignment1.constants.Constants;
import assignment1.exceptions.InvalidArguementsException;
import java.util.Arrays;
import java.util.HashMap;

public class ValidateArguements {

  /*
   * Function to validate if the command line arguements passed are in the correct form
   * */
  public static void validateInputs(String[] params) throws InvalidArguementsException {
    // check if -name and -type are present by checking the number of arguements and names
    if (params.length < 4 || !Arrays.asList(params).contains("-name") || !Arrays.asList(params)
        .contains("-type")) {
      throw new InvalidArguementsException(Constants.NAME_AND_TYPE_NOT_PRESENT);
    } else if (!params[0].equals("-name")) {
      // check if first arguement is -name
      throw new InvalidArguementsException(Constants.NAME_NOT_FIRST_EXCEPTION);
    }
  }

  public static void validatePriceAndQuantity(HashMap<String, String> map)
      throws InvalidArguementsException {
    if (map.containsKey("-price")) {
      if (!isNumeric(map.get("-price"))) {
        throw new InvalidArguementsException("Price" + Constants.PRICE_OR_VALUE_INVALID);
      } else if (!isNumeric(map.get("-quantity"))) {
        throw new InvalidArguementsException("Quantity" + Constants.PRICE_OR_VALUE_INVALID);
      }
    }
  }

  public static boolean isNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      double d = Double.parseDouble(strNum);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }
}
