package service;

import javax.management.openmbean.KeyAlreadyExistsException;

public class MyHashTable<K, V> implements MyHashTableInterface<K, V> {

    /**
     * Private inner class representing a node in the hash table.
     */
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;


        /**
         * Constructor for the HashNode class.
         *
         * @param key   the key for the node
         * @param value the value for the node
         */
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }


        /**
         * Returns a string representation of the node.
         *
         * @return a string representation of the node
         */
        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;


    /**
     * Default constructor for the MyHashTable class.
     * Initializes the chain array with the default size.
     */
    public MyHashTable() {
        this.chainArray = new HashNode[M];
    }


    /**
     * Constructor for the MyHashTable class.
     * Initializes the chain array with a user-specified size.
     *
     * @param M the size of the chain array
     */
    public MyHashTable(int M) {
        this.M = M;
        this.chainArray = new HashNode[M];
    }



    /**
     * This method computes an index value for a given key using the hash code of the key and a modulus operation with a constant value M.
     *
     * @param key the key to be hashed
     * @return the index value computed for the given key
     */
    private int hash(K key) {
        // Compute the hash code of the key, which is an integer value that uniquely identifies the key
        int hashCode = key.hashCode();
        // Take the absolute value of the hash code and compute the modulus of it with M, which is a constant
        // This ensures that the index value is within the range [0, M-1] and is not negative
        int index = Math.abs(hashCode) % M;
        // Return the computed index value
        return index;
    }


    /**
     * This method increases the capacity of the hash table by doubling the number of buckets, rehashing all existing entries, and redistributing them to new buckets.
     *
     * @throws KeyAlreadyExistsException if a key already exists in the hash table during rehashing
     */
    private void increaseBucket() throws KeyAlreadyExistsException {
        // Store the previous capacity of the hash table
        int prevM = M;
        // Double the capacity of the hash table
        M = M * 2;
        // Reset the size of the hash table to zero
        size = 0;
        // Store a reference to the previous array of hash nodes
        HashNode<K, V>[] prevChainArray = chainArray;
        // Create a new array of hash nodes with the increased capacity
        chainArray = new HashNode[M];
        // Rehash all existing entries and redistribute them to new buckets
        for (int i = 0; i < prevM; i++) {
            // Retrieve the first hash node in the chain at the current bucket
            HashNode<K, V> node = prevChainArray[i];
            // Traverse the chain at the current bucket and rehash all entries
            while (node != null) {
                // Store a reference to the next hash node in the chain
                HashNode<K, V> next = node.next;
                // Remove the current hash node from its previous chain
                node.next = null;
                // Put the current hash node in the new hash table, which will rehash it and redistribute it to a new bucket
                put(node.key, node.value);
                // Store a reference to the next hash node in the original chain for rehashing
                node = next;
            }
        }
    }


    /**
     * This method adds a new key-value pair to the hash table or updates an existing key-value pair with the new value. If the load factor of the hash table exceeds a certain threshold, the method calls the increaseBucket method to increase the capacity of the hash table.
     *
     * @param key the key of the new key-value pair
     * @param value the value of the new key-value pair
     * @throws IllegalArgumentException if the key or value is null
     * @throws KeyAlreadyExistsException if a key already exists in the hash table during rehashing
     */
    @Override
    public void put(K key, V value) throws IllegalArgumentException, KeyAlreadyExistsException {
        // Check if the key or value is null
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null.");
        }
        // Check if the load factor of the hash table exceeds a certain threshold, and if so, increase the capacity of the hash table
        if (M * 4 < size) {
            increaseBucket();
        }
        // Get the index of the bucket where the key-value pair should be stored
        int index = hash(key);
        // Retrieve the first hash node in the chain at the current bucket
        HashNode<K, V> node = chainArray[index];
        // Traverse the chain at the current bucket to find the key-value pair
        HashNode<K, V> prev = null;
        if (node == null){
            // If the chain is empty, create a new hash node with the key-value pair and add it to the chain
            chainArray[index] = new HashNode<>(key, value);
        } else {
            // If the chain is not empty, traverse it to find the key-value pair
            while (node != null) {
                // If the key of the current hash node matches the key of the new key-value pair, update the value of the hash node and return
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
                // Otherwise, continue traversing the chain
                prev = node;
                node = node.next;
            }
            // If the key-value pair was not found in the chain, create a new hash node with the key-value pair and add it to the end of the chain
            prev.next = new HashNode<>(key, value);
        }
        // Increment the size of the hash table
        size++;
    }


    /**
     * Returns the value associated with the specified key in this map, or null if this map does not contain the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or null if this map does not contain the key
     */
    @Override
    public V get(K key) {
        // Get the index of the bucket where the key should be stored
        int index = hash(key);
        // Get the first node in the chain at the given bucket
        HashNode<K, V> node = chainArray[index];
        // Traverse the chain until the key is found or the end of the chain is reached
        while (node != null) {
            if (node.key.equals(key)) {
                // Key found, return the associated value
                return node.value;
            }
            // Move to the next node in the chain
            node = node.next;
        }
        // Key not found, return null
        return null;
    }


    /**
     * Removes the value associated with the specified key from the map.
     * If the key is not found in the map, returns null.
     *
     * @param key the key to remove from the map
     * @return the value associated with the key, or null if the key is not found
     */
    @Override
    public V remove(K key) {
        // Determine which index in the array to search based on the hash of the key
        int index = hash(key);
        // Get the head of the chain of nodes at the selected index
        HashNode<K, V> node = chainArray[index];
        // Keep track of the previous node in the chain while iterating through the nodes
        HashNode<K, V> prev = null;
        // Iterate through the chain until the key is found or the end of the chain is reached
        while (node != null) {
            if (key.equals(node.key)) {
                // If the key is found, remove the node from the chain
                if (prev == null) {
                    // If the node is the head of the chain, set the next node as the new head
                    chainArray[index] = node.next;
                } else {
                    // Otherwise, link the previous node to the next node, effectively removing the current node
                    prev.next = node.next;
                }
                // Decrement the size of the map since a node has been removed
                size--;
                // Return the value associated with the removed key
                return node.value;
            }
            // Move to the next node in the chain
            prev = node;
            node = node.next;
        }
        // If the end of the chain is reached without finding the key, return null
        return null;
    }


    /**
     * Checks if the map contains the specified value.
     *
     * @param value the value to search for in the map
     * @throws NullPointerException if the specified value is null
     * @return true if the map contains the value, false otherwise
     */
    @Override
    public boolean contains(V value) throws NullPointerException {
        // Throw a NullPointerException if the specified value is null
        if (value == null) {
            throw new NullPointerException("Value cannot be null.");
        }
        // Iterate through each chain in the array and each node in the chain
        for (int i = 0; i < M; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                // If the current node's value matches the specified value, return true
                if (node.value.equals(value)) {
                    return true;
                }
                // Move to the next node in the chain
                node = node.next;
            }
        }
        // If the end of the map is reached without finding the value, return false
        return false;
    }


    /**
     * Returns the key associated with the specified value in the map.
     * If the value is not found in the map, returns null.
     *
     * @param value the value to search for in the map
     * @return the key associated with the value, or null if the value is not found
     */
    @Override
    public K getKey(V value) {
        // Iterate through each chain in the array and each node in the chain
        for (int i = 0; i < M; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                // If the current node's value matches the specified value, return the key associated with the node
                if (node.value.equals(value)) {
                    return node.key;
                }
                // Move to the next node in the chain
                node = node.next;
            }
        }
        // If the end of the map is reached without finding the value, return null
        return null;
    }


    /**
     * Prints the size of each bucket in the map and the total number of elements in the map.
     */
    @Override
    public void printBucketSize() {
        // Initialize a counter for the total number of elements in the map
        int all = 0;
        // Iterate through each chain in the array and count the number of nodes in each chain
        for (int i = 0; i < M; i++) {
            HashNode<K, V> node = chainArray[i];
            int elements = 0;
            while (node != null) {
                // Increment the counter for the total number of elements and the counter for the number of elements in the current chain
                all++;
                elements++;
                // Move to the next node in the chain
                node = node.next;
            }
            // Print the size of the current bucket
            System.out.println("Bucket: "+ i + ", Elements: "+ elements);
        }
        // Print the total number of elements in the map
        System.out.println("All: " + all);
    }

}
