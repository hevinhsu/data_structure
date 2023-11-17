package org.example.list.skiplist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.example.list.List;

public class SkipList<T extends Comparable<T>> implements List<T> {

	public static final float RANDOM_RATE = 0.5f;
	public static final int MAX_LEVEL = 2 << 1;  // 4
	private static final long DEFAULT_SEED = 1L;
	private final Random random = new Random(DEFAULT_SEED);


	private final SkipListNode<T> head;

	public SkipList() {
		head = new SkipListNode<>(null, MAX_LEVEL);  /* head does not contain any real element. */
	}

	public static void main(String[] args) {

		SkipList<Integer> integerSkipList = new SkipList<>();
		for (int i = 0; i < 10; i++) {
			integerSkipList.insert(i);
		}

		integerSkipList.delete(0);
		System.out.println(integerSkipList);

//		System.out.println(integerSkipList);

//		integerSkipList.search(1);

//		integerSkipList.delete(6);
//		System.out.println(integerSkipList);

//		integerSkipList.update(Integer.valueOf(2), Integer.valueOf(11));
//		System.out.println(integerSkipList);
	}


	@Override
	public void insert(T element) {

		if (head.next[0] == null) {
			SkipListNode<T> node = new SkipListNode(element, MAX_LEVEL);
			for (int i = 0; i < MAX_LEVEL; i++) {
				head.next[i] = node;
			}
			return;
		}

		SkipListNode<T> node = head;
		int newLevel = getRandomLevel(random);
		SkipListNode<T> newNode = new SkipListNode<>(element, newLevel);

		SkipListNode<T>[] prevNodes = getSkipListNodes(element, node);

		for (int i = 0; i < newLevel; ++i) {
			SkipListNode<T> prevNode = prevNodes[i];
			newNode.next[i] = prevNode.next[i];
			prevNode.next[i] = newNode;
		}

	}

	private int getRandomLevel(Random random) {
		int level = 1;
		while (random.nextFloat(0, 1) < RANDOM_RATE && level < MAX_LEVEL) {
			level += 1;
		}
		return level;
	}

	@Override
	public void update(int index, T element) {
		throw new UnsupportedOperationException("can not support index version");
	}

	public void update(T before, T after) {
		this.delete(before);
		this.insert(after);
	}

	@Override
	public void delete(T element) {

		SkipListNode<T> node = head;

		SkipListNode<T>[] prevNodes = getSkipListNodes(element, node);

		if(prevNodes[0].next[0] == null || prevNodes[0].next[0].value.compareTo(element) !=0) {
			return;
		}

		for (int i = 0; i < MAX_LEVEL; i++) {
			if(prevNodes[i].next[i]!= null && prevNodes[i].next[i].value.compareTo(element) !=0) {
				break;
			}

			prevNodes[i].next[i] = prevNodes[i].next[i].next[i];
		}


	}

	private <T> SkipListNode[] getSkipListNodes(T element, SkipListNode node) {
		SkipListNode[] prevNodes = new SkipListNode[MAX_LEVEL];

		for (int i = MAX_LEVEL - 1; i >= 0; --i) {
			while (node.next[i] != null && node.next[i].value.compareTo(element) < 0) {
				node = node.next[i];
			}

			prevNodes[i] = node;
		}
		return prevNodes;
	}

	/*
		the value of x, y is offset. not the absolute position in the Cartesian coordinate system.
 */
	@Override
	public T search(T element) {
		SkipListNode<T> node = head;
		int x = -1, y = MAX_LEVEL - 1;
		int step = 0;

		while (y >= 0) {

			SkipListNode<T>[] nextNode = node.next;
			// horizontal displacement
			while (nextNode[y] != null) {
				int compared = nextNode[y].value.compareTo(element);
				if (compared == 0) {
					step++;
					System.out.printf("step: %d, find value: %s at [%d, %d], value: %s --> %s%n", step,
							element, 1 + x, y, node.value, nextNode[y].value);
					return nextNode[y].value;
				} else if (compared < 0) {
					step++;
					String nextOldValue = node.value == null ? "NULL" : node.value.toString();
					String nextNewValue = nextNode[y].value == null ? "NULL" : nextNode[y].value.toString();
					System.out.printf("step: %d, move from [%d, %d] --> [%d, %d], value: %s --> %s.%n", step,
							x, y, ++x, y, nextOldValue, nextNewValue);
					node = nextNode[y];
					nextNode = node.next;
				} else {
					break;
				}
			}

			// vertical displacement
			step++;
			String nextOldValue = node.value == null ? "NULL" : node.value.toString();
			String nextNewValue = nextNode[y] == null ? "NULL"
					: nextNode[y - 1].value == null ? "NULL" : nextNode[y - 1].value.toString();
			System.out.printf(
					"step: %d, move from [%d, %d] --> [%d, %d], value: %s --> %s(next value).%n", step,
					x, y, x, --y, nextOldValue, nextNewValue);
		}

		System.out.println("found nothing with step: " + step);
		return null;
	}


	@Override
	public String toString() {
		ArrayList<ArrayList<T>> lines = new ArrayList<>();

		int size = 0;
		SkipListNode<T> forCountSize = this.head.next[0];
		while (forCountSize != null) {
			size++;
			forCountSize = forCountSize.next[0];
		}

		int maxStringSize = -1;
		for (int i = 0; i < MAX_LEVEL; ++i) {
			int counter = size;
			ArrayList<T> line = new ArrayList<>(size);

			SkipListNode<T> node = this.head.next[i];
			while (node != null) {
				line.add(node.value);
				maxStringSize = Math.max(maxStringSize, node.value.toString().length());
				--counter;

				node = node.next[i];
			}

			while (counter > 0) {
				line.add(null);
				--counter;
			}
			lines.add(line);
		}

		Collections.reverse(lines);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < MAX_LEVEL; i++) {
			for (int j = 0; j < size; j++) {

				String value = Optional.ofNullable(lines.get(i).get(j)).map(Objects::toString)
						.orElse("");
				if (!value.equals("")) {
					if (j != 0) {
						sb.append("--> ");
					}
					int spaceCount = maxStringSize - value.length();
					sb.append(" ".repeat(Math.max(0, spaceCount)));
					sb.append(value);
					sb.append(" ");
				} else {
					sb.append("-----");
					sb.append("-".repeat(Math.max(0, maxStringSize + 1)));
				}
			}
			if (sb.charAt(sb.length() - 1) == '-') {
				sb.append("---> NULL");
			} else {
				sb.append("--> NULL");
			}
			sb.append("\n");
		}

		return sb.toString();
	}


}


class SkipListNode<T extends Comparable<T>> {

	T value;
	SkipListNode<T>[] next;


	public SkipListNode(T value, int maxLevel) {
		this.value = value;
		this.next = new SkipListNode[maxLevel];  /* you only care about the current level */

		/*
		               v
		   1 --------------------------> null
		   1---------> 3 --------------> null
		   1 --------> 3 --------------> null
		   1 --> 2 --> 3 --> 4 --> 5 --> null
		*/
	}

}