package org.example.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;

public class DAG<T> {

	private final Map<T, List<T>> graph = new HashMap<>();

	public boolean isValidDAG() {
		return !hasCycle();
	}

	public void addNode(T node) {
		Objects.requireNonNull(node, "node could not be null");
		graph.putIfAbsent(node, new ArrayList<>());
	}

	public boolean addEdge(T from, T to) {
		addNode(from);
		addNode(to);
		graph.get(from).add(to);

		if (hasCycle()) {
			graph.get(from).remove(to);
			return false;
		}
		return true;
	}

	public boolean hasCycle() {
		Set<T> visited = new HashSet<>();
		Set<T> nodeRecursion = new HashSet<>();

		for (T node : graph.keySet()) {
			if (hasCycleRecursion(node, visited, nodeRecursion)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasCycleRecursion(T node, Set<T> visited, Set<T> nodeRecursion) {

		if (visited.contains(node)) {
			return false;
		}

		if (nodeRecursion.contains(node)) {
			return true;
		}

		visited.add(node);
		nodeRecursion.add(node);

		for (T nextNode : graph.getOrDefault(node, new ArrayList<>())) {
			if (hasCycleRecursion(nextNode, visited, nodeRecursion)) {
				return true;
			}
		}

		nodeRecursion.remove(node);
		return false;
	}

	public List<T> topologicalSort() {
		Set<T> visited = new HashSet<>();
		Stack<T> stack = new Stack<>();

		for (T node : graph.keySet()) {
			dfsSortRecursion(node, visited, stack);
		}

		List<T> result = new ArrayList<>();
		while (!stack.isEmpty()) {
			result.add(stack.pop());
		}
		return result;
	}

	private void dfsSortRecursion(T node, Set<T> visited, Stack<T> stack) {
		if (visited.contains(node)) {
			return;
		}

		visited.add(node);
		for (T nextNode : graph.getOrDefault(node, Collections.emptyList())) {
			dfsSortRecursion(nextNode, visited, stack);
		}
		stack.push(node);
	}

	public boolean removeNode(T node) {
		Objects.requireNonNull(node, "node could not be null");
		if (graph.remove(node) == null) {
			return false;
		}

		graph.values().forEach(l -> l.remove(node));
		return true;
	}

	public boolean removeEdge(T from, T to) {
		Objects.requireNonNull(from, "from node could not be null");
		Objects.requireNonNull(to, "to node could not be null");

		List<T> edges = graph.get(from);
		if(edges == null) {
			return false;
		}

		return edges.remove(to);
	}


	public static void main(String[] args) {
		DAG<String> dag = new DAG<>();

		dag.addNode("起床");
		dag.addNode("刷牙");
		dag.addNode("洗臉");
		dag.addNode("吃早餐");
		dag.addNode("出門");

		dag.addEdge("起床", "刷牙");
		dag.addEdge("起床", "洗臉");
		dag.addEdge("刷牙", "吃早餐");
		dag.addEdge("洗臉", "吃早餐");
		dag.addEdge("吃早餐", "出門");

		System.out.println("是否存在環：" + dag.hasCycle());

		System.out.println("拓撲排序結果：" + dag.topologicalSort());

		// 新增一個會造成環的邊
		dag.addEdge("出門", "起床");

		System.out.println("新增環後，是否存在環：" + dag.hasCycle());

//		dag.removeNode("刷牙");
		dag.removeNode("吃早餐");
		System.out.println("拓撲排序結果：" + dag.topologicalSort());
		System.out.println(dag.removeNode("XDD"));
		System.out.println("拓撲排序結果：" + dag.topologicalSort());


		dag.removeEdge("起床", "刷牙");
		dag.removeEdge("刷牙", "吃早餐");
		System.out.println("after remove edge 拓撲排序結果：" + dag.topologicalSort());

	}


}
