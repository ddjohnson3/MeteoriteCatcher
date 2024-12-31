import java.util.Iterator; 
import java.util.Stack;
import java.util.NoSuchElementException;

public class IterableMultiKeyRBT<T extends Comparable<T>> extends BinarySearchTree<KeyListInterface<T>>
		implements IterableMultiKeySortedCollectionInterface<T> {
	Comparable<T> startPoint;
	int numKeys;

	public IterableMultiKeyRBT() {
		startPoint = null;
		numKeys = 0;
	}

	public boolean insertSingleKey(T key) {
		KeyList<T> list = new KeyList<T>(key);
		Node<KeyListInterface<T>> node = findNode(list);
		if (node != null) {
			if (node.data.containsKey(key)) {
				node.data.addKey(key);
				numKeys++;
				return false;
			} else {
				node.data.addKey(key);
				numKeys++;
				return false;
			}
		} else {
			this.insert(list);
			numKeys++;
			return true;
		}

	}

	public int numKeys() {

		return numKeys;
	}

	protected Stack<Node<KeyListInterface<T>>> getStartStack() {

		Stack<Node<KeyListInterface<T>>> stack = new Stack<>();
		if (startPoint == null) {
			Node<KeyListInterface<T>> current = this.root;
			while (current != null) {
				stack.push(current);
				current = current.down[0];

			}

		} else {
			Node<KeyListInterface<T>> current = this.root;

			while (current != null) {
				if (startPoint.compareTo(current.data.iterator().next()) < 0) {
					stack.push(current);
					current = current.down[0];
				}
				if (current != null && startPoint.compareTo(current.data.iterator().next()) > 0) {
					current = current.down[1];
				} else {
					stack.push(current);
					return stack;
				}
			}
		}
		return stack;

	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Stack<Node<KeyListInterface<T>>> stack = getStartStack();
			Iterator<T> keyListIterator = null; 

			public boolean hasNext() {
				if (stack.size() > 0|| keyListIterator != null && keyListIterator.hasNext()) {
					
					return true;
				}

				return false;
				
			}

			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				if (keyListIterator != null && keyListIterator.hasNext()) {
					return keyListIterator.next();
				} else {
					
					Node<KeyListInterface<T>> node = stack.pop();
					if (node!=null) {
						Node<KeyListInterface<T>> current = node.down[1];

					
					while (current != null) {
						stack.push(current);
						current = current.down[0];
					}
					}
					if (node!= null && node.data!=null) {
					keyListIterator = node.data.iterator();
					}
					
				}
				
			
				return keyListIterator.next();
				
			}

		};
	}

	public void setIterationStartPoint(Comparable<T> startPoint) {
		this.startPoint = startPoint;
	}

	/**
	 * Removes all keys from the tree.
	 */
	@Override
	public void clear() {
		numKeys = 0;
		this.root = null;
		this.size = 0;
	}

	/*
	 * @Test public void testInsertSingleKey() { IterableMultiKeyRBT<Integer> rbt =
	 * new IterableMultiKeyRBT<>(); assertTrue(rbt.insertSingleKey(5));
	 * assertFalse(rbt.insertSingleKey(5)); }
	 * 
	 * @Test public void testNumKeys() { IterableMultiKeyRBT<Integer> rbt = new
	 * IterableMultiKeyRBT<>(); rbt.insertSingleKey(5); rbt.insertSingleKey(3);
	 * rbt.insertSingleKey(7); assertEquals(3, rbt.numKeys()); }
	 * 
	 * @Test public void testIterator() { IterableMultiKeyRBT<Integer> rbt = new
	 * IterableMultiKeyRBT<>(); rbt.insertSingleKey(5); rbt.insertSingleKey(3);
	 * rbt.insertSingleKey(7);
	 * 
	 * Iterator<Integer> iterator = rbt.iterator(); int sum = 0; while
	 * (iterator.hasNext()) { sum += iterator.next(); } assertEquals(15, sum); }
	 * 
	 */

}
