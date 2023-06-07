
/**
 * Defines a String class that can be used with hash tables
 *
 * @author Jalal Kawash
 * 
 */
public class HashableObject implements Hashable
{
    private int integer;
    // other members of the object
    
    /**
     * Constructor for objects of class HashableString
     */
    public HashableObject(int s)
    {
        integer = new Integer(s);
    }
    
    public int key()
    {
        return integer;
    }
}
