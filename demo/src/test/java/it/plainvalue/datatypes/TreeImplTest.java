package it.plainvalue.datatypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.Tree.Node;
import it.plainvalue.datatypes.TreeImpl.NodeImpl;

public class TreeImplTest {

	TreeImpl<Node> tree;

	@BeforeEach
	public void initTree() {
		tree = new TreeImpl<Node>(NodeImpl.class);
	}

	@Test
	public void testGetRoot() {
		assertNotNull(tree.getRoot());
	}

	@Test
	public void testPutNode() {
		tree.putNode(null, null);

		Node node = tree.newNode();
		tree.putNode(node, null);
		assertEquals(tree.getRoot(), node.getParent());
		assertIterableEquals(tree.getRoot().getChildren(), Collections.singleton(node));

		tree.putNode(node, null);
	}
}
