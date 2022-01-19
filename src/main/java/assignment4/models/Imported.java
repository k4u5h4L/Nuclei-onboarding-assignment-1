package assignment4.models;

import assignment4.models.enums.ItemType;

import javax.persistence.Entity;

@Entity
public class Imported extends ItemModel {

  public Imported() {
  }

  public Imported(final String name, final double price, final int quantity) {
    super(name, price, quantity);
    this.type = ItemType.IMPORTED;
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