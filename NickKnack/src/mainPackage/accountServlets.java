package mainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uga.cs4300.persistlayer.*;
import freemarker.template.Configuration;
import queryLayer.queryLogic;

/**
 * Servlet implementation class accountServlets
 */
@WebServlet("/accountServlets")
public class accountServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	queryLogic queryLayerMain = new queryLogic();
	
    Configuration cfg = null;
    
    private String templateDir = "/WEB-INF/templates";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public accountServlets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		DbAccessImpl accessImpl = new DbAccessImpl();
		String query = "";
		Connection con;	
        con = accessImpl.connect();  
        
        Map<String, Object> root = new HashMap<>();
        
        queryLayerMain.getAllProducts(root);
        
        final String DRIVE_NAME = "com.mysql.jdbc.Driver"; 
		final String ConnectionURL = "jdbc:mysql://localhost:3306/store"; 
		final String usernameDB = "root"; 
		final String passwordDB = "UGAgodawgs123"; 
		response.setContentType("text/html");

		int numberOfResults;
		
		// Gets user input for specific number of requests
		// if no response is made, then the html page prints the 
		// whole table
		// Start of the printwriter and dynamic html stuff
		PrintWriter writeOut = response.getWriter(); 
		// Checks to see if the input is valid
		
		String userName= request.getParameter("username");
        String passWord = request.getParameter("password");

		 

			 try{ 
				 
				
				Class.forName("com.mysql.jdbc.Driver"); 
				// Starts the connection to the database
				Connection mainConnection = DriverManager.getConnection(ConnectionURL, usernameDB, passwordDB); 
				Statement statementMain = mainConnection.createStatement(); 
				String sqlStatements;
				ResultSet results;
				// Runs SQL statements
				sqlStatements = "SELECT * FROM store.products;"; 
				results = statementMain.executeQuery(sqlStatements); 
				
				// Generates tables based on the user input for database

						while(results.next()){ 
								String username = results.getString("username"); 
								String password = results.getString("password");
								
							if(userName == username && passWord == password){
								
							}
								
								

						}			 
							
				// Closes all the necessary connections
				results.close(); 
				statementMain.close(); 
				mainConnection.close(); 
				}catch(SQLException | ClassNotFoundException se){ 
	 
				se.printStackTrace(); 
				}
		}
	}

