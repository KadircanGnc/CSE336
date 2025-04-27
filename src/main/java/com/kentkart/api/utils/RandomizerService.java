package com.kentkart.api.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;

public class RandomizerService {

  private RandomizerService() {
    throw new IllegalStateException("Utility class");
  }

  private static final Random random = new Random();

  /**
   * Creates a random boarding time using default date range (current time to one
   * week from now)
   * 
   * @return random timestamp in epoch milliseconds
   */
  public static Long createRandomTime() {
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = LocalDateTime.now().plusWeeks(1);
    return createRandomTime(startDate, endDate);
  }

  public static Long createRandomTime(LocalDateTime startDate, LocalDateTime endDate) {
    // Convert to epoch milliseconds
    long startEpochMilli = startDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    long endEpochMilli = endDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

    // Generate random time between start and end
    return startEpochMilli + random.nextLong(endEpochMilli - startEpochMilli);
  }

  public static String createRandomString(int length) {
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      sb.append(chars.charAt(random.nextInt(chars.length())));
    }
    return sb.toString();
  }

  public static int getRandomNumberInRange(int i, int j) {
    return random.nextInt(j - i + 1) + i;
  }

  public static Double getRandomLatLng() {
    return random.nextDouble() * 180 - 90;
  }

  public static double getRandomLatLng(double min, double max) {
    return min + (max - min) * Math.random(); // Generates a value within [min, max]
}

  public static List<String> getRandomElements(List<String> list, int count) {
    return random.ints(0, list.size()).distinct().limit(count).mapToObj(list::get).toList();
  }

  public static List<String> getRandomElements(int count) {
    List<String> randomStrings = new java.util.ArrayList<>();
    for (int i = 0; i < count; i++) {
      randomStrings.add(createRandomString(getRandomNumberInRange(5, 10)));
    }
    return randomStrings;
  }

  public static double getRandomDoubleInRange(double min, double max) {
    return min + (max - min) * Math.random(); // Math.random() generates a value between 0 and 1, scaled to the range [min, max]
}
}
