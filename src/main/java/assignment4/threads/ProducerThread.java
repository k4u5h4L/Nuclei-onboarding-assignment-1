package assignment4.threads;

import assignment4.Main;
import assignment4.constants.Constants;
import assignment4.models.ItemModel;
import assignment4.repositories.ItemRepository;
import assignment4.utils.ThreadUtil;
import java.util.ArrayDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerThread extends Thread {

  private static final Logger log = LoggerFactory.getLogger(Main.class);
  protected static long numberOfRows;
  static int value;

  final ArrayDeque<ItemModel> producerBuffer;
  ItemRepository repo;

  public ProducerThread(ItemRepository repo, ArrayDeque<ItemModel> producerBuffer) {
    this.repo = repo;
    this.producerBuffer = producerBuffer;
    numberOfRows = repo.count();
    value = 0;
  }

  public void run() {
    for (ItemModel item : repo.findAll()) {
      synchronized (producerBuffer) {
        while (producerBuffer.size() == Constants.BUFFER_SIZE) {
          System.out.println(Thread.currentThread().getName() + " is waiting...");
          ThreadUtil.wait(producerBuffer);
        }

        System.out.println(Constants.PRODUCER_PRODUCES_MESSAGE + item.getName() + ",\tThread: "
            + Thread.currentThread().getName());

        producerBuffer.add(item);
        producerBuffer.notify();
      }
    }

  }


  public static long getNumberOfRows() {
    return numberOfRows;
  }

  public static void setNumberOfRows(final long numberOfRows) {
    ProducerThread.numberOfRows = numberOfRows;
  }
}