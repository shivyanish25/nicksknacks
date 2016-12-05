package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductPersistImpl {

	String query;
    DbAccessImpl accessImpl = new DbAccessImpl();
    Connection con;
     
 // Constructor
    public ProductPersistImpl(){
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
