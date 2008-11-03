/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.BamaAccount;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author satyam
 */
public class DBUtils {

    public static List<BamaAccount> getAccounts() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BAMAPU");
        EntityManager em = emf.createEntityManager();
        return (List<BamaAccount>)em.createQuery("SELECT account FROM BamaAccount account").getResultList();
    }
}
