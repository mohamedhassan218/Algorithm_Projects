
public class Rectangle {

	// Attributes:
	private int xCoordinate;
	private int yCoordinate;
	private int width;
	private int height;

	// Constructor:
	public Rectangle(int x, int y, int w, int h) {
		xCoordinate = x;
		yCoordinate = y;
		height = h;
		width = w;
	}

	// Get x:
	public int getX() {
		return xCoordinate;
	}

	// Get y:
	public int getY() {
		return yCoordinate;
	}

	// Get the width:
	public int getWidth() {
		return width;
	}

	// Get the height:
	public int getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return String.format("%d, %d, %d, %d", xCoordinate, yCoordinate, width, height);
	}
}
