import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Assignment1 {

  public static void main(String[] args) {
    // check if -name and -type are present by checking the number of arguements
    // and names
    if (args.length < 4 || !Arrays.asList(args).contains("-name") || !Arrays.asList(args)
        .contains("-type")) {
      System.out.println("-name and -type not present. Exiting...");
      System.exit(1);
    } else if (!args[0].equals("-name")) {
      System.out.println("-name should be present first. Exiting...");
      System.exit(1);
    }

    // arraylist to store all the items
    ArrayList<Item> items = new ArrayList<Item>();

    // scanner to scan the user input from the cli
    Scanner scanner = new Scanner(System.in);

    // a hashmap to store all the command line arguements
    HashMap<String, String> item = new HashMap<String, String>();

    for (int i = 0; i < args.length - 1; i = i + 2) {
      // store the key value pair from the arguements passed
      item.put(args[i], args[i + 1]);
    }

    // check if type is present as it is mandatory
    if (!item.containsKey("-type")) {
      // send not there message and quit
      System.out.println("-type argument not provided. Exiting...");
      System.exit(1);
    }
    // if price is not present, assign a default value
    if (!item.containsKey("-price")) {
      item.put("-price", "0");
    }
    // if quantity is not present, assign a default value
    if (!item.containsKey("-quantity")) {
      item.put("-quantity", "0");
    }

    if (!(item.get("-type").equals("raw") || item.get("-type").equals("manufactured") || item.get(
        "-type").equals("imported"))) {
      System.out.println(
          "-type should have only 3 types of values: raw, manufactured and " + "imported");
      System.exit(1);
    }

    // finally push all the values into the arraylist
    items.add(new Item(item.get("-name"), Integer.parseInt(item.get("-price")),
        Integer.parseInt(item.get("-quantity")), item.get("-type")));

    // keep iterating until user says no
    while (true) {
      System.out.print("Do you want to enter details of any other item (y/n): ");
      char choice = scanner.next().charAt(0);

      if (choice == 'n' || choice == 'N') {
        break;
      }

      System.out.print("Enter item name: ");
      String itemName = scanner.next();
      System.out.print("Enter item price: ");
      int itemPrice = scanner.nextInt();
      System.out.print("Enter item quantity: ");
      int itemQuantity = scanner.nextInt();
      System.out.print("Enter item type (raw, manufactured, imported): ");
      String itemType = scanner.next();

      items.add(new Item(itemName, itemPrice, itemQuantity, itemType));
    }

    System.out.println("Your items are:\n");

    for (Item it : items) {
      System.out.println("Item name: " + it.getName());
      System.out.println("Item price: " + it.getPrice());
      System.out.println("Item quantity: " + it.getQuantity());
      System.out.println("Item type: " + it.getType());
      System.out.println("Item sales tax: " + it.getSalesTax());
      System.out.println("Item final price: " + it.getFinalPrice());
    }

    scanner.close();
  }
}

class Item {

  private String name;
  private int price;
  private int quantity;
  private String type;
  private double salesTax;
  private double finalPrice;

  Item(String name, int price, int quantity, String type) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.type = type;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return this.price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getSalesTax() {
    calculateSalesTax();
    return this.salesTax;
  }

  public double getFinalPrice() {
    calculateSalesTax();
    return this.finalPrice;
  }

  private void calculateSalesTax() {
    if (type.equals("raw")) {
      salesTax = 0.125 * price;
    } else if (type.equals("manufactured")) {
      salesTax = (0.125 * price) + (0.02 * (price + (0.125 * price)));
    } else {
      double surcharge;
      double importDuty = (0.1 * price) + price;
      if (importDuty < 100) {
        surcharge = 5;
      } else if (importDuty < 200) {
        surcharge = 10;
      } else {
        importDuty = 0.1 * price;
        importDuty = price + importDuty;
        surcharge = 0.05 * importDuty;
      }
      finalPrice = importDuty + surcharge;
      salesTax = finalPrice - price;
    }
  }
}
