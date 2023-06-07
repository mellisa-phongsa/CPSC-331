import java.util.LinkedList;

public class HashTableSC<T extends Hashable> implements HashTableInterface<T> {
    private LinkedList<Integer>[] hashTable;
    private int hashTableSize = 9973;

    public HashTableSC(int hashTableSize) {
        this.hashTableSize = hashTableSize;
        hashTable = (LinkedList<Integer>[]) new LinkedList[hashTableSize];
    }

    public void clear() {
        for (int i = 0; i < hashTableSize; i++) {
            hashTable[i] = null;
        }
    }

    public void add(int item) {
        Integer value = item;
        int hash = hash(value);
        if (hashTable[hash] == null) {
            hashTable[hash] = new LinkedList<>();
        }
        hashTable[hash].add(value);
    }

    public void remove(int item) {
        Integer value = item;
        int hash = hash(value);
        if (hashTable[hash] != null) {
            hashTable[hash].remove(value);
        }
    }

    public boolean contains(int item) {
        Integer value = item;
        int hash = hash(value);
        if (hashTable[hash] != null) {
            return hashTable[hash].contains(value);
        }
        return false;
    }

    private int hash(int x) {
        x = ((x >>> 16) ^ x) * 0x45d9f3b;
        x = ((x >>> 16) ^ x) * 0x45d9f3b;
        x = (x >>> 16) ^ x;
        return Math.abs(x) % hashTableSize;
    }
}


