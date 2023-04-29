import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 * @param <K> Key
 * @param <V> Value
 */
public class SkipList<K extends Comparable<? super K>, V> implements Iterable<KVPair<K, V>> {
	private SkipNode head; // First element of the top level
	private int size; // number of entries in the Skip List
	private int level; // number of levels in the head node

	/**
	 * Initializes the fields head, size and level
	 */
	public SkipList() {
		head = new SkipNode(null, 0);
		size = 0;
		level = -1;
	}

	/**
	 * Returns a random level number which is used as the depth of the SkipNode
	 * 
	 * @return a random level number
	 */
	int randomLevel() {
		int lev;
		Random value = new Random();
		for (lev = 0; Math.abs(value.nextInt()) % 2 == 0; lev++) {
			// Do nothing
		}
		return lev; // returns a random level
	}

	/**
	 * Searches for the KVPair using the key which is a Comparable object.
	 * 
	 * @param key key to be searched for
	 */
	public ArrayList<KVPair<K, V>> search(K key) {
		ArrayList<KVPair<K, V>> result = new ArrayList<KVPair<K, V>>();
		SkipNode pointer = head;
		for (int i = level; i >= 0; i--) {
			while ((pointer.forward[i] != null) && (pointer.forward[i].pair.getKey().compareTo(key) < 0)) {
				pointer = pointer.forward[i];
			}
		}
		pointer = pointer.forward[0];
		while (pointer != null && (pointer.pair.getKey().compareTo(key) == 0)) {
			result.add(pointer.pair);
			pointer = pointer.forward[0];
		}
		return result;
	}

	/*
	 * Method to search by Value. It loops in a linear way (O(n)) as if it's a
	 * linked list
	 */
	public ArrayList<KVPair<K, V>> searchByValue(V value) {
		ArrayList<KVPair<K, V>> result = new ArrayList<KVPair<K, V>>();
		SkipNode pointer = head;
		while (pointer.forward[0] != null) {
			pointer = pointer.forward[0];
			if (pointer.pair.getValue().equals(value))
				result.add(pointer.pair);
		}
		return result;
	}

	/**
	 * @return the size of the SkipList
	 */
	public int size() {
		return size;
	}

	public int level() {
		return level;
	}

	/**
	 * Inserts the KVPair in the SkipList at its appropriate spot as designated by
	 * its lexicoragraphical order.
	 * 
	 * @param it the KVPair to be inserted
	 */
	@SuppressWarnings("unchecked")
	public void insert(KVPair<K, V> it) {
		int newLevel = randomLevel();
		if (newLevel > level) {
			adjustHead(newLevel);
		}
		// Initialize update array to track the last node
		// at each level before the insert position is found.
		SkipNode[] update = new SkipNode(null, level + 1).forward;
		SkipNode pointer = head;
		for (int i = level; i >= 0; i--) {
			while ((pointer.forward[i] != null) && ((pointer.forward[i].pair.getKey().compareTo(it.getKey()) < 0))) {
				pointer = pointer.forward[i];
			}
			update[i] = pointer;
		}
		pointer = new SkipNode(it, newLevel);
		for (int i = 0; i <= newLevel; i++) {
			pointer.forward[i] = update[i].forward[i];
			update[i].forward[i] = pointer;
		}
		size++;
	}

	/**
	 * Increases the number of levels in head so that no element has more indices
	 * than the head.
	 * 
	 * @param newLevel the number of levels to be added to head
	 */
	@SuppressWarnings("unchecked")
	public void adjustHead(int newLevel) {
		SkipNode temp = head;
		head = new SkipNode(new KVPair<K, V>(null, null), newLevel);
		for (int i = 0; i <= level; i++) {
			head.forward[i] = temp.forward[i];
		}
		level = newLevel;
	}

	/**
	 * Removes the KVPair that is passed in as a parameter and returns true if the
	 * pair was valid and false if not.
	 * 
	 * @param pair the KVPair to be removed
	 * @return returns the removed pair if the pair was valid and null if not
	 */

