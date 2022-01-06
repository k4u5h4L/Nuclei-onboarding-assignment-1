import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemTest {

  @Test
  public void verifySalestaxCalculationImpoted() {
    Item item = new Item("cheese", 200, 5, "imported");

    Assertions.assertEquals(231, item.getFinalPrice());
  }

  @Test
  public void verifySalestaxCalculationRaw() {
    Item item = new Item("cheese", 200, 5, "raw");

    Assertions.assertEquals(225, item.getFinalPrice());
  }

  @Test
  public void verifySalestaxCalculationManufactured() {
    Item item = new Item("cheese", 200, 5, "manufactured");

    Assertions.assertEquals(229.5, item.getFinalPrice());
  }
}
