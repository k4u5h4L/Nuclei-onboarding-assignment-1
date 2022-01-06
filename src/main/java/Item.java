public class Item {

  private String name;
  private int price;
  private int quantity;
  private String type;
  private double salesTax;
  private double finalPrice;

  Item(String name, int price, int quantity, String type) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.type = type;
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

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
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
    if (type.equals("raw")) {
      salesTax = 0.125 * price;
      finalPrice = price + salesTax;
    } else if (type.equals("manufactured")) {
      salesTax = (0.125 * price) + (0.02 * (price + (0.125 * price)));
      finalPrice = price + salesTax;
    } else {
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
  }
}
