
public class DuplicatedKeyException extends Exception{
	
	// Parameterless Constructor
    public DuplicatedKeyException() {}

    // Constructor that accepts a message
    public DuplicatedKeyException(String message)
    {
       super(message);
    }

}
