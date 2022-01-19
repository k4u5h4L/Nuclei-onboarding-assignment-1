package assignment4;

import assignment4.models.Imported;
import assignment4.models.ItemModel;
import assignment4.models.Raw;
import assignment4.models.enums.ItemType;
import assignment4.repositories.ItemRepository;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
@SpringBootTest
public class ItemRepositoryTest {

  @Autowired
  ItemRepository itemRepository;

  private ItemModel item;
  private ItemModel anotherItem;

  @BeforeEach
  public void runBeforeEach() {
    item = new Raw("cheese", 100, 5);
    anotherItem = new Imported("paneer", 200, 10);
  }

  @Test
  @DisplayName("Item is being added to the database")
  public void verifyItemIsBeingAdded() {

    item = itemRepository.save(item);

    if (itemRepository.findById(item.getId()).isPresent()) {
      ItemModel itemToExpect = itemRepository.findById(item.getId()).get();

      Assertions.assertEquals(itemToExpect, item);

      itemRepository.delete(item);
    } else {
      Assertions.fail("Item could not be saved");
    }
  }

  @Test
  @DisplayName("Item is being fetched from the database")
  public void verifyItemIsBeingFetched() {
    item = itemRepository.save(item);

    ItemModel itemToExpect = itemRepository.findByType(ItemType.RAW).get(0);

    Assertions.assertEquals(itemToExpect.toString(), item.toString());

    itemRepository.delete(item);
  }
}