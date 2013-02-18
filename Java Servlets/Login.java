import java.io.IOException;
import java.io.PrintWriter;

import java.util.logging.Level;
import java.util.logging.Logger;
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

public class Login extends HttpServlet {
    
    private Connection connectdb(){
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hw3","root","******");
        } catch (Exception e) {}
         return (con);    
    }
/////////////////////////////////////////////////////////////////////////////////////

    private boolean log_in(Connection con, String username, String password) throws ServletException{
        Statement stmt = null;
        ResultSet rs = null;
        boolean entry=false;
        try{
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM login");
        
        while(rs.next()){  
            if(rs.getObject(1).toString().equals(username)&&rs.getObject(2).toString().equals(password)){
                entry=true;
                break;
            }
        }
        }catch (SQLException e){
            throw new ServletException("Servlet Could not display records.", e);
        }finally {
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
          } catch (Exception e) {}

          } 
        return (entry);
    }  
    
    private boolean record_check(Connection con, String username, String password) throws ServletException{
        Statement stmt = null;
        ResultSet rs = null;
        boolean entry=false;
        try{
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM login");
        
        while(rs.next()){  
            if(rs.getObject(1).toString().equals(username)){
                entry=true;
                break;
            }
        }
        }catch (SQLException e){
            throw new ServletException("Servlet Could not display records.", e);
        }finally {
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
          } catch (Exception e) {}

          } 
        return (entry);
    }

/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
    private String register(Connection con, String username, String password) throws ServletException{
        
        String return_exp=null;
        PreparedStatement pst=null;
        boolean record = record_check(connectdb(),username,password);
        try{
            if(username.length()>=3 && username.length()<20){
                if(password.length()>=6 && password.length()<20){
                    if(!record){
                    pst= con.prepareStatement("insert into login values (?,?)");
                    pst.setString(1, username);
                    pst.setString(2, password);
                    int rs1 = pst.executeUpdate();
                    return_exp="Register Completed";
                    }
                    else{return_exp="Username allready exists in our database.";
                    }
                }else if(password.length()<6){return_exp="Password is too weak. Password must be between 6 and 19 characters long";}
                else{return_exp="Password is too long";}
            }else{return_exp="Invalid username length must be between 3 and 19 characters long";}
        }catch (SQLException e){
            throw new ServletException("Servlet Could not display records.", e);
        }finally {
          try {
            if(pst != null) {
                pst.close();
                pst = null;
            }
            if(con != null) {
                con.close();
                con = null;
            }
            } catch (Exception e) {}
           }
        return (return_exp);
    }
        
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {

      boolean entry=false;
      Connection con = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      
      res.setContentType("text/html");
      PrintWriter out = res.getWriter();
       
      String user = req.getParameter("UserName");
      String password = req.getParameter("Password");
      String action = req.getParameter("Login");
      String registerstr=null;
      con=connectdb();
      
      if(action.compareTo("Log in")==0){
      entry=log_in(connectdb(),user,password);
      }
      else
      {
          registerstr=register(connectdb(),user,password);
      }
  
  
  /////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////////
  
    res.setContentType("text/html");
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"loginCss.css\">");
                
    if (!entry&&action.compareTo("Log in")==0) {
      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
      out.println("<BODY>Your login and password are invalid.<BR>");
      out.println("You may want to <A HREF=\"index.html\">try again</A>");
      out.println("</BODY></HTML>");
    }
    else if(action.compareTo("Register")==0&&!(registerstr.compareTo("Register Completed")==0)){
      out.println("<HTML><HEAD><TITLE>Register Error</TITLE></HEAD>");
      out.println("<BODY>"+registerstr+"<BR>");
      out.println("You may want to <A HREF=\"register.html\">try again</A>");
      out.println("</BODY></HTML>");
  
    }else if(action.compareTo("Register")==0&&(registerstr.compareTo("Register Completed")==0)){
      out.println("<HTML><HEAD><TITLE>Register Completed</TITLE></HEAD>");
      out.println("<BODY>"+registerstr+"<BR>");
      out.println("You may want to login now: <A HREF=\"loginpage.html\">login</A>");
      out.println("</BODY></HTML>");
    
    }
    else {
      // Valid login. Make a note in the session object.
      HttpSession session = req.getSession();
      session.setAttribute("Loged.in", user);
      // Try redirecting the client to the page he first tried to access
      try {
        String target = "categoriesNoUp.jsp";//(String) session.getAttribute("login.target");
        if (target != null) {
          res.sendRedirect(target);
          return;
        }
      } catch (Exception ignored) {
      }
      // Couldn't redirect to the target. Redirect to the site's home page.
      res.sendRedirect("/");
    }
  }
  }
  
//}
