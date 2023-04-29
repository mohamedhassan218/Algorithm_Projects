import java.awt.Rectangle;

/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class CommandProcessor {

	// the database object to manipulate the
	// commands that the command processor
	// feeds to it
	private Database data;

	/**
	 * The constructor for the command processor requires a database instance to
	 * exist, so the only constructor takes a database class object to feed commands
	 * to.
	 * 
	 * @param dataIn the database object to manipulate
	 */
	public CommandProcessor() {
		data = new Database();
	}

	/**
	 * This method identifies keywords in the line and calls methods in the database
	 * as required. Each line command will be specified by one of the keywords to
	 * perform the actions within the database required. These actions are performed
	 * on specified objects and include insert, remove, regionsearch, search,
	 * intersections, and dump. If the command in the file line is not one of these,
	 * an appropriate message will be written in the console. This processor method
	 * is called for each line in the file. Note that the methods called will
	 * themselves write to the console, this method does not, only calling methods
	 * that do.
	 * 
	 * @param line a single line from the text file
	 * insert returns 1
	 * remove(V) returns 2
	 * remove(K) returns 3
	 * regionsearch returns 4
	 * intersections returns 5
	 * dump returns 6
	 * search(V) returns 7
	 * search(K) returns 8
	 * invalidCommand returns -1
	 */
	public int processor(String line) {

		line = line.replaceAll("\\s+", " ");
		String[] words = line.split(" ");
		switch (words[0]) {
		case "insert":				// insert name x y w h
			String key = words[1];
			Rectangle value = new Rectangle(Integer.parseInt(words[2]), Integer.parseInt(words[3]),
					Integer.parseInt(words[4]), Integer.parseInt(words[5]));
			KVPair<String, Rectangle> pair = new KVPair<String, Rectangle>(key, value);
			data.insert(pair);
			return 1;

		case "remove":
			if (words.length > 2) { // remove x y w h
				data.remove(Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]),
						Integer.parseInt(words[4]));
				return 2;
			} else { // remove Name
				data.remove(words[1]);
				return 3;
			}

		case "regionsearch":
			data.regionsearch(Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]),
					Integer.parseInt(words[4]));
			return 4;

		case "intersections":
			data.intersections();
			return 5;

		case "dump":
			data.dump();
			return 6;

		case "search":
			if (words.length > 2) { // search x y w h
				data.search(Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]),
						Integer.parseInt(words[4]));
				return 7;
			} else { // search Name
				data.search(words[1]);
				return 8;
			}

		default:
			System.out.println("Invalid command: " + words[0] + "!");
			return -1;
		}
	}

}
