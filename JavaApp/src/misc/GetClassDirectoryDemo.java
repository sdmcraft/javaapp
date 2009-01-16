/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package misc;

/**
 *
 * @author satyam
 */
public class GetClassDirectoryDemo {
    public static void main(String[] args) {
        System.out.println(GetClassDirectoryDemo.class.getResource("/"));
        System.out.println(System.getProperty("user.dir"));
    }

}
