package assignment4.services;

import assignment4.models.ItemModel;
import java.util.ArrayList;

public class ListPrinterService {

  /**
   * Prints a dashed line into the console.
   */
  public static void printLine() {
    System.out.println("-----------------------------------------------------");
  }

  /**
   * Displays each attribute of the object in the collection
   *
   * @param items ArrayList of ItemModel objects
   */
  public void display(ArrayList<ItemModel> items, String threadName) {
    printLine();
    System.out.println("Thread: " + threadName);
    printLine();
    for (ItemModel item : items) {
      System.out.println("Name: " + item.getName());
      System.out.println("Price: " + item.getPrice());
      System.out.println("Quantity: " + item.getQuantity());
      System.out.println("Type: " + item.getType());
      System.out.println("Sales tax: " + item.getSalesTax());
      System.out.println("FInal price: " + item.getFinalPrice() + "\n");
      printLine();
    }
  }
}
