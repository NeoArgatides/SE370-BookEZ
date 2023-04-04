public class DBOSingleton {
    private static DBOSingleton instance = null;
    private String database = "accounts.txt";
    private String currentUser = null;

    private DBOSingleton() {

    }

    public static DBOSingleton getInstance() {
        if(instance == null) {
            instance = new DBOSingleton();
        }
        return instance;
    }

    public String getCurrentUser() {
        return currentUser;
    }
    /*
    public Object[] getData() {

    }
     */
}
