
public class BinarySearchTree implements BinarySearchTreeADT{

	BinaryNode root; // root of this BST
	
	// Constructs a new BST with a null root
	public BinarySearchTree() { 
		this.root = new BinaryNode();
	}
	
	// Returns the pixel of a specified location if it exists otherwise null
	public Pixel get(BinaryNode r, Location key) {
		
		if(r.isLeaf()) {
			return null;
		} else if(r.getData().getLocation().compareTo(key) == 0) { // node r contains key
			return r.getData();
		} else if(key.compareTo(r.getData().getLocation()) < 0) { // key is smaller than r
			return get(r.getLeft(), key); // check left subtree
		} else { // key is larger than r. check right subtree
			return get(r.getRight(), key);
		}
	}
	
	// Inserts new node into BST
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {
		
		if(r.isLeaf()) { // sets leaf data and sets new leaf children
			r.setData(data);
			r.setLeft(new BinaryNode());
			r.getLeft().setParent(r);
			r.setRight(new BinaryNode());
			r.getRight().setParent(r);
		// data already exists in node	
		} else if(data.getLocation().compareTo(r.getData().getLocation()) == 0) {
			throw new DuplicatedKeyException();
		// location is larger than r. insert in right subtree	
		} else if(data.getLocation().compareTo(r.getData().getLocation()) > 0) {
			put(r.getRight(), data);
		// location is smaller than r. insert into left subtree	
		} else {
			put(r.getLeft(), data);
		}
	}
	
	// Removes specified key from BST if exists
	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
		
		BinaryNode node = getNode(r, key);
		
		if(node.isLeaf() || node == null) { // key is not found in BST
			throw new InexistentKeyException();
		} else if(node.getLeft().isLeaf()) { // node has only right child
			node.getRight().setParent(node.getParent());
			if(node == getRoot()) { // node is root so set right child as new root
				this.root = node.getRight();
			} else if(node.getParent().getLeft() == node) { // node is a left child
				node.getParent().setLeft(node.getRight());
			} else { // node is a right child
				node.getParent().setRight(node.getRight());
			}
		} else if(node.getRight().isLeaf()) { // node has only left child
			node.getLeft().setParent(node.getParent());
			if(node == getRoot()) { // node is root so set left child as new root
				this.root = node.getLeft();
			} else if(node.getParent().getLeft() == node) { // node is a left child
				node.getParent().setLeft(node.getRight());
			} else { // node is a right child
				node.getParent().setRight(node.getRight());
			}
		} else { // node has two children 
			BinaryNode n = node.getRight();
			while(!n.isLeaf()) { // find smallest node in right sub tree
				n = n.getLeft();
			}
			n = n.getParent();
			node.setData(n.getData());
			if(n.getParent() == node) { // smallest is targets child
				node.setRight(n.getRight());
				n.getRight().setParent(node);
			} else {
				n.getParent().setLeft(n.getRight());
				n.getRight().setParent(n.getParent());
			}
		}
	}
	
	// Returns the Pixel with the smallest key larger than the given one
	public Pixel successor(BinaryNode r, Location key) {
		
		BinaryNode node = getNode(r, key); // find node with given key
		
		if (r.isLeaf()) {
			return null;
		} else if (node == null) {
			return null;
		} else if (!node.isLeaf() && !(node.getRight()).isLeaf()) {
		// node exists and has a right child
			BinaryNode n = node.getRight();
			
			while (!n.isLeaf()) { // find smallest in right subtree
				n = n.getLeft();
			}	
			
			n = n.getParent();
			return n.getData();
			
		} else if(node.getParent() == null) { // node is root
			return null;
		} else {		
		// node exists and has no right child
			BinaryNode n = node.getParent();
			// find smallest ancestor larger than node
			while ((n != null) && (n.getLeft() != node)) { 
				node = n;
				n = n.getParent();
			}
			if(n == null) { // not found
				return null;
			} else {
				return n.getData();
			}	
		}
	}
	
	// Returns the Pixel with the largest key smaller than the given one
	public Pixel predecessor(BinaryNode r, Location key) {
		
		BinaryNode node = getNode(r, key); // find node with given key
		
		if (r.isLeaf()) {
			return null;
		} else if (node == null) {
			return null;
		} else if (!node.isLeaf() && !(node.getLeft()).isLeaf()) {
		// node exists and has a left child
			BinaryNode n = node.getLeft();
			
			while (!n.isLeaf()) { // find largest in left subtree
				n = n.getRight();
			}	
			
			n = n.getParent();
			return n.getData();
			
		} else if(node.getParent() == null) { // node is root
			return null;
		} else {		
		// node exists and has no left child	
			BinaryNode n = node.getParent();
			// find largest ancestor smaller than node
			while ((n != null) && (n.getRight() != node)) {
				node = n;
				n = n.getParent();
			}
			if(n == null) { // not found
				return null;
			} else {
				return n.getData();
			}	
		}
		
	}
	
	// Returns smallest node in BST
	public Pixel smallest(BinaryNode r) throws EmptyTreeException {
		
		if(r.isLeaf()) {
			throw new EmptyTreeException();
		} else {
			while(!r.isLeaf()) {
				r = r.getLeft();
			}
			r = r.getParent();
			return r.getData();
		}
	}
	
	// Returns largest node in BST
	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		
		if(r.isLeaf()) {
			throw new EmptyTreeException();
		} else {
			while(!r.isLeaf()) {
				r = r.getRight();
			}
			r = r.getParent();
			return r.getData();
		}
	}
	
	// Returns the root of BST
	public BinaryNode getRoot() {
		return this.root;
	}
	
	// Finds the node in BST with given key
	private BinaryNode getNode(BinaryNode r, Location key) {
		
		if (r.isLeaf()) {
			return r;
		} else {
			Location k = r.getData().getLocation();
			if (k.compareTo(key) == 0) {
				return r;
			} else if (key.compareTo(k) < 0) {
				return getNode(r.getLeft(), key);
			} else {
				return getNode(r.getRight(), key);
			}	
		}	
	}

}
