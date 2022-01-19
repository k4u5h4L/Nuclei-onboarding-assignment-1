package assignment4;

import assignment4.repositories.ItemRepository;
import assignment4.threads.ConsumerThread;
import assignment4.threads.ProducerThread;

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
      ProducerThread p = new ProducerThread(repository);
      ConsumerThread c = new ConsumerThread(p, repository);

      p.start();
      c.start();
    };
  }

}
