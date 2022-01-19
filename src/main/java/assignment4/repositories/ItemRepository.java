package assignment4.repositories;

import assignment4.models.ItemModel;
import assignment4.models.enums.ItemType;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<ItemModel, Long> {

  List<ItemModel> findByType(ItemType type);

  ItemModel findById(long id);
}