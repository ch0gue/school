
public class InexistentKeyException extends Exception {
	
    // Parameterless Constructor
    public InexistentKeyException() {}

    // Constructor that accepts a message
    public InexistentKeyException(String message)
    {
       super(message);
    }
}
