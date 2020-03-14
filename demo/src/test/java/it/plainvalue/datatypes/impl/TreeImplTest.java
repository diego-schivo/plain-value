package it.plainvalue.datatypes.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.Tree.Node;

public class TreeImplTest {

	TreeImpl<Node> tree = TreeImpl.newTree();

	@Test
	public void testGetRoot() {
		assertNotNull(tree.getRoot());
	}

	@Test
	public void testAddNode() {
		Node node = tree.newNode();
		tree.putNode(node, tree.getRoot());
		assertEquals(tree.getRoot(), node.getParent());
		assertEquals(tree.getRoot().getChildren().iterator().next(), node);
	}
}
