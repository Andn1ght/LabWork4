public class StandUser {
    private String name;
    private int age;

    public StandUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof StandUser)) {
            return false;
        }
        StandUser other = (StandUser) obj;
        return this.name.equals(other.name) && this.age == other.age;
    }
}
