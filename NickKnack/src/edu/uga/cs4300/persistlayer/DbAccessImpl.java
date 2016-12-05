package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 
// The DbAccessImpl Class is designed to Connect to a mysql
 
public class DbAccessImpl extends DbAccessConfiguration implements DbAccessInterface  {
 
// Connects to the DB and returns the said connection
    public Connection connect(){
        Connection con = null; 
        try {
            Class.forName(DB_DRIVE_NAME).newInstance();
            con = DriverManager.getConnection(CONNECTION_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD); 
        }catch (Exception e) {
            System.err.println("Cannot connect to database server");
            System.err.println(e);
        }
        return con;
    }
    public  void disconnect(Connection con) {
        if (con != null) {
            try {
                con.close();
            }
            catch (Exception e) {}
        }
    }
    
    //This function takes the query and executes it using many d
    public ResultSet retrieve(Connection con, String query) {
        ResultSet result = null;
        try {
            Statement state = con.createStatement();
            result = state.executeQuery(query); 
        } catch (SQLException e) {
            System.err.println(e);
        }
        return result;

    }
 
    // This is the function that executes any other command requiring sql.
    public static void execute(Connection conn, String query) throws SQLException {
        Statement newState;
        try {
            newState = conn.createStatement();
            newState.execute(query);
            newState.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
 
 
    // Creates a new table or addition to table or review
    public int create(Connection con, String query) {
        Statement newState;
        try {
            // Execute Statement
            newState = con.createStatement();
            newState.execute(query);
            newState.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }
 
    public int update(Connection con, String query) {
        
        Statement newState;
        try {
            // Execute Statement
            newState = con.createStatement();
            newState.execute(query);
            newState.close();
 
        } catch (SQLException e) {
 
            System.err.println(e);
        }
        return 1;
    }
 
    public int delete(Connection con, String query) {
        Statement newState;
        try {
            // Execute Statement
            newState = con.createStatement();
            newState.execute(query);
            newState.close();
 
        } catch (SQLException e) {
 
            System.err.println(e);
        }
        return 1;
    }

}