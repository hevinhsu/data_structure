package org.example;

import java.util.Arrays;
import java.util.function.BiPredicate;

public class MonotonicStack {

	private int[] array;
	private int ptr;

	private BiPredicate<Integer, Integer> predicate;

	public enum Order {
		ASC((oldVal, newVal) -> newVal > oldVal),

		DESC((oldVal, newVal) -> newVal < oldVal);



		private final BiPredicate<Integer, Integer> order;

		Order(BiPredicate<Integer, Integer> order) {
			this.order = order;
		}
	}

	public MonotonicStack(int size) {
		this.ptr = -1;
		array = new int[size];
		predicate = Order.ASC.order;
	}

	public MonotonicStack(int size, Order order) {
		this.ptr = -1;
		array = new int[size];
		this.predicate = order.order;
	}

	public void push(int x) {
		while (!isEmpty()) {
			int top = this.peek();
			if (!predicate.test(top, x)) {
				this.pop();
			} else {
				break;
			}
		}

		++ptr;
		array[ptr] = x;
	}

	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("stack is empty");
		}
		int val = this.peek();
		--ptr;
		return val;
	}


	public int peek() {
		if (isEmpty()) {
			throw new RuntimeException("stack is empty");
		}
		return array[ptr];
	}

	public boolean isEmpty() {
		return ptr == -1;
	}

	public int size() {
		return 1 + ptr;
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}

		int[] ints = Arrays.copyOf(array, size());
		return Arrays.toString(ints);
	}

	public static void main(String[] args) {

		MonotonicStack stack = new MonotonicStack(5, Order.ASC);
		stack.push(1);
		stack.push(3);
		stack.push(5);
		stack.push(7);
		stack.push(9);
		stack.push(6);
		System.out.println(stack);
	}

}