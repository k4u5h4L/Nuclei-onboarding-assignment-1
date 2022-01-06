import org.junit.jupiter.api.Assertions;
import org.junit.Test;

public class Assignment1Test {

  @Test
  public void verifyNoExceptionThrown() {
    Assignment1.main(new String[]{});
  }

  @Test
  public void verifySalestaxCalculation() {
    Item item = new Item("cheese", 200, 5, "imported");

    Assertions.assertEquals(231, item.getFinalPrice());
  }
}
