package questions;

import helper.ResultHelper;
import helper.TimeHelper;

import java.math.BigDecimal;

public class Question55 {
    public static void main(String[] args) {
        TimeHelper.start();
        int no = 0;
        for (int i = 1; i < 10000; i++) {
            if (isLychrel(new BigDecimal(i))) {
                no++;
            }
        }
        TimeHelper.stop();
        ResultHelper.printOut(no);
    }

    private static boolean isLychrel(BigDecimal no) {
        BigDecimal temp = no;
        for (int i = 1; i < 50; i++) {
            if (i > 1 && isPalindrome(temp)) {
                return false;
            } else {
                temp = reverseAdd(temp);
            }
        }
        return true;
    }

    private static BigDecimal reverseAdd(BigDecimal no) {
        String noStr = no.toString(), reverse = "";
        while (noStr.length() > 0) {
            reverse += noStr.charAt(noStr.length() - 1);
            noStr = noStr.substring(0, noStr.length() - 1);
        }
        return new BigDecimal(reverse).add(no);
    }

    private static boolean isPalindrome(BigDecimal no) {
        String noStr = no.toString();
        while (noStr.length() > 1) {
            if (noStr.charAt(0) != noStr.charAt(noStr.length() - 1)) {
                return false;
            }
            noStr = noStr.substring(1, noStr.length() - 1);
        }
        return true;
    }
}
