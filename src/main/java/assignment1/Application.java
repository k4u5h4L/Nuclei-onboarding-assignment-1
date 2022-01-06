package assignment1;

import assignment1.exceptions.InvalidArguementsException;
import assignment1.exceptions.StopLoopException;
import assignment1.models.Item;
import assignment1.models.ItemWithTax;
import assignment1.services.CommandLineArguementsParser;
import assignment1.services.InteractiveScanning;
import assignment1.services.ValidateArguements;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Application {

  public static void init(String[] args) {
    try {
      ValidateArguements.validateInputs(args);
    } catch (InvalidArguementsException e) {
      e.printStackTrace();
      System.exit(1);
    }

    // arraylist to store all the items
    ArrayList<ItemWithTax> items = new ArrayList<ItemWithTax>();

    // scanner to scan the user input from the cli
    Scanner scan = new Scanner(System.in);

    // a hashmap to store all the command line arguements
    HashMap<String, String> item = new HashMap<String, String>();

    try {
      item = CommandLineArguementsParser.getArguements(args);
    } catch (InvalidArguementsException e) {
      e.printStackTrace();
      System.exit(1);
    }

    // finally push all the values into the arraylist
    items.add(new ItemWithTax(item.get("-name"), Integer.parseInt(item.get("-price")),
        Integer.parseInt(item.get("-quantity")), item.get("-type")));

    // keep iterating until user says no
    while (true) {
      try {
        items.add(InteractiveScanning.scanner(scan));
      } catch (StopLoopException e) {
        break;
      }
    }

    System.out.println("\n\nYour items are:\n");

    for (ItemWithTax it : items) {
      it.display();
    }

    scan.close();
  }
}
