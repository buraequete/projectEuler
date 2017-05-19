package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeHelper {
	static List<Boolean> primeList = new ArrayList<>();

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

	public static List<Integer> getPrimes(int limit) {
		return IntStream.range(1, limit).boxed().filter(p -> primeList.get(p)).collect(Collectors.toList());
	}
}
