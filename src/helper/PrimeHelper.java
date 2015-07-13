package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeHelper {
    protected static List<Boolean> primeList = new ArrayList<>();

    public static void fillPrimes(int n) {
        Boolean[] primes = new Boolean[n];
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

    public static boolean isPrime(int n) {
        return primeList.get(n);
    }
}
