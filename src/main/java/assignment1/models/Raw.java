package assignment1.models;

import assignment1.exceptions.InvalidArguementsException;
import assignment1.models.enums.ItemType;
import assignment1.utils.ValidateType;

public class Raw extends Item {

  public Raw(final String name, final double price, final int quantity) {
    super(name, price, quantity);
    this.type = ItemType.RAW;
  }

  /**
   * Function to calculate sales tax
   */
  private void calculateSalesTax() {
    salesTax = 0.125 * price;
    finalPrice = price + salesTax;
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
