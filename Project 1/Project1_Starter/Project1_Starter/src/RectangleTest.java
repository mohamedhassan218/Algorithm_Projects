import junit.framework.TestCase;

public class RectangleTest extends TestCase {

	Rectangle rectangle = new Rectangle(2, 2, 5, 8);
	Rectangle rectangle1 = new Rectangle(3, 3, 8, 8);
	Rectangle rectangle2 = new Rectangle(4, 4, 9, 9);

	/*
	 * Test the constructor.
	 */
	public void testConstructor() {
		assertNotNull(rectangle);
		assertNotNull(rectangle1);
		assertNotNull(rectangle2);
	}

	/*
	 * Test getX().
	 */
	public void testGetX() {
		assertEquals(rectangle.getX(), 2);
		assertEquals(rectangle1.getX(), 3);
		assertEquals(rectangle2.getX(), 4);
	}

	/*
	 * Test getY().
	 */
	public void testGetY() {
		assertEquals(rectangle.getY(), 2);
		assertEquals(rectangle1.getY(), 3);
		assertEquals(rectangle2.getY(), 4);
	}

	/*
	 * Test the getWidth() method.
	 */
	public void testGetWidth() {
		assertEquals(rectangle.getWidth(), 5);
		assertEquals(rectangle1.getWidth(), 8);
		assertEquals(rectangle2.getWidth(), 9);
	}

	/*
	 * Test getHeight().
	 */
	public void testGetHeight() {
		assertEquals(rectangle.getHeight(), 8);
		assertEquals(rectangle1.getHeight(), 8);
		assertEquals(rectangle2.getHeight(), 9);
	}

	/*
	 * Test toString().
	 */
	public void testToString() {
		String test = "2, 2, 5, 8";
		String test1 = "3, 3, 8, 8";
		String test2 = "4, 4, 9, 9";
		assertEquals(rectangle.toString(), test);
		assertEquals(rectangle1.toString(), test1);
		assertEquals(rectangle2.toString(), test2);
	}
}
