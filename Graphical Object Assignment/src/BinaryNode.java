
public class BinaryNode {

	Pixel value; // Variables identifying each node
	BinaryNode left;
	BinaryNode right;
	BinaryNode parent;
	
	// Constructs an internal node
	public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	// Constructs an empty node or leaf
	public BinaryNode() {
		this.value = null;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	
	// Returns the parent of a node
	public BinaryNode getParent() {
		return this.parent;
	}
	
	// Sets the parent of a node
	public void setParent(BinaryNode parent) {
		this.parent = parent;
	}
	
	// Sets the left child of a node
	public void setLeft(BinaryNode left) {
		this.left = left;
	}
	
	// Sets the right child of a node
	public void setRight(BinaryNode right) {
		this.right = right;
	}
	
	// Sets the data contained in a node
	public void setData(Pixel value) {
		this.value = value;
	}
	
	// Returns true if node is leaf, otherwise false
	public boolean isLeaf() {
		
		if (left == null && right == null) {
			return true;
		} else {
			return false;
		}	
	}
	
	// Returns the data contained in a node
	public Pixel getData() {
		return this.value;
	}
	
	// Returns the left child of a node
	public BinaryNode getLeft() {
		return this.left;
	}
	
	// Returns the right child of a node
	public BinaryNode getRight() {
		return this.right;
	}
}
