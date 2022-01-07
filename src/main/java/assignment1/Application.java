package assignment1;

import assignment1.constants.Constants;
import assignment1.exceptions.InvalidArguementsException;
import assignment1.exceptions.StopLoopException;
import assignment1.models.Item;
import assignment1.services.CommandLineArguementsParser;
import assignment1.services.InteractiveScanning;
import assignment1.utils.AddToArrayList;
import assignment1.utils.Printer;
import assignment1.utils.ValidateArguements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

  final static Logger logger = LoggerFactory.getLogger(Application.class);

  public static void init(String[] args) {
    try (Scanner scan = new Scanner(System.in)) {
      try {
        ValidateArguements.validateInputs(args);
      } catch (InvalidArguementsException e) {
        logger.error("Arguements not valid.", e);
        return;
      }

      // arraylist to store all the items
      List<Item> items = new ArrayList<>();

      // a hashmap to store all the command line arguements
      HashMap<String, String> item = new HashMap<String, String>();

      try {
        item = CommandLineArguementsParser.getArguements(args);
      } catch (InvalidArguementsException e) {
        logger.error("Error in parsing arguements.", e);
        return;
      }

      // finally push all the values into the arraylist
      AddToArrayList.add(items, item);

      // keep iterating until user says no
      while (true) {
        try {
          items.add(InteractiveScanning.scanner(scan));
        } catch (StopLoopException e) {
          break;
        } catch (InvalidArguementsException e) {
          logger.error("Error in parsing arguements.", e);
        }
      }

      System.out.println(Constants.DOUBLE_NEW_LINE + "Your items are:\n");

      for (Item it : items) {
        Printer.display(it);
      }
    } catch (InputMismatchException e) {
      logger.error("Input is not matching its type.", e);
    }
  }
}
