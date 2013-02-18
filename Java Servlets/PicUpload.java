import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class PicUpload extends HttpServlet {
   
   private boolean isMultipart;
   private String filePath;
   private int maxFileSize = 6000 * 1024;
   private int maxMemSize = 4 * 1024;
   private File file ;
   private String Nameforxml;

   public void init( ){
      // Get the file location where it would be stored.
      filePath = "/usr/share/apache-tomcat-7.0.32/webapps/termproj/images/";
             //getServletContext().getInitParameter("file-upload"); 
   }
   public void doPost(HttpServletRequest request, 
               HttpServletResponse response)
              throws ServletException, java.io.IOException {
       
     
      // Check that we have a file upload request
      isMultipart = ServletFileUpload.isMultipartContent(request);
      
      if( !isMultipart ){
        
         return;
      }
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("/usr/share/apache-tomcat-7.0.32/webapps/termproj/temp/"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try{ 
      // Parse the request to get file items.
      List fileItems = upload.parseRequest(request);
	
      // Process the uploaded file items
      Iterator i = fileItems.iterator();

      
      while ( i.hasNext () ) 
      {
         FileItem fi = (FileItem)i.next();
         if ( !fi.isFormField () )	
         {
            // Get the uploaded file parameters
            String fieldName = fi.getFieldName();
            String fileName = fi.getName();
            String contentType = fi.getContentType();
            boolean isInMemory = fi.isInMemory();
            long sizeInBytes = fi.getSize();
            // Write the file
            if( fileName.lastIndexOf("\\") >= 0 ){
               file = new File( filePath + 
               fileName.substring( fileName.lastIndexOf("\\"))) ;
            }else{
               file = new File( filePath + 
               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
            }
            fi.write( file ) ;
           
             HttpSession session = request.getSession();
             session.setAttribute("UploadedPic", fileName);
         }
      }
      
      
   }catch(Exception ex) {
       System.out.println(ex);
   }
      
      HttpSession session = request.getSession();
      session.getAttribute("UploadPicName");
      String rename=(String)session.getAttribute("UploadPicName");
      
      File file1=new File("/usr/share/apache-tomcat-7.0.32/webapps/termproj/images/"+(String)session.getAttribute("UploadedPic"));
      File file2=new File("/usr/share/apache-tomcat-7.0.32/webapps/termproj/images/"+rename+".png");
      file1.renameTo(file2);
      session.removeAttribute("UploadPicName");
      
      
      response.sendRedirect("categoriesNoUp.jsp");
   }
   public void doGet(HttpServletRequest request, 
                       HttpServletResponse response)
        throws ServletException, java.io.IOException {
        
        throw new ServletException("GET method used with " +
                getClass( ).getName( )+": POST method required.");
   } 
}
