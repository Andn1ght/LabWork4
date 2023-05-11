import java.util.NoSuchElementException;

public class MyHashTable<K, V> implements MyHashTableInterface<K, V>{

    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        this.chainArray = new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = M;
        this.chainArray = new HashNode[M];
    }

    private int hash(K key) {
        return key.hashCode() % M;
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        HashNode<K, V> prev = null;
        if (node == null) {
            chainArray[index] = new HashNode<>(key, value);
            size++;
        } else {
            while (node != null) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
                prev = node;
                node = node.next;
            }
            prev.next = new HashNode<>(key, value);
            size++;
        }
    }

    @Override
    public V get(K key) throws NoSuchElementException {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        throw new NoSuchElementException("Key not found in hash table.");
    }

    @Override
    public V remove(K key) throws NoSuchElementException{
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        HashNode<K, V> prev = null;
        while (node != null) {
            if (key.equals(node.key)) {
                if (prev == null) {
                    chainArray[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        throw new NoSuchElementException("Key not found in hash table.");
    }

    @Override
    public boolean contains(V value) {
        return false;
    }

    @Override
    public K getKey(V value) {
        return null;
    }
}
