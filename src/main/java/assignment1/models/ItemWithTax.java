package assignment1.models;

public class ItemWithTax extends Item {

  public ItemWithTax(final String name, final int price, final int quantity, final String type) {
    super(name, price, quantity, type);
  }

  public void display() {
    System.out.println("Item name: " + this.getName());
    System.out.println("Item price: " + this.getPrice());
    System.out.println("Item quantity: " + this.getQuantity());
    System.out.println("Item type: " + this.getType().toString().toLowerCase());
    System.out.println("Item sales tax: " + this.getSalesTax());
    System.out.println("Item final price: " + this.getFinalPrice() + "\n\n");
  }

  public double getSalesTax() {
    calculateSalesTax();
    return this.salesTax;
  }

  public double getFinalPrice() {
    calculateSalesTax();
    return this.finalPrice;
  }

  private void calculateSalesTax() {
    switch (type) {
      case RAW: {
        salesTax = 0.125 * price;
        finalPrice = price + salesTax;
        break;
      }

      case MANUFACTURED: {
        salesTax = (0.125 * price) + (0.02 * (price + (0.125 * price)));
        finalPrice = price + salesTax;
        break;
      }

      case IMPORTED: {
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
        break;
      }
    }
  }
}
