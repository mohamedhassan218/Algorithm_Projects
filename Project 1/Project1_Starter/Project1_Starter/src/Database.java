import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class Database {

	// this is the SkipList object that we are using
	// a string for the name of the rectangle and then
	// a rectangle object, these are stored in a KVPair,
	// see the KVPair class for more information
	private SkipList<String, Rectangle> list;

	/**
	 * The constructor for this class initializes a SkipList object with String and
	 * Rectangle a its parameters.
	 */
	public Database() {
		list = new SkipList<String, Rectangle>();
	}

	/**
	 * Inserts the KVPair in the SkipList if the rectangle has valid coordinates and
	 * dimensions, that is that the coordinates are non-negative and that the
	 * rectangle object has some area (not 0, 0, 0, 0). This insert will insert the
	 * KVPair specified into the sorted SkipList appropriately
	 * 
	 * @param pair the KVPair to be inserted
	 */
	public void insert(KVPair<String, Rectangle> pair) {

		Rectangle rectangle = pair.getValue();
		String name = pair.getKey();
		if (Character.isLetter(name.charAt(0)) && (rectangle.getHeight() + rectangle.getY()) <= 1024
				&& (rectangle.getX() + rectangle.getWidth()) <= 1024 && rectangle.getX() >= 0 && rectangle.getY() >= 0
				&& rectangle.getWidth() > 0 && rectangle.getHeight() > 0) {

			list.insert(pair);
			System.out.println("Rectangle inserted: (" + name + ", " + rectangle.getX() + ", " + rectangle.getY() + ", "
					+ rectangle.getWidth() + ", " + rectangle.getHeight() + ")");
		} else {
			System.out.println("Rectangle rejected: (" + name + ", " + rectangle.getX() + ", " + rectangle.getY() + ", "
					+ rectangle.getWidth() + ", " + rectangle.getHeight() + ")");

		}

	}

	/**
	 * Removes a rectangle with the name "name" if available. If not an error
	 * message is printed to the console.
	 * 
	 * @param name the name of the rectangle to be removed
	 */
	public void remove(String name) {

		KVPair<String, Rectangle> pair = list.remove(name);
		Rectangle rectangle = pair.getValue();
		if (rectangle.equals(null)) {
			System.out.println("Rectangle not found: (" + name + ")");
		} else {
			System.out.println("Rectangle removed: (" + name + ", " + rectangle.getX() + ", " + rectangle.getY() + ", "
					+ rectangle.getWidth() + ", " + rectangle.getHeight() + ")");
		}

	}

	/**
	 * Removes a rectangle with the specified coordinates if available. If not an
	 * error message is printed to the console.
	 * 
	 * @param x x-coordinate of the rectangle to be removed
	 * @param y x-coordinate of the rectangle to be removed
	 * @param w width of the rectangle to be removed
	 * @param h height of the rectangle to be removed
	 */
	public void remove(int x, int y, int w, int h) {

		Rectangle rectangle = new Rectangle(x, y, w, h);
		KVPair<String, Rectangle> pair = list.removeByValue(rectangle);

		if (pair.getKey().equals(null)) {
			System.out.println("Rectangle not found: (" + x + ", " + y + ", " + w + ", " + h + ")");
		} else {
			System.out.println("Rectangle removed: (" + pair.getKey() + ", " + rectangle.getX() + ", "
					+ rectangle.getY() + ", " + rectangle.getWidth() + ", " + rectangle.getHeight() + ")");
		}
	}

	/**
	 * Displays all the rectangles inside the specified region. The rectangle must
	 * have some area inside the area that is created by the region, meaning,
	 * Rectangles that only touch a side or corner of the region specified will not
	 * be said to be in the region. You will need a SkipList Iterator for this
	 * 
	 * @param x x-Coordinate of the region
	 * @param y y-Coordinate of the region
	 * @param w width of the region
	 * @param h height of the region
	 */
	public void regionsearch(int x, int y, int w, int h) {
		if (x >= 0 && y >= 0 && w > 0 && h > 0) {
			Iterator<KVPair<String, Rectangle>> iterator = list.iterator();
			KVPair<String, Rectangle> pair;
			System.out.println("Rectangles intersecting region (" + x + ", " + y + ", " + w + ", " + h + "):");
			while (iterator.hasNext()) {
				pair = iterator.next();
				if ((pair.getValue().getX() >= x && pair.getValue().getX() < x + w)
						&& (pair.getValue().getY() >= y && pair.getValue().getY() < y + h))
					System.out
							.println("(" + pair.getKey() + ", " + pair.getValue().getX() + ", " + pair.getValue().getY()
									+ ", " + pair.getValue().getWidth() + ", " + pair.getValue().getHeight() + ")");
			}
		} else {
			System.out.println("Rectangle rejected: (" + x + ", " + y + ", " + w + ", " + h + ")");
		}
	}

	/**
	 * Prints out all the rectangles that Intersect each other by calling the
	 * SkipList method for intersections. You will need to use two SkipList
	 * Iterators for this
	 */
	public void intersections() {

	}

	/**
	 * Prints out all the rectangles with the specified name in the SkipList. This
	 * method will delegate the searching to the SkipList class completely.
	 * 
	 * @param name name of the Rectangle to be searched for
	 */
	public void search(String name) {
		ArrayList<KVPair<String, Rectangle>> result = list.search(name);
		if (result.size() == 0)
			System.out.println("Rectangle not found: " + name);
		else {
			for (KVPair<String, Rectangle> pair : result)
				System.out.println("(" + pair.getKey() + ", " + pair.getValue().getX() + ", " + pair.getValue().getY()
						+ ", " + pair.getValue().getWidth() + ", " + pair.getValue().getHeight() + ")");
		}
	}

	// To search by values.
	public void search(int x, int y, int w, int h) {
		Rectangle rectangle = new Rectangle(x, y, w, h);
		ArrayList<KVPair<String, Rectangle>> result = list.searchByValue(rectangle);
		if (result.size() == 0) {
			System.out.println("Rectangle not found: (" + x + ", " + y + ", " + w + ", " + h + ")");
		} else {
			for (KVPair<String, Rectangle> pair : result) {
				System.out.println("(" + pair.getKey() + ", " + pair.getValue().getX() + ", " + pair.getValue().getY()
						+ ", " + pair.getValue().getWidth() + ", " + pair.getValue().getHeight() + ")");
			}
		}
	}

	/**
	 * Prints out a dump of the SkipList which includes information about the size
	 * of the SkipList and shows all of the contents of the SkipList. This will all
	 * be delegated to the SkipList.
	 */
	public void dump() {
		list.dump();
	}

}
