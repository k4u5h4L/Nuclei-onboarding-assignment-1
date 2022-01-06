package assignment1;

import assignment1.models.Item;
import assignment1.models.ItemWithTax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ItemTest {

  @Test
  @DisplayName("Imported items calculate the right final prices")
  public void verifySalestaxCalculationImpoted() {
    ItemWithTax item = new ItemWithTax("cheese", 200, 5, "imported");

    Assertions.assertEquals(231, item.getFinalPrice());
  }

  @Test
  @DisplayName("Raw items calculate the right final prices")
  public void verifySalestaxCalculationRaw() {
    ItemWithTax item = new ItemWithTax("cheese", 200, 5, "raw");

    Assertions.assertEquals(225, item.getFinalPrice());
  }

  @Test
  @DisplayName("Manufactured items calculate the right final prices")
  public void verifySalestaxCalculationManufactured() {
    ItemWithTax item = new ItemWithTax("cheese", 200, 5, "manufactured");

    Assertions.assertEquals(229.5, item.getFinalPrice());
  }
}
