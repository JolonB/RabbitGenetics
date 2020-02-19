package com.rabbit.map_container;

import java.util.logging.Logger;

public abstract class MapContainer<T> {
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
