package org.example.list;

public class MySingleLinkedList<T> implements List<T> {


	private Node<T> head;


	@Override
	public void insert(T element) {

		checkNull(element);

		if (head == null) {
			head = new Node<>(element, null);
			return;
		}

		Node<T> node = head;
		while (node.next != null) {
			node = node.next;
		}

		node.next = new Node<>(element, null);
	}

	private static <T> void checkNull(T element) {
		if (element == null) {
			throw new RuntimeException("element should not be null");
		}
	}

	@Override
	public void update(int index, T element) {
		checkValidIndex(index);
		int originalIndex = index;

		Node<T> node = head;
		while (index != 0 && node.next != null) {
			node = node.next;
			--index;
		}

		if (index != 0 || node == null) {
			throw new RuntimeException("Index out of bound: " + originalIndex);
		}

		node.value = element;
	}

	private static void checkValidIndex(int index) {
		if (index < 0) {
			throw new RuntimeException("index should be positive");
		}
	}

	@Override
	public void delete(T element) {

		if(head == null) {
			return;
		}

		// handle remove fist
		if (element.equals(head.value)) {
			head = head.next;
			return;
		}


		Node<T> node = head;

		while (node.next != null && !node.next.value.equals(element)) {
			node = node.next;
		}

		if(node.next == null || !node.next.value.equals(element)) {
			System.out.println("element not existed");
			return;
		}


		// handle normal remove
		if(node.next.next != null) {
			node.next = node.next.next;
			return;
		}


		// handle remove tail
		node.next = null;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("SingleLinkedList:\n");
		Node<T> node = head;
		if(node == null) {
			sb.append("LIST IS EMPTY");
			return sb.toString();
		}

		while(node != null) {
			sb.append(node.value);
			sb.append(" -> ");
			node = node.next;
		}

		sb.append(" NULL");

		return sb.toString();
	}
}


class Node<T> {

	T value;
	Node<T> next;

	public Node(T value, Node<T> next) {
		this.value = value;
		this.next = next;
	}
}
