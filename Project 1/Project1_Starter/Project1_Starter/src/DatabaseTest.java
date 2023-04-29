import java.awt.Rectangle;

import junit.framework.TestCase;

public class DatabaseTest extends TestCase {
	Database db = new Database();
	KVPair<String, Rectangle> pair = new KVPair<String, Rectangle>("r", new Rectangle(10, 10, 40, 50));
	KVPair<String, Rectangle> pair1 = new KVPair<String, Rectangle>("r1", new Rectangle(30, 3, 5, 5));
	KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("r2", new Rectangle(-71, 15, 42, 54));
	KVPair<String, Rectangle> pair3 = new KVPair<String, Rectangle>("r3", new Rectangle(14, 14, 43, 59));
	KVPair<String, Rectangle> pair4 = new KVPair<String, Rectangle>("r4", new Rectangle(14, 14, 43, 59));
	KVPair<String, Rectangle> pair5 = new KVPair<String, Rectangle>("r5", new Rectangle(14000, 14, 43, 59));
	KVPair<String, Rectangle> pair6 = new KVPair<String, Rectangle>("r6", new Rectangle(1400, 14, 43, 59));
	KVPair<String, Rectangle> pair7 = new KVPair<String, Rectangle>("r7", new Rectangle(100, 140, 43, 59));
	KVPair<String, Rectangle> pair8 = new KVPair<String, Rectangle>("r8", new Rectangle(19, 10, 43, 59));
	KVPair<String, Rectangle> pair9 = new KVPair<String, Rectangle>("r9", new Rectangle(7, 7, 40, 50));
	
	/*
	 * Test the constructor.
	 */
	public void testConstructor() {
		assertNotNull(db);
	}

	/*
	 * Test insert().
	 */
	public void testInsert() {
		int test;
		test = db.insert(pair);
		assertEquals(test, 1);
		test = db.insert(pair1);
		assertEquals(test, 1);
		test = db.insert(pair2);
		assertEquals(test, -1);
		test = db.insert(pair3);
		assertEquals(test, 1);
		test = db.insert(pair4);
		assertEquals(test, 1);
		test = db.insert(pair5);
		assertEquals(test, -1);
		test = db.insert(pair6);
		assertEquals(test, -1);
	}

	/*
	 * Test remove(key).
	 */
	public void testRemoveKey() {
		int test;
		db.insert(pair);
		db.insert(pair2);
		db.insert(pair3);
		db.insert(pair4);
		db.insert(pair5);
		test = db.remove(pair.getKey());
		assertEquals(test, 2);
		test = db.remove(pair1.getKey());
		assertEquals(test, -2);
		test = db.remove(pair2.getKey());
		assertEquals(test, -2);
		test = db.remove(pair3.getKey());
		assertEquals(test, 2);
		test = db.remove(pair4.getKey());
		assertEquals(test, 2);
		test = db.remove(pair5.getKey());
		assertEquals(test, -2);
		test = db.remove(pair6.getKey());
		assertEquals(test, -2);
	}

	/*
	 * Test remove(Value) method.
	 */
	public void testRemoveValue() {
		int test;
		db.insert(pair);
		db.insert(pair7);
		db.insert(pair8);
		test = db.remove(10, 10, 40, 50);
		assertEquals(test, 3);
		test = db.remove(100, 140, 43, 59);
		assertEquals(test, 3);
		test = db.remove(19, 10, 43, 59);
		assertEquals(test, 3);
	}

	/*
	 * Test regionsearch().
	 */
	public void testRegionSearch() {
		db.insert(pair);
		db.insert(pair1);
		db.insert(pair3);
		db.insert(pair4);
		db.insert(pair7);
		db.insert(pair8);
		int test;
		test = db.regionsearch(5, 5, 200, 200);
		assertEquals(test, 4);
		test = db.regionsearch(10, 56, 23, 20);
		assertEquals(test, 4);
		test = db.regionsearch(0, 1, 200, 200);
		assertEquals(test, 4);
	}

	/*
	 * Test intersections().
	 */
	public void testIntersections() {
		db.insert(pair);
		db.insert(pair1);
		db.insert(pair3);
		db.insert(pair4);
		db.insert(pair7);
		db.insert(pair8);
		int test = db.intersections();
		assertEquals(test, 5);
	}

	/*
	 * Test search(Key).
	 */
	public void testSearchKey() {
		int test;
		db.insert(pair);
		db.insert(pair1);
		db.insert(pair3);
		db.insert(pair4);
		db.insert(pair7);
		db.insert(pair8);
		db.insert(pair9);
		test = db.search("r");
		assertEquals(test, 6);
		test = db.search("r1");
		assertEquals(test, 6);
		test = db.search("r3");
		assertEquals(test, 6);
		test = db.search("r4");
		assertEquals(test, 6);
		test = db.search("r7");
		assertEquals(test, 6);
		test = db.search("r8");
		assertEquals(test, 6);
		test = db.search("r22");
		assertEquals(test, -6);
		test = db.search("r9");
		assertEquals(test, 6);
		test = db.search("r555");
		assertEquals(test, -6);
	}

	/*
	 * Test search(Value).
	 */
	public void testSearchValue() {
		int test;
		db.insert(pair);
		db.insert(pair1);
		db.insert(pair3);
		db.insert(pair4);
		db.insert(pair7);
		db.insert(pair8);
		db.insert(pair9);
		test = db.search(10, 10, 40, 50);
		assertEquals(test, 7);
		test = db.search(10, 110, 40, 50);
		assertEquals(test, -7);
		test = db.search(100, 140, 43, 59);
		assertEquals(test, 7);
		test = db.search(14, 14, 43, 59);
		assertEquals(test, 7);
		test = db.search(30, 3, 5, 5);
		assertEquals(test, 7);
		test = db.search(1000, 20, 22, 3333);
		assertEquals(test, -7);
	}

	/*
	 * Test dump()
	 */
	public void testDump() {
		db.insert(pair);
		db.insert(pair1);
		db.insert(pair3);
		db.insert(pair4);
		db.insert(pair7);
		db.insert(pair8);
		db.insert(pair9);
		int test = db.dump();
		assertEquals(test, 8);
	}

}
