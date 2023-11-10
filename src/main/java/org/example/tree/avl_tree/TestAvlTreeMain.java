package org.example.tree.avl_tree;

import org.example.tree.avl_tree.AvlTree;
import org.example.tree.avl_tree.AvlTreeTreeNode;

public class TestAvlTreeMain {

	public static void main(String[] args) {
		AvlTree<String> tree = AvlTree.newTree();
		tree.insert("APR");
		tree.insert("AUG");
		tree.insert("DEC");
		tree.insert("FEB");
		tree.insert("JAN");
		tree.insert("JULY");
		tree.insert("JUNE");
		tree.insert("MAR");
		tree.insert("MAY");
		tree.insert("NOV");
		tree.insert("OCT");
		tree.insert("SEPT");
		tree.traverse();
		tree.delete("APR");
		tree.delete("JAN");
		tree.delete("FEB");
		tree.traverse();

		System.out.println(tree.find("SEPT"));
	}

}
