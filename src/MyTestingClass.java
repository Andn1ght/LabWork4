public class MyTestingClass {

    /**
     * A simple class representing a testing object with a
     * stand name and an ID from anime series 'JoJo:Bizarre Adventures'.
     */

    private String standName;
    private int id;


    /**
     * Constructor for the MyTestingClass.
     *
     * @param standName the name of the testing stand
     * @param id        the ID of the testing object
     */
    public MyTestingClass(String standName, int id) {
        this.standName = standName;
        this.id = id;
    }


    /**
     * Returns the name of the testing stand.
     *
     * @return the name of the testing stand
     */
    public String getStandName() {
        return standName;
    }


    /**
     * Returns the ID of the testing object.
     *
     * @return the ID of the testing object
     */
    public int getId() {
        return id;
    }


    /**
     * Checks whether the current object is equal to another object.
     * Equality is defined as having the same ID and stand name.
     *
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        // Check if the object is null, if so return false
        if (obj == null) {
            return false;
        }
        // Check if the object is not an instance of MyTestingClass, if so return false
        if (!(obj instanceof MyTestingClass)) {
            return false;
        }
        // Cast the object to a MyTestingClass instance
        MyTestingClass other = (MyTestingClass) obj;
        // Check if the IDs and stand names are equal, if so return true, otherwise return false
        return this.id == other.id && this.standName.equals(other.standName);
    }


    /**
     * Returns the hash code value for this MyTestingClass object.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        // Initialize hash to a prime number
        int hash = 7;
        // Multiply by a prime number and add the hash code of the stand name (or 0 if it's null)
        hash = 31 * hash + (standName == null ? 0 : standName.hashCode());
        // Multiply by a prime number and add the ID
        hash = 31 * hash + id;
        // Take the absolute value of the hash code and return it
        return Math.abs(hash);
    }


    /**
     * Returns a String representation of this MyTestingClass object.
     *
     * @return a String representation of this object
     */
    @Override
    public String toString() {
        // Concatenate the class name, ID, and stand name into a formatted string
        return "MyTestingClass{" +
                "id=" + id +
                ", Stand-Name='" + standName + '\'' +
                '}';
    }


}
