package assignment1.models;

import assignment1.exceptions.InvalidArguementsException;
import assignment1.models.enums.ItemType;
import assignment1.utils.ValidateType;

public class Imported extends Item {

  public Imported(final String name, final double price, final int quantity) {
    super(name, price, quantity);
    this.type = ItemType.IMPORTED;
  }

  /**
   * Function to calculate sales tax
   */
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

  /**
   * @return sales tax after calculation
   */
  @Override
  public double getSalesTax() {
    calculateSalesTax();
    return this.salesTax;
  }

  /**
   * @return final price after calculation
   */
  @Override
  public double getFinalPrice() {
    calculateSalesTax();
    return this.finalPrice;
  }
}