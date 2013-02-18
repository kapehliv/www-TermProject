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

public class SearchIng extends HttpServlet
{ 
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         
         //epiloges xristi
         String[] veg_checked = null;
         String[] herb_checked = null;
         String[] meat_checked = null;
         String[] fluid_checked = null;
         
         veg_checked = request.getParameterValues("Vegetables");
         herb_checked = request.getParameterValues("Herbs");
         meat_checked = request.getParameterValues("Meat");
         fluid_checked = request.getParameterValues("Fluids");
        
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
                Node node = null;//gia to recipe
                Node node_veg = null;//gia ta veg
                Node node_herb = null;
                Node node_meat = null;
                Node node_fluids = null;
                
                NodeList tagVeg = null; //xml
                NodeList tagHerb = null;
                NodeList tagMeat = null;
                NodeList tagFluid = null;
                
                String photo = null; //suntagi pou tupwnetai
 
                
                List<String> total_match=new ArrayList<String>(); //ola tairiazoun    
                List<String> less_match = new ArrayList<String>(); // exw parapanw ulika apo ti suntagh
                List<String> notex_match =new ArrayList<String>();// exw ligotera       >>
                
                                    boolean flag_total = false;
                    boolean flag_less = false;
                    boolean flag_notex = false;
                
                //out.println("<h3>nodes.getLength(): "+nodes.getLength()+"</h3>");
                for(int i=0; i<nodes.getLength(); i++)//gia kathe recipe
                {
                    //out.println("<h3>i: "+i+"</h3>");
                    node = nodes.item(i); //recipe 1,2,                  
                    Element element = (Element) node; 
                    
                    tagVeg = element.getElementsByTagName("vegetable"); //nodeList
                    tagHerb = element.getElementsByTagName("herb"); //nodeList
                    tagMeat = element.getElementsByTagName("meat"); //nodeList
                    tagFluid = element.getElementsByTagName("fluid"); //nodeList
                    //briskw ta koina vegetables
                    
                    try
                    {
                          
                        if(tagVeg!=null)
                        {
                            for(int j=0; j<tagVeg.getLength(); j++)
                            {
                                node_veg = tagVeg.item(j);
                                Element element2 = (Element) node_veg; 

                                if(veg_checked!=null)
                                {
                                    for(int k=0; k<veg_checked.length; k++) 
                                    {
                                        if(element2.getAttribute("name").equalsIgnoreCase(veg_checked[k])) //tautisi ulikwn
                                        {

                                            match++;      
                                            
                                            break;

                                        }
                                    }                          
                                } 
                            }
                        }
                    }catch(Exception e){ out.println("<h3>E1:"+e+"</h3>");}
                    
                    
                    
                     //briskw ta koina herbs
                    
                   try{
                        if(tagHerb!=null)
                        {
                                for(int j=0; j<tagHerb.getLength(); j++)
                                {
                                    node_herb = tagHerb.item(j);
                                    Element element2 = (Element) node_herb; 


                                  
                                    if(herb_checked!=null)
                                    {
                                            for(int k=0; k<herb_checked.length; k++) 
                                            {
                                                if(element2.getAttribute("name").equalsIgnoreCase(herb_checked[k])) //tautisi ulikwn
                                                {
                                                    match++;
                                                    
                                                    break;

                                                }
                                            }                          
                                    }
                                }
                        }
                   }catch(Exception e){ out.println("<h3>E2:"+e+"</h3>");}
                   
                   
                   
                   
                   
                    
                     
                     //briskw ta koina kreata
                    try{
                        if(tagMeat!=null)
                        {
                                for(int j=0; j<tagMeat.getLength(); j++)
                                {
                                    node_meat = tagMeat.item(j);
                                    Element element2 = (Element) node_meat; 

                                    if(meat_checked!=null)
                                    {
                                            for(int k=0; k<meat_checked.length; k++) 
                                            {
                                                if(element2.getAttribute("name").equalsIgnoreCase(meat_checked[k])) //tautisi ulikwn
                                                {
                                                    match++;
                                                    //out.println("<h3>tha kanw break apo ta meat</h3>");
                                                    break;

                                                }
                                            }                          
                                    }
                                }
                        }
                    }catch(Exception e){ out.println("<h3>E3:"+e+"</h3>");}
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                     try
                    {
                          
                        if(tagFluid!=null)
                        {
                            for(int j=0; j<tagFluid.getLength(); j++)
                            {
                                node_fluids = tagFluid.item(j);
                                Element element2 = (Element) node_fluids; 

                                if(fluid_checked!=null)
                                {
                                    for(int k=0; k<fluid_checked.length; k++) 
                                    {
                                        if(element2.getAttribute("name").equalsIgnoreCase(fluid_checked[k])) //tautisi ulikwn
                                        {

                                            match++;      
                                            
                                            break;

                                        }
                                    }                          
                                } 
                            }
                        }
                    }catch(Exception e){ out.println("<h3>E1:"+e+"</h3>");}
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    String total = getValue("total",element);
                    int total_int = Integer.parseInt(total);

                    
                    if(match == total_int) //akribws h suntagh
                    {
                        photo = getValue("photo", element);
                        
                        total_match.add(photo);
                        flag_total = true;
                        
                    }
                    else if(match == total_int && (veg_checked.length+herb_checked.length+meat_checked.length)>total_int)
                    {
                        photo = getValue("photo", element);
                        less_match.add(photo);
                        flag_less = true;
                        
                    }
                    else if(match!=0 && total_int - match <= 2)
                    {
                        photo = getValue("photo", element);
                        notex_match.add(photo);
                        flag_notex = true;
                        
                    }
                    else
                    {
                        
                        
                    }
                    
                match=0;
                }//////////////for
                out.println("<html>");
                out.println("<title>Recipes</title>");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"SearchIngCss.css\">");
                          
                out.println("<head>");
                out.println("<body>");
                
                        
                
                if(veg_checked==null&&herb_checked==null&&meat_checked==null&&fluid_checked==null){
                        out.println("<div id=\"empty\">");             
                        out.println("<h5>No ingredients selected,");
                        out.println(" it's time for you to go shopping!!</h5>");
                        out.println("</div>"); 
                
                }
                else if(total_match.isEmpty() && less_match.isEmpty() && notex_match.isEmpty() )
                {       
                        out.println("<div id=\"empty\">");             
                        out.println("<h5>We are sorry,");
                        out.println(" no recipes were found!!</h5>");
                        out.println("</div>"); 
                }
                
                else
                {
                   Iterator it = total_match.iterator();
                   Iterator it2 = less_match.iterator();
                   Iterator it3 = notex_match.iterator();
                   
                   
                   
                    
                   
                    
                    
                    int photoNum1=0;
                    if(flag_total){ out.println("<div>");
                    out.println("<ul id=\"ex\">"); out.println("<h3>You have all the ingredients to cook the following recipe/recipes:</h3>");flag_total=false;
                    
                    
                    while(it.hasNext())
                    {
                         out.println("<li>");
                        String value=(String)it.next();
                        String name = value.substring(0,value.length()-4); 
                       
                          out.println("<form action=\"DisplayRecipe?file="+value+"\" method=\"post\">"+"</form>");
                          out.println("<a href=\"javascript:document.forms["+photoNum1+"].submit()\"><img src=\"images/"+value+"\" onError=\"this.onerror=null;this.src=\'default.png\';\"></a>");
                          out.println("<h2>"+name+"</h2>");
                          out.println("</li>");
                          photoNum1++;
                    }
                    
                     out.println("</ul>");
                     out.println("</div>");
                     
                    }
                     
                     if(flag_less){out.println("<div>");
                    out.println("<ul id=\"less\">"); out.println("<h3>Save some ingredients and cook the following:</h3>");flag_less=false;
                    int photoNum2 =0;
                    
                    while(it2.hasNext())
                    {
                        out.println("<li>");
                        String value=(String)it2.next();
                        String name = value.substring(0,value.length()-4);
                       
                         out.println("<form action=\"DisplayRecipe?file="+value+"\" method=\"post\">"+"</form>");
                          out.println("<a href=\"javascript:document.forms["+photoNum1+"].submit()\"><img src=\"images/"+value+"\" onError=\"this.onerror=null;this.src=\'default.png\';\"></a>");
                          out.println("<h2>"+name+"</h2>");
                          out.println("</li>");
                          photoNum1++;
                    }
                   
                     out.println("</ul>");
                     out.println("</div>");
                     
                     }
                     
                     
                    
                    if(flag_notex){ out.println("<div>");
                    out.println("<ul id=\"buy\">"); out.println("<h3>You need one or two more ingredients to cook these:</h3>"); flag_notex=false;
                    int photoNum3 =0;
                    
                    while(it3.hasNext())
                    {
                        out.println("<li>");
                        String value=(String)it3.next();
                        String name = value.substring(0,value.length()-4);
                       
                          out.println("<form action=\"DisplayRecipe?file="+value+"\" method=\"post\">"+"</form>");
                          out.println("<a href=\"javascript:document.forms["+photoNum1+"].submit()\"><img src=\"images/"+value+"\" onError=\"this.onerror=null;this.src=\'default.png\';\"></a>");
                          out.println("<h2>"+name+"</h2>");
                          out.println("</li>");
                          photoNum1++;
                    }
                     
                     out.println("</ul>");
                     out.println("</div>");
                    }
                   
                    
                }
                 out.println("</body>");
                 out.println("</html>");
                 out.println("</head>");
                
        }catch(Exception e){ out.println("<h3>E:"+e+"</h3>");}
        
        
        
    }
    
    
    private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }
}

