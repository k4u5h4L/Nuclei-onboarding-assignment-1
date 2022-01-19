package assignment4.threads;

import assignment4.Main;
import assignment4.constants.Constants;
import assignment4.models.ItemModel;
import assignment4.repositories.ItemRepository;
import assignment4.services.ListPrinterService;
import assignment4.utils.WaitForThreadUtil;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerThread extends Thread {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  final ProducerThread prod;
  ItemRepository repo;
  ListPrinterService listPrinterService;

  ArrayList<ItemModel> consumerItems;

  public ConsumerThread(ProducerThread prod, ItemRepository repo) {
    this.prod = prod;
    this.repo = repo;
    consumerItems = new ArrayList<>();
    listPrinterService = new ListPrinterService();
  }

  public void run() {
    for (int i = 0; i < ProducerThread.getNumberOfRows(); i++) {
      ItemModel item;
      synchronized (prod) {
        while (ProducerThread.getProducerItems().size() == 0) {
          WaitForThreadUtil.wait(prod);
        }

        item = ProducerThread.getProducerItems().remove(0);

        System.out.println(Constants.CONSUMER_CONSUMES_MESSAGE + item.getName());
        prod.notify();
      }

      consumerItems.add(item);
    }

    listPrinterService.display(consumerItems);
  }
}