import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.json.*;


public class DBConnection {
    private static DBConnection instance;
    private Connection conn;

    String DB_URL;
    String USER;
    String PASS;

    
    private DBConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ConfigurationLoader loader = new ConfigurationLoader(DB_URL, USER, PASS);

        loader.loadConfiguration();  
        this.conn = DriverManager.getConnection(loader.getDBURL(), loader.getUser(), loader.getPass());
        System.out.println("DB connection successful to: " + loader.getDBURL());

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }

        return instance;
    }
    
    
    public Connection getConnection() {
        return conn;
    }
}
