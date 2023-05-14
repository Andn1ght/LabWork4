# LabWork4 🚀

### Laboratory work for assignment 4 in AITU. This is a Java program that implements a ***`MyHashTable`*** to store data about fictional ***`Stand Users`*** and their ***`Stands`***, inspired by the manga series ***`Jojo's Bizarre Adventure`*** 🎯:
## How It Works?
*The program creates a hash table using a custom implementation of the ***`MyHashTable`*** class, which uses separate chaining to handle collisions. The hash table maps instances of the ***`MyTestingClass`*** class (which represents a Stand) to instances of the ***`StandUser`*** class (which represents a Stand User).* 

*The program generates 10,000 random ***`Stand-User`*** pairs and stores them in the hash table. Each ***`Stand`*** is named after a ***`Stand`*** from the series, with a unique identifier added to distinguish it from other instances of the same ***`Stand`***. Each ***`Stand User`*** is given a random name and age.*

*The program then prints out the number of elements in each bucket of the hash table, as well as the total number of elements.*
## `MyHashTable` implementation [module src](https://github.com/Andn1ght/LabWork4/blob/master/src/service/MyHashTable.java) 📈:
*The program provides `MyHashTable` implementation by using an interface ***[`MyHashTableInterface`](https://github.com/Andn1ght/LabWork4/blob/master/src/service/MyHashTableInterface.java)***.*
* ***[`MyHashTableInterface`](https://github.com/Andn1ght/LabWork4/blob/master/src/service/MyHashTableInterface.java)*** that declares methods for the `MyHashTable` implementation.
```java
public interface MyHashTableInterface<K, V> {
    void put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean contains(V value);
    K getKey(V value);
    void printBucketSize();
}
```
* ***[`MyHashTable`](https://github.com/Andn1ght/LabWork4/blob/master/src/service/MyHashTable.java)*** implementation
```java
public class MyHashTable<K, V> implements MyHashTableInterface<K, V> {

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
        int hashCode = key.hashCode();
        int index = Math.abs(hashCode) % M;
        return index;
    }

    private void increaseBucket() throws KeyAlreadyExistsException {
        int prevM = M;
        M = M * 2;
        size = 0;
        HashNode<K, V>[] prevChainArray = chainArray;
        chainArray = new HashNode[M];
        for (int i = 0; i < prevM; i++) {
            HashNode<K, V> node = prevChainArray[i];
            while (node != null) {
                HashNode<K, V> next = node.next;
                node.next = null;
                put(node.key, node.value);
                node = next;
            }
        }
    }

    @Override
    public void put(K key, V value) throws IllegalArgumentException, KeyAlreadyExistsException {
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
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public V remove(K key) {
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
        return null;
    }

    @Override
    public boolean contains(V value) throws NullPointerException {
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
        for (int i = 0; i < M; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                if (node.value.equals(value)) {
                    return node.key;
                }
                node = node.next;
            }
        }
        return null;
    }

    @Override
    public void printBucketSize() {
        int all = 0;
        for (int i = 0; i < M; i++) {
            HashNode<K, V> node = chainArray[i];
            int elements = 0;
            while (node != null) {
                all++;
                elements++;
                node = node.next;
            }
            System.out.println("Bucket: "+ i + ", Elements: "+ elements);
        }
        System.out.println("All: " + all);
    }
}
```





