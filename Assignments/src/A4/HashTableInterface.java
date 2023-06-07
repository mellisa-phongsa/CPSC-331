
/**
 * Specification of the ADT HashTable
 *
 * @author Jalal Kawash
 *
 */

public interface HashTableInterface<T extends Hashable>
{
    public void clear();
    public void add(int item);
    public void remove(int item);
    public boolean contains(int item);
}
