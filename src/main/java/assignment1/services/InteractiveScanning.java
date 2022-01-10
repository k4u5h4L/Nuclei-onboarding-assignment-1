package assignment1.services;

import assignment1.constants.Constants;
import assignment1.exceptions.InvalidArguementsException;
import assignment1.exceptions.StopLoopException;
import assignment1.models.Imported;
import assignment1.models.Item;
import assignment1.models.Manufactured;
import assignment1.models.Raw;
import assignment1.models.enums.ItemType;
import assignment1.utils.ValidateType;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InteractiveScanning {

  final static Logger logger = LoggerFactory.getLogger(InteractiveScanning.class);

  /**
   * Function to scan input from the command line and return an Item.
   *
   * @param scan Scanner object which is used to scan input from the command line
   * @return Item object
   * @throws StopLoopException
   * @throws InvalidArguementsException
   */
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

    try {
      ValidateType.validateTypeInput(itemType);
    } catch (InvalidArguementsException e) {
      logger.error("-type should have 'raw', 'manufactured' or 'imported'.", e);
    }

    Item newItem;
    ItemType itemTypeChoice = ItemType.valueOf(itemType.toUpperCase());

    switch (itemTypeChoice) {
      case RAW: {
        newItem = new Raw(itemName, itemPrice, itemQuantity);
        break;
      }

      case MANUFACTURED: {
        newItem = new Manufactured(itemName, itemPrice, itemQuantity);
        break;
      }

      case IMPORTED: {
        newItem = new Imported(itemName, itemPrice, itemQuantity);
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
