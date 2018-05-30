
public class GraphicalObject implements GraphicalObjectADT {
	
	int id; // Initialization of global class variables utilized by methods
	int width;
	int height;
	String type;
	Location pos;
	BinarySearchTree tree;
	
	// Constructor creates empty tree and sets values for class variables 
	public GraphicalObject (int id, int width, int height, String type, Location pos) {
		
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type; 
		this.pos = pos;
		tree = new BinarySearchTree();
	}
	
	// Sets object type: fixed, user, computer, or target
	public void setType(String type) {
		this.type = type;
	}
	
	// Returns width of object
	public int getWidth() {
		return this.width;
	}
	
	// Returns height of object
	public int getHeight() {
		return this.height;
	}
	
	// Returns type: fixed, user, computer, or target
	public String getType() {
		return this.type;
	}
	
	// Returns ID of object
	public int getId() {
		return this.id;
	}
	
	// Returns this objects location
	public Location getOffset() {
		return this.pos;
	}
	
	// Sets this objects location
	public void setOffset(Location value) {
		this.pos = value;
	}
	
	// Adds a pixel as a node in the BST
	public void addPixel(Pixel pix) throws DuplicatedKeyException {
		tree.put(tree.getRoot(), pix);
	}
	
	// Returns true if passed object intersects this object
	public boolean intersects(GraphicalObject gobj) {
		
		int x = this.pos.xCoord(); // this objects top left corner
		int y = this.pos.yCoord();
		int xx = gobj.getOffset().xCoord(); // passed objects top left corner
		int yy = gobj.getOffset().yCoord();

		// checks for intersection of the boxes of both objects
		if((x <= xx && x + this.width < xx) || x > xx + gobj.getWidth()) {
			return false;
		} else if((y <= yy && y + this.height < yy) || y > yy + gobj.getHeight()) {
			return false;
		}	

		// checks the BST for existence of node with intersecting coordinates
		try {
			Pixel p = tree.smallest(tree.getRoot());
			Location loc;
			Location locn;
			while (p != null) {
				loc = p.getLocation();
				locn = new Location(loc.xCoord() + x-xx, loc.yCoord() + y-yy);
				if (!gobj.findPixel(locn)) { // if node does not exist set p to its successor
					p = tree.successor(tree.getRoot(), loc);
				} else {
					return true;
				}	
			}
		} catch (EmptyTreeException e) {
			System.out.println("Tree is Empty");
		}
		return false;
	}
	
	// Returns true if key is in BST otherwise false
	private boolean findPixel(Location key) {

		if(tree.get(tree.getRoot(), key) == null) {
			return false;
		} else {
			return true;
		}
	}
}
