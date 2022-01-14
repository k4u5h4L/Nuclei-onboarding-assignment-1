package assignment1.utils;

import assignment1.exceptions.InvalidArguementsException;
import assignment1.models.Imported;
import assignment1.models.Item;
import assignment1.models.Manufactured;
import assignment1.models.Raw;
import assignment1.models.enums.ItemType;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddToArrayList {

  final static Logger logger = LoggerFactory.getLogger(AddToArrayList.class);

  /**
   * Function to add an item to an ArrayList
   *
   * @param items ArrayList object to which the item shoud be added
   * @param item  The Item object which should be added to the ArrayList
   */
  public static void add(List<Item> items, HashMap<String, String> item) {
    try {
      ValidateType.validateTypeInput(item.get("-type"));
    } catch (InvalidArguementsException e) {
      logger.error("-type should have 'raw', 'manufactured' or 'imported'.", e);
    }

    ItemType itemTypeChoice = ItemType.valueOf(item.get("-type").toUpperCase());
    switch (itemTypeChoice) {

      case RAW: {
        items.add(new Raw(item.get("-name"), Double.parseDouble(item.get("-price")),
            Integer.parseInt(item.get("-quantity"))));
        break;
      }

      case MANUFACTURED: {
        items.add(new Manufactured(item.get("-name"), Integer.parseInt(item.get("-price")),
            Integer.parseInt(item.get("-quantity"))));
        break;
      }

      case IMPORTED: {
        items.add(new Imported(item.get("-name"), Integer.parseInt(item.get("-price")),
            Integer.parseInt(item.get("-quantity"))));
        break;
      }
    }
  }
}
