package question;

import java.math.BigDecimal;

import helper.ResultHelper;
import helper.TimeHelper;

public class Question063 {
  public static void main(String[] args) {
    TimeHelper.start();

    int no = 0;
    for (int j = 1; j < 100000; j++) {
      int anti_no = 0;
      for (int i = 1; i < 10; i++) {
        if (length(i, j) == j) {
          no++;
        } else {
          anti_no++;
        }

        if (anti_no == 9) {
          ResultHelper.printOut(no);
          TimeHelper.stop();
          return;
        }
      }
    }
  }

  static int length(int i, int j) {
    return new BigDecimal(i).pow(j).toString().length();
  }
}
