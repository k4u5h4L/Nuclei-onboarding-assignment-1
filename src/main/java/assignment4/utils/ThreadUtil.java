package assignment4.utils;

import assignment4.Main;
import assignment4.constants.Constants;
import assignment4.models.ItemModel;
import java.util.ArrayDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadUtil {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  /**
   * A function to wait for another thread.
   *
   * @param producerBuffer lock object
   */
  public static void wait(ArrayDeque<ItemModel> producerBuffer) {
    try {
      producerBuffer.wait();
    } catch (InterruptedException e) {
      log.error(Constants.THREAD_INTERRUPED, e);
    }
  }

  public static void sleep(int miliseconds) {
    try {
      Thread.sleep(miliseconds);
    } catch (InterruptedException e) {
      log.error(Constants.THREAD_INTERRUPED, e);
    }
  }
}
