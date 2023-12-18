package org.example.graph;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Dfs {

	public static void main(String[] args) {

		Dfs dfs = new Dfs();
		System.out.println("expect:\n2 4 6 8 100 12 16 3 5 7 14");
		System.out.println("actual:");
		dfs.doDfs(GraphNode.generate());

	}

	private void doDfs(GraphNode root) {
		Set<GraphNode> set = new HashSet<>();

		recursive(root, set);

	}

	private void recursive(GraphNode node, Set<GraphNode> set) {

		if (Objects.isNull(node) || set.contains(node)) {
			return;
		}

		set.add(node);
		System.out.printf("%d ", node.getValue());
		recursive(node.getLeft(), set);
		recursive(node.getRight(), set);

	}

}
