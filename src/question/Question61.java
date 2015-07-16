package question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import helper.PrimeHelper;
import helper.ResultHelper;
import helper.TimeHelper;

public class Question61 extends PrimeHelper {
  static Map<Integer, Map<Integer, Integer>> polygonalityMap = new HashMap<>();
  static List<Integer> fourDigits =
      IntStream.rangeClosed(1000, 9999).boxed().collect(Collectors.toList());

  public static void main(String[] args) {
    TimeHelper.start();
    fillLists();
    for (int i : fourDigits) {
      for (int j : fourDigits) {
        if (i == j) {
          continue;
        }
        for (int k : fourDigits) {
          if (i == k && j == k) {
            continue;
          }
          for (int l : fourDigits) {
            if (i == l && j == l && k == l) {
              continue;
            }
            for (int m : fourDigits) {
              if (i == m && j == m && k == m && l == m) {
                continue;
              }
              for (int n : fourDigits) {
                if (i == n && j == n && k == n && l == n && m == n) {
                  continue;
                }
                if (isCircular(i, j, k, l, m, n)) {
                  System.out.println(i + " " + j + " " + k + " " + l + " " + m + " " + n);
                }
              }
            }
          }
        }
      }
    }
    ResultHelper.printOut("");
    TimeHelper.stop();
  }

  // private static boolean comparePolygonality(int i, int j) {
  // Set<Integer> keyset_i = polygonalityMap.get(i).keySet();
  // Set<Integer> keyset_j = polygonalityMap.get(j).keySet();
  // if () {
  //
  // }
  // }

  private static boolean isCircular(int a, int b, int c, int d, int e, int f) {
    return checkParts(a, b) && checkParts(b, c) && checkParts(c, d) && checkParts(d, e)
        && checkParts(e, f) && checkParts(f, a);
  }

  private static boolean checkParts(int i, int j) {
    return ((int) j / 100) == (i % 100);
  }

  private static void fillLists() {
    for (int i = 1; i < 200; i++) {
      insertIntoMap(i * (i + 1) / 2, 3, i);
      insertIntoMap(i * i, 4, i);
      insertIntoMap(i * (3 * i - 1) / 2, 5, i);
      insertIntoMap(i * (2 * i - 1), 6, i);
      insertIntoMap(i * (5 * i - 3) / 2, 7, i);
      insertIntoMap(i * (3 * i - 2), 8, i);
    }
  }

  private static void insertIntoMap(int v, int p, int n) {
    if (polygonalityMap.containsKey(v)) {
      polygonalityMap.get(v).put(p, n);
    } else {
      Map<Integer, Integer> tempMap = new HashMap<>();
      tempMap.put(p, n);
      polygonalityMap.put(v, tempMap);
    }
  }
}
