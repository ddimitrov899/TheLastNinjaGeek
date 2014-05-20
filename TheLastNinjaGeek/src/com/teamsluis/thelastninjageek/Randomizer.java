package com.teamsluis.thelastninjageek;

import java.util.List;
import java.util.Random;

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
		for (int i = 0; i < a.size(); i++) {
			int j = getRandomNumberInRange(0, a.size() - 1);
			swap(a, i, j);
		}
	}

	private static <T> void swap(List<T> a, int i, int j) {
		T swap = a.get(i);
		a.set(i, a.get(j)); 
		a.set(j, swap); 
	}
}