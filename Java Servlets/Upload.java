
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Upload extends HttpServlet {
    String[] vegetables = null;
         String[] herb = null;
         String[] meat = null;
         String[] fluids = null;
         
         String[] vegquantity = null;
         String[] herbquantity = null;
         String[] meatquantity = null;
         String[] fluidquantity = null;
         String[] steps = null;
         String name=null;
         String wine=null;
         String time=null;
         
         
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
         
         vegetables = request.getParameterValues("Vegetable");
         herb = request.getParameterValues("Herb");
         meat = request.getParameterValues("Meat");
         fluids=request.getParameterValues("Fluids");
         name=request.getParameter("title");
         wine=request.getParameter("Wine");
         vegquantity=request.getParameterValues("vegquantity");
         herbquantity=request.getParameterValues("herbquantity");
         meatquantity=request.getParameterValues("meatquantity");
         fluidquantity=request.getParameterValues("fluidquantity");
         steps = request.getParameterValues("step");
         time=request.getParameter("time");
         int total=0;
         try{
  // Create file 
   LastLineDel();
  FileWriter fstream = new FileWriter("/usr/share/apache-tomcat-7.0.32/webapps/termproj/cookbook.xml",true);
  BufferedWriter out = new BufferedWriter(fstream);
  out.write("<recipe name=\""+name+"\">\n");
  out.write("<title>"+name+"</title>\n");
  if(vegetables!=null){
  for(int i=0;i<vegetables.length;i++){
  out.write("<vegetable name=\""+vegetables[i]+"\">"+vegetables[i]+"</vegetable>\n");
  
  }
  total+=vegetables.length;
  }
  if(herb!=null){
  for(int i=0;i<herb.length;i++){
  out.write("<herb name=\""+herb[i]+"\">"+herb[i]+"</herb>\n");
  
  }
  total+=herb.length;
  }
  if(meat!=null){
  for(int i=0;i<meat.length;i++){
  out.write("<meat name=\""+meat[i]+"\">"+meat[i]+"</meat>\n");
  }
  total+=meat.length;
  }
  
  if(fluids!=null){
  for(int i=0;i<fluids.length;i++){
  out.write("<fluid name=\""+fluids[i]+"\">"+fluids[i]+"</fluid>\n");
  
  }
  total+=fluids.length;
  }
  out.write("<wine>"+wine+"</wine>\n");
  
  out.write("<total>"+total+"</total>\n");
  out.write("<photo>"+name+".png</photo>\n");
  out.write("</recipe>\n");
  out.write("</cookbook>");
  
  out.close();
  createSeperateXml();
    
  }catch (Exception e){//Catch exception if any
  System.err.println("Error: " + e.getMessage());
  }
/*********************************************************/
         
         response.setContentType("text/html");
      java.io.PrintWriter out = response.getWriter( );
         
      out.println("<html>");
         out.println("<head>");
         out.println("<link rel='stylesheet' href='uploadCss.css' type='text/css'> ");  
         out.println("<title>Picture Upload</title>");  
         out.println("</head>");
         out.println("<body>");
         out.println("<h1>Upload your photo..</h1>");
        out.println("<form name=\"form2\" action=\"PicUpload\" method=\"post\" enctype=\"multipart/form-data\" > "
                + "<input type=\"file\" name=\"file\" size=\"25\" accept=\"image/*\" required><br />");
         out.println("<input type=\"submit\" value=\"Upload\"></form>"); 
         out.println("</body>");
         out.println("</html>");
         HttpSession session = request.getSession();
         session.setAttribute("UploadPicName", name);
         
/********************************************************/
         
         
    }
    
    public void LastLineDel(){
    
        
        try{
        File inputFile = new File("/usr/share/apache-tomcat-7.0.32/webapps/termproj/cookbook.xml");
        File tempFile = new File("/usr/share/apache-tomcat-7.0.32/webapps/termproj/cookbooktemp.xml");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = "</cookbook>";
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine);
        }
        writer.close();
        boolean successful = tempFile.renameTo(inputFile);


          }catch (Exception e){//Catch exception if any
          System.err.println("Error: " + e.getMessage());
          }


        }
    public void createSeperateXml(){
    int total=0;
    try{
  // Create file 
   
  FileWriter fstream = new FileWriter("/usr/share/apache-tomcat-7.0.32/webapps/termproj/xmlRecipes/"+name+".xml");
  BufferedWriter out = new BufferedWriter(fstream);
  out.write("<recipe name=\""+name+"\">\n");
  out.write("<title>"+name+"</title>\n");
  
  if(vegetables!=null){
  for(int i=0;i<vegetables.length;i++){
  out.write("<ingredient name=\""+vegetables[i]+"\" quantity=\""+vegquantity[i]+"g\"/>\n");
  
  }
  total+=vegetables.length;
  }
  if(herb!=null){
  for(int i=0;i<herb.length;i++){
    out.write("<ingredient name=\""+herb[i]+"\" quantity=\""+herbquantity[i]+"g\"/>\n");

  }
  total+=herb.length;
  }
  if(meat!=null){
  for(int i=0;i<meat.length;i++){
  out.write("<ingredient name=\""+meat[i]+"\" quantity=\""+meatquantity[i]+"g\"/>\n");
  
  }
  total+=meat.length;
  }
  
  if(fluids!=null){
  for(int i=0;i<fluids.length;i++){
  out.write("<ingredient name=\""+fluids[i]+"\" quantity=\""+fluidquantity[i]+"g\"/>\n");
  
  }
  total+=fluids.length;
  }
  out.write("<execution>\n");
  for(int i=0;i<steps.length;i++){
    out.write("<step>"+(i+1)+"."+steps[i]+" </step>\n");
  }
  
  
  out.write("</execution>\n");
  
  out.write("<wine>"+wine+"</wine>\n");
  
  out.write("<time>"+time+" min.</time>\n");
  out.write("<total>"+total+"</total>\n");
  out.write("<rate>3</rate>\n");
  out.write("<photo>"+name+".png</photo>\n");
  out.write("</recipe>\n");
  

  //Close the output stream
  out.close();
    
  }catch (Exception e){//Catch exception if any
  System.err.println("Error: " + e.getMessage());
  }
    
    
    }

}
