package assignment4.threads;

import assignment4.Main;
import assignment4.constants.Constants;
import assignment4.models.ItemModel;
import assignment4.repositories.ItemRepository;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerThread extends Thread {

  private static final Logger log = LoggerFactory.getLogger(Main.class);
  protected static long numberOfRows;
  static int value;

  static ArrayList<ItemModel> producerItems;
  ItemRepository repo;

  public ProducerThread(ItemRepository repo) {
    producerItems = new ArrayList<>();
    this.repo = repo;
    numberOfRows = repo.count();
    value = 0;
  }

  public void run() {
    for (ItemModel item : repo.findAll()) {
      System.out.println(Constants.PRODUCER_PRODUCES_MESSAGE + item.getName());

      synchronized (this) {
        producerItems.add(item);
        this.notify();
      }
    }
  }

  public static ArrayList<ItemModel> getProducerItems() {
    return producerItems;
  }

  public static void setProducerItems(final ArrayList<ItemModel> producerItems) {
    ProducerThread.producerItems = producerItems;
  }

  public static long getNumberOfRows() {
    return numberOfRows;
  }

  public static void setNumberOfRows(final long numberOfRows) {
    ProducerThread.numberOfRows = numberOfRows;
  }
}