package it.plainvalue.datatypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.Tree.Node;
import it.plainvalue.datatypes.TreeImpl.NodeImpl;

public class TreeImplTest {

	TreeImpl<Node> tree = new TreeImpl<Node>(NodeImpl.class);

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