	@SuppressWarnings("unchecked")
	public KVPair<K, V> remove(K key) {
		SkipNode removeNode = null;
		SkipNode pointer = head;
		for (int i = level; i >= 0; i--) {
			while (pointer.forward[i] != null && pointer.forward[i].pair.getKey().compareTo(key) < 0) {
				pointer = pointer.forward[i];
			}
			if (pointer.forward[i] != null && pointer.forward[i].pair.getKey().compareTo(key) == 0) {
				removeNode = pointer.forward[i];
				break;
			}
		}
		if (removeNode == null) {
			return null;
		}
		SkipNode current = head;
		for (int i = level; i >= 0; i--) {
			while (current.forward[i] != null && current.forward[i].pair.getKey().compareTo(key) <= 0) {
				if (current.forward[i] != removeNode) {
					current = current.forward[i];
				}
				if (current.forward[i] != null && current.forward[i].pair.getKey().compareTo(key) == 0) {
					if (current.forward[i] == removeNode) {
						current.forward[i] = removeNode.forward[i];
						break;
					} else {
						current = current.forward[i];
					}
				}
			}
		}
		size--;
		return removeNode.pair;
	}

	/**
	 * Removes a KVPair with the specified value.
	 * 
	 * @param val the value of the KVPair to be removed
	 * @return returns true if the removal was successful
	 */
	public KVPair<K, V> removeByValue(V val) {
		SkipNode removeNode = null;
		int levelOfNode = -1;
		SkipNode pointer = head;
		while (pointer.forward[0] != null) {
			if (pointer.forward[0].pair.getValue().equals(val)) {
				removeNode = pointer.forward[0];
				levelOfNode = removeNode.level;
				break;
			}
			pointer = pointer.forward[0];
		}
		if (removeNode == null) {
			return null;
		}
		SkipNode current = head;
		for (int i = levelOfNode; i >= 0; i--) {
			while (current.forward[i] != removeNode) {
				current = current.forward[i];
			}
			current.forward[i] = removeNode.forward[i];
		}
		size--;
		return removeNode.pair;
	}

	/**
	 * Prints out the SkipList in a human readable format to the console.
	 */
	public int dump() {
		System.out.println("SkipList dump:");
		System.out.println("Node has depth " + level + ", Value (null)");
		SkipNode current = head;
		while (current.forward[0] != null) {
			current = current.forward[0];
			System.out.print("Node has depth " + current.level + ", Value ");
			System.out.println(current.pair);
		}
		System.out.println("SkipList size is : " + size);
		return 1;
	}

	/**
	 * This class implements a SkipNode for the SkipList data structure.
	 * 
	 * @author CS Staff
	 * 
	 * @version 2016-01-30
	 */
	private class SkipNode {

		// the KVPair to hold
		private KVPair<K, V> pair;
		// what is this
		private SkipNode[] forward;
		// the number of levels
		private int level;

		/**
		 * Initializes the fields with the required KVPair and the number of levels from
		 * the random level method in the SkipList.
		 * 
		 * @param tempPair the KVPair to be inserted
		 * @param level    the number of levels that the SkipNode should have
		 */
		@SuppressWarnings("unchecked")
		public SkipNode(KVPair<K, V> tempPair, int level) {
			pair = tempPair;
			forward = (SkipNode[]) Array.newInstance(SkipList.SkipNode.class, level + 1);
			this.level = level;

		}

		/**
		 * Returns the KVPair stored in the SkipList.
		 * 
		 * @return the KVPair
		 */
		public KVPair<K, V> element() {
			return pair;
		}

	}

	private class SkipListIterator implements Iterator<KVPair<K, V>> {
		private SkipNode current;

		public SkipListIterator() {
			current = head;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current.forward[0] != null;
		}

		@Override
		public KVPair<K, V> next() {
			// TODO Auto-generated method stub
			current = current.forward[0];
			return current.element();
		}

	}

	@Override
	public Iterator<KVPair<K, V>> iterator() {
		// TODO Auto-generated method stub
		return new SkipListIterator();
	}

}
