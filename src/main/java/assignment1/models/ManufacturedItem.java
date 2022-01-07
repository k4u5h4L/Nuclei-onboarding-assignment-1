package assignment1.models;

import assignment1.exceptions.InvalidArguementsException;
import assignment1.models.enums.ItemType;
import assignment1.utils.ValidateType;

public class ManufacturedItem extends Item {

  public ManufacturedItem(final String name, final double price, final int quantity,
                          final String type) {
    super(name, price, quantity);
    try {
      ValidateType.validateTypeInput(type);
      this.type = ItemType.valueOf(type.toUpperCase());
    } catch (InvalidArguementsException e) {
      logger.error("-type should have 'raw', 'manufactured' or 'imported'.", e);
    }
  }

  private void calculateSalesTax() {
    salesTax = (0.125 * price) + (0.02 * (price + (0.125 * price)));
    finalPrice = price + salesTax;
  }

  @Override
  public double getSalesTax() {
    calculateSalesTax();
    return this.salesTax;
  }

  @Override
  public double getFinalPrice() {
    calculateSalesTax();
    return this.finalPrice;
  }
}

