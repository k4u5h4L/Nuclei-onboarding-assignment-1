package assignment4.models;

import assignment4.models.enums.ItemType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ItemModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  protected String name;
  protected double price;
  protected int quantity;
  protected double salesTax;
  protected ItemType type;
  protected double finalPrice;

  final static Logger logger = LoggerFactory.getLogger(ItemModel.class);

  public ItemModel() {
  }

  ItemModel(String name, double price, int quantity) {
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

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return String.format("Item: [name=%s, price=%f, quantity=%d, type=%s, finalPrice=%f]", name,
        price, quantity, type, finalPrice);
  }
}