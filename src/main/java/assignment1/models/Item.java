package assignment1.models;


import assignment1.Application;
import assignment1.models.enums.ItemType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Item {

  protected String name;
  protected double price;
  protected int quantity;
  protected double salesTax;
  protected ItemType type;
  protected double finalPrice;

  final static Logger logger = LoggerFactory.getLogger(Item.class);

  Item(String name, double price, int quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public ItemType getType() {
    return type;
  }

  public void setType(final ItemType type) {
    this.type = type;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
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

  public double getSalesTax() {
    return salesTax;
  }

  public void setSalesTax(final double salesTax) {
    this.salesTax = salesTax;
  }

  public double getFinalPrice() {
    return finalPrice;
  }

  public void setFinalPrice(final double finalPrice) {
    this.finalPrice = finalPrice;
  }
}
