
public class Location {
	
	int x; // X and Y coordinates
	int y;
	
	// Constructs a location object with given x and y values
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// Returns the x coordinate of a location
	public int xCoord() {
		return this.x;
	}
	
	// Returns the y coordinate of a location
	public int yCoord() {
		return this.y;
	}
	
	// Compares two locations
	public int compareTo(Location p) {
		
		if (x == p.xCoord() && y == p.yCoord()) return 0;
		else if ((x > p.xCoord())||((x == p.xCoord()) && (y > p.yCoord()))) return  1;
		else return -1;
		
	}

}
