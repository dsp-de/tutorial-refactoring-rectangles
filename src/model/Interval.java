/**
 * "Refactoring Rectangles" demonstrates bad smells for educational purposes.
 * Copyright (C) 2009-2013 Daniel Speicher and University of Bonn
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package model;

public class Interval {

	public int start;
	public int end;

	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public Interval(Interval other) {
		this(other.start, other.end);
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Interval))
			return false;
		Interval other = (Interval) obj;
		return (start == other.start) && (end == other.end);
	}

	public String toString() {
		return "[" + start + ", " + end + "]";
	}

}
