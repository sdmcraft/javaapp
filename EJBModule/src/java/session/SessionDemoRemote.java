/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import javax.ejb.Remote;

/**
 *
 * @author satyam
 */
@Remote
public interface SessionDemoRemote {

    void businessMethod();
    
}
