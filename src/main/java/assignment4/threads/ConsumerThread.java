package assignment4.threads;

import assignment4.Main;
import assignment4.constants.Constants;
import assignment4.models.ItemModel;
import assignment4.repositories.ItemRepository;
import assignment4.utils.ConsumerUtil;
import assignment4.utils.ThreadUtil;
import java.util.ArrayDeque;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerThread extends Thread {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  ItemRepository repo;
  final ArrayDeque<ItemModel> producerBuffer;

  ArrayList<ItemModel> consumerItems;

  public ConsumerThread(ItemRepository repo, ArrayDeque<ItemModel> producerBuffer) {
    this.repo = repo;
    this.producerBuffer = producerBuffer;
    consumerItems = new ArrayList<>();
  }

  public void run() {
    for (int i = 0; i < ProducerThread.getNumberOfRows(); i++) {
      ItemModel item;
      synchronized (producerBuffer) {
        while (producerBuffer.isEmpty() || ConsumerUtil.isItemPresent(consumerItems,
            producerBuffer.getFirst().getName())) {
          System.out.println(Thread.currentThread().getName() + " is waiting...");
          ThreadUtil.wait(producerBuffer);
        }

        item = producerBuffer.remove();

        System.out.println(Constants.CONSUMER_CONSUMES_MESSAGE + item.getName() + ",\tThread: "
            + Thread.currentThread().getName());
        producerBuffer.notify();
      }
      consumerItems.add(item);
    }
  }

  public ArrayList<ItemModel> getConsumerItems() {
    return consumerItems;
  }
}