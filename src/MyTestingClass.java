public class MyTestingClass {
    private String standName;
    private int id;

    public MyTestingClass(String standName, int id) {
        this.standName = standName;
        this.id = id;
    }

    public String getStandName() {
        return standName;
    }

    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MyTestingClass)) {
            return false;
        }
        MyTestingClass other = (MyTestingClass) obj;
        return this.id == other.id && this.standName.equals(other.standName);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (standName == null ? 0 : standName.hashCode());
        hash = 31 * hash + id;
        return Math.abs(hash);
    }

}
