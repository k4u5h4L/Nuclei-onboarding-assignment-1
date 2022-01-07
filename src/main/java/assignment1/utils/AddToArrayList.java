package assignment1.utils;

import assignment1.models.ImportedItem;
import assignment1.models.Item;
import assignment1.models.ManufacturedItem;
import assignment1.models.RawItem;
import assignment1.models.enums.ItemType;
import java.util.HashMap;
import java.util.List;

public class AddToArrayList {

  public static void add(List<Item> items, HashMap<String, String> item) {
    switch (item.get("-type")) {

      case "raw": {
        items.add(new RawItem(item.get("-name"), Double.parseDouble(item.get("-price")),
            Integer.parseInt(item.get("-quantity")), "raw"));
        break;
      }

      case "manufactured": {
        items.add(new ManufacturedItem(item.get("-name"), Integer.parseInt(item.get("-price")),
            Integer.parseInt(item.get("-quantity")), "manufactured"));
        break;
      }

      case "imported": {
        items.add(new ImportedItem(item.get("-name"), Integer.parseInt(item.get("-price")),
            Integer.parseInt(item.get("-quantity")), "imported"));
        break;
      }
    }
  }
}
