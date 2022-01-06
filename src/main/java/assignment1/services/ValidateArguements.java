package assignment1.services;

import assignment1.exceptions.InvalidArguementsException;
import java.util.Arrays;

public class ValidateArguements {

  /*
   * Function to validate if the command line arguements passed are in the correct form
   * */
  public static void validateInputs(String[] params) throws InvalidArguementsException {
    // check if -name and -type are present by checking the number of arguements and names
    if (params.length < 4 || !Arrays.asList(params).contains("-name") || !Arrays.asList(params)
        .contains("-type")) {
      throw new InvalidArguementsException("-name and -type not present.");
    } else if (!params[0].equals("-name")) {
      // check if first arguement is -name
      throw new InvalidArguementsException("-name should be present first.");
    }
  }
}
