package it.plainvalue.datatypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.Tree.Node;
import it.plainvalue.datatypes.TreeImpl.NodeImpl;

public class NodeImplTest {

	NodeImpl node;

	@BeforeEach
	public void initNode() {
		node = new NodeImpl();
		node.parent = new NodeImpl();
		node.children = new ArrayList<>(Collections.singleton(new NodeImpl()));
	}

	@Test
	public void testGetParent() {
		assertNotNull(node.getParent());
	}

	@Test
	public void testSetParent() {
		Node parent = new NodeImpl();
		node.setParent(parent);
		assertEquals(parent, node.parent);
	}

	@Test
	public void testGetChildren() {
		assertIterableEquals(node.children, node.getChildren());
	}

	@Test
	public void testAddChild() {
		node.addChild(null);
		assertEquals(1, node.children.size());

		Node child = new NodeImpl();
		node.addChild(child);
		assertEquals(2, node.children.size());
	}

	@Test
	public void testRemoveChild() {
		node.removeChild(null);
		assertEquals(1, node.children.size());

		node.removeChild(node.children.get(0));
		assertIterableEquals(Collections.emptySet(), node.getChildren());
	}
}
