package org.example.tree.binary_search_tree;

import java.util.Objects;

public class BinarySearchTree<T extends Comparable> {

	private BstTreeNode<T> rootNode;

	private BinarySearchTree() {
	}

	public static <T extends Comparable> BinarySearchTree<T> newInstance() {
		return new BinarySearchTree<>();
	}

	public boolean isEmpty() {
		return Objects.isNull(rootNode);
	}

	public void add(T value) {
		if (isEmpty()) {
			BstTreeNode<T> node = BstTreeNode.newNode(null, value);
			this.rootNode = node;
			return;
		}

		T rootValue = rootNode.getValue();
		if (rootValue.compareTo(value) == 0) {
			return;
		}

		if (rootValue.compareTo(value) < 0) {
			addLeft(this.rootNode, value);
		}


	}

	private void addLeft(BstTreeNode<T> parentNode, T value) {
		BstTreeNode<T> leftNode = parentNode.getLeftNode();
		if (Objects.isNull(leftNode)) {
			BstTreeNode<T> newNode = BstTreeNode.newNode(parentNode, value);
			parentNode.setLeftNode(newNode);
			return;
		}

		if (leftNode.getValue().compareTo(value) == 0) {
			return;
		}

		if (leftNode.getValue().compareTo(value) < 0) {
			addLeft(leftNode, value);
			return;
		}
		addRight(leftNode, value);
	}

	private void addRight(BstTreeNode<T> parentNode, T value) {
		BstTreeNode<T> rightNode = parentNode.getRightNode();
		if (Objects.isNull(rightNode)) {
			BstTreeNode<T> newNode = BstTreeNode.newNode(parentNode, value);
			parentNode.setRightNode(newNode);
			return;
		}

		if (rightNode.getValue().compareTo(value) == 0) {
			return;
		}

		if (rightNode.getValue().compareTo(value) < 0) {
			addLeft(rightNode, value);
			return;
		}
		addRight(rightNode, value);
	}

	public void preOrder() {
		if (Objects.isNull(this.rootNode)) {
			System.out.print("Empty Binary Search Tree");
			return;
		}

		System.out.print(this.rootNode.getValue());
		print(this.rootNode.getLeftNode());
		print(this.rootNode.getRightNode());
	}

	private void print(BstTreeNode<T> node) {
		if (Objects.isNull(node)) {
			return;
		}
		System.out.print(" - " + node.getValue());
		print(node.getLeftNode());
		print(node.getRightNode());
	}

	public boolean isExisted(T value) {
		return this.isExisted(this.rootNode, value);
	}

	private boolean isExisted(BstTreeNode<T> node, T value) {
		if(Objects.isNull(node)) {
			return false;
		}
		T nodeValue = node.getValue();
		if(nodeValue.compareTo(value) == 0) {
			return true;
		}

		if(nodeValue.compareTo(value) < 0) {
			return isExisted(node.getLeftNode(), value);
		}
		return isExisted(node.getRightNode(), value);
	}


	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = BinarySearchTree.newInstance();
		tree.add(1);
		tree.add(3);
		tree.add(2);
		tree.add(100);
		tree.add(30);
		tree.add(77);
		tree.preOrder();
		System.out.println();
		System.out.println(tree.isExisted(77));
	}

}
