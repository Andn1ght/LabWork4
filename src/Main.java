import service.MyHashTable;

import java.util.Random;

public class Main {

    private static final String[] standNames = {"Star Platinum", "The World", "Crazy Diamond", "Killer Queen",
            "Gold Experience", "King Crimson", "Sticky Fingers", "Tusk", "D4C", "Made in Heaven"};
    private static final int NUM_STANDS = standNames.length;

    public static void main(String[] args) {

        MyHashTable<MyTestingClass, StandUser> hashTable = new MyHashTable<>();
        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {

            String standName = standNames[rand.nextInt(NUM_STANDS)];

            MyTestingClass key = new MyTestingClass(standName + i, rand.nextInt(1000));

            StandUser value = new StandUser("name" + i, rand.nextInt(20) + 10);

            hashTable.put(key, value);

        }

        hashTable.printBucketSize();
    }
}