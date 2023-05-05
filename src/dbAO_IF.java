import java.sql.Connection;
//import java.sql.SQLException;

public interface dbAO_IF {    

  //dbAO_IF getInstance() throws SQLException;

  Connection getConnection();

}
