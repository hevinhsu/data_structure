package org.example.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class Bfs {


	public static void main(String[] args) {

		Bfs bsf = new Bfs();
		System.out.println("expect:\n2 4 3 6 12 5 8 16 7 14 100");
		System.out.println("actual:");
		bsf.doBfs(GraphNode.generate());


	}

	private void doBfs(GraphNode root) {
		Set<GraphNode> set = new HashSet<>();
		Queue<GraphNode> queue = new LinkedList<>();
		queue.add(root);

		bfsLoop(queue, set);

	}

	private void bfsLoop(Queue<GraphNode> queue, Set<GraphNode> set) {

		for (; ; ) {
			GraphNode node = queue.poll();
			if (Objects.isNull(node)) {
				if (queue.isEmpty()) {
					return;
				} else {
					// prevent null object existed in queue.
					continue;
				}
			}

			if (set.contains(node)) {
				continue;
			} else {
				set.add(node);
			}

			queue.add(node.getLeft());
			queue.add(node.getRight());

			System.out.printf("%d ", node.getValue());

		}

	}

}
