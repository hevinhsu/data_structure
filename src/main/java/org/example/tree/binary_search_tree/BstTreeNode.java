package org.example.tree.binary_search_tree;

public class BstTreeNode<T extends Comparable> {

	private T value;
	private BstTreeNode<T> parentNode;
	private BstTreeNode<T> leftNode;
	private BstTreeNode<T> rightNode;

	private BstTreeNode() {
	}

	public static <T extends Comparable> BstTreeNode<T> newNode(BstTreeNode<T> parentNode, T value) {
		BstTreeNode<T> node = new BstTreeNode<>();
		node.parentNode = parentNode;
		node.value = value;
		return node;
	}

	public void addLeftNode(BstTreeNode<T> node) {
		this.leftNode = node;
	}

	public void addRightNode(BstTreeNode<T> node) {
		this.rightNode = node;
	}

	public T getValue() {
		return this.value;
	}

	BstTreeNode<T> getLeftNode() {
		return this.leftNode;
	}

	BstTreeNode<T> getRightNode() {
		return this.rightNode;
	}

	void setLeftNode(BstTreeNode<T> node) {
		this.leftNode = node;
	}

	void setRightNode(BstTreeNode<T> node) {
		this.rightNode = node;
	}
}
