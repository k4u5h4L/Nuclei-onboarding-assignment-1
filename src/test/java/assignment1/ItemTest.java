package assignment1;

import assignment1.models.ImportedItem;
import assignment1.models.ManufacturedItem;
import assignment1.models.RawItem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ItemTest {

  @Test
  @DisplayName("Imported items calculate the right final prices")
  public void verifySalestaxCalculationImpoted() {
    ImportedItem item = new ImportedItem("cheese", 200, 5, "imported");

    Assertions.assertEquals(231, item.getFinalPrice());
  }

  @Test
  @DisplayName("Raw items calculate the right final prices")
  public void verifySalestaxCalculationRaw() {
    RawItem item = new RawItem("cheese", 200, 5, "raw");

    Assertions.assertEquals(225, item.getFinalPrice());
  }

  @Test
  @DisplayName("Manufactured items calculate the right final prices")
  public void verifySalestaxCalculationManufactured() {
    ManufacturedItem item = new ManufacturedItem("cheese", 200, 5, "manufactured");

    Assertions.assertEquals(229.5, item.getFinalPrice());
  }
}
