package questions;

import helper.ResultHelper;
import helper.TimeHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question58 {
    static List<Boolean> primeList = new ArrayList<>();
    static List<Integer> primeDiagonals = new ArrayList<>();
    static List<Integer> diagonals = new ArrayList<>();

    public static void main(String[] args) {
        TimeHelper.start();
        fillPrimes();
        int result = 0, lastValue = 1;
        diagonals.add(1);

        for (int i = 3;; i += 2) {
            List<Integer> newDiagonals = new ArrayList<>();
            int increment = i - 1;
            for (int j = 0; j < 4; j++) {
                lastValue += increment;
                newDiagonals.add(lastValue);
            }
            if (getPercentage(newDiagonals) < 10) {
                result = i;
                break;
            }
        }
        TimeHelper.stop();
        ResultHelper.printOut(result);
    }

    private static double getPercentage(List<Integer> newDiagonals) {
        diagonals.addAll(newDiagonals);
        primeDiagonals.addAll(newDiagonals.stream().filter(d -> isPrime(d)).collect(Collectors.toList()));

        double denumerator = diagonals.size();
        double numerator = primeDiagonals.size();
        double percentage = numerator * 100 / denumerator;
        return percentage;
    }

    private static void fillPrimes() {
        // Due to this part, takes ~40 seconds to get the results.
        // Also had to use -Xmx4096m for VM not to have OutOfMemoryException
        Boolean[] primes = new Boolean[700000000];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i = 2; i < primes.length; i++) {
            if (primes[i]) {
                for (int j = 2; i * j < primes.length; j++) {
                    primes[i * j] = false;
                }
            }
        }
        primeList = Arrays.asList(primes);
    }

    private static boolean isPrime(int n) {
        return primeList.get(n);
    }
}
