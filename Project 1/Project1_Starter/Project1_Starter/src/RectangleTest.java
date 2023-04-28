import junit.framework.TestCase;

public class RectangleTest extends TestCase{

	Rectangle rectangle = new Rectangle(2, 2, 5, 8);
	
	/*
	 * Test the constructor.
	 */
	public void testConstructor() {
		assertNotNull(rectangle);
	}
	
	/*
	 * Test the getX() method.
	 */
	public void testGetX() {
		assertEquals(rectangle.getX(), 2);
	}
	
	/*
	 * Test the getY() method.
	 */
	public void testGetY() {
		assertEquals(rectangle.getY(), 2);
	}
	
	/*
	 * Test the getWidth() method.
	 */
	public void testGetWidth() {
		assertEquals(rectangle.getWidth(), 5);
	}
	
	/*
	 * Test the getX() method.
	 */
	public void testGetHeight() {
		assertEquals(rectangle.getHeight(), 8);
	}
	
	/*
	 * Test the toString() method.
	 */
	public void testToString() {
		String test = "2, 2, 5, 8";
		assertEquals(rectangle.toString(), test);
	}
}
