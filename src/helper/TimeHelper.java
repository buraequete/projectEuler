package helper;

public class TimeHelper {
  static long startTime;
  static long stopTime;

  public static void start() {
    startTime = System.currentTimeMillis();
  }

  public static void stop() {
    stopTime = System.currentTimeMillis();
    System.out.println("Took " + (stopTime - startTime) + " ms");
  }
}
