package com.github.joakimpersson.tda367.model.positions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.positions.Position;

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
		assertThat(positionOne, equalTo(positionOne));
		assertThat(positionOne, not(equalTo(null)));

		assertThat(positionTwo, equalTo(positionTwo));
		assertThat(positionTwo, not(equalTo(null)));
		
		assertThat(positionThree, equalTo(positionThree));
		assertThat(positionThree, not(equalTo(null)));
		
		// positionOne and positionTwo should not be equal
		assertThat(positionOne, not(equalTo(positionTwo)));

		// positionOne and positionThree should not be equal
		assertThat(positionTwo, not(equalTo(positionThree)));

		// positionTwo and positionThree should not be equal
		assertThat(positionTwo, not(equalTo(positionThree)));

		// Testing when equal should be true
		Position expected = new Position(10, 10);
		assertThat(positionOne, equalTo(expected));

		expected = new Position(2, 3);
		assertThat(positionTwo, equalTo(expected));

		expected = new Position(5, -1);
		assertThat(positionThree, equalTo(expected));
	}

	@Test
	public void testHashCode() {

		// Testing when equal should be true
		Position expected = new Position(10, 10);
		assertThat(positionOne.hashCode(), equalTo(expected.hashCode()));

		expected = new Position(2, 3);
		assertThat(positionTwo.hashCode(), equalTo(expected.hashCode()));

		expected = new Position(5, -1);
		assertThat(positionThree.hashCode(), equalTo(expected.hashCode()));
	}

	@After
	public void tearDown() throws Exception {
		positionOne = null;
		positionTwo = null;
		positionThree = null;
	}
}
