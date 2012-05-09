package com.github.joakimpersson.tda367.model.utils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

		float expected = 5.5F;
		float actual = positionOne.getX();
		assertTrue(expected == actual);
		expected = 2F;
		actual = positionTwo.getX();
		assertTrue(expected == actual);

		expected = 5.1F;
		actual = positionThree.getX();
		assertTrue(expected == actual);

	}

	@Test
	public void testGetY() {
		float expected = 10.5F;
		float actual = positionOne.getY();
		assertTrue(expected == actual);

		expected = 3F;
		actual = positionTwo.getY();
		assertTrue(expected == actual);

		expected = -1.9F;
		actual = positionThree.getY();
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
