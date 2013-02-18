import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DisplayRecipe extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         
         String file = request.getParameter("file");
         
         String [] nameXml;
         String stop = "\\.";
         nameXml = file.split(stop);
         String in = nameXml[0].concat(".xml"); //this is the xml file be loaded
         File path = new File("/usr/share/apache-tomcat-7.0.32/webapps/termproj/xmlRecipes");
         
         try 
         {
                File description = new File(path,in); //xml loaded
                
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = null;
                try{doc = dBuilder.parse(description);}catch(Exception e){out.println("<h3>E:"+e+"</h3>");}
                doc.getDocumentElement().normalize();
                Node node = null;
                
                NodeList nodes1 = doc.getElementsByTagName("title");
                out.println("<html>");
                out.println("<title>Recipe Display</title>");
                out.println("<body>");
                
                out.println("<div id=\"fb-root\"></div><script>(function(d, s, id) { var js, fjs = d.getElementsByTagName(s)[0];  if (d.getElementById(id)) return;"
                        + " js = d.createElement(s); js.id = id; js.src = \"//connect.facebook.net/en_US/all.js#xfbml=1&appId=504424612922032\";"
                        + " fjs.parentNode.insertBefore(js, fjs);}(document, 'script', 'facebook-jssdk'));</script>");
                
                
                for(int i=0; i<nodes1.getLength(); i++)//gia kathe recipe
                {
                    node = nodes1.item(i); //recipe 1,2,                  
                    Element element = (Element) node; 
                    
                    String title =  element.getTextContent();
                    out.println("<h1>");
                    out.println("Recipe for "+title);
                    out.println("</h1>");
                }
                
                
                nodes1 = doc.getElementsByTagName("photo");
                
                for(int i=0; i<nodes1.getLength(); i++)//gia kathe recipe
                {
                    node = nodes1.item(i); //recipe 1,2,                  
                    Element element = (Element) node; 
                    
                    String ing =  element.getTextContent();
                    out.println("<div id=\"imagediv\">");
                    out.println("<img id=\"myimg\" src=\"images/"+ing+"\" onError=\"this.onerror=null;this.src=\'default.png\';\">");
                    out.println("</div>");
                }
                
                
                
                
//-------------------------ingredients------------------------------------------------------                
                NodeList nodes = doc.getElementsByTagName("ingredient");
                
                out.println("<h5>Ingredients :</h5>");
                out.println("<div id=\"ingdiv\">");
                for(int i=0; i<nodes.getLength(); i++)//gia kathe recipe
                {
                    node = nodes.item(i); //recipe 1,2,                  
                    Element element = (Element) node; 
                    
                    String ing =  element.getAttribute("name");
                    String quan =  element.getAttribute("quantity");
                    out.println("<div id=\"h4div\">");
                    out.println("<h4>"+ing+" "+  quan+"</h4>");
                    out.println("</div>");
                }
                 out.println("</div>");
 //-------------------------ingredients------------------------------------------------------               
                
//-------------------------Steps------------------------------------------------------
                
                XPathFactory xPathFactory = XPathFactory.newInstance();
                XPath xPath = xPathFactory.newXPath();
                XPathExpression expr = xPath.compile("/recipe/execution/step");

                NodeList nl = (NodeList) expr.evaluate(doc , XPathConstants.NODESET);
try{
                out.println("<h5>Execution :</h5>");
                for (int i = 0; i < nl.getLength(); i++) {
                    Node node2 = nl.item(i);
                
                    
                    out.println("<h3>"+node2.getTextContent()+"</h3>");
                }
}catch(Exception e){ out.println("<h3>E3:"+e+"</h3>");}
                
///////**************************************************************///////////////////

                nodes1 = doc.getElementsByTagName("time");
                
                for(int i=0; i<nodes1.getLength(); i++)//gia kathe recipe
                {
                    node = nodes1.item(i); //recipe 1,2,                  
                    Element element = (Element) node; 
                    
                    String time =  element.getTextContent();
                    out.println("<h2>Execution time </h2>");
                    out.println("<div id=\"winediv\">");
                    out.println(time);
                    out.println("</div>");
                }






///////**************************************************************///////////////////
                nodes1 = doc.getElementsByTagName("wine");
                
                for(int i=0; i<nodes1.getLength(); i++)//gia kathe recipe
                {
                    node = nodes1.item(i); //recipe 1,2,                  
                    Element element = (Element) node; 
                    
                    String wine =  element.getTextContent();
                    out.println("<h2>Wine suggestion </h2>");
                    out.println("<div id=\"winediv\">");
                    out.println(wine);
                    out.println("</div>");
                }








//-------------------------Steps------------------------------------------------------
                
                //out.println("<head>");
               // out.println("<body>");
                out.println("<link rel='stylesheet' href='vote.css' type='text/css'> ");  
                out.println("<div>");
                
               
                HttpSession session = request.getSession();
                if(session.getAttribute("Loged.in")!=null && session.getAttribute("Loged.in")!=""){
                
                out.println("<span id=\"rateStatus\">Rate Me...</span><span id=\"ratingSaved\">Rating Saved!</span>"); 
                out.println("<div id=\"rateMe\" title=\"Rate Me...\">");
                out.println("<a onclick=\"rateIt(this)\" id=\"_1\" title=\"1\" onmouseover=\"rating(this)\" onmouseout=\"off(this)\"></a>");
                out.println("<a onclick=\"rateIt(this)\" id=\"_2\" title=\"2\" onmouseover=\"rating(this)\" onmouseout=\"off(this)\"></a>");
                out.println("<a onclick=\"rateIt(this)\" id=\"_3\" title=\"3\" onmouseover=\"rating(this)\" onmouseout=\"off(this)\"></a>");
                out.println("<a onclick=\"rateIt(this)\" id=\"_4\" title=\"4\" onmouseover=\"rating(this)\" onmouseout=\"off(this)\"></a>");
                out.println("<a onclick=\"rateIt(this)\" id=\"_5\" title=\"5\" onmouseover=\"rating(this)\" onmouseout=\"off(this)\"></a>");
                out.println("</div>");
                
                }
                
                out.println("<div class=\"fb-like\" data-href=\"http://83.212.101.24:10000/termproj/index.html\""
                        + " data-send=\"false\" data-layout=\"button_count\" data-width=\"450\""
                        + " data-show-faces=\"true\" data-font=\"lucida grande\"></div>");
                
                out.println("</div>");
                
                out.println("<script type=\"text/javascript\" language=\"javascript\" src=\"ratingsys.js\"></script> ");

                 out.println("</body>");
                 
                 out.println("</html>");
                
         }catch(Exception e){ out.println("<h3>E3:"+e+"</h3>");}
         
         
         
         
         
         
    }
    
    private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }
}
