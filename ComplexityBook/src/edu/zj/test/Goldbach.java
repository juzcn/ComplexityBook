package edu.zj.test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Goldbach {

	public static void main(String[] args) {
		BigInteger evenNumber = new BigInteger("4");
		List<BigInteger> primeNumberList = new ArrayList<>();
		BigInteger TWO = new BigInteger("2");
		primeNumberList.add(TWO);
		primeNumberList.add(new BigInteger("3"));
		BigInteger sum;
		int i;
		int j;
		while (true) {
			iLoop: for (i = 0; i < primeNumberList.size(); i++) {
				if (primeNumberList.get(i).multiply(TWO).compareTo(evenNumber)>0) {
					System.out.println(evenNumber + " Not found");
					System.exit(-1);
				}
				jLoop: for (j = i; j < primeNumberList.size(); j++) {
					sum = primeNumberList.get(i).add(primeNumberList.get(j));
					if (sum.compareTo(evenNumber) < 0)
						continue jLoop;
					if (sum.compareTo(evenNumber) == 0) {
						System.out.println(evenNumber + "=" + primeNumberList.get(i) + "+" + primeNumberList.get(j));
						break iLoop;
					}
					if (sum.compareTo(evenNumber) > 0) {
						continue iLoop;
					}
				}
			}
			evenNumber = evenNumber.add(BigInteger.ONE);
			for (i = 1; i < primeNumberList.size(); i++) {
				if (evenNumber.remainder(primeNumberList.get(i)).equals(BigInteger.ZERO)) {
					break;
				}
			}
			if (i == primeNumberList.size())
				primeNumberList.add(evenNumber);
			evenNumber = evenNumber.add(BigInteger.ONE);
		}
	}
}
