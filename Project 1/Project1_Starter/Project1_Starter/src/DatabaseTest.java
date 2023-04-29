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
		
	}

	/*
	 * Test regionsearch().
	 */
	public void testRegionSearch() {

	}

	/*
	 * Test intersections().
	 */
	public void testIntersections() {

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
	 * Test dump()
	 */
	public void testDump() {
		int test = db.dump();
		assertEquals(test, 8);
	}

}
