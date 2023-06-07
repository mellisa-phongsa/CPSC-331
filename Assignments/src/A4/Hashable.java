
/**
 * Defines elements that can be added to hashstable
 * My hash functions are defined for Strings. So,
 * A Hashable object's key is a String
 *
 * @author Jalal Kawash
 *
 */

public interface Hashable<T extends Comparable>
{
    public int key();
}
