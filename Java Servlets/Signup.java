import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.String;
import java.sql.PreparedStatement;

public class Signup extends HttpServlet {

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,
      IOException {

      boolean entry=false;
      Connection con = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      res.setContentType("text/html");
      PrintWriter out = res.getWriter();

      String user = req.getParameter("UserName");
      String password = req.getParameter("Password");
      
  
  try {
  Class.forName("com.mysql.jdbc.Driver");
  con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hw3","root","******");
  
	
  PreparedStatement pst = con.prepareStatement("insert into login values (?,?)");
pst.setString(1, user);
pst.setString(2, password);
int rs1 = pst.executeUpdate();
  
  // displaying records
  
  ///////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////
  
  } catch (SQLException e) {
 throw new ServletException("Servlet Could not display records.", e);
  } catch (ClassNotFoundException e) {
  throw new ServletException("JDBC Driver not found.", e);
  } finally {
  try {
  if(rs != null) {
  rs.close();
  rs = null;
  }
  if(stmt != null) {
  stmt.close();
  stmt = null;
  }
  if(con != null) {
  con.close();
  con = null;
  }
  } catch (SQLException e) {}
  /////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////////
  
    res.setContentType("text/html");

    if (!entry) {
      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
      out.println("<BODY>DATABASE UPDATE.<BR>"+user+"<br>"+password);
      out.println("You may want to <A HREF=\"/hrispiro/index.html\">try again</A>");
      out.println("</BODY></HTML>");
    }
      // Couldn't redirect to the target. Redirect to the site's home page.
      res.sendRedirect("homepage.jsp");
    
  }
  }
  
}