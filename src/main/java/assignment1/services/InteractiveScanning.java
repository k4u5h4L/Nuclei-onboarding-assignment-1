package assignment1.services;

import assignment1.exceptions.StopLoopException;
import assignment1.models.ItemWithTax;
import java.util.Scanner;

public class InteractiveScanning {

  public static ItemWithTax scanner(Scanner scan) throws StopLoopException {
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

    return new ItemWithTax(itemName, itemPrice, itemQuantity, itemType);
  }
}
