package question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import helper.ResultHelper;
import helper.TimeHelper;
import resource.Resource59;

public class Question59 extends Resource59 {
  public static void main(String[] args) {
    TimeHelper.start();
    int i, j, k, l;
    bigloop: for (i = 97; i < 123; i++) {
      for (j = 97; j < 123; j++) {
        for (k = 97; k < 123; k++) {
          List<Integer> decryptedText = new ArrayList<>();
          for (l = 0; l < encryptedText.size(); l += 3) {
            Integer x = encryptedText.get(l) ^ i;
            if (l == 1200) {
              decryptedText.add(x);
            } else {
              Integer y = encryptedText.get(l + 1) ^ j, z = encryptedText.get(l + 2) ^ k;
              decryptedText.addAll(Arrays.asList(x, y, z));
            }
          }
          String text = decryptedText.stream().map(c -> "" + Character.toChars(c)[0])
              .collect(Collectors.joining());
          if (text.contains("the") && text.contains("be") && text.contains("to")
              && text.contains("of") && text.contains("and")) {
            ResultHelper
                .printOut(decryptedText.stream().reduce((a, b) -> a + b).get() + " -- " + text);
            break bigloop;
          }
        }
      }
    }
    TimeHelper.stop();
  }
}
