public class MyTestingClass {
    private String standName;
    private String user;
    private int rank;

    public MyTestingClass(String standName, String user) {
        this.standName = standName;
        this.user = user;
        this.rank = (int) (Math.random() * 100);
    }

    public String getStandName() {
        return standName;
    }

    public String getUser() {
        return user;
    }

    public int getRank() {
        return rank;
    }
}
