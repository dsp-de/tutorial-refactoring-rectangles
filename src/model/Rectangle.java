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

import static java.lang.Math.*;
import gui.View;

public class Rectangle {

	private Interval horizontal;
	private Interval vertical;
	private View view;

	public Rectangle(View view, int startX, int startY, int endX, int endY) {
		if ((startX > endX) || (startY > endY))
			throw new IllegalArgumentException("Parameters in wrong order.");
		this.horizontal = new Interval(startX, endX);
		this.vertical = new Interval(startY, endY);
		this.view = view;
		view.rectangle = this;
	}

	/**
	 * Create an empty Rectangle with no location.
	 */
	public Rectangle(View view) {
		this.horizontal = null;
		this.vertical = null;
		this.view = view;
		view.rectangle = this;
	}

	public Vertex getCenter() {
		if (horizontal == null)
			return null;
		return new Vertex((horizontal.start + horizontal.end) / 2,
				(vertical.start + vertical.end) / 2);
	}

	public boolean contains(Rectangle parameter) {
		if (parameter.horizontal == null)
			return false;
		if (horizontal == null)
			return false;
		// check whether the horizontal interval
		// contains the horizontal interval
		// in the other rectangle
		// AND
		// check whether the vertical interval
		// contains the vertical interval
		// in the other rectangle
		return ((horizontal.start <= parameter.horizontal.start) && (parameter.horizontal.end <= horizontal.end))
				&& ((vertical.start <= parameter.vertical.start) && (parameter.vertical.end <= vertical.end));
	}

	/**
	 * Enlarge the Rectangle just enough so that it contains the other
	 * Rectangle. If the Rectangle was empty it gets the same extension as the
	 * other Rectangle.
	 */
	public void accommodate(Rectangle other) {
		if ((other == null) || (other.horizontal == null))
			return;
		if (horizontal == null) {
			horizontal = new Interval(other.horizontal);
			vertical = new Interval(other.vertical);
			return;
		}
		horizontal.start = min(other.horizontal.start, horizontal.start);
		horizontal.end = max(other.horizontal.end, horizontal.end);
		vertical.start = min(other.vertical.start, vertical.start);
		vertical.end = max(other.vertical.end, vertical.end);
		view.redraw();
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Rectangle))
			return false;
		Rectangle other = (Rectangle) obj;
		if (horizontal == null)
			return (other.horizontal == null);
		return (horizontal.equals(other.horizontal))
				&& (vertical.equals(other.vertical));
	}

	public String toString() {
		return "Rectangle(" + horizontal + " x " + vertical + ")";
	}

	public Vertex getBottomLeft() {
		if (horizontal == null)
			return null;
		return new Vertex(horizontal.start, vertical.start);
	}

	public Object getTopRight() {
		if (horizontal == null)
			return null;
		return new Vertex(horizontal.end, vertical.end);
	}

	public void accommodate(Vertex vertex) {
		accommodate(new Rectangle(view, vertex.x, vertex.y, vertex.x, vertex.y));
	}
}
