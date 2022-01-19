package assignment4.utils;

import assignment4.Main;
import assignment4.models.Imported;
import assignment4.models.ItemModel;
import assignment4.models.Manufactured;
import assignment4.models.Raw;
import assignment4.models.enums.ItemType;
import assignment4.repositories.ItemRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseSeederUtil {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  /**
   * Function to populate the database with some initial values
   *
   * @param repository repository for the Item table
   */
  public static void seed(ItemRepository repository) {
    // save a few customers
    repository.save(new Raw("cheese", 100, 5));
    repository.save(new Imported("paneer", 200, 10));
    repository.save(new Manufactured("curd", 50, 15));

    repository.save(new Raw("milk", 150, 3));
    repository.save(new Imported("yogurt", 100, 7));
    repository.save(new Manufactured("butter", 250, 14));
  }
}