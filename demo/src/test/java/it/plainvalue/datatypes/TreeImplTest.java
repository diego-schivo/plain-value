package it.plainvalue.datatypes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.Tree.Node;
import it.plainvalue.datatypes.TreeImpl.NodeImpl;

public class TreeImplTest {

	TreeImpl<Node> tree;

	List<Node> rootChildren;

	@BeforeEach
	public void initTree() {
		tree = new TreeImpl<Node>(NodeImpl.class);

		NodeImpl node = new NodeImpl();
		node.setParent(tree.root);

		rootChildren = ((NodeImpl) tree.root).children;
		rootChildren.add(node);
	}

	@Test
	public void testGetRoot() {
		assertEquals(tree.root, tree.getRoot());
	}

	@Test
	public void testPutNode() {
		tree.putNode(null, null);

		Node node = tree.newNode();
		tree.putNode(node, null);
		assertEquals(tree.root, node.getParent());
		assertEquals(2, rootChildren.size());
		assertEquals(rootChildren.get(1), node);

		tree.putNode(node, null);
		assertEquals(2, rootChildren.size());
	}

	@Test
	public void testRemoveNode() {
		tree.removeNode(null);
		tree.removeNode(new NodeImpl());
		assertEquals(1, rootChildren.size());

		tree.removeNode(rootChildren.get(0));
		assertEquals(0, rootChildren.size());
	}
}
