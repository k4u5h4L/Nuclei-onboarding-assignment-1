package assignment1.models;

import assignment1.exceptions.InvalidArguementsException;
import assignment1.models.enums.ItemType;

public class Item {

  protected String name;
  protected int price;
  protected int quantity;
  protected ItemType type;
  protected double salesTax;
  protected double finalPrice;

  Item(String name, int price, int quantity, String type) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    try {
      validateTypeInput(type);
      this.type = ItemType.valueOf(type.toUpperCase());
    } catch (InvalidArguementsException e) {
      System.out.println("-type should have 'raw', 'manufactured' or 'imported'. Exiting...");
      System.exit(1);
    }
  }

  /*
   * Function to check if 'type' has only one of the 3 values or not
   */
  public static void validateTypeInput(String type) throws InvalidArguementsException {
    if ((!(type.equalsIgnoreCase("raw") || type.equalsIgnoreCase("manufactured")
        || type.equalsIgnoreCase("imported")))) {
      throw new InvalidArguementsException(
          "-type should have only 3 types of values: raw, manufactured and imported");
    }
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

  public ItemType getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = ItemType.valueOf(type.toUpperCase());
  }

}
