import java.util.ArrayList;

import junit.framework.TestCase;

public class SkipListTest extends TestCase {

	SkipList<String, Rectangle> skipList = new SkipList();
	
	KVPair<String, Rectangle> pair = new KVPair<String, Rectangle>("r", new Rectangle(10, 10, 40, 50));
	KVPair<String, Rectangle> pair1 = new KVPair<String, Rectangle>("r1", new Rectangle(30, 3, 5, 5));
	KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("r2", new Rectangle(71, 15, 42, 54));
	KVPair<String, Rectangle> pair3 = new KVPair<String, Rectangle>("r3", new Rectangle(14, 14, 43, 59));
	
	/*
	 * Test the constructor.
	 */
	public void testConstructor() {
		assertNotNull(skipList);
	}

	/*
	 * Test search(Key).
	 */
	public void testSearchKey() {
		
	}

	/*
	 * Test search(Value).
	 */
	public void testSearchValue() {
		
	}

	/*
	 * Test insert().
	 */
	public void testInsert() {
		skipList.insert(pair);
		assertEquals(skipList.size(), 1);
		skipList.insert(pair1);
		assertEquals(skipList.size(), 2);
		skipList.insert(pair2);
		assertEquals(skipList.size(), 3);
		skipList.insert(pair3);
		assertEquals(skipList.size(), 4);
	}

	/*
	 * Test adjustHead().
	 */
	public void testAdjustHead() {
		skipList.adjustHead(80);
		assertEquals(skipList.level(), 80);
	}

	/*
	 * Test remove(Value).
	 */
	public void testRemoveValue() {
		
	}

	/*
	 * Test remove(Key).
	 */
	public void testRemoveKey() {

	}

	/*
	 * Test dump().
	 */
	public void testDump() {

	}

	/*
	 * Test iterator().
	 */
	public void testIterator() {
		assertNotNull(skipList.iterator());
	}

}
