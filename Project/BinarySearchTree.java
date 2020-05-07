import java.io.IOException;
import java.io.Writer;

/**
 * Implements an unbalanced binary search tree. Note that all "matching" is
 * based on the compareTo method.
 * 
 * @author Mark Allen Weiss
 */

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
	/** The tree root. */
	private BinaryNode<AnyType> root;

	/**
	 * Construct the tree.
	 */
	public BinarySearchTree()
	{
		root = null;
	}

	/**
	 * Insert into the tree; duplicates are ignored.
	 * 
	 * @param x the item to insert.
	 */
	public void insert(AnyType x)
	{
		root = insert(x, root);
	}

	/**
	 * Internal method to insert into a subtree.
	 * 
	 * @param x the item to insert.
	 * @param t the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t)
	{
		if (t == null)
			return new BinaryNode<>(x, null, null);

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0) // value in CURRENT root 't' > new value
			t.left = insert(x, t.left);
		else if (compareResult > 0) // value in CURRENT root 't' < new value
			t.right = insert(x, t.right);
		else
			; // Duplicate; do nothing
		return t;
	}

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * 
	 * @param x the item to remove.
	 */
	public void remove(AnyType x)
	{
		root = remove(x, root);
	}

	/**
	 * Internal method to remove from a subtree.
	 * 
	 * @param x the item to remove.
	 * @param t the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t)
	{
		if (t == null)
			return t; // Item not found; do nothing

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			t.left = remove(x, t.left);
		else if (compareResult > 0)
			t.right = remove(x, t.right);
		else if (t.left != null && t.right != null) // Two children
		{
			// move smallest value into this place!
			t.element = findMin(t.right).element;
			// now delete this node
			t.right = remove(t.element, t.right);
		} else
			t = (t.left != null) ? t.left : t.right;
		return t;
	}

	/**
	 * Find the smallest item in the tree.
	 * 
	 * @return smallest item or null if empty.
	 */
	public AnyType findMin()
	{
		if (isEmpty())
			throw new UnderflowException("Could not find min, tree is empty");
		return findMin(root).element;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * 
	 * @param t the node that roots the subtree.
	 * @return node containing the smallest item.
	 */
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t)
	{
		if (t == null)
			return null;
		else if (t.left == null)
			return t;
		return findMin(t.left);
	}

	/**
	 * Find the largest item in the tree.
	 * 
	 * @return largest item or null if empty.
	 */
	public AnyType findMax()
	{
		if (isEmpty())
			throw new UnderflowException("Could not find max, tree is empty");
		return findMax(root).element;
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * 
	 * @param t the node that roots the subtree.
	 * @return node containing the largest item.
	 */
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t)
	{
		if (t == null)
			return null;
		else if (t.right == null)
			return t;
		return findMax(t.right);
	}

	/**
	 * Find an item in the tree.
	 * 
	 * @param x the item to search for.
	 * @return true if found.
	 */
	public boolean contains(AnyType x)
	{
		return contains(x, root);
	}

	/**
	 * Internal method to find an item in a subtree.
	 * 
	 * @param x is item to search for.
	 * @param t the node that roots the subtree.
	 * @return true if found.
	 */
	private boolean contains(AnyType x, BinaryNode<AnyType> t)
	{
		if (t == null)
			return false;

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			return contains(x, t.left);
		else if (compareResult > 0)
			return contains(x, t.right);
		else
			return true; // Match
	}

	/**
	 * Find an item in the tree.
	 * 
	 * @param x the item to search for.
	 * @return the item if found.
	 */
	public AnyType find(AnyType x)
	{
		return find(x, root);
	}

	/**
	 * Internal method to find an item in a subtree.
	 * 
	 * @param x is item to search for.
	 * @param t the node that roots the subtree.
	 * @return node containing the matched item.
	 */
	private AnyType find(AnyType x, BinaryNode<AnyType> t)
	{
		if (t == null)
			return null;

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			return find(x, t.left);
		else if (compareResult > 0)
			return find(x, t.right);
		else
			return t.element; // Match
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty()
	{
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		return root == null;
	}

	/**
	 * Prints a binary search tree in sorted order (in-order traversal)
	 */
	public void printTree()
	{
		printTreeHelper(root);
	}

	/**
	 * Prints a subtree of a binary search tree in sorted order (in-order traversal)
	 * 
	 * @param node the current node in the tree
	 */
	private void printTreeHelper(BinaryNode<AnyType> node)
	{
		if (node == null)
			return;

		if (node.left != null)
			printTreeHelper(node.left);

		System.out.println(node.element);

		if (node.right != null)
			printTreeHelper(node.right);
	}

	/**
	 * Writes a binary search tree in sorted order (in-order traversal) to a file
	 */
	public void printTree(Writer writer) throws IOException
	{
		printTreeHelper(writer, root);
	}

	/**
	 * Writes a subtree of a binary search tree in sorted order (in-order traversal)
	 * to a file
	 * 
	 * @param node the current node in the tree
	 * @throws IOException
	 */
	private void printTreeHelper(Writer writer, BinaryNode<AnyType> node) throws IOException
	{
		if (node == null)
			return;

		if (node.left != null)
			printTreeHelper(writer, node.left);

		writer.write(node.element.toString() + "\n");

		if (node.right != null)
			printTreeHelper(writer, node.right);
	}

	/**
	 * Internal method to compute height of a subtree.
	 * 
	 * @param t the node that roots the subtree.
	 */
	private int height(BinaryNode<AnyType> t)
	{
		if (t == null)
			return -1;
		else
			return 1 + Math.max(height(t.left), height(t.right));
	}

	// Basic node stored in unbalanced binary search trees
	static class BinaryNode<AnyType>
	{
		// Constructors
		BinaryNode(AnyType theElement)
		{
			this(theElement, null, null);
		}

		BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt)
		{
			element = theElement;
			left = lt;
			right = rt;
		}

		AnyType element; // The data in the node
		BinaryNode<AnyType> left; // Left child
		BinaryNode<AnyType> right; // Right child
	}

	// Test program
	public static void main(String[] args)
	{
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		final int NUMS = 4000;
		final int GAP = 37;

		System.out.println("Checking... (no more output means success)");

		for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
		{
			t.insert(i);
		}

	}

}