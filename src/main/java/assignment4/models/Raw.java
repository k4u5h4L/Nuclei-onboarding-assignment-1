package assignment4.models;

import assignment4.models.enums.ItemType;

import javax.persistence.Entity;

@Entity
public class Raw extends ItemModel {

  public Raw() {
  }

  public Raw(final String name, final double price, final int quantity) {
    super(name, price, quantity);
    this.type = ItemType.RAW;
  }

  private void calculateSalesTax() {
    salesTax = 0.125 * price;
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
