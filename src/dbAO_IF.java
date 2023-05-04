public interface dbAO_IF {    
  dbAO_IF getInstance() throws SQLException;
  Connection getConnection();
}
