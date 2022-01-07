package assignment1.models;

import assignment1.exceptions.InvalidArguementsException;
import assignment1.models.enums.ItemType;
import assignment1.utils.ValidateType;

public class ImportedItem extends Item {

  public ImportedItem(final String name, final double price, final int quantity,
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
    double surcharge;
    double importDuty = (0.1 * price) + price;
    if (importDuty < 100) {
      surcharge = 5;
    } else if (importDuty < 200) {
      surcharge = 10;
    } else {
      importDuty = 0.1 * price;
      importDuty = price + importDuty;
      surcharge = 0.05 * importDuty;
    }
    finalPrice = importDuty + surcharge;
    salesTax = finalPrice - price;
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