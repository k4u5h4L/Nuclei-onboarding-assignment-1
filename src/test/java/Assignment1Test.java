import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class Assignment1Test {

  @Test
  @DisplayName("Exception thrown if name is not being passed first")
  public void verifyNameNotBeingWrittenFirst() {
    Assertions.assertThrows(InvalidArguementsException.class, () -> {
      Assignment1.validateInputs(new String[]{"-price", "20", "-name", "paneer", "-type", "raw"});
    });
  }

  @Test
  @DisplayName("Exception thrown if not enough arguements are passed")
  public void verifyNotEnoughArguements() {
    Assertions.assertThrows(InvalidArguementsException.class, () -> {
      Assignment1.validateInputs(new String[]{"-name", "20"});
    });
  }

  @Test
  @DisplayName("SI Hashmap is being created properly")
  public void verifyHashMapFormation() {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("-name", "cheese");
    map.put("-price", "200");
    map.put("-type", "raw");
    map.put("-quantity", "5");
    try {
      Assertions.assertEquals(Assignment1.getArguements(
          new String[]{"-name", "cheese", "-price", "200", "-type", "raw", "-quantity", "5"}), map);
    } catch (InvalidArguementsException e) {
      Assertions.fail("Should have not thrown an exception");
    }
  }

  @Test
  @DisplayName("Exception thrown if type does have any values but the ones defined")
  public void verifyTypeValues() {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("-name", "cheese");
    map.put("-price", "200");
    map.put("-type", "domestic");
    map.put("-quantity", "5");

    Assertions.assertThrows(InvalidArguementsException.class, () -> {
      Assignment1.validateTypeInput(map);
    });
  }
}
