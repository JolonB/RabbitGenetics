package map_container;

public abstract class MapContainer {
	MapComponent[][] contents;

	public MapContainer(MapComponent[][] contents) throws Error {
		int numRows = contents.length;
		int numCols = contents[0].length;
		if (numRows <= 0) {
			throw new Error("Number of rows must be equal to or greater than 1. Currently have " + numRows);
		}
		if (numCols <= 0) {
			throw new Error("Number of columns must be equal to or greater than 1. Currently have " + numCols);
		}

		for (MapComponent[] line : contents) {
			if (line.length != numCols) {
				throw new Error("Array must be rectangular. Length 1 = " + numCols + ", length 2 = " + line.length);
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
		/* Do edge case of null first */
		if (this.contents == null) {
			return null;
		}

		return null; // FIXME
//		MapComponent[][] newContents = (MapComponent[][]) new Object[this.contents.length][];
//		for (int i = 0; i < this.contents.length; i++) {
//			newContents[i] = Arrays.copyOf(this.contents[i], this.contents[i].length);
//		}
//		return newContents;
	}
}
