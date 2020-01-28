package map_container;

import java.util.Arrays;
import java.util.logging.Logger;

public abstract class MapContainer {
	private static final Logger LOGGER = Logger.getLogger(MapContainer.class.getName());
	
	MapComponent[][] contents;

	public MapContainer(MapComponent[][] contents) {
		if (contents == null) {
			throw new NullPointerException("Need to provide a contents array");
		}
		int numRows = contents.length;
		int numCols = contents[0].length;
		if (numRows <= 0) {
			throw new ArrayIndexOutOfBoundsException(
					"Number of rows must be equal to or greater than 1. Currently have " + numRows);
		}
		if (numCols <= 0) {
			throw new ArrayIndexOutOfBoundsException(
					"Number of columns must be equal to or greater than 1. Currently have " + numCols);
		}

		for (MapComponent[] line : contents) {
			if (line.length != numCols) {
				throw new ArrayIndexOutOfBoundsException(
						"Array must be rectangular. Length 1 = " + numCols + ", length 2 = " + line.length);
			}
		}

		this.contents = contents;
	}

	public MapComponent[][] getContents() {
		return this.contents;
	}

	/**
	 * Returns a copy of the map array. This array is technically mutable, however,
	 * any modifications to it will not affect the map.
	 * 
	 * @return A copy of the contents of the map
	 */
	public MapComponent[][] getContentsImmutable() {
		/* Copy array */
		MapComponent[][] newContents = new MapComponent[this.contents.length][];
		for (int i = 0; i < this.contents.length; i++) {
			newContents[i] = Arrays.copyOf(this.contents[i], this.contents.length);
		}

		return newContents;
	}
}
