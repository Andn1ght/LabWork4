public class Main {
    public static void main(String[] args) {
        MyHashTable<String, Integer> hashTable = new MyHashTable<>();
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);

        System.out.println("Value for key 'one': " + hashTable.get("one"));
        System.out.println("Value for key 'two': " + hashTable.get("two"));
        System.out.println("Value for key 'three': " + hashTable.get("three"));

        System.out.println("Removing key 'two'...");
        hashTable.remove("two");

        System.out.println("Value for key 'one': " + hashTable.get("one"));
        System.out.println("Value for key 'three': " + hashTable.get("three"));

        System.out.println("Does the hash table contain value 3? " + hashTable.contains(3));
        System.out.println("Does the hash table contain value 2? " + hashTable.contains(2));
    }
}