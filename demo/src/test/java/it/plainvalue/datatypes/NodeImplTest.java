package it.plainvalue.datatypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.Tree.Node;
import it.plainvalue.datatypes.TreeImpl.NodeImpl;

public class NodeImplTest {

	NodeImpl node = new NodeImpl();

	@Test
	public void testGetParent() {
		assertNull(node.getParent());
	}

	@Test
	public void testSetParent() {
		Node parent = new NodeImpl();
		node.setParent(parent);
		assertEquals(parent, node.getParent());
	}

	@Test
	public void testGetChildren() {
		assertNotNull(node.getChildren());
	}

	@Test
	public void testAddChild() {
		Node child = new NodeImpl();
		node.addChild(child);
		assertIterableEquals(Collections.singleton(child), node.getChildren());
	}
}
