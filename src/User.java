import com.ebay.sdk.*;
import com.ebay.sdk.call.*;
import com.ebay.soap.eBLBaseComponents.*;

/*
 * Values for username and password
 */
public abstract class User implements User_IF{
    private static User user = null;

    private String username;
    private String password;

    public User(String s, String p){
        this.username = s;
        this.password = p;

        try{

        }catch(){ //#Needs Work to connect API
        
        }
    }

    @Override
    public abstract void login();

}
