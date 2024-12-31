//import static org.junit.jupiter.api.Assertions.assertTrue;

//import org.junit.jupiter.api.Test;

public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {
	public RedBlackTree() {
		super();
	}

	protected static class RBTNode<T> extends Node<T> {
		public int blackHeight = 0;

		public RBTNode(T data) {
			super(data);
		}

		public RBTNode<T> getUp() {
			return (RBTNode<T>) this.up;
		}

		public RBTNode<T> getDownLeft() {
			return (RBTNode<T>) this.down[0];
		}

		public RBTNode<T> getDownRight() {
			return (RBTNode<T>) this.down[1];
		}
	}

	protected void enforceRBTreePropertiesAfterInsert(RBTNode<T> newNode) {

		if (newNode == this.root) {
			return;
		}

		if (newNode.getUp().blackHeight == 1) {
			return;
		}
		RBTNode<T> parentNode = newNode.getUp();
		RBTNode<T> grandparentNode = parentNode.getUp();
		RBTNode<T> siblingNode;
		boolean rightChild; 
		if (grandparentNode.getDownLeft() == parentNode) {
			rightChild = false; 
			if (grandparentNode.getDownRight() == null) {
				siblingNode = new RBTNode<T>(null);
				siblingNode.blackHeight = 1;
			} else {
				siblingNode = grandparentNode.getDownRight();
			}

		} else {
			rightChild = true; 

			if (grandparentNode.getDownLeft() == null) {
				
				siblingNode = new RBTNode<T>(null);
				siblingNode.blackHeight = 1;
			} else {
				siblingNode = grandparentNode.getDownLeft();
			}
			}
		if (siblingNode.blackHeight == 0) {
			grandparentNode.blackHeight = 0;
			parentNode.blackHeight = 1;
			siblingNode.blackHeight = 1;

			enforceRBTreePropertiesAfterInsert(grandparentNode);
		}
		// case 2 and 3
		else {

			if (grandparentNode.getDownRight() == parentNode && parentNode.getDownLeft() == newNode
					|| grandparentNode.getDownLeft() == parentNode && parentNode.getDownRight() == newNode) {
				rotate(newNode, parentNode);
			}
			
			grandparentNode.blackHeight = 0; 
			parentNode.blackHeight = 1; 
			rotate(parentNode, grandparentNode);
			
			
		}
	}

	@Override
	public boolean insert(T data) throws NullPointerException {
		if (data == null) {
			throw new NullPointerException("node data is null");
		}
		RBTNode<T> newNode = new RBTNode<T>(data);
		insertHelper(newNode);
		enforceRBTreePropertiesAfterInsert(newNode);
		RBTNode<T> root = (RBTNode<T>) this.root;
		root.blackHeight = 1;

		return true;
	}

	/*
	 * Tests inserting into empty RBT
	 * 
	 

	@Test
	public void testInsertFirstNode() {
		RedBlackTree<Integer> rbTree = new RedBlackTree<Integer>();
		rbTree.insert(1);
		RBTNode<Integer> root = (RBTNode<Integer>) rbTree.root;

		assertTrue(root.blackHeight == 1);
	}
	/*
	 * Tests inserting for a left rotation
	 

	@Test
	public void testCase3() {

		RedBlackTree<Integer> rbTree = new RedBlackTree<Integer>();
		rbTree.insert(2);

		rbTree.insert(1);

		RBTNode<Integer> root = (RBTNode<Integer>) rbTree.root;

		rbTree.insert(3);

		root = (RBTNode<Integer>) rbTree.root;
		assertTrue(root.blackHeight == 1);
		assertTrue(root.getDownLeft().blackHeight == 0);
		assertTrue(root.getDownRight().blackHeight == 0);
	}

	/*
	 * Tests inserting for a right rotation
	 
	@Test
	public void testCase2() {
		RedBlackTree<Integer> rbTree = new RedBlackTree<Integer>();
		RBTNode<Integer> root = (RBTNode<Integer>) rbTree.root;

		rbTree.insert(2);
		rbTree.insert(1);
		//RBTNode<Integer> root = (RBTNode<Integer>) rbTree.root;
		rbTree.insert(3);

		root = (RBTNode<Integer>) rbTree.root;
		assertTrue(root.blackHeight == 1);
		assertTrue(root.getDownLeft().blackHeight == 0);
		assertTrue(root.getDownRight().blackHeight == 0);
	}
	*/

}
