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
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import gui.View;

public class RectangleTest {
	
  View view;
	
  @Before
  public void setUp() throws Exception {
	 view = new View();
  }

  @Test
  public void nonEmptyRectanglesHaveACenter() {
    Rectangle rect1 = new Rectangle(view);
    assertNull(rect1.getCenter());
    Rectangle rect2 = new Rectangle(view, 0, 0, 2, 2);
    assertEquals(new Vertex(1, 1), rect2.getCenter());
    Rectangle rect3 = new Rectangle(view, 1, 2, 3, 4);
    assertEquals(new Vertex(2, 3), rect3.getCenter());
  }
  
  @Test
  public void rectangleContainsAnotherOneIfAllEdgesAreInside() {
    //      +-----------+   +---+
    //      |         r |   | s |
    //  +---+---+---+   |   |   |
    //  | l |   | m |   |   |   |
    //  |   |   +---+   |   |   |
    //  |   |       |   |   |   |
    //  +---+-------+   |   +---+
    //      |           |
    //      +-----------+
    Rectangle left = new Rectangle(view, 0, 1, 3, 3);
    Rectangle middle = new Rectangle(view, 2, 2, 3, 3);
    Rectangle right = new Rectangle(view, 1, 0, 4, 4);
    Rectangle somewhere = new Rectangle(view, 4, 1, 6, 4);
    assertTrue(left.contains(left));
    assertTrue(left.contains(middle));
    assertTrue(!left.contains(right));
    assertTrue(!left.contains(somewhere));
    assertTrue(!middle.contains(left));
    assertTrue(middle.contains(middle));
    assertTrue(!middle.contains(right));
    assertTrue(!middle.contains(somewhere));
    assertTrue(!right.contains(left));
    assertTrue(right.contains(middle));
    assertTrue(right.contains(right));
    assertTrue(!right.contains(somewhere));
    assertTrue(!somewhere.contains(left));
    assertTrue(!somewhere.contains(middle));
    assertTrue(!somewhere.contains(right));
    assertTrue(somewhere.contains(somewhere));
  }

  @Test
  // for completeness
  public void thereIsNothingLikeContainmentForEmptyRectangles(){
    Rectangle somewhere = new Rectangle(view,4, 4, 8, 8);
    Rectangle nowhere = new Rectangle(view);
    assertTrue(!somewhere.contains(nowhere));
    assertTrue(!nowhere.contains(somewhere));
  }
  
  @Test
  public void rectanglesCanAccommodateThemselvesToContainAVertex() {
    Rectangle rect = new Rectangle(view);
    rect.accommodate(new Vertex(1, 2));
    assertEquals(new Rectangle(view,1, 2, 1, 2), rect);
    rect.accommodate(new Vertex(-3, -4));
    assertEquals(new Rectangle(view,-3, -4, 1, 2), rect);
    rect.accommodate(new Vertex(-4, 4));
    assertEquals(new Rectangle(view,-4, -4, 1, 4), rect);
  }

  @Test
  public void rectanglesCanAccommodateThemselvesToContainARectangle() {
    //                      +---+
    //                      | r |
    //      +-----------+   |   |
    //      |         m |   |   |
    //  +---+-------+   |   |   |
    //  | l |       |   |   |   |
    //  +---+-------+   |   +---+
    //      |           |
    //      +-----------+
    Rectangle left = new Rectangle(view, 0, 1, 3, 2);
    Rectangle middle = new Rectangle(view, 1, 0, 4, 3);
    Rectangle right = new Rectangle(view, 4, 1, 6, 4);
    Rectangle rect = new Rectangle(view);
    rect.accommodate(middle);
    assertEquals(new Rectangle(view, 1, 0, 4, 3), rect);
    rect.accommodate(left);
    assertEquals(new Rectangle(view, 0, 0, 4, 3), rect);
    rect.accommodate(right);
    assertEquals(new Rectangle(view, 0, 0, 6, 4), rect);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void creationWithInvalidArgumentsResultsInAnException(){
      new Rectangle(view, 2, 2, 1, 1);
  }


}
