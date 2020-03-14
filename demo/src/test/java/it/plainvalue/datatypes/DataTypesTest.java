package it.plainvalue.datatypes;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class DataTypesTest {

	@Test
	public void testNewTree() {
		assertNotNull(DataTypes.newTree());
	}

	@Test
	public void testNewRepository() {
		assertNotNull(DataTypes.newRepository());
	}

	@Test
	public void testNewTreeRepository() {
		assertNotNull(DataTypes.newTreeRepository());
	}
}
