public class StandUser {

    /**
     * A class representing a stand user with a name and age.
     */

    private String name;
    private int age;


    /**
     * Creates a new StandUser object with the given name and age.
     *
     * @param name the name of the stand user
     * @param age  the age of the stand user
     */
    public StandUser(String name, int age) {
        this.name = name;
        this.age = age;
    }


    /**
     * Returns the name of the stand user.
     *
     * @return the name of the stand user
     */
    public String getName() {
        return name;
    }


    /**
     * Returns the age of the stand user.
     *
     * @return the age of the stand user
     */
    public int getAge() {
        return age;
    }

    /**
     * Checks if this StandUser object is equal to another object.
     *
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        // Check if the object is null
        if (obj == null) {
            return false;
        }
        // Check if the object is of the correct class
        if (!(obj instanceof StandUser)) {
            return false;
        }
        // Cast the object to StandUser
        StandUser other = (StandUser) obj;
        // Check if the name and age of this object are equal to the name and age of the other object
        return this.name.equals(other.name) && this.age == other.age;
    }

    /**
     * Returns a hash code for this StandUser object. The hash code value is
     * computed by using the name and age of the StandUser object.
     *
     * @return a hash code value for this StandUser object
     */
    @Override
    public int hashCode() {
        // Initialize the hash code to a prime number
        int hash = 7;
        // Multiply the hash code by another prime number and add the hash code of the name field
        hash = 31 * hash + name.hashCode();
        // Multiply the hash code by another prime number and add the age field
        hash = 31 * hash + age;
        // Return the hash code
        return hash;
    }

    /**
     * Returns a string representation of this StandUser object.
     *
     * @return a string representation of the object, consisting of the name and age of the StandUser.
     */
    @Override
    public String toString() {
        return "Stand-User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
