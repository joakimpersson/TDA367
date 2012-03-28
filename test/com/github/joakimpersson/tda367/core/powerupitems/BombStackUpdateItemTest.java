package com.github.joakimpersson.tda367.core.powerupitems;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.core.Attribute;

/**
 * 
 * @author joakimpersson
 * 
 */
public class BombStackUpdateItemTest {

	private BombStackUpdateItem item;

	@Before
	public void setUp() throws Exception {
		item = new BombStackUpdateItem();
	}

	@Test
	public void testGetAttr() {
		Attribute attr = item.getAttr();
		assertEquals(Attribute.BombStack, attr);
	}

	@After
	public void tearDown() throws Exception {

		item = null;
	}

}