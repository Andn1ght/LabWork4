public class MyTestingClass {
    private String standName;
    private String user;

    public MyTestingClass(String standName, String user) {
        this.standName = standName;
        this.user = user;
    }

    public String getStandName() {
        return standName;
    }

    public String getUser() {
        return user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (standName == null ? 0 : standName.hashCode());
        hash = 31 * hash + (user == null ? 0 : user.hashCode());
        return Math.abs(hash);
    }
}
