package edu.uga.cs4300.persistlayer;
 
 
import java.sql.*;
import java.util.ArrayList;
//import edu.uga.cs4300.persistlayer.*;
//import edu.uga.cs4300.boundary.readServlet;
import java.util.Map;
 
public  class MoviePersistImpl{
     
    String query;
    DbAccessImpl accessImpl = new DbAccessImpl();
    Connection con;
     
 // Constructor
    public MoviePersistImpl(){
         query = "";
         con = accessImpl.connect();   
    }
     
    public void runQuery(String Query){
         
        try {
        	accessImpl.execute(con, Query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
     
    public void closeCon(){
    	accessImpl.disconnect(con);
    }
     
     
}