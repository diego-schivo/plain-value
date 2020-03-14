package it.plainvalue.adt.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import it.plainvalue.adt.Node;
import it.plainvalue.adt.Tree;

public class TreeImplTest {

	Tree tree = new TreeImpl();

	@Test
	public void testGetRoot() {
		assertNotNull(tree.getRoot());
	}

	@Test
	public void testAddNode() {
		Node node = tree.addNode(tree.getRoot());
		assertEquals(tree.getRoot(), node.getParent());
		assertEquals(tree.getRoot().getChildren().iterator().next(), node);
	}
}
