package assignment4;

import assignment4.constants.Constants;
import assignment4.models.ItemModel;
import assignment4.repositories.ItemRepository;
import assignment4.services.ListPrinterService;
import assignment4.threads.ConsumerThread;
import assignment4.threads.ProducerThread;

import java.util.ArrayDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  /**
   * @param repository The Item Repository which interacts with the database table
   * @return a function which runs on the command line along with the spring application
   */
  @Bean
  public CommandLineRunner runner(ItemRepository repository) {
    return (args) -> {
      ArrayDeque<ItemModel> producerBuffer = new ArrayDeque<>(Constants.BUFFER_SIZE);

      ProducerThread producerThread1 = new ProducerThread(repository, producerBuffer);
      ProducerThread producerThread2 = new ProducerThread(repository, producerBuffer);
      ProducerThread producerThread3 = new ProducerThread(repository, producerBuffer);

      ConsumerThread consumerThread1 = new ConsumerThread(repository, producerBuffer);
      ConsumerThread consumerThread2 = new ConsumerThread(repository, producerBuffer);
      ConsumerThread consumerThread3 = new ConsumerThread(repository, producerBuffer);

      Thread[] threadPool =
          {producerThread1, producerThread2, producerThread3, consumerThread1, consumerThread2,
              consumerThread3};

      int producerCount = 1;
      int consumerCount = 1;

      for (Thread thread : threadPool) {
        if (thread.getClass() == ProducerThread.class) {
          thread.setName("Producer " + producerCount++);
        } else {
          thread.setName("Consumer " + consumerCount++);
        }

        thread.start();
      }

      for (Thread thread : threadPool) {
        thread.join();
      }

      ListPrinterService listPrinterService = new ListPrinterService();

      for (Thread thread : threadPool) {
        if (thread.getClass() == ConsumerThread.class) {
          listPrinterService.display(((ConsumerThread) thread).getConsumerItems(),
              thread.getName());
        }
      }
    };
  }

}
