package map_container;

import java.util.logging.Logger;

public abstract class MapContainer<T extends MapComponent> {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(MapContainer.class.getName());

	T[][] contents;

	public MapContainer(T[][] contents) {
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

		for (T[] line : contents) {
			if (line.length != numCols) {
				throw new ArrayIndexOutOfBoundsException(
						"Array must be rectangular. Length 1 = " + numCols + ", length 2 = " + line.length);
			}
		}

		this.contents = contents;
	}

	public T[][] getContents() {
		return this.contents;
	}

	@Override
	public String toString() {
		StringBuilder mapString = new StringBuilder();
		for (int i = 0; i < this.contents.length; i++) {
			for (int j = 0; j < this.contents[0].length; j++) {
				mapString.append(this.contents[i][j].toChar());
			}
			mapString.append('\n');
		}
		return mapString.toString();
	}

	@SafeVarargs
	public static String toLayeredString(MapContainer<MapComponent>... layers) {
		if (!checkArrays(layers)) {
			throw new ArrayIndexOutOfBoundsException("All arrays must have the same dimensions");
		}

		StringBuilder mapString = new StringBuilder();
		for (int i = 0; i < layers[0].contents.length; i++) {
			for (int j = 0; j < layers[0].contents[0].length; j++) {
				int k = 0;
				char c;
				do {
					c = layers[k].contents[i][j].toChar();
					k++;
				} while (c == 'n' && k < layers.length);
				mapString.append(c);
			}
			mapString.append('\n');
		}
		return mapString.toString();
	}

	@SafeVarargs
	public static boolean checkArrays(MapContainer<MapComponent>... arrays) {
		if (arrays.length == 0) {
			throw new IllegalArgumentException("Needs at least one array to verify");
		}

		int rows = arrays[0].contents.length;
		int cols = arrays[0].contents[0].length;
		for (MapContainer<MapComponent> map : arrays) {
			if (map.contents.length != rows) {
				return false;
			}
			for (int i = 0; i < map.contents.length; i++) {
				if (map.contents[i].length != cols) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Returns a copy of the map array. This array is technically mutable, however,
	 * any modifications to it will not affect the map.
	 * 
	 * @param class1
	 * 
	 * @param class1
	 * @return
	 * 
	 * @return A copy of the contents of the map
	 */
	public abstract T[][] getContentsImmutable();
}
