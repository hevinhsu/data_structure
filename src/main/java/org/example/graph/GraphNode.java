package org.example.graph;

public class GraphNode {

	private final Integer value;

	private GraphNode left;
	private GraphNode right;

	public GraphNode(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public GraphNode getLeft() {
		return left;
	}

	public GraphNode getRight() {
		return right;
	}


	public void appendLeft(Integer value) {
		this.left = new GraphNode(value);
	}

	public void appendRight(Integer value) {
		this.right = new GraphNode(value);
	}

	public void setLeft(GraphNode left) {
		this.left = left;
	}

	public void setRight(GraphNode right) {
		this.right = right;
	}

	/*

input data:

2 ---> 4 -->  6 -->  8 --------->|
|      |                         v
|      | --> 12 --> 16 -------> 100
|                                ^
|                                |
|----> 3 --> 5 -->  7 ---------->|
             |                   |
             | --> 14 ---------->|

 */

	public static GraphNode generate() {
		GraphNode root = new GraphNode(2);
		GraphNode endNode = new GraphNode(100);
		endNode.setRight(root);


		root.appendLeft(4);
		root.getLeft().appendLeft(6);
		root.getLeft().getLeft().appendLeft(8);
		root.getLeft().getLeft().getLeft().setLeft(endNode);

		root.getLeft().appendRight(12);
		root.getLeft().getRight().appendRight(16);
		root.getLeft().getRight().getRight().setRight(endNode);

		root.appendRight(3);
		root.getRight().appendLeft(5);
		root.getRight().getLeft().appendLeft(7);
		root.getRight().getLeft().getLeft().setLeft(endNode);
		root.getRight().getLeft().appendRight(14);
		root.getRight().getLeft().getRight().setLeft(endNode);

		return root;
	}
}
