import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Search extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         
         String[] wine_checked = null;
         wine_checked = request.getParameterValues("Wines");
        
        int match = 0;
        
         
        File path = new File("/usr/share/apache-tomcat-7.0.32/webapps/termproj/");
      
        
        
        try{
                File f = new File(path, "cookbook.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = null;
                try{doc = dBuilder.parse(f);}catch(Exception e){out.println("<h3>E:"+e+"</h3>");}
                doc.getDocumentElement().normalize();
               
                NodeList nodes = doc.getElementsByTagName("recipe");
                Node node = null;
                Node node_wine = null;//gia ta wines
                NodeList tagWine = null; //xml
                String photo = null; //suntagi pou tupwnetai
                
                
                List<String> total_match = new ArrayList<String>(); //ola tairiazoun    
                boolean flag_total = false;
               
                
                
                for(int i=0; i<nodes.getLength(); i++)
                {
                    node = nodes.item(i); //recipe 1,2, ..                 
                    Element element = (Element) node; 
                    tagWine = element.getElementsByTagName("wine"); //nodeList
                    
                    
                            try
                                {

                                    if(tagWine!=null)
                                    {

                                        for(int j=0; j<tagWine.getLength(); j++)
                                        {
                                            node_wine = tagWine.item(j);
                                            Element element2 = (Element) node_wine; 


                                            if(wine_checked!=null)
                                            {
                                                for(int k=0; k<wine_checked.length; k++) 
                                                {
                                                    


                                                    if(element2.getTextContent().equalsIgnoreCase(wine_checked[k])) //tautisi ulikwn
                                                    {
                                                        
                                                        match++; 
                                                        break;

                                                    }
                                                }                          
                                            } 
                                        }
                                    }
                                }catch(Exception e){ out.println("<h3>E1:"+e+"</h3>");} 
                   
                   
                   
                                
                                if (match!=0)
                                {   
                                    photo = getValue("photo", element);
                                    total_match.add(photo);
                                    flag_total = true;
                                }
                               
                   
                                match=0;
                        }
                      
                               out.println("<html>");
                               out.println("<title>Recipes</title>");
                               out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"Search.css\">");     
                               out.println("<head>");
                               out.println("<body>");
                            
                                if(total_match.isEmpty())
                                {       
                                    out.println("<div id=\"empty\">");             
                                    out.println("<h5>The wine you have chosen does not");
                                    out.println(" match to a recipe..</h5>");
                                    out.println("</div>"); 
                                }
                                
                                else
                                {
                                            Iterator it = total_match.iterator();
                                            out.println("<div>");
                                            out.println("<ul id=\"ex\">");
                                            if(flag_total){ out.println("<h3>The recipes that match best the chosen wine/s are:</h3>");flag_total=false;}
                                            int photoNum1=0;
                                            
                                            while(it.hasNext())
                                            {
                                                String value=(String)it.next();
                                            
                                                String name = value.substring(0,value.length()-4); 
                                                
                                                 
                                                out.println("<li>");
                                                out.println("<form action=\"DisplayRecipe?file="+value+"\" method=\"post\">"+"</form>");
                                                out.println("<a href=\"javascript:document.forms["+photoNum1+"].submit()\"><img src=\"images/"+value+"\" onError=\"this.onerror=null;this.src=\'default.png\';\"></a>");
                                                out.println("<h2>"+name+"</h2>");
                                                out.println("</li>");
                                                
                                                photoNum1++;
                                            }
                                           
                                            out.println("</ul>");
                                            out.println("</div>");
                                            
                                            
                                           
                                }
                                            out.println("</body>");
                                            out.println("</html>");
                                            out.println("</head>");
                
                
                
                    }catch(Exception e){ out.println("<h3>E1:"+e+"</h3>");}
        
    }
    
    
    private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }
}
