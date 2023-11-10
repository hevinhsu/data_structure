package org.example.tree.avl_tree;

public class AvlTreeTreeNode<T extends Comparable<T>> {

	private T value;
	private int height;
	private AvlTreeTreeNode<T> leftNode;
	private AvlTreeTreeNode<T> rightNode;

	private AvlTreeTreeNode() {
	}

	@Override
	public String toString() {
		return "[ " + value + " ]";
	}

	public static <T extends Comparable<T>> AvlTreeTreeNode<T> newNode(T value) {
		AvlTreeTreeNode<T> node = new AvlTreeTreeNode<>();
		node.height = 0;
		node.value = value;
		return node;
	}

	T getValue() {
		return this.value;
	}

	void setValue(T value) {
		this.value = value;
	}

	int getHeight() {
		return this.height;
	}

	AvlTreeTreeNode<T> getLeftNode() {
		return this.leftNode;
	}

	AvlTreeTreeNode<T> getRightNode() {
		return this.rightNode;
	}

	void setLeftNode(AvlTreeTreeNode<T> node) {
		this.leftNode = node;
	}
	void setRightNode(AvlTreeTreeNode<T> node) {
		this.rightNode = node;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
