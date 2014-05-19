package com.teamsluis.thelastninjageek;

import java.util.Random;
import java.util.List;

public class Randomizer<T> {
	private static Random randomGenerator = new Random();

	public static int getRandomNumberInRange(int lowerBound, int upperBound) {
		if (lowerBound > upperBound) {
			throw new IllegalArgumentException(
					"Lower bound cannot be greater than upper bound.");
		}

		long range = (long) upperBound - (long) lowerBound + 1;
		long fraction = (long) (range * randomGenerator.nextDouble());
		int randomNumber = (int) (fraction + lowerBound);

		return randomNumber;
	}

	public static <T> void shuffleList(List<T> a) {
		int n = a.size();
		Random random = new Random();
		random.nextInt();
		for (int i = 0; i < n; i++) {
			int change = i + random.nextInt(n - i);
			swap(a, i, change);
		}
	}

	private static <T> void swap(List<T> a, int i, int change) {
		T swap = a.get(i);
		a.set(i, a.get(change));
		a.set(change, swap);
	}
}