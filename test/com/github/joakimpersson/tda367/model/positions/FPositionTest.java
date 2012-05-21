package com.github.joakimpersson.tda367.model.positions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.positions.FPosition;

/**
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public class FPositionTest {

	private FPosition positionOne = null;
	private FPosition positionTwo = null;
	private FPosition positionThree = null;

	@Before
	public void setUp() throws Exception {
		positionOne = new FPosition(5.5F, 10.5F);
		positionTwo = new FPosition(2, 3);
		positionThree = new FPosition(5.1F, -1.9F);
	}

	@Test
	public void testGetX() {

		float expected = Float.floatToIntBits(5.5F);
		float actual = Float.floatToIntBits(positionOne.getX());
		assertTrue(expected == actual);
		expected = 2F;
		actual = positionTwo.getX();
		assertTrue(expected == actual);

		expected = Float.floatToIntBits(5.1F);
		actual = Float.floatToIntBits(positionThree.getX());
		assertTrue(expected == actual);

	}

	@Test
	public void testGetY() {
		float expected = Float.floatToIntBits(10.5F);
		float actual = Float.floatToIntBits(positionOne.getY());
		assertTrue(expected == actual);

		expected = 3F;
		actual = positionTwo.getY();
		assertTrue(expected == actual);

		expected = Float.floatToIntBits(-1.9F);
		actual = Float.floatToIntBits(positionThree.getY());
		assertTrue(expected == actual);
	}

	@Test
	public void testEqualsObject() {
		// positionOne and positionTwo should not be equal
		assertThat(positionOne, not(equalTo(positionTwo)));

		// positionOne and positionThree should not be equal
		assertThat(positionTwo, not(equalTo(positionThree)));

		// positionTwo and positionThree should not be equal
		assertThat(positionTwo, not(equalTo(positionThree)));

		// Testing when equal should be true
		FPosition expected = new FPosition(5.5F, 10.5F);
		assertThat(positionOne, equalTo(expected));

		expected = new FPosition(2, 3);
		assertThat(positionTwo, equalTo(expected));

		expected = new FPosition(5.1F, -1.9F);
		assertThat(positionThree, equalTo(expected));
	}

	@Test
	public void testHashCode() {

		// Testing when equal should be true
		FPosition expected = new FPosition(5.5F, 10.5F);
		assertThat(positionOne.hashCode(), equalTo(expected.hashCode()));

		expected = new FPosition(2, 3);
		assertThat(positionTwo.hashCode(), equalTo(expected.hashCode()));

		expected = new FPosition(5.1F, -1.9F);
		assertThat(positionThree.hashCode(), equalTo(expected.hashCode()));
	}

	@After
	public void tearDown() throws Exception {
		positionOne = null;
		positionTwo = null;
		positionThree = null;
	}

}
