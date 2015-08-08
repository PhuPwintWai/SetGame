
package com.ca.team11.business;




import com.ca.team11.model.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;

@Stateless
public class CardEJB {
    
   @PersistenceContext private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
      List<Card> lst;
    @GET
    public List<Card> RetrieveCard(){
        List<Card> listCard = em.createNamedQuery("Card.findAll", Card.class).getResultList();
        
        
            List<Card> cards = new ArrayList<>();
            cards = listCard;
       
            cards.remove(cards.size()-2);
       // Collections.shuffle(cards);
        return cards;
       
    }
    
    public Card[] draw12Card()
    {
        Card table []= new Card[12];
        lst = RetrieveCard();
      for (int i=0; i<12; i++)
       {
           table[i]=lst.get(i);
           lst.remove(i);
           System.out.println("this is from servlet : "+table[i].getImageName());
       }
        return table;   
    }
    public Card[] draw3Card()
    {
        List<Card> threeCard = lst;
        Card replaceCard []=new Card[3];
        for (int i = 0; i<3 ;i++)
        {
            replaceCard[i]=threeCard.get(i);
            lst.remove(i);
        }
        return replaceCard;
        
    }
    
    
    
    public Card getCardById(String cardId) {
       
        Card cc = em.find(Card.class, cardId);
        TypedQuery<Card> query;
        query = em.createQuery("select c from Card c", Card.class);
        for (Card c: query.getResultList())
        if (c.getCardId().equals(cardId))
        return (c);
        return (null);
        
    }

    
    
    
    public List<Card> RetrieveCardById(Card c){
        Query createNamedQuery = getEntityManager().createNamedQuery("Card.findByCardId");

        createNamedQuery.setParameter("cardId", c.getCardId());
        
        if (createNamedQuery.getResultList().size() > 0) {
            List<Card> lst = createNamedQuery.getResultList();
  
            return lst;
        }
        else {
            return null;
        }
    }
    
  
}


//When I set the bean as @Stateless I always get the same object ID and counter always increments.
//When I set the bean as @Stateful I get a new instance every time I refresh the page.
//When I set it to @Singleton I get the same results as when I set it to @Stateless: same object ID, counter incrementing.