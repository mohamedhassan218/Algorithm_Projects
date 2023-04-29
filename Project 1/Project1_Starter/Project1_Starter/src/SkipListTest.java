import java.util.ArrayList;

import junit.framework.TestCase;

public class SkipListTest extends TestCase {

	SkipList<String, Rectangle> lst = new SkipList();
	Rectangle r = new Rectangle(10, 10, 40, 50);
	Rectangle r1 = new Rectangle(30, 3, 5, 5);
	Rectangle r2 = new Rectangle(71, 15, 42, 54);
	Rectangle r3 = new Rectangle(140, 104, 143, 359);
	Rectangle r4 = new Rectangle(531, 49, 43, 591);
	KVPair<String, Rectangle> pair = new KVPair<String, Rectangle>("r", r);
	KVPair<String, Rectangle> pair1 = new KVPair<String, Rectangle>("r1", r1);
	KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("r2", r2);
	KVPair<String, Rectangle> pair3 = new KVPair<String, Rectangle>("r3", r3);
	KVPair<String, Rectangle> pair4 = new KVPair<String, Rectangle>("r4", r4);
	
	
	/*
	 * Test the constructor.
	 */
	public void testConstructor() {
		assertNotNull(lst);
	}

	/*
	 * Test search(Key).
	 */
	public void testSearchKey() {
		lst.insert(pair);
		lst.insert(pair1);
		lst.insert(pair2);
		lst.insert(pair3);
		lst.insert(pair4);
		
		ArrayList<KVPair<String, Rectangle>> test;
		test = lst.search("r1");
		assertTrue(test.size() > 0);
		test = lst.search("r");
		assertTrue(test.size() > 0);
		test = lst.search("r2");
		assertTrue(test.size() > 0);
		test = lst.search("r3");
		assertTrue(test.size() > 0);
		test = lst.search("r4");
		assertTrue(test.size() > 0);
		test = lst.search("r1111");
		assertTrue(test.size() == 0);
	}

	/*
	 * Test search(Value).
	 */
	public void testSearchValue() {
		ArrayList<KVPair<String, Rectangle>> test;
		lst.insert(pair);
		lst.insert(pair1);
		lst.insert(pair2);
		lst.insert(pair3);
		lst.insert(pair4);
		test = lst.searchByValue(r);
		assertNotNull(test);
		test = lst.searchByValue(r1);
		assertNotNull(test);
		test = null;
		assertNull(test);
		test = lst.searchByValue(r2);
		assertNotNull(test);
		test = lst.searchByValue(r2);
		assertNotNull(test);
		test = lst.searchByValue(r3);
		assertNotNull(test);
		test = lst.searchByValue(r4);
		assertNotNull(test);
	}

	/*
	 * Test insert().
	 */
	public void testInsert() {
		lst.insert(pair);
		assertEquals(lst.size(), 1);
		lst.insert(pair1);
		assertEquals(lst.size(), 2);
		lst.insert(pair2);
		assertEquals(lst.size(), 3);
		lst.insert(pair3);
		assertEquals(lst.size(), 4);
	}

	/*
	 * Test adjustHead().
	 */
	public void testAdjustHead() {
		lst.adjustHead(100);
		assertEquals(lst.level(), 100);
		lst.adjustHead(200);
		assertEquals(lst.level(), 200);
	}

	/*
	 * Test remove(Value).
	 */
	public void testRemoveValue() {
		KVPair<String, Rectangle> test;
		lst.insert(pair);
		lst.insert(pair1);
		lst.insert(pair2);
		lst.insert(pair3);
		lst.insert(pair4);
		test = lst.removeByValue(r);
		assertNotNull(test);
		test = lst.removeByValue(r1);
		assertNotNull(test);
		test = lst.removeByValue(r2);
		assertNotNull(test);
		test = lst.removeByValue(r3);
		assertNotNull(test);
		test = lst.removeByValue(r4);
		assertNotNull(test);
	}

	/*
	 * Test remove(Key).
	 */
	public void testRemoveKey() {
		KVPair<String, Rectangle> test;
		lst.insert(pair);
		lst.insert(pair1);
		lst.insert(pair2);
		lst.insert(pair3);
		lst.insert(pair4);
		test = lst.remove("r");
		assertNotNull(test);
		test = null;
		assertNull(test);
		test = lst.remove("r1");
		assertNotNull(test);
		test = lst.remove("r2");
		assertNotNull(test);
		test = lst.remove("r3");
		assertNotNull(test);
		test = null;
		assertNull(test);
		test = lst.remove("r4");
		assertNotNull(test);
	}

	/*
	 * Test dump().
	 */
	public void testDump() {
		lst.insert(pair);
		lst.insert(pair1);
		lst.insert(pair2);
		lst.insert(pair3);
		lst.insert(pair4);
		int test = lst.dump();
		assertEquals(test, 1);
		lst.remove("r2");
		lst.dump();
	}

	/*
	 * Test iterator().
	 */
	public void testIterator() {
		assertNotNull(lst.iterator());
	}

}
