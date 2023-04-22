import javax.json.*;
import java.io.*;

public class ConfigurationLoader {
    private String dbUrl;
    private String user;
    private String pass;
    // private String databaseName;

    public ConfigurationLoader(String dbUrl,String user, String pass){
        this.dbUrl = dbUrl;
        this.user = user;
        this.pass = pass;
    }

    public void loadConfiguration() {
        try {
            // Read the JSON file
            ///config.json
            JsonReader jsonReader = Json.createReader(new FileReader("config.json"));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            dbUrl = jsonObject.getString("DB_URL");
            user = jsonObject.getString("USER");
            pass = jsonObject.getString("PASS");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDBURL(){
        return dbUrl;
    }

    public String getUser(){
        return user;
    }

    public String getPass(){
        return pass;
    }

}
            

    