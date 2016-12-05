package queryLayer;

import edu.uga.cs4300.persistlayer.*;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import edu.uga.cs4300.persistlayer.DbAccessImpl;
import edu.uga.cs4300.persistlayer.MoviePersistImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class queryLogic {
	
    ProductPersistImpl persist = new ProductPersistImpl();
    DbAccessImpl queryImplementor = new DbAccessImpl();
    Connection con;
	
    public queryLogic(){
        con = queryImplementor.connect();
    }
    
    public void getAllProducts(Map<String, Object> newHashMap)
    {
        String query = " ";
        String input = " ";
        
        query = "SELECT * from store.products";
        
        ArrayList<Product> main = new ArrayList<>();

        ResultSet result = queryImplementor.retrieve(con, query);
        
        try {
			while(result.next()){
				String name = result.getString("name");
				int price = result.getInt("price");
				int id = result.getInt("sku");
				String description = result.getString("description");
				String image = result.getString("image");
				
				Product newProduct = new Product(name, price, id, description, image);
				main.add(newProduct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        newHashMap.put("products", main);
        
    }
    
    public int getAccountValidation(String username, String password){
    	 String query = "SELECT * from store.accounts";
   
         ResultSet result = queryImplementor.retrieve(con, query);
         
         int resultID = 0;
         
         int loopCount = 4;
         
         try {
 			while(result.next() && loopCount == 4){
 				String userName = result.getString("username"); 
 				String passWord = result.getString("password");
 				
 				if(username == userName){
 					if(password == passWord){
 						resultID = 1;
 						loopCount = 3; 
 					}
 					else resultID = 2;
 				}
 				
 			}
 		} catch (SQLException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
		return resultID;
    }
    
    public void getAccount(Map<String, Object> newHashMap, String username)
    {
        String query = " ";
        String input = " ";
        
       
        query = "SELECT * from store.accounts where username = '" + username + "'";
        
        ResultSet result = queryImplementor.retrieve(con, query);
        
        try {
            ResultSetMetaData resultOfMetaData = result.getMetaData();
            int columnCount = resultOfMetaData.getColumnCount();
            ArrayList<String> namesOfColumns = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                namesOfColumns.add(resultOfMetaData.getColumnName(i));
            }
            newHashMap.put("colNames", namesOfColumns);
            ArrayList<Row> rowList = new ArrayList<>();
            while (result.next()) {
                Row newRow = new Row();
                for (int j = 1; j <= columnCount; j++) {
                    input = result.getString(j);
                    if (input == null){
                        input = "null";
                    }
                    newRow.add(input);
                }
                rowList.add(newRow);
            }
            newHashMap.put("products", rowList);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    public class Row {
        private ArrayList<String> data;         
        public Row(){
            data = new ArrayList<String>();
        }
        public void add(String s){ 
            data.add(s); 
        }
        public ArrayList<String> getData(){ 
            return data; 
        }
    }
    
	public void deployProducts(HttpServletResponse response, Map<String, Object> root, Configuration cfg) {
		
	    response.setContentType("text/html");
	    try {
	        PrintWriter out = response.getWriter();
	        Template temp = null;

	        temp = cfg.getTemplate("products.ftl");

	        temp.process(root, out);
	
	    }catch (TemplateException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	   
	}
  }
	
    public void addAccount(String username,  String password, String firstName, String lastName) throws SQLException
    {       
        String query = "Insert into accounts(username, password, firstname,lastname) values(("
                + username + "," + password + "," + firstName + "," + lastName +"')'";
        persist.runQuery(query);
    }

}
