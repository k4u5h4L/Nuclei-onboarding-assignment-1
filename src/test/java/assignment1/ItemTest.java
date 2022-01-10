package assignment1;

import assignment1.models.Imported;
import assignment1.models.Manufactured;
import assignment1.models.Raw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ItemTest {

  @Test
  @DisplayName("Imported items calculate the right final prices")
  public void verifySalestaxCalculationImpoted() {
    Imported item = new Imported("cheese", 200, 5);

    Assertions.assertEquals(231, item.getFinalPrice());
  }

  @Test
  @DisplayName("Raw items calculate the right final prices")
  public void verifySalestaxCalculationRaw() {
    Raw item = new Raw("cheese", 200, 5);

    Assertions.assertEquals(225, item.getFinalPrice());
  }

  @Test
  @DisplayName("Manufactured items calculate the right final prices")
  public void verifySalestaxCalculationManufactured() {
    Manufactured item = new Manufactured("cheese", 200, 5);

    Assertions.assertEquals(229.5, item.getFinalPrice());
  }
}
