package assignment1.services;

import assignment1.exceptions.InvalidArguementsException;
import java.util.HashMap;

public class CommandLineArguementsParser {

  /*
   * Function to parse all the command line arguements and return a hashmap
   */
  public static HashMap<String, String> getArguements(String[] args)
      throws InvalidArguementsException {
    HashMap<String, String> item = new HashMap<String, String>();

    for (int i = 0; i < args.length - 1; i = i + 2) {
      // store the key value pair from the arguements passed
      item.put(args[i], args[i + 1]);
    }

    // check if type is present as it is mandatory
    if (!item.containsKey("-type")) {
      // send not there message and quit
      throw new InvalidArguementsException("-name should be present first.");
    }
    // if price is not present, assign a default value
    if (!item.containsKey("-price")) {
      System.out.println("-price wasn't provided, using 0 as default value...");
      item.put("-price", "0");
    }
    // if quantity is not present, assign a default value
    if (!item.containsKey("-quantity")) {
      System.out.println("-quantity wasn't provided, using 0 as default value...");
      item.put("-quantity", "0");
    }

    return item;
  }
}