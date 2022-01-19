package assignment4.utils;

import assignment4.models.ItemModel;
import java.util.ArrayList;

public class ConsumerUtil {

  /**
   * Checks if the requested item is already present in the arraylist
   *
   * @param items    ArrayList containing items
   * @param itemName The name of the item
   * @return true if item is present, false otherwise
   */
  public static boolean isItemPresent(ArrayList<ItemModel> items, String itemName) {
    for (ItemModel item : items) {
      if (item.getName().equalsIgnoreCase(itemName)) {
        return true;
      }
    }

    return false;
  }
}
