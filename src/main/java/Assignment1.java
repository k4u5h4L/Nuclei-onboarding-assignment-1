import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

class InvalidArguementsException extends Exception {

  InvalidArguementsException(String s) {
    super(s);
  }
}

class StopLoopException extends Exception {

  StopLoopException(String s) {
    super(s);
  }
}

public class Assignment1 {

  String itemName, itemType;
  int itemPrice, itemQuantity;

  /*
   * Function to validate if the command line arguements passed are in the correct form
   * */
  private static void validateInputs(String[] params) throws InvalidArguementsException {
    // check if -name and -type are present by checking the number of arguements
    // and names
    if (params.length < 4 || !Arrays.asList(params).contains("-name") || !Arrays.asList(params)
        .contains("-type")) {
//      System.out.println("-name and -type not present. Exiting...");
      throw new InvalidArguementsException("-name and -type not present.");
    } else if (!params[0].equals("-name")) {
//      System.out.println("-name should be present first. Exiting...");
      throw new InvalidArguementsException("-name should be present first.");
    }
  }

  /*
   * Function to parse all the command line arguements and return a hashmap
   */
  private static HashMap<String, String> getArguements(String[] args)
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

  private static void validateTypeInput(HashMap<String, String> item)
      throws InvalidArguementsException {
    if (!(item.get("-type").equals("raw") || item.get("-type").equals("manufactured") || item.get(
        "-type").equals("imported"))) {
      throw new InvalidArguementsException(
          "-type should have only 3 types of values: raw, manufactured and imported");
    }
  }

  private static void printer(ArrayList<Item> items) {
    System.out.println("\n\nYour items are:\n");

    // print each item and its attributes
    for (Item it : items) {
      System.out.println("Item name: " + it.getName());
      System.out.println("Item price: " + it.getPrice());
      System.out.println("Item quantity: " + it.getQuantity());
      System.out.println("Item type: " + it.getType());
      System.out.println("Item sales tax: " + it.getSalesTax());
      System.out.println("Item final price: " + it.getFinalPrice() + "\n\n");
    }
  }

  private static Item scanner(Scanner scan) throws StopLoopException {
    System.out.print("Do you want to enter details of any other item (y/n): ");
    char choice = scan.next().charAt(0);

    if (choice == 'n' || choice == 'N') {
      throw new StopLoopException("User interrupted.");
    }

    System.out.print("Enter item name: ");
    String itemName = scan.next();
    System.out.print("Enter item price: ");
    int itemPrice = scan.nextInt();
    System.out.print("Enter item quantity: ");
    int itemQuantity = scan.nextInt();
    System.out.print("Enter item type (raw, manufactured, imported): ");
    String itemType = scan.next();

    return new Item(itemName, itemPrice, itemQuantity, itemType);
  }

  public static void main(String[] args) {
    try {
      validateInputs(args);
    } catch (InvalidArguementsException e) {
      e.printStackTrace();
      System.exit(1);
    }

    // arraylist to store all the items
    ArrayList<Item> items = new ArrayList<Item>();

    // scanner to scan the user input from the cli
    Scanner scan = new Scanner(System.in);

    // a hashmap to store all the command line arguements
    HashMap<String, String> item = new HashMap<String, String>();
    try {
      item = getArguements(args);
    } catch (InvalidArguementsException e) {
      e.printStackTrace();
      System.exit(1);
    }

    try {
      validateTypeInput(item);
    } catch (InvalidArguementsException e) {
      e.printStackTrace();
      System.exit(1);
    }

    // finally push all the values into the arraylist
    items.add(new Item(item.get("-name"), Integer.parseInt(item.get("-price")),
        Integer.parseInt(item.get("-quantity")), item.get("-type")));

    // keep iterating until user says no
    while (true) {
      try {
        items.add(scanner(scan));
      } catch (StopLoopException e) {
        break;
      }
    }

    printer(items);

    scan.close();
  }
}


