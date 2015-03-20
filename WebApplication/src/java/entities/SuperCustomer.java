/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;

/**
 *
 * @author satyam
 */
@MappedSuperclass
public class SuperCustomer {

    @PostLoad
    public void postLoadofCustomer() {
        System.out.println("This is postload of super customer!!");
    }
}
