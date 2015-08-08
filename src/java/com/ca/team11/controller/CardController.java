
package com.ca.team11.controller;

import com.ca.team11.business.CardEJB;
import com.ca.team11.model.Card;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/getCardList")
public class CardController extends HttpServlet
{

     @EJB
    private CardEJB cardejb;
     
   // @Inject @ServerSentEventContext("/cardUpdateList")
    //ServerSentEventHandlerContext<CardConnection> connections;
    Card[] table;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String userPath = req.getServletPath();
        System.out.println(userPath);
        table = cardejb.draw12Card();
        JsonArrayBuilder items = Json.createArrayBuilder();

                for (Card inv : table) 
                {
                    System.out.println("this is cardid from servlet : "+inv.getCardId());
                    JsonObjectBuilder item = Json.createObjectBuilder();
                    item.add("cardId", inv.getCardId());
                    item.add("pImg", inv.getImageName());
                    items.add(item); 
                }
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
               // resp.setHeader("CardList", "Success");               
                try (PrintWriter pw = resp.getWriter()) 
                {
                    pw.println(items.build().toString());                                 
                } 
        }

      
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        String first = req.getParameter("cardId1");
        String second = req.getParameter("cardId2");
        String third = req.getParameter("cardId3");
        int loc1 = Integer.parseInt(req.getParameter("loc1"));
        int loc2 = Integer.parseInt(req.getParameter("loc2"));
        int loc3 = Integer.parseInt(req.getParameter("loc3"));

           
        System.out.println("this is card id of set "+first+" "+second+" "+third);
        System.out.println("this is location of card : "+loc1+loc2+loc3);
        
        Card a = cardejb.getCardById(first);
        Card b = cardejb.getCardById(second);
        Card c = cardejb.getCardById(third);
        
        
       int pass1 = 0 ;
        if (((a.getNumber().equals(b.getNumber())) && (b.getNumber().equals(c.getNumber()))) ||
             (!(a.getNumber().equals(b.getNumber())) && !(a.getNumber().equals(c.getNumber())) && !(b.getNumber().equals(c.getNumber())))) {
             pass1 = 1;
            System.out.println("Pass1 No1: "+ pass1);
       }
        if (((a.getShape().equals(b.getShape())) && (b.getShape().equals(c.getShape()))) ||
               (!(a.getShape().equals(b.getShape())) && !(a.getShape().equals(c.getShape())) && !(b.getShape().equals(c.getShape())))) {
                pass1 ++;    
                  System.out.println("Pass1 No2: "+ pass1);
        }
        if (((a.getShade().equals(b.getShade())) && (b.getShade().equals(c.getShade()))) ||
                (!(a.getShade().equals(b.getShade())) && !(a.getShade().equals(c.getShade())) && !(b.getShade().equals(c.getShade())))) {
              pass1 ++; 
                System.out.println("Pass1 No3: "+ pass1);
        }
        if (((a.getColor().equals(b.getColor())) && (b.getColor().equals(c.getColor()))) ||
                (!(a.getColor().equals(b.getColor())) && !(a.getColor().equals(c.getColor())) && !(b.getColor().equals(c.getColor())))) {
            pass1 ++;  
              System.out.println("Pass1 No4: "+ pass1);
        }
  
        System.out.println("Pass 1 : "+pass1);
        
        
        if(pass1 == 4){
            System.out.println("Set Found");
            Card [] replaceCards = cardejb.draw3Card();
            Card [] showcards= new Card[3];
            showcards[0]=a;
            showcards[1]=b;
            showcards[2]=c;
            System.out.println("thisis form servlet show card a : "+ showcards[0].getImageName());
//            JsonArrayBuilder arrBuilder=Json.createArrayBuilder();
//        
//        
//        
//        for (Card show : showcards) 
//                {
//                JsonObjectBuilder objBuilder=Json.createObjectBuilder();
////                    objBuilder.add("replaceId", ca.getCardId());
////                    objBuilder.add("reImg", ca.getImageName());
//                    
//                    objBuilder.add("showcardId", show.getCardId());
//                    objBuilder.add("showcardName", show.getImageName());
//
//                    arrBuilder.add(objBuilder); 
//                     // JsonObject json=objBuilder.build();
//                }
//      
//        resp.setContentType("application/json");
//       resp.setStatus((HttpServletResponse.SC_OK));
//       //resp.getWriter().write(json.toString());
// 
//        }
//        else{
//            System.out.println("Not Found");
//        }
            
            
             JsonArrayBuilder items = Json.createArrayBuilder();

                for (Card showc : showcards) 
                {
                    System.out.println("this is cardid from servlet : "+showc.getCardId());
                    JsonObjectBuilder item = Json.createObjectBuilder();
                    item.add("showcardId", showc.getCardId());
                    //item.add("showImg", showc.getImageName());
                    items.add(item); 
                }
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
               // resp.setHeader("CardList", "Success");               
                try (PrintWriter pw = resp.getWriter()) 
                {
                    pw.println(items.build().toString());                                 
                } 
            
            
        }
        
    }
        
   
}
    

