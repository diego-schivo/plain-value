package it.plainvalue.datatypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.TreeImpl.NodeImpl;

public class NodeImplTest {

	NodeImpl node = new NodeImpl();

	@Test
	public void testGetParent() {
		assertNull(node.getParent());
	}

	@Test
	public void testGetChildren() {
		assertNotNull(node.getChildren());
	}

	@Test
	public void testGetValue() {
		assertNull(node.getValue());
	}

	@Test
	public void testSetValue() {
		Object value = new Object();
		node.setValue(value);
		assertEquals(value, node.getValue());
	}
}
