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
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (standName == null ? 0 : standName.hashCode());
        hash = 31 * hash + id;
        return Math.abs(hash);
    }

}
