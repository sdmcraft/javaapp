/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package misc;

/**
 *
 * @author satyam
 */
public class ThreadLocalDemo {
    
    public static void main(String[] args) {      
        MultiThreadHarness h = new MultiThreadHarness(new Task());
        h.doWork(5);
    }

}
