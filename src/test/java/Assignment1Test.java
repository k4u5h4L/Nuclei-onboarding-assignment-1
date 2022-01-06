import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Assignment1Test {

  @Test
  public void verifySalestaxCalculation() {
    Item item = new Item("cheese", 200, 5, "imported");

    Assertions.assertEquals(231, item.getFinalPrice());
  }

  @Test
  public void verifySales() {
    Item item = new Item("cheese", 200, 5, "imported");

    Assertions.assertEquals(231, item.getFinalPrice());
  }
}
