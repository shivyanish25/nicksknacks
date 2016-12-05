package mainPackage;


import edu.uga.cs4300.persistlayer.*;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import queryLayer.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Servlet implementation class productPageServlet
 */
@WebServlet("/productPageServlet")
public class productPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	queryLogic queryLayerMain = new queryLogic();
	
    Configuration cfg = null;
    
    private String templateDir = "/WEB-INF/templates";
	
    public productPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
        // Create your Configuration instance, and specify if up to what FreeMarker
        // version (here 2.3.25) do you want to apply the fixes that are not 100%
        // backward-compatible. See the Configuration JavaDoc for details.
        cfg = new Configuration(Configuration.VERSION_2_3_25);
        
        // Specify the source where the template files come from.
        cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
        
        // Sets how errors will appear.
        // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        
        // Don't log exceptions inside FreeMarker that it will thrown at you anyway:
        cfg.setLogTemplateExceptions(false);
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
        
        
        
        
	}
	

}
