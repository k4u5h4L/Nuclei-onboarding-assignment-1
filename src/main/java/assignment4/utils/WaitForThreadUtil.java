package assignment4.utils;

import assignment4.threads.ProducerThread;

public class WaitForThreadUtil {

  /**
   * A function to wait for another thread.
   *
   * @param prod lock object
   */
  public static void wait(ProducerThread prod) {
    try {
      prod.wait();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
