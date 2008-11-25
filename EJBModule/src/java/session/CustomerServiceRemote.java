/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entities.Cust;
import javax.ejb.Remote;

/**
 *
 * @author satyam
 */
@Remote
public interface CustomerServiceRemote {

    Cust createCustomer(Cust cust);

    public Cust updateNameAndCity( Integer custId, String name,java.lang.String city);

    public void updateCity(Integer custId, String city);
    
}
