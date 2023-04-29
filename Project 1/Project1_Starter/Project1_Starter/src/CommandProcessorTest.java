import junit.framework.TestCase;

public class CommandProcessorTest extends TestCase {

	CommandProcessor cmd = new CommandProcessor();

	/**
	 * Test the constructor of CommandProcessor.
	 */
	public void testConstructor() {
		assertNotNull(this.cmd);
	}

	/**
	 * Test processor().
	 */
	public void testProcessor() {
		int test;
		test = cmd.processor("insert 					r2 15 15			 5 5	");
		assertEquals(test, 1);
		test = cmd.processor("insert inExistRec_0 1 1 -1 -2");
		assertEquals(test, 1);
		test = cmd.processor("insert r3 10 15 166 20");
		assertEquals(test, 1);
		test = cmd.processor("insert r11 100 100 10 1000");
		assertEquals(test, 1);
		test = cmd.processor("insert 		r33 120 			115 	166 23");
		assertEquals(test, 1);
		test = cmd.processor("search you ");
		assertEquals(test, 8);
		test = cmd.processor("intersections");
		assertEquals(test, 5);
		test = cmd.processor("remove r3");
		assertEquals(test, 3);
		test = cmd.processor("regionsearch 9 9 9 9");
		assertEquals(test, 4);
		test = cmd.processor("intersections");
		assertEquals(test, 5);
		test = cmd.processor("search r4");
		assertEquals(test, 8);
		test = cmd.processor("search 20 15 			3 3");
		assertEquals(test, 7);
		test = cmd.processor("dump");
		assertEquals(test, 6);
		test = cmd.processor("invalid command");
		assertEquals(test, -1);
	}

}
