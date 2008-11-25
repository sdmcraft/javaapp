/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entities.Cust;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author satyam
 */
@Stateless
public class CustomerServiceBean implements CustomerServiceRemote {
    @PersistenceContext(unitName="EJBModulePU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public Cust createCustomer(Cust cust) {
        persist(cust);
        return cust;
    }

    public Cust updateNameAndCity(Integer custId, String name,java.lang.String city) {
        Cust cust = em.find(Cust.class, custId);
        cust.setName(name);
        persist(cust);
        System.out.println("Name:"+cust.getName());
        System.out.println("City:"+cust.getCity());
        System.out.println("----------------------");
        updateCity(custId,city);
        System.out.println("Name:"+cust.getName());
        System.out.println("City:"+cust.getCity());
        System.out.println("----------------------");        
        return cust;
    }

    public void updateCity(Integer custId, String city) {
        Cust cust = em.find(Cust.class, custId);
        cust.setCity(city);
        persist(cust);
    }
    
    
    
    // Add business logic below. (Right-click in editor and choose
    // "EJB Methods > Add Business Method" or "Web Service > Add Operation")
 
}
