import service.MyHashTable;

import java.util.Random;

/**
 * A class that demonstrates the use of the MyHashTable class.
 * The main method generates 10,000 random StandUser objects with unique MyTestingClass keys and inserts them into
 * a hash table. It then prints the number of elements in each bucket and the total number of elements in the hash table.
 */
public class Main {

    // An array of strings representing the names of different stands that will select randomly
    private static final String[] standNames = {"Star Platinum", "The World", "Crazy Diamond", "Killer Queen",
            "Gold Experience", "King Crimson", "Sticky Fingers", "Tusk", "D4C", "Made in Heaven"};

    // The total number of stands
    private static final int NUM_STANDS = standNames.length;

    // A Random object used to generate random StandUser objects
    private static final Random rand = new Random();

    /**
     * The main method generates 10,000 random StandUser objects with unique MyTestingClass keys and inserts them into
     * a hash table. It then prints the number of elements in each bucket and the total number of elements in the hash table.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        // Create a new MyHashTable object
        MyHashTable<MyTestingClass, StandUser> hashTable = new MyHashTable<>();

        // Generate and insert 10,000 random StandUser objects into the hash table
        for (int i = 0; i < 10000; i++) {

            String standName = standNames[rand.nextInt(NUM_STANDS)];

            MyTestingClass key = new MyTestingClass(standName + i, rand.nextInt(1000));
            StandUser value = new StandUser("name" + i, rand.nextInt(20) + 10);

            hashTable.put(key, value);

        }

        // Print the number of elements in each bucket and the total number of elements in the hash table
        hashTable.printBucketSize();
    }
}
