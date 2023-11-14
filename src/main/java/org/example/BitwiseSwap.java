package org.example;

public class BitwiseSwap {


	public static void main(String[] args) {

		int a = 10;
		int b = 20;

		bitwiseSwap(a, b);
	}

	private static void bitwiseSwap(int a, int b) {

		System.out.printf("before:\na: %d\nb: %d\n", a, b);

		a = a ^ b;
		b = a ^ b;
		a = a ^ b;

		System.out.println();
		System.out.println();
		System.out.printf("after:\na: %d\nb: %d\n", a, b);

	}

}
