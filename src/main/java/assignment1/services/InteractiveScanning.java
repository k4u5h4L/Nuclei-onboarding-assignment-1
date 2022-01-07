package assignment1.services;

import assignment1.constants.Constants;
import assignment1.exceptions.InvalidArguementsException;
import assignment1.exceptions.StopLoopException;
import assignment1.models.ImportedItem;
import assignment1.models.Item;
import assignment1.models.ManufacturedItem;
import assignment1.models.RawItem;
import java.util.Scanner;

public class InteractiveScanning {

  public static Item scanner(Scanner scan) throws StopLoopException, InvalidArguementsException {
    System.out.print(Constants.ASK_USER_TO_CONTINUE);
    char choice = scan.next().charAt(0);

    if (choice == 'n' || choice == 'N') {
      throw new StopLoopException(Constants.USER_INTERRUPTED);
    }

    System.out.print("Enter item name: ");
    String itemName = scan.next();
    System.out.print("Enter item price: ");
    double itemPrice = scan.nextDouble();
    System.out.print("Enter item quantity: ");
    int itemQuantity = scan.nextInt();
    System.out.print("Enter item type (raw, manufactured, imported): ");
    String itemType = scan.next();

    Item newItem;

    switch (itemType) {
      case "raw": {
        newItem = new RawItem(itemName, itemPrice, itemQuantity, "raw");
        break;
      }

      case "manufactured": {
        newItem = new ManufacturedItem(itemName, itemPrice, itemQuantity, "manufactured");
        break;
      }

      case "imported": {
        newItem = new ImportedItem(itemName, itemPrice, itemQuantity, "imported");
        break;
      }

      default: {
        throw new InvalidArguementsException(
            "-type should have only 3 types of values: raw, " + "manufactured and imported");
      }
    }
    return newItem;
  }
}
