package org.example.tree.avl_tree;

import java.util.Objects;
import java.util.Optional;

public class AvlTree<T extends Comparable<T>> {

	private AvlTreeTreeNode<T> rootNode;

	private AvlTree() {
	}

	public static <T extends Comparable<T>> AvlTree<T> newTree() {
		return new AvlTree<>();
	}

	public boolean isEmpty() {
		return Objects.isNull(rootNode);
	}

	public AvlTreeTreeNode<T> find(T value) {
		if (Objects.isNull(value)) {
			throw new IllegalArgumentException("value can not be null");
		}
		if (isEmpty()) {
			return null;
		}
		return find(rootNode, value);
	}

	private AvlTreeTreeNode<T> find(AvlTreeTreeNode<T> node, T value) {
		if (Objects.isNull(value)) {
			throw new IllegalArgumentException("value can not be null");
		}
		if(Objects.isNull(node)) {
			return null;
		}
		if (node.getValue().compareTo(value) == 0) {
			return node;
		} else if (node.getValue().compareTo(value) > 0) {
			return find(node.getLeftNode(), value);
		} else {
			return find(node.getRightNode(), value);
		}
	}

	public void delete(T value) {
		if (Objects.isNull(value)) {
			throw new IllegalArgumentException("value can not be null");
		}
		this.rootNode = deleteAndGetBalancedNode(this.rootNode, value);

	}

	private AvlTreeTreeNode<T> deleteAndGetBalancedNode(AvlTreeTreeNode<T> node, T value) {
		if (Objects.isNull(node)) {
			return null;
		}

		T nodeValue = node.getValue();
		if (nodeValue.compareTo(value) > 0) {
			node.setLeftNode(deleteAndGetBalancedNode(node.getLeftNode(), value));
		} else if (nodeValue.compareTo(value) < 0) {
			node.setRightNode(deleteAndGetBalancedNode(node.getRightNode(), value));
		} else {
			if (Objects.isNull(node.getLeftNode()) && Objects.isNull(node.getRightNode())) {
				return null;
			} else if (Objects.isNull(node.getLeftNode())) {
				return node.getRightNode();
			} else if (Objects.isNull(node.getRightNode())) {
				return node.getLeftNode();
			} else {
				// find max node from left subTree or find min node from right subTree
				// we choose max node from left subTree
				AvlTreeTreeNode<T> newNode = findMaxNode(node.getLeftNode());
				// swap the value in node
				node.setValue(newNode.getValue());
				newNode.setValue(nodeValue);
				// deletion continue
				node.setLeftNode(deleteAndGetBalancedNode(node.getLeftNode(), value));
			}
		}

		updateHeight(node);
		return getBalancedNode(node);
	}

	private AvlTreeTreeNode<T> findMaxNode(AvlTreeTreeNode<T> node /* node is not null cuz it's already checked by caller */) {
		if(Objects.nonNull(node.getRightNode())) {
			return findMaxNode(node.getRightNode());
		}
		return node;
	}

	public void insert(T value) {
		if (Objects.isNull(value)) {
			throw new IllegalArgumentException("value can not be null");
		}
		this.rootNode = insert(rootNode, value);
	}

	private AvlTreeTreeNode<T> insert(AvlTreeTreeNode<T> node, T value) {
		if (Objects.isNull(node)) {
			return AvlTreeTreeNode.newNode(value);
		}

		T nodeValue = node.getValue();
		if (nodeValue.compareTo(value) == 0) {
			return node;
		} else if (nodeValue.compareTo(value) > 0) {
			node.setLeftNode(insert(node.getLeftNode(), value));
		}
		if (nodeValue.compareTo(value) < 0) {
			node.setRightNode(insert(node.getRightNode(), value));
		}

		updateHeight(node);
		return getBalancedNode(node);
	}

	private AvlTreeTreeNode<T> getBalancedNode(AvlTreeTreeNode<T> node) {
		int balanceFactor = getBalanceFactor(node);
		if (balanceFactor <= 1 && balanceFactor >= -1) {
			return node;
		}

		if (balanceFactor > 1 /* unbalanced from left side */) {
			// check is LL or LR
			int childBalanceFactor = getBalanceFactor(node.getLeftNode());

			if (childBalanceFactor == 1 /* LL */) {
				return rightRotate(node);
			}
			// LR
			node.setLeftNode(leftRotate(node.getLeftNode()));
			return rightRotate(node);
		}

		/* unbalanced from right */
		int childBalanceFactor = getBalanceFactor(node.getRightNode());
		if (childBalanceFactor == -1 /* RR */) {
			return leftRotate(node);
		}
		// RL
		node.setRightNode(rightRotate(node.getRightNode()));
		return leftRotate(node);
	}

	private int getBalanceFactor(AvlTreeTreeNode<T> node) {
		return getNodeHeight(node.getLeftNode()) - getNodeHeight(node.getRightNode());
	}


	/*

		A                   A                                    B
	       / \                 / \                                  / \
              x   B      ->       x   y   +    B           ->          A   C
		 / \                            \                     / \
                y   C                            C                   x   y

	 */
	private AvlTreeTreeNode<T> leftRotate(AvlTreeTreeNode<T> node) {
		AvlTreeTreeNode<T> newParent = node.getRightNode();
		node.setRightNode(newParent.getLeftNode());
		newParent.setLeftNode(node);
		updateHeight(node);
		updateHeight(newParent);
		return newParent;
	}

	/*

	        A               A                           B
	       / \             / \                         / \
	      B   y     ->    x   y    +    B     ->      C   A
	     / \                           /                 / \
	    C   x                         C                 x   y
	*/
	private AvlTreeTreeNode<T> rightRotate(AvlTreeTreeNode<T> node) {
		AvlTreeTreeNode<T> newParent = node.getLeftNode();
		node.setLeftNode(newParent.getRightNode());
		newParent.setRightNode(node);
		updateHeight(node);
		updateHeight(newParent);
		return node;
	}


	private void updateHeight(AvlTreeTreeNode<T> node) {
		if (Objects.isNull(node)) {
			return;
		}
		int height =
				Math.max(getNodeHeight(node.getLeftNode()), getNodeHeight(node.getRightNode())) + 1;
		node.setHeight(height);
	}

	private int getNodeHeight(AvlTreeTreeNode<T> node) {
		return Objects.isNull(node) ? -1 : node.getHeight();
	}

	public void traverse() {
		System.out.println("Traversing the tree:");
		traverse(rootNode);
	}

	private void traverse(AvlTreeTreeNode<T> node) {
		Optional.ofNullable(node).ifPresent(n -> {
			traverse(n.getLeftNode());
			System.out.println(" -> " + n.getValue());
			traverse(n.getRightNode());
		});
	}
}
