/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Binding;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

/**
 *
 * @author satyam
 */
@Stateless
public class SessionDemoBean implements SessionDemoRemote {

    @EJB
    private SessionDemo2Remote sessionDemo2Bean2;
    @EJB
    private SessionDemo3Remote sessionDemo2Bean3;

    public void businessMethod() {
        sessionDemo2Bean2.businessMethod();
        sessionDemo2Bean3.businessMethod();
        try {
            InitialContext ictx = new InitialContext();
            NamingEnumeration list = ictx.listBindings("java:comp/env");
            while (list.hasMore()) {
                Binding binding = (Binding) list.next();
                System.out.println("Hiiiiiiiii"+binding.toString());
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }    // Add business logic below. (Right-click in editor and choose
    // "EJB Methods > Add Business Method" or "Web Service > Add Operation")
}
