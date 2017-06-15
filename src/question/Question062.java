package question;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import helper.ResultHelper;
import helper.TimeHelper;

public class Question062 {
  static int size = 10000;
  static Map<String, List<String>> cubes = new HashMap<>();

  static final char ZERO = '0', ONE = '1', TWO = '2', THREE = '3', FOUR = '4';
  static final char FIVE = '5', SIX = '6', SEVEN = '7', EIGHT = '8', NINE = '9';

  public static void main(String[] args) {
    TimeHelper.start();
    fillCubes();
    for (List<String> cubeList : cubes.values()) {
      if (cubeList.size() == 5) {
        ResultHelper.printOut(cubeList.stream().sorted().findFirst().get());
      }
    }
    TimeHelper.stop();
  }

  private static void fillCubes() {
    for (int i = 1; i <= size; i++) {
      BigDecimal I = new BigDecimal(i);
      String I_str = I.multiply(I).multiply(I).toString();
      String digiKey = createDigitStr(I_str);
      if (cubes.get(digiKey) == null) {
        List<String> cubeList = new ArrayList<>();
        cubeList.add(I_str);
        cubes.put(digiKey, cubeList);
      } else {
        cubes.get(digiKey).add(I_str);
      }
    }
  }

  private static String createDigitStr(String str) {
    String input = str;
    int d0 = 0, d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0, d6 = 0, d7 = 0, d8 = 0, d9 = 0;
    while (input.length() > 0) {
      int index = input.length() - 1;
      switch (input.charAt(index)) {
        default:
          d0++;
          break;
        case ONE:
          d1++;
          break;
        case TWO:
          d2++;
          break;
        case THREE:
          d3++;
          break;
        case FOUR:
          d4++;
          break;
        case FIVE:
          d5++;
          break;
        case SIX:
          d6++;
          break;
        case SEVEN:
          d7++;
          break;
        case EIGHT:
          d8++;
          break;
        case NINE:
          d9++;
          break;
      }
      input = input.substring(0, index);
    }
    return str.length() + "-" + d0 + "," + d1 + "," + d2 + "," + d3 + "," + d4 + "," + d5 + "," + d6
        + "," + d7 + "," + d8 + "," + d9;
  }
}
