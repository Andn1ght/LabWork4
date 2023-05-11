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
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null.");
        }
        if (M * 4 < size) {
            increaseBucket();
        }
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        HashNode<K, V> prev = null;
        if (node == null){
            chainArray[index] = new HashNode<>(key, value);
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
        }
        size++;
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
    public boolean contains(V value) throws NullPointerException{
        if (value == null) {
            throw new NullPointerException("Value cannot be null.");
        }
        for (int i = 0; i < M; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    @Override
    public K getKey(V value) {
        return null;
    }
}
