public class Main {
    public static void main(String[] args) {
        MyHashTable<String, Integer> hashTable = new MyHashTable<>();
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);
        hashTable.put("four", 4);
        hashTable.put("five", 5);
        hashTable.put("six", 6);
        hashTable.put("seven", 7);
        hashTable.put("eight", 8);
        hashTable.put("nine", 9);
        hashTable.put("ten", 10);
        hashTable.put("eleven", 11);
        hashTable.put("twelve", 12);
        hashTable.put("thirteen", 13);


        System.out.println("Value for key 'one': " + hashTable.get("one"));
        System.out.println("Value for key 'eight': " + hashTable.get("eight"));
        System.out.println("Value for key 'thirteen': " + hashTable.get("thirteen"));

        System.out.println("Removing key 'eleven'...");
        hashTable.remove("eleven");

        System.out.println("Value for key 'twelve': " + hashTable.get("eleven"));
        System.out.println("Value for key 'twelve': " + hashTable.get("twelve"));
        System.out.println("Value for key 'thirteen': " + hashTable.get("thirteen"));

        System.out.println("Does the hash table contain value 13? " + hashTable.contains(13));
        System.out.println("Does the hash table contain value 11? " + hashTable.contains(11));

        System.out.println(hashTable.getKey(11));
    }
}