
package edu.uga.cs4300.persistlayer;
 
import java.sql.ResultSet;
import java.sql.Connection;
 
public interface DbAccessInterface {
     
	// Creates the functions and the the structure
	// of the database implementation
    public Connection connect();
    public ResultSet retrieve(Connection con, String query);
    public void disconnect(Connection con);
	int update(Connection con, String query);
	int create(Connection con, String query);
	int delete(Connection con, String query);

}