package com.github.joakimpersson.tda367.model.positions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author joakimpersson
 * 
 */
public class PositionTest {

	private Position positionOne = null;
	private Position positionTwo = null;
	private Position positionThree = null;

	@Before
	public void setUp() throws Exception {
		positionOne = new Position(10, 10);
		positionTwo = new Position(2, 3);
		positionThree = new Position(5, -1);
	}

	@Test
	public void testGetX() {

		int expected = 10;
		int actual = positionOne.getX();
		assertEquals(expected, actual);

		expected = 2;
		actual = positionTwo.getX();
		assertEquals(expected, actual);

		expected = 5;
		actual = positionThree.getX();
		assertEquals(expected, actual);

	}

	@Test
	public void testGetY() {
		int expected = 10;
		int actual = positionOne.getY();
		assertEquals(expected, actual);

		expected = 3;
		actual = positionTwo.getY();
		assertEquals(expected, actual);

		expected = -1;
		actual = positionThree.getY();
		assertEquals(expected, actual);
	}

	@Test
	public void testEquals() {

		// testing for self refrence and null

		assertTrue(positionOne.equals(positionOne));
		assertFalse(positionOne.equals(null));

		assertTrue(positionTwo.equals(positionTwo));
		assertFalse(positionTwo.equals(null));

		assertTrue(positionThree.equals(positionThree));
		assertFalse(positionThree.equals(null));

		// positionOne and positionTwo should not be equal
		assertFalse(positionOne.equals(positionTwo));

		// positionOne and positionThree should not be equal
		assertFalse(positionOne.equals(positionThree));

		// positionTwo and positionThree should not be equal
		assertFalse(positionTwo.equals(positionThree));

		// Testing when equal should be true
		Position expected = new Position(10, 10);
		assertTrue(positionOne.equals(expected));

		expected = new Position(2, 3);
		assertTrue(positionTwo.equals(expected));

		expected = new Position(5, -1);
		assertTrue(positionThree.equals(expected));
	}

	@Test
	public void testHashCode() {

		// Testing when equal should be true
		Position expected = new Position(10, 10);
		assertTrue(positionOne.hashCode() == expected.hashCode());

		expected = new Position(2, 3);
		assertTrue(positionTwo.hashCode() == expected.hashCode());

		expected = new Position(5, -1);
		assertTrue(positionThree.hashCode() == expected.hashCode());
	}

	@After
	public void tearDown() throws Exception {
		positionOne = null;
		positionTwo = null;
		positionThree = null;
	}
}
