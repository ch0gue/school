
public class Pixel {
	
	Location p; // Attributes possessed by each pixel object
	int color;

	// Constructs a pixel with location and color
	public Pixel(Location p, int color) {
		this.p = p;
		this.color = color;
	}
	
	// Returns the location of a pixel
	public Location getLocation() {
		return this.p;
	}
	
	// Returns the color of a pixel
	public int getColor() {
		return this.color;
	}
}
